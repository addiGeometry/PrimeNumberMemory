package game;

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

    //@ignoreEfficiency I know right pretty stupid code :) - but it does what it should do
    @Override
    public Coordinates reconstructCard(int v, int h) throws GameException {
        if(v==0 && h==0) return Coordinates.A1;
        else if(v==1 && h==0) return Coordinates.B1;
        else if(v==2 && h==0) return Coordinates.C1;
        else if(v==3 && h==0) return Coordinates.D1;
        else if(v==4 && h==0) return Coordinates.E1;
        else if(v==5 && h==0) return Coordinates.F1;
        else if(v==0 && h==1) return Coordinates.A2;
        else if(v==1 && h==1) return Coordinates.B2;
        else if(v==2 && h==1) return Coordinates.C2;
        else if(v==3 && h==1) return Coordinates.D2;
        else if(v==4 && h==1) return Coordinates.E2;
        else if(v==5 && h==1) return Coordinates.F2;
        else if(v==0 && h==2) return Coordinates.A3;
        else if(v==1 && h==2) return Coordinates.B3;
        else if(v==2 && h==2) return Coordinates.C3;
        else if(v==3 && h==2) return Coordinates.D3;
        else if(v==4 && h==2) return Coordinates.E3;
        else if(v==5 && h==2) return Coordinates.F3;
        else if(v==0 && h==3) return Coordinates.A4;
        else if(v==1 && h==3) return Coordinates.B4;
        else if(v==2 && h==3) return Coordinates.C4;
        else if(v==3 && h==3) return Coordinates.D4;
        else if(v==4 && h==3) return Coordinates.E4;
        else if(v==5 && h==3) return Coordinates.F4;
        else if(v==0 && h==4) return Coordinates.A5;
        else if(v==1 && h==4) return Coordinates.B5;
        else if(v==2 && h==4) return Coordinates.C5;
        else if(v==3 && h==4) return Coordinates.D5;
        else if(v==4 && h==4) return Coordinates.E5;
        else if(v==5 && h==4) return Coordinates.F5;
        else if(v==0 && h==5) return Coordinates.A6;
        else if(v==1 && h==5) return Coordinates.B6;
        else if(v==2 && h==5) return Coordinates.C6;
        else if(v==3 && h==5) return Coordinates.D6;
        else if(v==4 && h==5) return Coordinates.E6;
        else if(v==5 && h==5) return Coordinates.F6;
        else throw new GameException("Illegal use");
    }
}
