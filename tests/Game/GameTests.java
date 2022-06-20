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
    private static final PlayerLogic PLAYER_LOGIC_1 = PlayerLogic.P1;
    private static final PlayerLogic PLAYER_LOGIC_2 = PlayerLogic.P2;

    //Erzeuge zwei Kartenschablonen für Tests
    Card testCard = new CardImplementation(2);
    Card dummy = new CardImplementation(4);//Dummy-Karte 4 ist keine Primzahl

    Card toDeactivate = new CardImplementation(-1);

    //Der BoardGenerator generiert ein zufälliges Memory-Feld aus der Zahlenmenge. Bildlich gesehen mischt er die Karten und teilt Sie aus
    BoardGenerator boardGenerator = new BoardGeneratorImplementation();

    //Erstelle das Standard Memory-Brett
    private Memory getMemory(){
        return new Memory(PLAYER_LOGIC_1, PLAYER_LOGIC_2, ALICE);
    }

    //Erzeuge die Entwicklervariante des Memories mit Hintertür
    //@param devBoardGenerator
    private DevMemory getDevMemory(){
        return new DevMemory(PLAYER_LOGIC_1, PLAYER_LOGIC_2, ALICE);
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

    /************************************************************************************************************
     ***                                           Spielvorbereitung                                          ***
     ***********************************************************************************************************/
    @Test
    public void erfolgreichesBoardErstellt(){
        /**
         * Teste, ob der board Generator sachgerecht funktioniert
         */
        Board memory = getMemory();
        Assert.assertEquals(true, memory.isFull());
    }

    @Test
    public void alleKartenInaktiv(){
        /**     Spiel wird mit nur inaktiven Karten initialisiert
         *
         *           1   2   3   4   5   6
         *      A    x   x   x   x   x   x
         *      B    x   x   x   x   x   x
         *      C    x   x   x   x   x   x
         *      D    x   x   x   x   x   x
         *      E    x   x   x   x   x   x
         *      F    x   x   x   x   x   x
         * */

        DevMemory devMemory = getDevMemory();

        toDeactivate.deactivate();

        Card[][] testFeld = new Card[][] {
                {toDeactivate,toDeactivate,toDeactivate,toDeactivate,toDeactivate,toDeactivate},
                {toDeactivate,toDeactivate,toDeactivate,toDeactivate,toDeactivate,toDeactivate},
                {toDeactivate,toDeactivate,toDeactivate,toDeactivate,toDeactivate,toDeactivate},
                {toDeactivate,toDeactivate,toDeactivate,toDeactivate,toDeactivate,toDeactivate},
                {toDeactivate,toDeactivate,toDeactivate,toDeactivate,toDeactivate,toDeactivate},
                {toDeactivate,toDeactivate,toDeactivate,toDeactivate,toDeactivate,toDeactivate}
        };

        devMemory.setBoard(testFeld);
        devMemory.isEmpty();
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

    /************************************************************************************************************
     ***                                            Flip-Methode                                              ***
     ***********************************************************************************************************/

    @Test (expected = DoublePickException.class)
    public void zweiMalDieselbeKarte() throws NotYourTurnException, CardsGoneException, DoublePickException, StatusException, GameException {
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
        memory.flip(PLAYER_LOGIC_1, Coordinates.A1, Coordinates.A1);
    }

    @Test (expected = NotYourTurnException.class)
    public void spielerIstNichtDran() throws NotYourTurnException, CardsGoneException, DoublePickException, StatusException, GameException {
        /**     Szenario: PLAYER_LOGIC_1 ist für alle Tests Alice. Hier entsteht ein neues (manipuliertes) Spiel
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
        memory.flip(PLAYER_LOGIC_2, Coordinates.A1, Coordinates.A2);
    }

    @Test (expected = CardsGoneException.class)
    public void deckeKartenAufDieSchonWegSind() throws CardsGoneException, NotYourTurnException, DoublePickException, StatusException, GameException {
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

        DevMemory devMemory = getDevMemory();

        devMemory.deactivateCard(Coordinates.A1);
        devMemory.deactivateCard(Coordinates.A2);

        //GameException
        devMemory.flip(PLAYER_LOGIC_1, Coordinates.A1, Coordinates.A2);
    }

    @Test (expected = CardsGoneException.class)
    public void deckeEineKarteAufDieWegIst() throws CardsGoneException, NotYourTurnException, DoublePickException, StatusException, GameException {
        /**     Szenario: A1,A2 fehlen - Eine Karte soll aufgedeckt werden, die schon weg ist. CardsGoneException soll geworfen werden.
         *
         *           1   2   3   4   5   6
         *      A    x  [/]  [/] [/] [/] [/]
         *      B   [/] [/] [/] [/] [/] [/]
         *      C   [/] [/] [/] [/] [/] [/]
         *      D   [/] [/] [/] [/] [/] [/]
         *      E   [/] [/] [/] [/] [/] [/]
         *      F   [/] [/] [/] [/] [/] [/]
         * */

        DevMemory devMemory = getDevMemory();

        devMemory.deactivateCard(Coordinates.A1);
        //GameException !
        devMemory.flip(PLAYER_LOGIC_1, Coordinates.A1, Coordinates.A3);
    }

    @Test
    public void karteWirdWeggenommen() throws NotYourTurnException, CardsGoneException, DoublePickException, StatusException, GameException {
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

            Card[][] testFeld = new Card[][] {
                    {testCard,testCard,dummy,dummy,dummy,dummy},
                    {dummy,dummy,dummy,dummy,dummy,dummy},
                    {dummy,dummy,dummy,dummy,dummy,dummy},
                    {dummy,dummy,dummy,dummy,dummy,dummy},
                    {dummy,dummy,dummy,dummy,dummy,dummy},
                    {dummy,dummy,dummy,dummy,dummy,dummy}
            };

            DevMemory devMemory = getDevMemory();
            devMemory.setBoard(testFeld);

            Assert.assertEquals(testCard, devMemory.getCard(0,0));
            Assert.assertEquals(testCard, devMemory.getCard(0,1));

            Assert.assertEquals(testCard.getValue(), devMemory.getCard(0,1).getValue());

            devMemory.flip(PLAYER_LOGIC_1, Coordinates.A1, Coordinates.A2);

            Assert.assertEquals(false, devMemory.getCard(0,0).isActive());
            Assert.assertEquals(false, devMemory.getCard(0,1).isActive());

            //Stichprobe: is B2 aktiv?
            Assert.assertEquals(true, devMemory.getCard(1,1).isActive());
    }

    @Test
    public void TesteSpielerScore() throws NotYourTurnException, CardsGoneException, DoublePickException, StatusException, GameException {
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
        int expectedScoreP1 = 1;
        int expectedScoreP2 = 0;


        DevMemory devMemory = getDevMemory();
        devMemory.setBoard(testFeld);

        Assert.assertEquals(testCard, devMemory.getCard(0,0));
        Assert.assertEquals(testCard, devMemory.getCard(0,1));

        Assert.assertEquals(testCard.getValue(), devMemory.getCard(0,1).getValue());


        devMemory.flip(PLAYER_LOGIC_1, Coordinates.A1, Coordinates.A2);

        Assert.assertEquals(expectedScoreP1, devMemory.hasScore(PLAYER_LOGIC_1));
        Assert.assertEquals(expectedScoreP2, devMemory.hasScore(PLAYER_LOGIC_2));
    }
    @Test
    public void TesteSpielerStatusWechsel() throws NotYourTurnException, CardsGoneException, DoublePickException, StatusException, GameException {
        /**     Szenario: A1,A2 sind beide "2" und werden von Alice aufgedeckt. Jetzt ist Bob dran
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

        DevMemory devMemory = getDevMemory();
        devMemory.setBoard(testFeld);

        Assert.assertEquals(testCard, devMemory.getCard(0,0));
        Assert.assertEquals(testCard, devMemory.getCard(0,1));

        devMemory.flip(PLAYER_LOGIC_1, Coordinates.A1, Coordinates.A2);

        //Jetzt ist Player2 dran
        Assert.assertEquals(Status.P2_Turn,devMemory.status);
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
        toDeactivate.deactivate();
        Card[][] testFeld = new Card[][] {
                {toDeactivate,toDeactivate,toDeactivate,toDeactivate,toDeactivate,toDeactivate},
                {toDeactivate,testCard,toDeactivate,toDeactivate,toDeactivate,toDeactivate},
                {toDeactivate,toDeactivate,toDeactivate,toDeactivate,toDeactivate,toDeactivate},
                {toDeactivate,toDeactivate,toDeactivate,toDeactivate,toDeactivate,toDeactivate},
                {toDeactivate,toDeactivate,toDeactivate,testCard,toDeactivate,toDeactivate},
                {toDeactivate,toDeactivate,toDeactivate,toDeactivate,toDeactivate,toDeactivate}
        };


        DevMemory devMemory = getDevMemory();
        devMemory.setBoard(testFeld);

        devMemory.setPlayerScore(PLAYER_LOGIC_1, 8);
        devMemory.flip(PLAYER_LOGIC_1, Coordinates.B2, Coordinates.E4);

        Assert.assertEquals(true,devMemory.hasWon(PLAYER_LOGIC_1));
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

        devMemory.setPunkteStand(PLAYER_LOGIC_1, 0);
        devMemory.setPunkteStand(PLAYER_LOGIC_2, 1);

        try {
            devMemory.flip(PLAYER_LOGIC_1, Coordinates.B2, Coordinates.E4);
        } catch (StatusException e) {
            throw new RuntimeException(e);
        } catch (GameException e) {
            throw new RuntimeException(e);
        }

        Assert.assertEquals(true,devMemory.isDraw());
    }

    /************************************************************************************************************
     ***                                        Test von "Aufgeben"                                           ***
     ***********************************************************************************************************/

    @Test
    public void spielerGibtAufStatusÄndertSich() throws StatusException {
        /**     Szenario: Bob gibt auf, weil er keine Chancen mehr sieht zu gewinnen
         *      ⇨ Somit hat PLAYER_LOGIC_1, Alice gewonnen
         *
         *           1   2   3   4   5   6
         *      A   [/] [/] [/] [/] [/] [/]
         *      B   [/] [/] [/] [/] [/] [/]
         *      C   [/] [/] [/] [/] [/] [/]
         *      D   [/] [/] [/] [/] [/] [/]
         *      E   [/] [/] [/] [/] [/] [/]
         *      F   [/] [/] [/] [/] [/] [/]
         * */
        DevMemory devMemory = getDevMemory();

        devMemory.surrender(PLAYER_LOGIC_2);

        Assert.assertEquals(Status.ENDED, devMemory.getStatus());
    }

    @Test
    public void spielerGibtAufGegenseiteGewinnt() throws StatusException {
        /**     Szenario: Bob gibt auf, weil er keine Chancen mehr sieht zu gewinnen
         *      ⇨ Somit hat PLAYER_LOGIC_1, Alice gewonnen
         *
         *           1   2   3   4   5   6
         *      A   [/] [/] [/] [/] [/] [/]
         *      B   [/] [/] [/] [/] [/] [/]
         *      C   [/] [/] [/] [/] [/] [/]
         *      D   [/] [/] [/] [/] [/] [/]
         *      E   [/] [/] [/] [/] [/] [/]
         *      F   [/] [/] [/] [/] [/] [/]
         * */
        boolean expectedOtherSideWinner = true;

        DevMemory devMemory = getDevMemory();

        devMemory.surrender(PLAYER_LOGIC_2);

        Assert.assertEquals(expectedOtherSideWinner, devMemory.hasWon(PLAYER_LOGIC_1));
        //TODO
    }

    /************************************************************************************************************
     ***                                        Exception-Tests                                               ***
     ***********************************************************************************************************/

}
