package game;

import network.GameSessionEstablishedListener;
import network.ProtocolEngine;

import java.io.*;
import java.util.Random;

public class PNMTCPProtocolEngine extends PNMProtocolEngine implements Runnable, ProtocolEngine{
    public static final int METHOD_SET = 1;
    public static final int METHOD_SURRENDER = 2;

    private static final String DEFAULT_NAME = "anonymousProtocolEngine";
    private String name;
    private OutputStream os;
    private InputStream is;
    private final Memory gameEngine;

    private Thread protocolThread = null;

    private boolean oracle;
    private String partnerName;

    /**
     * constructor has an additional name - helps debugging.
     * @param gameEngine
     * @param name
     */
    public PNMTCPProtocolEngine(Memory gameEngine, String name){
        this.gameEngine = gameEngine;
        this.name = name;
    }

    public PNMTCPProtocolEngine(Memory gameEngine) {
        this(gameEngine, DEFAULT_NAME);
    }

    @Override
    public boolean flip(PlayerLogic playerLogic, Coordinates firstCard, Coordinates secondCard) throws NotYourTurnException, CardsGoneException, DoublePickException, StatusException, GameException {
        this.log("send set message to other side");
        this.serializeFlip(playerLogic, firstCard, secondCard, this.os);
        return false;
    }

    private void deserializeFlip() throws GameException{
        this.log("deserialize received flip message");

        try{
            FlipCommand flipCommand = this.deserializeFlip(this.is);
            this.gameEngine.flip(flipCommand.getPlayer(),flipCommand.getFirst(),flipCommand.getSecond());
        }
        catch(StatusException | IOException | CardsGoneException | DoublePickException | NotYourTurnException e){
            throw new GameException("could not deserialize command", e);
        }
    }

    private void deserializeSurrender() throws GameException{
        this.log("deserialize received surrender message");
        try{
            this.gameEngine.surrender(this.deserializeSurrender(this.is));
        } catch (StatusException | IOException e) {
            throw new GameException("could not deserialize command", e);
        }
    }

    boolean read() throws GameException {
        this.log("Protocol Engine: read from input stream");
        DataInputStream dis = new DataInputStream(this.is);

        // read method id
        try {
            int commandID = dis.readInt();
            switch (commandID) {
//                case METHOD_PICK: this.deserializePick(); return true;
                case METHOD_SET: this.deserializeFlip(); return true;
                case METHOD_SURRENDER: this.deserializeSurrender(); return true;
//                case RESULT_PICK: this.deserializeResultPick(); return true;
                default: this.log("unknown method, throw exception id == " + commandID); return false;
            }
        } catch (IOException e) {
            this.log("IOException caught - most probably connection close - stop thread / stop engine");
            try {
                this.close();
            } catch (IOException ioException) {
                // ignore
            }
            return false;
        }
    }

    @Override
    public boolean surrender(PlayerLogic playerLogic) throws StatusException, GameException {
        this.log("send set message to other side");
        this.serializeSurrender(playerLogic, this.os);
        return false;
    }

    @Override
    public void run() {
        this.log("Protocol Engine started - flip a coin");
        long seed = this.hashCode() * System.currentTimeMillis();
        Random random = new Random(seed);

        int localInt = 0, remoteInt = 0;
        try {
            DataOutputStream dos = new DataOutputStream(this.os);
            DataInputStream dis = new DataInputStream(this.is);
            do {
                localInt = random.nextInt();
                this.log("flip and take number " + localInt);
                dos.writeInt(localInt);
                remoteInt = dis.readInt();
            } while(localInt == remoteInt);

            this.oracle = localInt < remoteInt;
            this.log("Flipped a coin and got an oracle == " + this.oracle);
            //this.oracleSet = true;

            // finally - exchange names
            dos.writeUTF(this.name);
            this.partnerName = dis.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.notifyGamesSessionEstablished(PNMTCPProtocolEngine.this.oracle,
                PNMTCPProtocolEngine.this.partnerName);

        try {
            boolean again = true;
            while(again) {
                again = this.read();
            }
        } catch (GameException e) {
            this.logError("exception called in protocol engine thread - fatal and stop");
            e.printStackTrace();
            // leave while - end thread
        }
    }

    @Override
    public void handleConnection(InputStream is, OutputStream os) throws IOException {
        this.is = is;
        this.os = os;

        this.protocolThread = new Thread(this);
        this.protocolThread.start();
    }

    @Override
    public void close() throws IOException {
        if(this.os != null) { this.os.close();}
        if(this.is != null) { this.is.close();}
    }

    private String produceLogString(String message) {
        StringBuilder sb = new StringBuilder();
        if(this.name != null) {
            sb.append(this.name);
            sb.append(": ");
        }

        sb.append(message);

        return sb.toString();
    }

    private void log(String message) {
        System.out.println(this.produceLogString(message));
    }

    private void logError(String message) {
        System.err.println(this.produceLogString(message));
    }
}
