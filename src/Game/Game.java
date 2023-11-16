package Game;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Jatek osztaly.
 * Ez a fo adatszerkezet a jatek megvaloitasaban.
 * Ebben az osztalyban van osszefogva
 * a jatek tarolasa hasznalt adatszerkezet és
 * az azt szabalyozo logika is. Emellet serializalhato,
 * ami nagyban megkonnyiti a gyors egyszeri file-ba irast es betoltest.
 */
public class Game implements java.io.Serializable {
    public final Board board;
    private final Logic logic;
    public final String type;
    private String date;
    /**
     * Konstruktor, egy jatek letrehozasa.
     * @param board A jatek soran hasznalt tabla.
     * @param type A jatekmod, ervenyes erteke: "PVP" vagy "AI"
     * @param date A jatek datuma, visszatolteskor van jelentosege.
     */
    public Game(Board board, String type, String date) {
        this.board = new Board(board);
        if (board.getLastRow() != -1)
            logic = new Logic(board.getCellValue(board.getLastRow(), board.getLastColumn()));
        else logic = new Logic(GameNames.PLAYER2);
        this.type = type;
        this.date = date;
    }
    /**
     * Megadja a jatek aktualis datumat.
     * @return A jatek datuma.
     */
    public String getDate() {
        return date;
    }
    /**
     * Frissiti a datumot az aktualis datumra, masodpercre pontosan.
     */
    public void updateDate() {
        this.date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
    /**
     * Megadja, hogy nyert-e a legutobb lepett jatekos.
     * @return Igaz, ha a jatekos nyert, hamis, ha nem.
     */
    public boolean isPlayerWin() {
        return logic.isPlayerWin(board);
    }
    /**
     * Megadja a jatek kimenetelet.
     * @return Igaz, jatekos nyerese, dontetlen eseten, egyebkent hamis.
     */
    public int getWinner() {
        return logic.getWinner();
    }
    /**
     * Megadja a soron kovetkezo jatekos ID-jat.
     * @return kovetkezo jatekos ID-ja.
     */
    public int getNextPlayerID() {
        return logic.getNextPlayerID();
    }
    /**
     * A jatek konstruktorapan megadott paraqmeterek Stringe alakitasa.
     * @return A 'board.n' és a 'type' illetve 'date' adattagok String-je.
     */
    public String toString() {
        return type + " | " + board.n + "x" + board.n + " | " + date;
    }
}
