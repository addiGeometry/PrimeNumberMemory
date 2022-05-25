package Game;

class CardImplementation implements Card {
    private int value;
    private boolean faceup;

    /** Eine Karte wird druch ihren Wert identifiziert und dargstellt. Eine Karte ist entweder aufgedeckt (faceup) oder zugedeckt (faceup=false)
     *  Standardmäßig werden die Karten zugedeckt erzeugt (Memory-Karten werden umgedreht ausgeteilt) */

    public CardImplementation(int cValue){
        this.value = cValue;
        this.faceup = false;
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

    @Override
    public boolean aufgedeckt() {
        return this.faceup;
    }

    @Override
    public boolean flipCard() {
        return false;
    }
}

