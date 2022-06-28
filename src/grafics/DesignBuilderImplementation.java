package grafics;

public class DesignBuilderImplementation implements DesignBuilder{
    //Das Muster aus dem der Rahmen generiert wird
    private final static String[] PATTERN={
            "\\  \\__\\  \\__\\  \\__\\  \\__\\  \\__\\  \\__\\  \\__\\  \\__\\  \\__\\  \\__\\  \\__\\  \\",
            " \\__\\  \\__\\  \\__\\  \\__\\  \\__\\  \\__\\  \\__\\  \\__\\  \\__\\  \\__\\  \\__\\  \\__",
            "__\\  \\__\\  \\__\\  \\__\\  \\__\\  \\__\\  \\__\\  \\__\\  \\__\\  \\__\\  \\__\\  \\__\\ "
    };
    private final static int PATTERNLINES = 3;
    private static final String firstline = PATTERN[0];
    private final static int LINELENGTH = firstline.length(); // =70; gilt für alle 3 Lines.

    private int borderIterator=0;

    //Leerzeichenrand PADDING und Rahmen: BORDER; MESSAGEBOX: Maximale Länge einer Zeichenkette in der Box
    private static final int PADDING=4;
    private static final int BORDER =4;
    private final static int MESSAGEBOX = LINELENGTH - 2 * PADDING - 2 * BORDER; // =54


    /************************************************************************************************************
     ***                                        Pattern-Methods                                               ***
     ***********************************************************************************************************/

    //Gebe das Muster auf dem das Design beruht aus
    private String pattern(){
        String pattern;
        StringBuilder pbuilder = new StringBuilder();
        for(int i = 0; i< PATTERNLINES; i++) {
            pbuilder.append(PATTERN[i]);
            pbuilder.append("\n");
        }
        pattern = pbuilder.toString();
        return pattern;
    }

    //Gebe einen String bestehend aus X-mal dem Muster aus
    private String patternXtimes(int x){
        String multipattern;
        StringBuilder pbuilder = new StringBuilder();
        for(int i=0; i<x; i++){
            pbuilder.append(this.pattern());
        }
        multipattern = pbuilder.toString();
        return multipattern;
    }

    //Gibt die X-te Linie des Musters aus
    private String patternLine(int x){
        String pattern = PATTERN[x];
        return pattern;
    }

    private void iterateBorder(){
        if(borderIterator == 0) this.borderIterator++;
        else if(borderIterator == 1) this.borderIterator++;
        else if(borderIterator == 2) this.borderIterator=0;
    }

    private String borderGenerator(){
        if(borderIterator == 2) return this.patternLine(2);
        else if(borderIterator == 1) return this.patternLine(1);
        return this.patternLine(0);
    }



    //Gebe eine Nachricht mit
    private String returnPaddedMessage(String message, int mlength) {
        int paddedMLength = (MESSAGEBOX - mlength) / 2;

        StringBuilder pm = new StringBuilder();
        int newPadding = paddedMLength;

        //String.format is confusing me
        for(int q=0; q<newPadding; q++){
            pm.append(" ");
        }
        //pm.append(String.format("%1" + newPadding + "s", spacePadding).replace(" ","0"));
        pm.append(message);
        for(int q=0; q<newPadding; q++){
            pm.append(" ");
        }
        if((mlength / 2) != 0) pm.append(" ");
        return pm.toString();
    }

    private String appendLeftBorder(int line) {
        StringBuilder border = new StringBuilder();
        border.append(borderGenerator().substring(0, BORDER));
        for(int i=0; i<PADDING; i++){border.append(" ");}
        return border.toString();
    }

    private String appendRightBorder(int line) {
        StringBuilder border = new StringBuilder();
        for(int i=0; i<PADDING; i++){border.append(" ");}
        border.append(borderGenerator().substring(LINELENGTH-BORDER,LINELENGTH));
        return border.toString();
    }

    private String returnBorderedMessage(String message){
        message = message.replace("\n", " ");
        StringBuilder submessagebuilder = new StringBuilder();
        submessagebuilder.append(this.patternLine(0));
        submessagebuilder.append("\n");

        int mlength = message.length();
        int current = 0;


        //LINELENGTH - PADDING - MARGIN = 54
        while(mlength >= MESSAGEBOX){
            this.iterateBorder();
            submessagebuilder.append(this.appendLeftBorder(borderIterator));
            submessagebuilder.append(message.substring(current, current + MESSAGEBOX));
            if( (((current + MESSAGEBOX) - current) %2) != 0) submessagebuilder.append(" ");
            submessagebuilder.append(this.appendRightBorder(borderIterator));
            submessagebuilder.append("\n");
            current += 54;
            mlength -= 54;

        }
        this.iterateBorder();
        submessagebuilder.append(this.appendLeftBorder(borderIterator));
        submessagebuilder.append(this.returnPaddedMessage(message.substring(current,message.length()),(message.length()-current)));
        submessagebuilder.append(this.appendRightBorder(borderIterator));
        submessagebuilder.append("\n");
        this.iterateBorder();
        submessagebuilder.append(this.borderGenerator());
        return submessagebuilder.toString();
    }

    /************************************************************************************************************
     ***                                        Public-Method                                                 ***
     ***********************************************************************************************************/

    @Override
    public String returnBorderedOutput(String input) {
        return this.returnBorderedMessage(input);
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
