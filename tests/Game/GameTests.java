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
        return new Memory(PLAYER_1, PLAYER_2, ALICE);
    }

    //Erzeuge die Entwicklervariante des Memories mit Hintertür
    //@param devBoardGenerator
    private DevMemory getDevMemory(){
        return new DevMemory(PLAYER_1, PLAYER_2, ALICE);
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
    public void erfolgreichesBoardErstellt(){
        /**
         * Teste, ob der board Generator sachgerecht funktioniert
         */
        Board memory = getMemory();
        Assert.assertEquals(true, memory.isFull());
    }

    @Test
    public void ReihenfolgeZugewiesen(){
        /**
         * Teste, ob die Reihenfolge zugewiesen worden ist und ein neuer Status eingetreten ist.
          */
        DevMemoryAPI game = getDevMemory();

        Assert.assertEquals(Status.P1_Turn, game.getStatus());
        Assert.assertEquals(
                true,
                game.getFirstPlayer() == ALICE || game.getFirstPlayer() == "default"
                //TODO
        );
    }


    @Test
    public void karteWirdWeggenommen() throws NotYourTurnException, CardsGoneException, DoublePickException{
            /**     Szenario: A1,A2 sind beide "2" und werden von Alice aufgedeckt. Die Karten verschwinden von dem
             *      Spielbrett.
             *
             *           1   2   3   4   5   6
             *      A   [2] [2] [/] [/] [/] [/]
             *      B   [/] [/] [/] [/] [/] [/]
             *      C   [/] [/] [/] [/] [/] [/]
             *      D   [/] [/] [/] [/] [/] [/]
             *      E   [/] [/] [/] [/] [/] [/]
             *      F   [/] [/] [/] [/] [/] [/]
             * */
            boolean aufDemFeld = true;

            Card[][] testFeld = new Card[][] {
                    {testCard,testCard,dummy,dummy,dummy,dummy},
                    {dummy,dummy,dummy,dummy,dummy,dummy},
                    {dummy,dummy,dummy,dummy,dummy,dummy},
                    {dummy,dummy,dummy,dummy,dummy,dummy},
                    {dummy,dummy,dummy,dummy,dummy,dummy},
                    {dummy,dummy,dummy,dummy,dummy,dummy}
            };

            DevBoardGenerator devBoardGenerator = new DevBoardGeneratorImpl(testFeld);
            DevMemory devMemory = getDevMemory();

            Assert.assertEquals(testCard, testFeld[0][0]);
            Assert.assertEquals(testCard, testFeld[0][1]);

        try {
            devMemory.flip(PLAYER_1, Coordinate.A1, Coordinate.A2);
        } catch (StatusException e) {
            throw new RuntimeException(e);
        } catch (GameException e) {
            throw new RuntimeException(e);
        }

        Assert.assertEquals(null, testFeld[0][0]);
            Assert.assertEquals(null, testFeld[0][1]);
    }

    @Test
    public void TesteSpielerScore() throws NotYourTurnException, CardsGoneException, DoublePickException{
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

        int expcetedScore = 1;

        DevBoardGenerator devBoardGenerator = new DevBoardGeneratorImpl(testFeld);
        DevMemory devMemory = getDevMemory();

        try {
            devMemory.flip(PLAYER_1, Coordinate.A1, Coordinate.A2);
        } catch (StatusException e) {
            throw new RuntimeException(e);
        } catch (GameException e) {
            throw new RuntimeException(e);
        }

        Assert.assertEquals(expcetedScore, devMemory.hasScore(PLAYER_1));
    }

    @Test
    public void spielerGibtAuf(){
        /**     Szenario: Bob gibt auf, weil er keine Chancen mehr sieht zu gewinnen
         *      ⇨ Somit hat PLAYER_1, Alice gewonnen
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
    public void spielerGewinnt() throws NotYourTurnException, CardsGoneException, DoublePickException, StatusException, GameException {
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
        DevMemory devMemory = getDevMemory();


        devMemory.flip(PLAYER_1, Coordinate.B2, Coordinate.E4);

        Assert.assertEquals(true,devMemory.isWinner(PLAYER_2));
    }

    @Test
    public void testeUnentschieden() throws NotYourTurnException, CardsGoneException, DoublePickException{
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
        DevMemory devMemory = getDevMemory();

        devMemory.setPunkteStand(PLAYER_1, 0);
        devMemory.setPunkteStand(PLAYER_2, 1);

        try {
            devMemory.flip(PLAYER_1, Coordinate.B2, Coordinate.E4);
        } catch (StatusException e) {
            throw new RuntimeException(e);
        } catch (GameException e) {
            throw new RuntimeException(e);
        }

        Assert.assertEquals(true,devMemory.isDraw());
    }


    /************************************************************************************************************
     ***                                        Exception-Tests                                               ***
     ***********************************************************************************************************/

    @Test (expected = DoublePickException.class)
    public void zweiMalDieselbeKarte() throws NotYourTurnException, CardsGoneException, DoublePickException {
        /**     Szenario: Alice beginnt und versucht zweimal dieselbe Karte aufzudecken
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

        try {
            memory.flip(PLAYER_2,Coordinate.A1, Coordinate.A1);
        } catch (StatusException e) {
            throw new RuntimeException(e);
        } catch (GameException e) {
            throw new RuntimeException(e);
        }

    }
    @Test (expected = CardsGoneException.class)
    public void deckeKartenAufDieSchonWegSind() throws CardsGoneException, NotYourTurnException, DoublePickException {
        /**     Szenario: A1,A2 fehlen - Beide sollen aufgedeckt werden - CardsGoneException soll geworfen werden weil
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
        DevMemory devMemory = getDevMemory();

        //GameException !
        try {
            devMemory.flip(PLAYER_1, Coordinate.A1, Coordinate.A2);
        } catch (StatusException e) {
            throw new RuntimeException(e);
        } catch (GameException e) {
            throw new RuntimeException(e);
        }
    }

    @Test (expected = CardsGoneException.class)
    public void deckeEineKarteAufDieWegIst() throws CardsGoneException, NotYourTurnException, DoublePickException{
        /**     Szenario: A1,A2 fehlen - Eine Karte soll aufgedeckt werden, die schon weg ist. CardsGoneException soll geworfen werden.
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
        DevMemory devMemory = getDevMemory();

        //GameException !
        try {
            devMemory.flip(PLAYER_1, Coordinate.A1, Coordinate.A3);
        } catch (StatusException e) {
            throw new RuntimeException(e);
        } catch (GameException e) {
            throw new RuntimeException(e);
        }
    }

    @Test (expected = NotYourTurnException.class)
    public void spielerIstNichtDran() throws NotYourTurnException, CardsGoneException, DoublePickException {
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
        try {
            memory.flip(PLAYER_2,Coordinate.A1, Coordinate.A2);
        } catch (StatusException e) {
            throw new RuntimeException(e);
        } catch (GameException e) {
            throw new RuntimeException(e);
        }
    }
}
