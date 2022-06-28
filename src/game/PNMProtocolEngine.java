package game;

import network.GameSessionEstablishedListener;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static game.PNMTCPProtocolEngine.METHOD_SET;
import static game.PNMTCPProtocolEngine.METHOD_SURRENDER;

public abstract class PNMProtocolEngine implements MemoryAPI {
        public static final int PLAYER_1 = 0;
        public static final int PLAYER_2 = 1;

        private BoardParser coordParser = new BoardParserImplementation();

        void serializeFlip(PlayerLogic player, Coordinates first, Coordinates second, OutputStream os) throws GameException {
            DataOutputStream dos = new DataOutputStream(os);

            //write method id
            try{
                dos.writeInt(METHOD_SET);
                //serialize player
                dos.writeInt(this.getIntValue4Piece(player));
                //serialize card1
                int firstvert = coordParser.parseLetterCoord(first);
                int firsthori = coordParser.parseNumberCoord(first);
                dos.writeInt(firstvert);
                dos.writeInt(firsthori);
                //serialize card2
                int secondvert = coordParser.parseLetterCoord(second);
                int secondhori = coordParser.parseLetterCoord(second);
                dos.writeInt(secondvert);
                dos.writeInt(secondhori);

            } catch (IOException e) {
                throw new GameException(e.getLocalizedMessage());
            }

        }

        FlipCommand deserializeFlip(InputStream is) throws GameException, IOException{
            DataInputStream dis = new DataInputStream(is);
            //read serialized player
            int playerInt = dis.readInt();
            //convert to player
            PlayerLogic player = this.getPieceFromIntValue(playerInt);

            //read serialized card1
            int firstvert = dis.readInt();
            int firsthori = dis.readInt();
            Coordinates first = coordParser.reconstructCard(firstvert,firsthori);
            //read serialized card2
            int secondtvert = dis.readInt();
            int secondhori = dis.readInt();
            Coordinates second = coordParser.reconstructCard(secondtvert,secondhori);

            return new FlipCommand(player,first,second);
        }

        void serializeSurrender(PlayerLogic player, OutputStream os) throws GameException {
            DataOutputStream dos = new DataOutputStream(os);

            try{
                //write method id
                dos.writeInt(METHOD_SURRENDER);
                //serialize player
                dos.writeInt(this.getIntValue4Piece(player));
            } catch (IOException e) {
                throw new GameException(e.getLocalizedMessage());
            }

        }

    PlayerLogic deserializeSurrender(InputStream is) throws GameException, IOException{
        DataInputStream dis = new DataInputStream(is);
        //read serialized player
        int playerInt = dis.readInt();
        //convert to player
        PlayerLogic player = this.getPieceFromIntValue(playerInt);

        return player;
    }

        private PlayerLogic getPieceFromIntValue(int playerInt) throws GameException {
            switch (playerInt) {
                case PLAYER_1: return PlayerLogic.P1;
                case PLAYER_2: return PlayerLogic.P2;
                default: throw new GameException("unknown symbol: " + playerInt);
            }
        }

        private int getIntValue4Piece(PlayerLogic piece) throws GameException {
            switch (piece) {
                case P1: return PLAYER_1;
                case P2: return PLAYER_2;
                default: throw new GameException("unknown symbol: " + piece);
            }
        }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                         oracle creation listener                                      //
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////

    private List<GameSessionEstablishedListener> sessionCreatedListenerList = new ArrayList<>();

    public void subscribeGameSessionEstablishedListener(GameSessionEstablishedListener ocListener) {
        this.sessionCreatedListenerList.add(ocListener);
    }

    public void unsubscribeGameSessionEstablishedListener(GameSessionEstablishedListener ocListener) {
        this.sessionCreatedListenerList.remove(ocListener);
    }

    void notifyGamesSessionEstablished(boolean oracle, String partnerName) {
        // call listener
        if (this.sessionCreatedListenerList != null && !this.sessionCreatedListenerList.isEmpty()) {
            for (GameSessionEstablishedListener oclistener : this.sessionCreatedListenerList) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1); // block a moment to let read thread start - just in case
                        } catch (InterruptedException e) {
                            // will not happen
                        }
                        oclistener.gameSessionEstablished(oracle, partnerName);
                    }
                }).start();
            }
        }
    }
}

