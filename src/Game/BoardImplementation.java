package Game;

public class BoardImplementation implements Board{

    private final Card[][] board;

    //Quadratisches 6x6-Feld
    private static final int BOARDSIZE = 6;


    public BoardImplementation(Card[][] board){
        this.board = board;
    }


    @Override
    public Card[][] getCurrentBoard() {
        return this.board;
    }

    @Override
    public boolean isFull() {
        for(int i=0; i<BOARDSIZE; i++){
            for(int j=0; j<BOARDSIZE; j++){
                if(board[i][j] == null) return false;
            }
        }
        return true;
    }

    @Override
    public boolean isEmpty() {
        for(int i=0; i<BOARDSIZE; i++){
            for(int j=0; j<BOARDSIZE; j++){
                if(board[i][j] != null) return false;
            }
        }
        return true;
    }

    @Override
    public int removeCards(Coordinates x, Coordinates y) {
        //TODO
        return 0;
    }
}
