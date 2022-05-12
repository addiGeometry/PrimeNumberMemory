import UI.DesignBuilder;
import UI.DesignBuilderImplementation;
import UI.MemoryGui;
import org.junit.Test;

public class GraficTests {
    public final String playerName = "granti";
    DesignBuilderImplementation dia = new DesignBuilderImplementation();
    MemoryGui gui = new MemoryGui(playerName,System.in, System.out);
    DesignBuilder desiBuild = dia;
    //Test für die ausgeschmückten Dialoge im Terminal


   @Test
    public void test1(){
       System.out.println(desiBuild.returnBorderedOutput("In der Bibel steht, dass Mose zu den Juden gehörte, die im Alten Ägypten lebten. Dort mussten sie als Sklaven für die Ägypter arbeiten. Der ägyptische König, der Pharao, fand damals, dass es zu viele Juden in seinem Reich gab. Darum ließ er alle Neugeborenen der Juden töten. Die Bibel erzählt, dass Mose als Säugling in einen Korb gelegt und vom Fluss Nil fortgetragen wurde.\n" +
               "\n" +
               "Die Tochter des Pharaos fand ihn und nahm ihn mit. So kam es, dass er am Königshof aufwuchs. Doch bald musste er aus Ägypten fliehen, weil er in seiner Wut einen Aufseher der Sklaven getötet hatte. Dort hütete Ziegen in der Wüste. Die Bibel erzählt, dass ihm da aus einem brennenden Dornbusch eine Stimme auftrug, die Juden aus Ägypten herauszuführen.\n" +
               "\n" +
               "So kehrte Mose nach Ägypten zurück. Der Pharao wollte die Juden zunächst nicht gehen lassen. Doch Gott, so erzählt die Bibel, ließ zehn Plagen über das ägyptische Volk hereinbrechen: Zum Beispiel verwandelte sich das Wasser in Blut, es gab eine Heuschreckenplage und die ältesten Kinder der Ägypter starben. "));
   }
}
