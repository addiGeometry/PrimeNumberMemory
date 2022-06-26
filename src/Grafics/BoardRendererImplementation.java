package Grafics;

import Game.*;
import Grafics.BoardRenderer;

import java.util.HashMap;

public class BoardRendererImplementation implements BoardRenderer {

    private final static String CARDFACEDOWN    =  "[/]";
    private final static String CARDGONE      =  " x ";
    private final static int BOARDSIZE          =   6;
    private final static String THIRTEENSPACES = "             ";
    private final static String TWELVESPACES = "            ";
    private final static String LEFTEDGE = "[";
    private final static String RIGHTEDGE = "]";

    private HashMap<Coordinates, String> toRender;

    private BoardParser parser;

    private String feld =    "                                                      "     +
            "                   1   2   3   4   5   6              \n"   +
            "             A   [/] [/] [/] [/] [/] [/]             \n"    +
            "             B   [/] [/] [/] [/] [/] [/]             \n"    +
            "             C   [/] [/] [/] [/] [/] [/]             \n"    +
            "             D   [/] [/] [/] [/] [/] [/]             \n"    +
            "             E   [/] [/] [/] [/] [/] [/]             \n"    +
            "             F   [/] [/] [/] [/] [/] [/]             ";


    public BoardRendererImplementation(){
        parser = new BoardParserImplementation();
    }

    @Override
    public void renderBoard() {

    }

    public void renderOpenBoard(int a, int b, int value) {
        StringBuilder renderer = new StringBuilder();
        renderer.append("\n");
        renderer.append("                   1   2   3   4   5   6              ");
        renderer.append("\n");

        //TODO


    }

    public String renderOpenBoard(Card[][] toRender, Coordinates a, Coordinates b){
        StringBuilder build = new StringBuilder();
        String[] lines  = new String[]{"A","B","C","D","E","F"};
        int acolumn = parser.parseLetterCoord(a);
        int arow    = parser.parseNumberCoord(a);
        int bcolumn = parser.parseLetterCoord(b);
        int brow    = parser.parseNumberCoord(b);

        for(int i=0; i<BOARDSIZE; i++){
            build.append(THIRTEENSPACES);
            build.append(lines[i]);
            build.append("   ");
            for(int j=0; j<BOARDSIZE; j++){
                if(!toRender[i][j].isActive()){
                    build.append(CARDGONE);
                    build.append(" ");
                    continue;
                }
                else if( ((i==arow) && (j==acolumn))  || ((i==brow) && (j==bcolumn)) ){
                    build.append(LEFTEDGE);
                    if (toRender[i][j].getValue() < 10) build.append("0");
                    build.append(toRender[i][j].getValue());
                    build.append(RIGHTEDGE);
                    build.append(" ");
                }
                else{
                    build.append(CARDFACEDOWN);
                    build.append(" ");
                }
            }
            build.append(TWELVESPACES);
            build.append("\n");
        }
        return build.toString();
    }
    private void generateBoard(){
    }

    private String buildFaceUpCard(int x){
        StringBuilder render = new StringBuilder();
        render.append(LEFTEDGE);
        render.append(x);
        render.append(RIGHTEDGE);
        return render.toString();
    }
}
