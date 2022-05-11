package Entities;

public class CardImplementation implements Card {
    private int value;
    private boolean active;

    /** Eine Karte wird druch ihren Wert identifiziert und dargstellt. Eine Karte ist entweder aktiv oder inaktiv (nicht aktiv) */
    public CardImplementation(int cValue){
        this.value = cValue;
        this.active = true;
    }
    private int returnValue(){
        return this.value;
    }

    @Override
    public int getValue() {
        return this.value;
    }

    @Override
    public void setCard(int cValue) {
        this.value = cValue;
    }
}

