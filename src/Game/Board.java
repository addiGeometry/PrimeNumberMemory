package Game;

public interface Board{

    /**
     * Gebe das Spielbrett mit den übrigen Karten zurück
     * @return Aktuelles Spielfeld
     */
    public Card[][] getCurrentBoard();

    /**
     * @return Wahr, wenn alle Paare noch im Spiel sind
     */
    public boolean isFull();

    /**
     * @return Wahr, wenn keine Paare mehr im Spiel sind
     */
    public boolean isEmpty();

    /**
     * Entferne ein (gefundenes) Paar aus dem Spiel
     * @param x Koordinaten Karte 1
     * @param y Koordinaten Karte 2
     * @return  Gibt den Wert der Karte zurück (Damit ein Kartenstapel erzeugt werden kann)
     */
    public int removeCards(Coordinate x, Coordinate y);
}
