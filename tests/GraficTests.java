import Exceptions.MessageOutOfBoundsException;
import UI.DesignBuilder;
import UI.MemoryGui;
import org.junit.Test;

public class GraficTests {
    public final String playerName = "granti";
    DesignBuilder dia = new DesignBuilder();
    MemoryGui gui = new MemoryGui(playerName,System.in, System.out);

    //Test für die ausgeschmückten Dialoge im Terminal


    @Test
    public void test1() throws MessageOutOfBoundsException{
        System.out.println(dia.returnBorderedMessage("ALSNFLAKSNFLKANSFLKNALKSFNKLASNFKLASNFKLANSFKLANSLKFNASFNKALSNFdddLKASNFLKANSFLKskladnflsdknglksdnglksdnglksdnglknsdkgnsdklgnlskdgnlksdgnlksdnglksdnglsdnglksdngls"));
    }
}
