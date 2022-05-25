package Game;


import org.junit.Assert;
import org.junit.Test;

public class GameTests{
    private static final String ALICE = "Alice";
    private static final String BOB = "Bob";

    private static final Player player1 = Player.P1;
    private static final Player player2 = Player.P2;

    private Memory getMemory(){
        return new Memory();
    }

    private DevMemory getDevMemory(){
        return new DevMemory();
    }

    /**
     *
     * Notation:
     *
     *      [/] = Verdeckte, aktive Karte
     *      [p] = Aufgedeckte, aktive Karte mit Primzahn p
     *       x  = Inaktive Karte (Vom Feld)
     *
     */

    // Teste ob die Anzahl der gefunden Paare eines Spielers sich erhöht
    @Test
    public void TesteSpielerScore() throws NotYourTurnException, GameException{
        /**     Szenario: A1,A2 sind beide "2" und werden von Alice aufgedeckt. Paaranzahl von Alice erhöht sich
         *           1   2   3   4   5   6
         *      A   [2] [2] [/] [/] [/] [/]
         *      B   [/] [/] [/] [/] [/] [/]
         *      C   [/] [/] [/] [/] [/] [/]
         *      D   [/] [/] [/] [/] [/] [/]
         *      E   [/] [/] [/] [/] [/] [/]
         *      F   [/] [/] [/] [/] [/] [/]
         * */
        Card dummy2 = new CardImplementation(2);
        Card dummyBlanko = new CardImplementation(7);

        Card[][] testFeld = new Card[][] {
                {dummy2,dummy2,dummyBlanko,dummyBlanko,dummyBlanko,dummyBlanko},
                {dummyBlanko,dummyBlanko,dummyBlanko,dummyBlanko,dummyBlanko,dummyBlanko},
                {dummyBlanko,dummyBlanko,dummyBlanko,dummyBlanko,dummyBlanko,dummyBlanko},
                {dummyBlanko,dummyBlanko,dummyBlanko,dummyBlanko,dummyBlanko,dummyBlanko},
                {dummyBlanko,dummyBlanko,dummyBlanko,dummyBlanko,dummyBlanko,dummyBlanko},
                {dummyBlanko,dummyBlanko,dummyBlanko,dummyBlanko,dummyBlanko,dummyBlanko}
        };

        Position a1 = Position.A1;
        Position a2 = Position.A2;

        DevGameEngine game = getDevMemory();
        game.setBoard(testFeld);

        game.assignPlayer(ALICE, player1);
        game.assignPlayer(BOB, player2);
        game.flip(player1, a1, a2);

        Assert.assertEquals(1, game.hasScore(player1));
    }


    @Test (expected = GameException.class)
    public void DeckeKartenAufDieSchonWegSind() throws GameException, NotYourTurnException {

        /**     Szenario: A1,A2 fehlen - GameException soll geworfen werden weil
         *           1   2   3   4   5   6
         *      A    x   x  [/] [/] [/] [/]
         *      B   [/] [/] [/] [/] [/] [/]
         *      C   [/] [/] [/] [/] [/] [/]
         *      D   [/] [/] [/] [/] [/] [/]
         *      E   [/] [/] [/] [/] [/] [/]
         *      F   [/] [/] [/] [/] [/] [/]
         * */
        Card nulldummy = null;
        Card dummyBlanko = new CardImplementation(7);

        Card[][] testFeld = new Card[][] {
                {nulldummy,nulldummy,dummyBlanko,dummyBlanko,dummyBlanko,dummyBlanko},
                {dummyBlanko,dummyBlanko,dummyBlanko,dummyBlanko,dummyBlanko,dummyBlanko},
                {dummyBlanko,dummyBlanko,dummyBlanko,dummyBlanko,dummyBlanko,dummyBlanko},
                {dummyBlanko,dummyBlanko,dummyBlanko,dummyBlanko,dummyBlanko,dummyBlanko},
                {dummyBlanko,dummyBlanko,dummyBlanko,dummyBlanko,dummyBlanko,dummyBlanko},
                {dummyBlanko,dummyBlanko,dummyBlanko,dummyBlanko,dummyBlanko,dummyBlanko}
        };

        Position a1 = Position.A1;
        Position a2 = Position.A2;

        DevGameEngine game = getDevMemory();
        game.setBoard(testFeld);

        game.assignPlayer(ALICE, player1);
        game.assignPlayer(BOB, player2);

        //GameException !
        game.flip(player1, a1, a2);
    }

    @Test (expected = NotYourTurnException.class)
    public void SpielerIstNichtDran() throws NotYourTurnException, GameException{
        /**     Szenario: Player1 Alice meldet sich zuerst an und darf somit beginnen, aber Bob beginnt hier
         *           1   2   3   4   5   6
         *      A   [/] [/] [/] [/] [/] [/]
         *      B   [/] [/] [/] [/] [/] [/]
         *      C   [/] [/] [/] [/] [/] [/]
         *      D   [/] [/] [/] [/] [/] [/]
         *      E   [/] [/] [/] [/] [/] [/]
         *      F   [/] [/] [/] [/] [/] [/]
         * */
        Position a1 = Position.A1;
        Position a2 = Position.A2;

        GameEngine game = getMemory();
        game.assignPlayer(ALICE, player1);
        game.assignPlayer(BOB, player2);

        //NotYourTurnException
        game.flip(player2, a1, a2);
    }

    public void SpielerGibtAuf() throws NotYourTurnException, GameException{
        /**     Szenario: Bob gibt auf, weil er keine Chancen mehr sieht zu gewinnen
         *           1   2   3   4   5   6
         *      A   [/] [/] [/] [/] [/] [/]
         *      B   [/] [/] [/] [/] [/] [/]
         *      C   [/] [/] [/] [/] [/] [/]
         *      D   [/] [/] [/] [/] [/] [/]
         *      E   [/] [/] [/] [/] [/] [/]
         *      F   [/] [/] [/] [/] [/] [/]
         * */

        Position a1 = Position.A1;
        Position a2 = Position.A2;

        DevGameEngine game = getDevMemory();

        game.assignPlayer(ALICE, player1);
        game.assignPlayer(BOB, player2);

        game.surrender(player2);
        Assert.assertEquals(true,game.hasLost(player2));
    }

    public void SpielerGewinnt() throws NotYourTurnException, GameException{
        /**     Szenario: Alice gewinnt (Im ersten Zug weil das Feld manipuliert worden ist)
         *           1   2   3   4   5   6
         *      A    x   x   x   x   x   x
         *      B    x  [2]  x   x   x   x
         *      C    x   x   x   x   x   x
         *      D    x   x   x   x   x   x
         *      E    x   x   x  [2]  x   x
         *      F    x   x   x   x   x   x
         * */

        Card dummy2 = new CardImplementation(2);
        Card dummyBlanko = new CardImplementation(7);

        Card[][] testFeld = new Card[][] {
                {dummyBlanko,dummyBlanko,dummyBlanko,dummyBlanko,dummyBlanko,dummyBlanko},
                {dummyBlanko,dummy2,dummyBlanko,dummyBlanko,dummyBlanko,dummyBlanko},
                {dummyBlanko,dummyBlanko,dummyBlanko,dummyBlanko,dummyBlanko,dummyBlanko},
                {dummyBlanko,dummyBlanko,dummyBlanko,dummyBlanko,dummyBlanko,dummyBlanko},
                {dummyBlanko,dummyBlanko,dummyBlanko,dummyBlanko,dummyBlanko,dummyBlanko},
                {dummyBlanko,dummyBlanko,dummyBlanko,dummy2,dummyBlanko,dummyBlanko}
        };

        Position b2 = Position.B2;
        Position e4 = Position.E4;

        DevGameEngine game = getDevMemory();
        game.setBoard(testFeld);
        game.assignPlayer(ALICE, player1);
        game.assignPlayer(BOB, player2);

        game.flip(player1, b2, e4);

        Assert.assertEquals(true,game.hasWon(player2));
    }


}
