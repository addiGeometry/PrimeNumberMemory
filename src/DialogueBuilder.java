public class DialogueBuilder {
    private final static String[] PATTERN={
            "\\  \\__\\  \\__\\  \\__\\  \\__\\  \\__\\  \\__\\  \\__\\  \\__\\  \\__\\  \\__\\  \\__\\  \\",
            " \\__\\  \\__\\  \\__\\  \\__\\  \\__\\  \\__\\  \\__\\  \\__\\  \\__\\  \\__\\  \\__\\  \\__",
            "__\\  \\__\\  \\__\\  \\__\\  \\__\\  \\__\\  \\__\\  \\__\\  \\__\\  \\__\\  \\__\\  \\__\\ "
    };
    private final static int PLINES = 3;
    private static final int PADDING=5;
    private final static int LINELENGTH = PATTERN[0].length();

    private final int dialogueLength;





    public DialogueBuilder(String inputMessage){
        dialogueLength = inputMessage.length();




    }
    /************************************************************************************************************
     ***                                        Pattern-Getter                                                ***
     ***********************************************************************************************************/
    public String printPattern(){
        String pattern;
        StringBuilder pbuilder = new StringBuilder();
        for(int i=0; i<PLINES; i++) {
            pbuilder.append(PATTERN[i]);
            pbuilder.append("\n");
        }
        pattern = pbuilder.toString();
        return pattern;
    }

    public String printPatternXtimes(int x){
        String pattern;
        StringBuilder pbuilder = new StringBuilder();
        for(int i=0; i<x; i++){
            pbuilder.append(this.printPattern());
        }
        pattern = pbuilder.toString();
        return pattern;
    }

    //Returns single line of the Pattern
    public String printPatternLine(int x){
        String pattern = PATTERN[x];
        return pattern;
    }

    public String welcomeMessage(String message) throws ArrayIndexOutOfBoundsException{
        String pattern;
        StringBuilder pbuilder = new StringBuilder();
        pbuilder.append(this.printPatternLine(0));
        pbuilder.append("\n");

        //Edit the message in
        String rightpad = String.format("%-" + PADDING + "s", message);
        String leftanrightpad = String.format("%" + PADDING + "s", message);


        //ATTENTION this only works for Strings shorter than LINELENGHT!!
        int corners = (LINELENGTH - leftanrightpad.length()) / 2;
        String ledge = PATTERN[1].substring(0, corners);
        String redge = PATTERN[1].substring(corners, 0);

        pbuilder.append(ledge + message + redge);
        pbuilder.append("\n");

        pbuilder.append(this.printPatternLine(2));
        pbuilder.append("\n");

        pattern = pbuilder.toString();
        return pattern;
    }

    /*
        __\  \__\  \__\  \__\  \__\  \__\  \__\  \__\  \__\  \__\  \__\  \__\
        \  \__\  \__\  \__\  \__\  \__\  \__\  \__\  \__\  \__\  \__\  \__\  \
         \__\  \__\  \__\  \__\  \__\  \__\  \__\  \__\  \__\  \__\  \__\  \__
        __\  \__\  \__\  \__\  \__\  \__\  \__\  \__\  \__\  \__\  \__\  \__\
        \  \__\  \__\  \__\  \__\  \__\  \__\  \__\  \__\  \__\  \__\  \__\  \
         \__\  \__\  \__\  \__\  \__\  \__\  \__\  \__\  \__\  \__\  \__\  \__
        __\  \__\  \__\  \__\  \__\  \__\  \__\  \__\  \__\  \__\  \__\  \__\
        \  \__\  \__\  \__\  \__\  \__\  \__\  \__\  \__\  \__\  \__\  \__\  \
         \__\  \__\  \__\  \__\  \__\  \__\  \__\  \__\  \__\  \__\  \__\  \__
        __\  \__\  \__\  \__\  \__\  \__\  \__\  \__\  \__\  \__\  \__\  \__\
        \  \__\  \__\  \__\  \__\  \__\  \__\  \__\  \__\  \__\  \__\  \__\  \
         \__\  \__\  \__\  \__\  \__\  \__\  \__\  \__\  \__\  \__\  \__\  \__
        __\  \__\  \__\  \__\  \__\  \__\  \__\  \__\  \__\  \__\  \__\  \__\
        \  \__\  \__\  \__\  \__\  \__\  \__\  \__\  \__\  \__\  \__\  \__\  \
         \__\  \__\  \__\  \__\  \__\  \__\  \__\  \__\  \__\  \__\  \__\  \__
        __\  \__\  \__\  \__\  \__\  \__\  \__\  \__\  \__\  \__\  \__\  \__\  **/
}
