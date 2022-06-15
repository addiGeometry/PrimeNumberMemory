package Game;

import Grafics.BoardRenderer;

import java.util.HashMap;

public class BoardRendererImplementation implements BoardRenderer {

    private final static String CARDFACEDOWN    =  "[/]";
    private final static String CARDFACEUP      =  " x ";
    private final static String LEFTEDGE = "[";
    private final static String RIGHTEDGE = "]";

    private HashMap<Coordinates, String> toRender;

    private String feld =    "                                                      "     +
            "                   1   2   3   4   5   6              \n"   +
            "             A   [/] [/] [/] [/] [/] [/]             \n"    +
            "             B   [/] [/] [/] [/] [/] [/]             \n"    +
            "             C   [/] [/] [/] [/] [/] [/]             \n"    +
            "             D   [/] [/] [/] [/] [/] [/]             \n"    +
            "             E   [/] [/] [/] [/] [/] [/]             \n"    +
            "             F   [/] [/] [/] [/] [/] [/]             ";



    @Override
    public void renderBoard() {

    }

    @Override
    public void renderOpenBoard() {
        StringBuilder renderer = new StringBuilder();
        renderer.append("\n");
        renderer.append("                   1   2   3   4   5   6              ");
        renderer.append("\n");

        //TODO


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
