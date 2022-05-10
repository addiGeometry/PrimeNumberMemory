import Exceptions.MessageOutOfBoundsException;

public class DesignBuilder {
    private final static String[] PATTERN={
            "\\  \\__\\  \\__\\  \\__\\  \\__\\  \\__\\  \\__\\  \\__\\  \\__\\  \\__\\  \\__\\  \\__\\  \\",
            " \\__\\  \\__\\  \\__\\  \\__\\  \\__\\  \\__\\  \\__\\  \\__\\  \\__\\  \\__\\  \\__\\  \\__",
            "__\\  \\__\\  \\__\\  \\__\\  \\__\\  \\__\\  \\__\\  \\__\\  \\__\\  \\__\\  \\__\\  \\__\\ "
    };
    private final static int PLINES = 3;
    private static final int PADDING=4;
    private static final int MARGIN=4;
    private static final String firstline = PATTERN[0];
    private final static int LINELENGTH = firstline.length();

    private final static int MESSAGEBOX = LINELENGTH - 2 * PADDING - 2 * MARGIN;

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

    //Gebe das Muster auf dem das Design beruht aus
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

    //Gebe einen String bestehend aus X-mal dem Muster aus
    public String patternXtimes(int x){
        String multipattern;
        StringBuilder pbuilder = new StringBuilder();
        for(int i=0; i<x; i++){
            pbuilder.append(this.pattern());
        }
        multipattern = pbuilder.toString();
        return multipattern;
    }

    //Gibt die X-te Linie des Musters aus
    public String patternLine(int x){
        String pattern = PATTERN[x];
        return pattern;
    }

    public String borderIterator(int i){
        //I immer mindestens 1
        if(i%3 == 0)    return this.patternLine(2);
        if(i%2 == 0)    return this.patternLine(1);
        return this.patternLine(0);
    }


    //Gebe eine Nachricht mit
    public String returnPaddedMessage(String message) {

        StringBuilder pm = new StringBuilder();
        int newPadding = (MESSAGEBOX - message.length()) / 2;

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

    public String appendLeftBorder(int line) {
        StringBuilder border = new StringBuilder();
        border.append(borderIterator(line).substring(0,MARGIN-1));
        for(int i=0; i<PADDING; i++){border.append(" ");}
        return border.toString();
    }

    public String appendRightBorder(int line) {
        StringBuilder border = new StringBuilder();
        for(int i=0; i<PADDING; i++){border.append(" ");}
        border.append(borderIterator(line).substring(LINELENGTH-MARGIN-1,LINELENGTH-1));
        return border.toString();
    }

    public String returnBorderedMessage(String message){
        StringBuilder submessagebuilder = new StringBuilder();
        submessagebuilder.append(this.patternLine(0));
        submessagebuilder.append("\n");

        int mlength = message.length();
        int current = 0;
        int borderIterator=1;


        //LINELENGTH - PADDING - MARGIN = 54
        while(mlength > MESSAGEBOX){
            borderIterator++;
            submessagebuilder.append(this.appendLeftBorder(borderIterator));
            submessagebuilder.append(this.returnPaddedMessage(message.substring(current, current + MESSAGEBOX-1)));
            submessagebuilder.append(this.appendRightBorder(borderIterator));
            submessagebuilder.append("\n");
            current += 54;
            mlength -= 54;

        }
        borderIterator++;
        submessagebuilder.append(this.appendLeftBorder(borderIterator));
        submessagebuilder.append(this.returnPaddedMessage(message.substring(current, message.length()-1)));
        submessagebuilder.append(this.appendRightBorder(borderIterator));
        submessagebuilder.append("\n");
        submessagebuilder.append(this.borderIterator(borderIterator));
        return submessagebuilder.toString();
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
