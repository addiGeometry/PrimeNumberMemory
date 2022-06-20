package Game;

public interface Card{
    /** Gebe eine Karte zurück */
    int getValue();

    /**
     * @return Ist die Karte aktiv?
     */
    boolean isActive();

    void deactivate();

    /** Setze den Wert einer Karte */
    void setCard(int cValue);

    /** Drehe die Karte um */
    boolean flipCard();

    /** Überprüfe, ob die Karte aufgedeckt (true) oder zugedeckt (false) ist.*/
    boolean aufgedeckt();
}
