import org.junit.Test;

public class GraficTests {
    DialogueBuilder dia = new DialogueBuilder("Hellow");

    @Test
    public void test1(){
        System.out.println(dia.welcomeMessage("Hey There"));

    }
}
