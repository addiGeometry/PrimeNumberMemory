package Game;


import org.junit.Assert;
import org.junit.Test;

public class GameTests{

    /************************************************************************************************************
     ***                                            Test-Umgebung:                                            ***
     ***********************************************************************************************************/

    //Zwei lokale Testspieler
    private static final String ALICE = "Alice";
    private static final String BOB = "Bob";

    //Ein Spiel kann nur gleichzeitig zwei Spieler haben.
    private static final Player PLAYER_1 = Player.P1;
    private static final Player PLAYER_2 = Player.P2;

    //Erzeuge zwei Kartenschablonen für Tests
    Card testCard = new CardImplementation(2);
    Card dummy = new CardImplementation(4); //Dummy-Karte 4 ist keine Primzahl

    //Der BoardGenerator generiert ein zufälliges Memory-Feld aus der Zahlenmenge. Bildlich gesehen mischt er die Karten und teilt Sie aus
    BoardGenerator boardGenerator = new BoardGeneratorImplementation();

    //Erstelle das Standard Memory-Brett
    private Memory getMemory(){
        return new Memory(boardGenerator, PLAYER_1, PLAYER_2);
    }

    //Erzeuge die Entwicklervariante des Memories mit Hintertür
    //@param devBoardGenerator
    private DevMemory getDevMemory(DevBoardGenerator devBoardGenerator){
        return new DevMemory(boardGenerator, devBoardGenerator, PLAYER_1, PLAYER_2);
    }

    /************************************************************************************************************
     ***                                              Tests                                                   ***
     ***********************************************************************************************************/

    /**
     *
     * Notation:
     *
     *      [/] = Verdeckte, aktive Karte
     *      [p] = Aufgedeckte, aktive Karte mit Primzahl p
     *       x  = Karte ist nicht im Spiel (inaktiv)
     *
     */

    @Test
    public void TesteSpielerScore() throws NotYourTurnException, CardsGoneException{
        /**     Szenario: A1,A2 sind beide "2" und werden von Alice aufgedeckt. Paaranzahl von Alice erhöht sich
         *
         *           1   2   3   4   5   6
         *      A   [2] [2] [/] [/] [/] [/]
         *      B   [/] [/] [/] [/] [/] [/]
         *      C   [/] [/] [/] [/] [/] [/]
         *      D   [/] [/] [/] [/] [/] [/]
         *      E   [/] [/] [/] [/] [/] [/]
         *      F   [/] [/] [/] [/] [/] [/]
         * */

        Card[][] testFeld = new Card[][] {
                {testCard,testCard,dummy,dummy,dummy,dummy},
                {dummy,dummy,dummy,dummy,dummy,dummy},
                {dummy,dummy,dummy,dummy,dummy,dummy},
                {dummy,dummy,dummy,dummy,dummy,dummy},
                {dummy,dummy,dummy,dummy,dummy,dummy},
                {dummy,dummy,dummy,dummy,dummy,dummy}
        };

        DevBoardGenerator devBoardGenerator = new DevBoardGeneratorImpl(testFeld);
        DevMemory devMemory = getDevMemory(devBoardGenerator);

        devMemory.flip(PLAYER_1, Coordinate.A1, Coordinate.A2);

        Assert.assertEquals(1, devMemory.hasScore(PLAYER_1));
    }


    @Test (expected = CardsGoneException.class)
    public void DeckeKartenAufDieSchonWegSind() throws CardsGoneException, NotYourTurnException {
        /**     Szenario: A1,A2 fehlen - GameException soll geworfen werden weil
         *
         *           1   2   3   4   5   6
         *      A    x   x  [/] [/] [/] [/]
         *      B   [/] [/] [/] [/] [/] [/]
         *      C   [/] [/] [/] [/] [/] [/]
         *      D   [/] [/] [/] [/] [/] [/]
         *      E   [/] [/] [/] [/] [/] [/]
         *      F   [/] [/] [/] [/] [/] [/]
         * */
        Card nulldummy = null;
        Card[][] testFeld = new Card[][] {
                {nulldummy,nulldummy,dummy,dummy,dummy,dummy},
                {dummy,dummy,dummy,dummy,dummy,dummy},
                {dummy,dummy,dummy,dummy,dummy,dummy},
                {dummy,dummy,dummy,dummy,dummy,dummy},
                {dummy,dummy,dummy,dummy,dummy,dummy},
                {dummy,dummy,dummy,dummy,dummy,dummy}
        };

        DevBoardGenerator devBoardGen = new DevBoardGeneratorImpl(testFeld);
        DevMemory devMemory = getDevMemory(devBoardGen);

        //GameException !
        devMemory.flip(PLAYER_1, Coordinate.A1, Coordinate.A2);
    }


    @Test (expected = NotYourTurnException.class)
    public void SpielerIstNichtDran() throws NotYourTurnException, CardsGoneException{
        /**     Szenario: PLAYER_1 ist für alle Tests Alice. Hier entsteht ein neues (manipuliertes) Spiel
         *      und noch niemand hat gezogen. Deshalb ist eigentlich Alice dran, aber Bob versucht zu ziehen.
         *
         *           1   2   3   4   5   6
         *      A   [/] [/] [/] [/] [/] [/]
         *      B   [/] [/] [/] [/] [/] [/]
         *      C   [/] [/] [/] [/] [/] [/]
         *      D   [/] [/] [/] [/] [/] [/]
         *      E   [/] [/] [/] [/] [/] [/]
         *      F   [/] [/] [/] [/] [/] [/]
         * */
        Memory memory = getMemory();

        //NotYourTurnException
        memory.flip(PLAYER_2,Coordinate.A1, Coordinate.A2);
    }

    //TODO Spieler versucht zwei mal die gleiche Karte zu Flippen test

    public void SpielerGibtAuf() throws NotYourTurnException, GameException{
        /**     Szenario: Bob gibt auf, weil er keine Chancen mehr sieht zu gewinnen
         *      ⇨ Somit hat PLAYER_2 Alice
         *
         *           1   2   3   4   5   6
         *      A   [/] [/] [/] [/] [/] [/]
         *      B   [/] [/] [/] [/] [/] [/]
         *      C   [/] [/] [/] [/] [/] [/]
         *      D   [/] [/] [/] [/] [/] [/]
         *      E   [/] [/] [/] [/] [/] [/]
         *      F   [/] [/] [/] [/] [/] [/]
         * */
        Memory memory = getMemory();

        memory.surrender(PLAYER_2);

        Assert.assertEquals(true,memory.isWinner(PLAYER_1));
    }

    @Test
    public void spielerGewinnt() throws NotYourTurnException, CardsGoneException{
        /**     Szenario: Alice gewinnt (Im ersten Zug weil das Feld manipuliert worden ist)
         *           1   2   3   4   5   6
         *      A    x   x   x   x   x   x
         *      B    x  [2]  x   x   x   x
         *      C    x   x   x   x   x   x
         *      D    x   x   x   x   x   x
         *      E    x   x   x  [2]  x   x
         *      F    x   x   x   x   x   x
         * */


        Card[][] testFeld = new Card[][] {
                {dummy,dummy,dummy,dummy,dummy,dummy},
                {dummy,testCard,dummy,dummy,dummy,dummy},
                {dummy,dummy,dummy,dummy,dummy,dummy},
                {dummy,dummy,dummy,dummy,dummy,dummy},
                {dummy,dummy,dummy,dummy,dummy,dummy},
                {dummy,dummy,dummy,testCard,dummy,dummy}
        };

        DevBoardGenerator devBoardGen = new DevBoardGeneratorImpl(testFeld);
        DevMemory devMemory = getDevMemory(devBoardGen);

        devMemory.flip(PLAYER_1, Coordinate.B2, Coordinate.E4);

        Assert.assertEquals(true,devMemory.isWinner(PLAYER_2));
    }

    @Test
    public void testeUntentschieden() throws NotYourTurnException, CardsGoneException{
        /**     Szenario: Alice nimmt die letzten zwei Karten und verursacht
         *      ein Unentschieden, weil danach Punktegleichstand herrscht.
         *
         *           1   2   3   4   5   6
         *      A    x   x   x   x   x   x
         *      B    x  [2]  x   x   x   x
         *      C    x   x   x   x   x   x
         *      D    x   x   x   x   x   x
         *      E    x   x   x  [2]  x   x
         *      F    x   x   x   x   x   x
         * */

        Card[][] testFeld = new Card[][] {
                {dummy,dummy,dummy,dummy,dummy,dummy},
                {dummy,testCard,dummy,dummy,dummy,dummy},
                {dummy,dummy,dummy,dummy,dummy,dummy},
                {dummy,dummy,dummy,dummy,dummy,dummy},
                {dummy,dummy,dummy,dummy,dummy,dummy},
                {dummy,dummy,dummy,testCard,dummy,dummy}
        };

        DevBoardGenerator devBoardGen = new DevBoardGeneratorImpl(testFeld);
        DevMemory devMemory = getDevMemory(devBoardGen);

        devMemory.setPunkteStand(PLAYER_1, 0);
        devMemory.setPunkteStand(PLAYER_2, 1);

        devMemory.flip(PLAYER_1, Coordinate.B2, Coordinate.E4);

        Assert.assertEquals(true,devMemory.isDraw());
    }

}
