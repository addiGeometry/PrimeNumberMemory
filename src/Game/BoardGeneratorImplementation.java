package Game;

import java.util.*;

public class BoardGeneratorImplementation implements BoardGenerator{


    private final static ArrayList<Integer> PRIMENUMBERS = new ArrayList<>(Arrays.asList(2,3,5,7,11,13,17,19,23,29,31,37,41,43,47,53,59,61));
    private final static int UPPERBORDER = 6;

    private final static int HALFUPPERBORDER = 3;
    private final static int HALFLOWERBORDER = 2;

    private Card[][] board = new Card[UPPERBORDER][UPPERBORDER];

    @Override
    public Card[][] generateBoard6x6() {
        int listIterator = 0;
        Collections.shuffle(PRIMENUMBERS);

        for(int i = 0; i < UPPERBORDER; i++) {
            for(int j = 0; j < HALFUPPERBORDER; j++) {
                board[i][j] = new CardImplementation(PRIMENUMBERS.get(listIterator));
                listIterator++;
            }
        }
        listIterator = 0;
        Collections.shuffle(PRIMENUMBERS);
        for(int i = 0; i < UPPERBORDER; i++) {
            for(int j = HALFUPPERBORDER; j < UPPERBORDER; j++) {
                board[i][j] = new CardImplementation(PRIMENUMBERS.get(listIterator));
                listIterator++;
            }
        }
        return board;
    }

}
