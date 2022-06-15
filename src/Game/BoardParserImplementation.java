package Game;

public class BoardParserImplementation implements BoardParser{


    private int parse(Coordinates c, Direction d) {
        int[] parsed = new int[2];
        switch (c) {
            case A1 -> {
                parsed[0]=0;
                parsed[1]=0;
            }
            case A2 -> {
                parsed[0]=0;
                parsed[1]=1;
            }
            case A3 -> {
                parsed[0]=0;
                parsed[1]=2;
            }
            case A4 -> {
                parsed[0]=0;
                parsed[1]=3;
            }
            case A5 -> {
                parsed[0]=0;
                parsed[1]=4;
            }
            case A6 -> {
                parsed[0]=0;
                parsed[1]=5;
            }
            case B1 -> {
                parsed[0]=1;
                parsed[1]=0;
            }
            case B2 -> {
                parsed[0]=1;
                parsed[1]=1;
            }
            case B3 -> {
                parsed[0]=1;
                parsed[1]=2;
            }
            case B4 -> {
                parsed[0]=1;
                parsed[1]=3;
            }
            case B5 -> {
                parsed[0]=1;
                parsed[1]=4;
            }
            case B6 -> {
                parsed[0]=1;
                parsed[1]=5;
            }
            case C1 -> {
                parsed[0]=2;
                parsed[1]=0;
            }
            case C2 -> {
                parsed[0]=2;
                parsed[1]=1;
            }
            case C3 -> {
                parsed[0]=2;
                parsed[1]=2;
            }
            case C4 -> {
                parsed[0]=2;
                parsed[1]=3;
            }
            case C5 -> {
                parsed[0]=2;
                parsed[1]=4;
            }
            case C6 -> {
                parsed[0]=2;
                parsed[1]=5;
            }
            case D1 -> {
                parsed[0]=3;
                parsed[1]=0;
            }
            case D2 -> {
                parsed[0]=3;
                parsed[1]=1;
            }
            case D3 -> {
                parsed[0]=3;
                parsed[1]=2;
            }
            case D4 -> {
                parsed[0]=3;
                parsed[1]=3;
            }
            case D5 -> {
                parsed[0]=3;
                parsed[1]=4;
            }
            case D6 -> {
                parsed[0]=3;
                parsed[1]=5;
            }
            case E1 -> {
                parsed[0]=4;
                parsed[1]=0;
            }
            case E2 -> {
                parsed[0]=4;
                parsed[1]=1;
            }
            case E3 -> {
                parsed[0]=4;
                parsed[1]=2;
            }
            case E4 -> {
                parsed[0]=4;
                parsed[1]=3;
            }
            case E5 -> {
                parsed[0]=4;
                parsed[1]=4;
            }
            case E6 -> {
                parsed[0]=4;
                parsed[1]=5;
            }
            case F1 -> {
                parsed[0]=5;
                parsed[1]=0;
            }
            case F2 -> {
                parsed[0]=5;
                parsed[1]=1;
            }
            case F3 -> {
                parsed[0]=5;
                parsed[1]=2;
            }
            case F4 -> {
                parsed[0]=5;
                parsed[1]=3;
            }
            case F5 -> {
                parsed[0]=5;
                parsed[1]=4;
            }
            case F6 -> {
                parsed[0]=5;
                parsed[1]=5;
            }
        }
        if(d == Direction.vert) return parsed[0];
        else return parsed[1];
    }

    @Override
    public int parseLetterCoord(Coordinates c) {
        return this.parse(c,Direction.vert);
    }

    @Override
    public int parseNumberCoord(Coordinates c) {
        return this.parse(c,Direction.hori);
    }
}
