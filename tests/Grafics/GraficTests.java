package Grafics;

import Game.*;
import org.junit.Test;

public class GraficTests{
    /**
    Test für "ausgeschmückte Terminal-Dialoge
     */

    Card testCard = new CardImplementation(2);
    Card dummy = new CardImplementation(4);//Dummy-Karte 4 ist keine Primzahl

   @Test
    public void test1(){
       DesignBuilderImplementation dia = new DesignBuilderImplementation();
       DesignBuilder desiBuild = dia;

       System.out.println(desiBuild.returnBorderedOutput("In der Bibel steht, dass Mose zu den Juden gehörte, die im Alten Ägypten lebten. Dort mussten sie als Sklaven für die Ägypter arbeiten. Der ägyptische König, der Pharao, fand damals, dass es zu viele Juden in seinem Reich gab. Darum ließ er alle Neugeborenen der Juden töten. Die Bibel erzählt, dass Mose als Säugling in einen Korb gelegt und vom Fluss Nil fortgetragen wurde.\n" +
               "\n" +
               "Die Tochter des Pharaos fand ihn und nahm ihn mit. So kam es, dass er am Königshof aufwuchs. Doch bald musste er aus Ägypten fliehen, weil er in seiner Wut einen Aufseher der Sklaven getötet hatte. Dort hütete Ziegen in der Wüste. Die Bibel erzählt, dass ihm da aus einem brennenden Dornbusch eine Stimme auftrug, die Juden aus Ägypten herauszuführen.\n" +
               "\n" +
               "So kehrte Mose nach Ägypten zurück. Der Pharao wollte die Juden zunächst nicht gehen lassen. Doch Gott, so erzählt die Bibel, ließ zehn Plagen über das ägyptische Volk hereinbrechen: Zum Beispiel verwandelte sich das Wasser in Blut, es gab eine Heuschreckenplage und die ältesten Kinder der Ägypter starben. "));

   }
   @Test
    public void wieEsClosedAussehenSoll(){
       DesignBuilder desiBuild = new DesignBuilderImplementation();
       String feld =
               "                                                      "     +
               "                   1    2    3    4    5    6       \n"     +
               "              A   [//] [//] [//] [//] [//] [//]      \n"    +
               "              B   [//] [//] [//] [//] [//] [//]      \n"    +
               "              C   [//] [//] [//] [//] [//] [//]      \n"    +
               "              D   [//] [//] [//] [//] [//] [//]      \n"    +
               "              E   [//] [//] [//] [//] [//] [//]      \n"    +
               "              F   [//] [//] [//] [//] [//] [//]     \n"     +
               "                                                      ";


       System.out.println(desiBuild.returnBorderedOutput(feld));
   }

    @Test
    public void openBoardOnStart() {
        Memory memory = new Memory(PlayerLogic.P1, PlayerLogic.P2, "test");

        BoardRenderer render = new BoardRendererImplementation();
        DesignBuilder desiBuild = new DesignBuilderImplementation();

        System.out.println(desiBuild.returnBorderedOutput(render.renderOpenBoard(memory.getCurrentBoard(), Coordinates.D5, Coordinates.C3)));
    }

    @Test
    public void openBoardTwoCardsMissing() throws StatusException, NotYourTurnException, CardsGoneException, DoublePickException, GameException {
        /**     Generiere das Board mit D5, C2 aufgedeckt, nachdem zwei Karten weg sind
         *
         *           1   2   3   4   5   6
         *      A   [//] [//] [//] [//] [//] [//]
         *      B   [//] [//] [//] [//] [//] [//]
         *      C   [//] [//] [//] [//] [//] [//]
         *      D   [//] [//] [//] [//] [//] [//]
         *      E   [//] [//] [//] [//] [//] [//]
         *      F   [//] [//] [//] [//] [//] [//]
         * */

        Card[][] testFeld = new Card[][] {
                {testCard,testCard,dummy,dummy,dummy,dummy},
                {dummy,dummy,dummy,dummy,dummy,dummy},
                {dummy,dummy,dummy,dummy,dummy,dummy},
                {dummy,dummy,dummy,dummy,dummy,dummy},
                {dummy,dummy,dummy,dummy,dummy,dummy},
                {dummy,dummy,dummy,dummy,dummy,dummy}
        };

       DevMemory demory = new DevMemory(PlayerLogic.P1, PlayerLogic.P2, "test");

        BoardRenderer render = new BoardRendererImplementation();
        DesignBuilder desiBuild = new DesignBuilderImplementation();

        demory.setBoard(testFeld);

        demory.flip(PlayerLogic.P1,Coordinates.A1,Coordinates.A2);

        System.out.println(desiBuild.returnBorderedOutput(render.renderOpenBoard(demory.getDevBoard(), Coordinates.D5, Coordinates.C2)));
    }
    @Test
    public void closedBoardFourCardsFlipped() throws StatusException, NotYourTurnException, CardsGoneException, DoublePickException, GameException {
        /**     Generiere das Board, bei dem die Karten A1,A2,D4 und E3 schon inaktiv sind,
         *
         *            1    2    3    4    5    6
         *      A    xx   xx  [//] [//] [//] [//]
         *      B   [//] [//] [//] [//] [//] [//]
         *      C   [//] [//] [//] [//] [//] [//]
         *      D   [//] [//] [//]  xx  [//] [//]
         *      E   [//] [//]  xx  [//] [//] [//]
         *      F   [//] [//] [//] [//] [//] [//]
         * */

        Card[][] testFeld = new Card[][] {
                {testCard,testCard,dummy,dummy,dummy,dummy},
                {dummy,dummy,dummy,dummy,dummy,dummy},
                {dummy,dummy,dummy,dummy,dummy,dummy},
                {dummy,dummy,dummy,testCard,dummy,dummy},
                {dummy,dummy,testCard,dummy,dummy,dummy},
                {dummy,dummy,dummy,dummy,dummy,dummy}
        };

        DevMemory demory = new DevMemory(PlayerLogic.P1, PlayerLogic.P2, "test");

        BoardRenderer render = new BoardRendererImplementation();
        DesignBuilder desiBuild = new DesignBuilderImplementation();

        demory.setBoard(testFeld);

        demory.flip(PlayerLogic.P1,Coordinates.A1,Coordinates.A2);

        System.out.println(desiBuild.returnBorderedOutput(render.renderClosedBoard(demory.getDevBoard(), Coordinates.D5, Coordinates.C3)));
    }

    @Test
    public void emptyBoard() throws StatusException, NotYourTurnException, CardsGoneException, DoublePickException, GameException {
        /**
         * Aufgepasst diese Methode provoziert (absichtlich) einen lustigen Bug, der aber im normalen Spiel nicht eintreten kann:
         * Ergebnis wenn alle Karten dummys sind und beide (in Wahrheit nur eine ;) deaktiviert wird.
         *
         *                    1    2    3    4    5    6
         *              A    xx   xx   xx   xx   xx   xx
         *              B    xx   xx   xx   xx   xx   xx
         *              C    xx   xx   xx   xx   xx   xx
         *              D    xx   xx   xx   xx   xx   xx
         *              E    xx   xx   xx   xx   xx   xx
         *              F    xx   xx   xx   xx   xx   xx
         *
         */
        Card[][] testFeld = new Card[][] {
                {dummy,dummy,dummy,dummy,dummy,dummy},
                {dummy,dummy,dummy,dummy,dummy,dummy},
                {dummy,dummy,dummy,dummy,dummy,dummy},
                {dummy,dummy,dummy,dummy,dummy,dummy},
                {dummy,dummy,dummy,dummy,dummy,dummy},
                {dummy,dummy,dummy,dummy,dummy,dummy}
        };

        DevMemory demory = new DevMemory(PlayerLogic.P1, PlayerLogic.P2, "test");

        BoardRenderer render = new BoardRendererImplementation();
        DesignBuilder desiBuild = new DesignBuilderImplementation();

        demory.setBoard(testFeld);

        demory.flip(PlayerLogic.P1,Coordinates.A1,Coordinates.A2);

        System.out.println(desiBuild.returnBorderedOutput(render.renderOpenBoard(demory.getDevBoard(), Coordinates.D5, Coordinates.C2)));
    }

}
