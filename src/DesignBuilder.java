public class DesignBuilder {
    private final static String[] PATTERN={
            "\\  \\__\\  \\__\\  \\__\\  \\__\\  \\__\\  \\__\\  \\__\\  \\__\\  \\__\\  \\__\\  \\__\\  \\",
            " \\__\\  \\__\\  \\__\\  \\__\\  \\__\\  \\__\\  \\__\\  \\__\\  \\__\\  \\__\\  \\__\\  \\__",
            "__\\  \\__\\  \\__\\  \\__\\  \\__\\  \\__\\  \\__\\  \\__\\  \\__\\  \\__\\  \\__\\  \\__\\ "
    };
    private final static int PLINES = 3;
    private static final int PADDING=4;
    private static final String firstline = PATTERN[0];
    private final static int LINELENGTH = firstline.length();


    public DesignBuilder(){
    }
    /************************************************************************************************************
     ***                                     Getters and Setters                                              ***
     ***********************************************************************************************************/

    public int getLinelength() {
        return LINELENGTH;
    }

    public String getfirstline() {
        return firstline;
    }



    /************************************************************************************************************
     ***                                        Pattern-Methods                                               ***
     ***********************************************************************************************************/

    public String pattern(){
        String pattern;
        StringBuilder pbuilder = new StringBuilder();
        for(int i=0; i<PLINES; i++) {
            pbuilder.append(PATTERN[i]);
            pbuilder.append("\n");
        }
        pattern = pbuilder.toString();
        return pattern;
    }

    public String patternXtimes(int x){
        String pattern;
        StringBuilder pbuilder = new StringBuilder();
        for(int i=0; i<x; i++){
            pbuilder.append(this.pattern());
        }
        pattern = pbuilder.toString();
        return pattern;
    }

    //Returns single line of the Pattern
    public String patternLine(int x){
        String pattern = PATTERN[x];
        return pattern;
    }

    public String returnPaddedMessage(String message) throws MessageOutOfBoundsException{
        if(message.length() > 70){
            throw new MessageOutOfBoundsException("your message was too long for a line");
        }

        StringBuilder pm = new StringBuilder();
        int newPadding = (LINELENGTH - message.length()) / 2;
        String spacePadding="";

        //String.format is confusing me
        for(int q=0; q<newPadding; q++){
            pm.append(" ");
        }
        //pm.append(String.format("%1" + newPadding + "s", spacePadding).replace(" ","0"));
        pm.append(message);
        for(int q=0; q<newPadding; q++){
            pm.append(" ");
        }
        return pm.toString();
    }

    public String welcomeMessage(String message) throws ArrayIndexOutOfBoundsException{
        String pattern;
        StringBuilder pbuilder = new StringBuilder();
        pbuilder.append(this.patternLine(0));
        pbuilder.append("\n");

        //Edit the message in
        String rightpad = String.format("%-1" + PADDING + "s", message).replace(" ", "0");
        String leftanrightpad = String.format("%1" + PADDING + "s", rightpad).replace(" ", "0");
        System.out.println(rightpad);
        System.out.println(leftanrightpad);

        //ATTENTION this only works for Strings shorter than LINELENGHT!!
        /* int corners = (LINELENGTH - leftanrightpad.length()) / 2;
        String ledge = PATTERN[1].substring(0, corners);
        String redge = PATTERN[1].substring(corners, 0);

        pbuilder.append(ledge + message + redge);
        pbuilder.append("\n");

        pbuilder.append(this.printPatternLine(2));
        pbuilder.append("\n"); **/

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
