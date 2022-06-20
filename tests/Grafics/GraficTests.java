package Grafics;

import Game.Card;
import Game.Coordinates;
import Game.Memory;
import Game.PlayerLogic;
import org.junit.Test;

public class GraficTests {
    /**
    Test für "ausgeschmückte Terminal-Dialoge
     */

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
    public void test2(){
       DesignBuilder desiBuild = new DesignBuilderImplementation();
       String feld =    "                                                      "     +
                        "                   1   2   3   4   5   6              \n"   +
                        "             A    x   x  [/] [/] [/] [/]             \n"    +
                        "             B   [/] [/] [/] [/] [/] [/]             \n"    +
                        "             C   [/] [/] [/] [/] [/] [/]             \n"    +
                        "             D   [/] [/] [/] [/] [/] [/]             \n"    +
                        "             E   [/] [/] [/] [/] [/] [/]             \n"    +
                        "             F   [/] [/] [/] [/] [/] [/]             ";

       System.out.println(desiBuild.returnBorderedOutput(feld));
   }

    @Test
    public void boardOnStart() {
        Memory memory = new Memory(PlayerLogic.P1, PlayerLogic.P2, "test");

        BoardRenderer render = new BoardRendererImplementation();
        DesignBuilder desiBuild = new DesignBuilderImplementation();

        System.out.println(desiBuild.returnBorderedOutput(render.renderOpenBoard(memory.getCurrentBoard(), Coordinates.A1, Coordinates.B1)));
    }

    @Test
    public void boardTwoCardsFlipped() {
    }

    @Test
    public void boardTwoCardsMissing() {
    }

    @Test
    public void boardMoreThenTwoCardsMissing() {
    }

    @Test
    public void boardTwoRandomCardsFlipped() {
    }

}
