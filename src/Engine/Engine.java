package Engine;

import Game.*;
import Menu.*;

/**
 * Engine absztrakt osztaly.
 * A jatek egyes reszeinek megvalositasara szolgal.
 */
abstract public class Engine implements MenuNames {
    protected static Board board;
    protected static String type;
    protected static String date;
    protected Menu menu;
    /**
     * Megadja, hogy kileptek-e a menubol.
     * @return Igaz, ha a menubol kileptek, hamis ha nem.
     */
    public boolean isQuit() {
        return menu.isQuit();
    }
    /**
     * A kovetkezo menu ID-jat adja vissza.
     * @return Kovetkezo menu ID-ja.
     */
    public int getNextID() {
        return menu.getNextMenuID();
    }
    /**
     * Megadja az eltarolt menut.
     * @return Eltarolt menu.
     */
    public Menu getMenu() {
        return menu;
    }
}
