import java.io.*;

public class MemoryGui{
    //Gui Options
    private static final String EXIT = "exit";
    private static final String CONNECT = "connect";
    private static final String OPEN = "open";
    private static final String PRINT_BOARD = "print board";
    private static final String FLIP = "flip";
    private static final String RULES = "rules";
    private static final String SURRENDER = "surrender";




    //Stream Logic
    private final PrintStream outStream;
    private final BufferedReader inBufferedReader;

    //Game Logic
    private final String playerName;

    //Pattern
    private DesignBuilder designer;


    public MemoryGui(String pN, InputStream in, PrintStream out){
        this.playerName = pN;
        this.outStream = out;
        this.inBufferedReader = new BufferedReader(new InputStreamReader(in));
        /* More awesome stuff to come! **/
        designer = new DesignBuilder();
    }

    public void doCommandLoop(){
        boolean again = true;

        while(again){
            String cmdLineString = null;
            try{
                /*Read user Input. Kleine Anmerkung: Der Buffered Reader liest aus einem Character-Input-Stream.
                Er (buffered) sammelt die ankommenden Characters, in diesem Fall am Input-Stream
                und speichert sie zwischen. Die methode Read Line wartet auf einen Zeilenumbruch und liest dann ein**/
                cmdLineString = inBufferedReader.readLine();

                if(cmdLineString == null) break;

                //Trim white spaces on input
                cmdLineString.trim();

                //Jetzt für Aufrufe mit Argumenten:
                int spaceIndex = cmdLineString.indexOf(" ");
                spaceIndex = spaceIndex != -1 ? spaceIndex : cmdLineString.length();

                String commandString = cmdLineString.substring(0, spaceIndex);

                //Paramterbehandlung:
                String parameterSting = cmdLineString.substring(0, spaceIndex);
                parameterSting = parameterSting.trim();

                //Command Loop
                switch(commandString){
                    case "q":
                    case EXIT:
                        this.doExit();
                        break;
                    case CONNECT:
                        this.doConnect(parameterSting);
                        break;
                    case OPEN:
                        this.doOpen();
                        break;
                    case FLIP:
                        this.doFLip(parameterSting);
                        break;
                    case PRINT_BOARD:
                        this.doPrint();
                        break;
                    case RULES:
                        this.doPrintRules();
                        break;
                    case SURRENDER:
                        this.doSurrender();
                        break;
                    default:
                        this.outStream.println("unknown command:" + cmdLineString);
                        this.printInstructions();
                        break;
                }
            }
            catch(Exception e){}
        }
    }



    /************************************************************************************************************
     ***                                            UI Implementation                                         ***
     ***********************************************************************************************************/
    private void doPrint() {
    }

    private void doFLip(String tiles) {
    }

    private void doConnect(String connectadress) {
    }

    private void doOpen(){
    }
    private void doSurrender() {
    }
    private void doPrintRules() {
    this.outStream.println(designer.patternLine(0));
    this.outStream.println("the rules of the game are quite simple");
    this.outStream.println(designer.patternLine(2));

    }

    private void doExit(){
        this.outStream.println("Bye " + playerName);
        System.exit(0);
    }

    public void printInstructions(){
        StringBuilder m = new StringBuilder();
        m.append("\n");
        m.append("\n");
        m.append("valid commands: ");
        m.append("\n");
        m.append(RULES);
        m.append(".. print the rules of the game");
        m.append("\n");
        m.append(CONNECT);
        m.append(".. connect as tcp client");
        m.append("\n");
        m.append(OPEN);
        m.append(".. open port to become tcp server");
        m.append("\n");
        m.append(PRINT_BOARD);
        m.append(".. print game table");
        m.append("\n");
        m.append(FLIP);
        m.append(".. flip two cards");
        m.append("\n");
        m.append(SURRENDER);
        m.append(".. also an honorable way to finish your game");
        m.append("\n");
        m.append(EXIT);
        m.append(".. exit");

        this.outStream.println(m.toString());
    }
}

/************************************************************************************************************
 ***                                        OPTICS Implementation                                         ***
 ***********************************************************************************************************/