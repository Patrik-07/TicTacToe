package Game;

/**
 * Tabla osztaly.
 * Itt tarolodnak el a jatekosok altal lepett lepesek.
 * Lekerdezo metodusokat, tartalmaz,
 * amik a jatek logikai megvalositasan alkalmazandok.
 */
public class Board implements java.io.Serializable {
    private final int[][] board;
    public final int n;
    private int lastRow = -1;
    private int lastColumn = -1;
    /**
     * Konstruktor
     * Inicializálja a 2 dimenzios tomb minden cellajat
     * a kezdeti, EMPTY ertekekkel, a parameterben megadott n szerint.
     * @param n Az eggyetlen parameter, ez fogja a tabla meretet megadni.
     */
    public Board(int n) {
        board = new int[n][n];
        this.n = n;
        for (int row = 0; row < n; row++)
            for (int column = 0; column < n; column++)
                board[row][column] = GameNames.EMPTY;
    }
    /**
     * Copy Konstruktor
     * Egy masolatot keszit egy tablarol.
     * Visszatolteseknel van jelentosege.
     * @param board Egyetlen parameter a masolando tabla.
     */
    public Board(Board board) {
        n = board.n;
        lastRow = board.lastRow;
        lastColumn = board.lastColumn;
        this.board = new int[n][n];
        for (int row = 0; row < n; row++)
            System.arraycopy(board.board[row], 0, this.board[row], 0, n);
    }
    /**
     * Beallitja a tabla parameterben megadott cellajat,
     * szinten a parameterben megadott ertekre.
     * @param row A sort fogja kijelolni a 2D tomben.
     * @param column Az oszlopot fogja kijelolni a 2D tomben.
     * @param value A tablara felvinni kivant integer ertek.
     */
    public void setCellValue(int row, int column, int value) {
        if (isEmptyCell(row, column)) {
            board[row][column] = value;
            lastRow = row;
            lastColumn = column;
        }
    }
    /**
     * Megadja az utolso elhelyezes sorat.
     * @return Az utolso elhelyezes sora, -1 ha meg nem helyeztek el semmit a tablan.
     */
    public int getLastRow() {
        return lastRow;
    }
    /**
     * Megadja az utolso elhelyezes oszlopat..
     * @return Az utolso elhelyezes oszlopa, -1 ha meg nem helyeztek el semmit a tablan.
     */
    public int getLastColumn() {
        return lastColumn;
    }
    /**
     * Visszadaja a lekerdezni kivant cella erteket.
     * @param row Lekerdezni kivant cella sora.
     * @param column Lekerdezni kivant cella oszlopa.
     * @return A cella erteke
     */
    public int getCellValue(int row, int column) {
        return board[row][column];
    }
    /**
     * Megadja, hogy egy cella ures-e.
     * @param row Lekerdezni kivant cella sora.
     * @param column Lekerdezni kivant cella oszlopa.
     * @return Igaz, ha a cella ures. Hamis, ha a cellaban mar van ertek.
     */
    public boolean isEmptyCell(int row, int column) {
        return board[row][column] == GameNames.EMPTY;
    }

    /**
     * Megadja, hogy a tabla ures-e.
     * @return Igaz, ha minden cellaban ures ertek szerepel, hamis ha nem.
     */
    public boolean isEmpty() {
        for (int row = 0; row < n; row++)
            for (int column = 0; column < n; column++)
                if (!isEmptyCell(row, column))
                    return false;
        return true;
    }
    /**
     * Megadja, hogy a tabla tele van-e.
     * @return Igaz, ha minden cellaban van ertek, hamis ha nem.
     */
    public boolean isFull() {
        for (int row = 0; row < n; row++)
            for (int column = 0; column < n; column++)
                if (isEmptyCell(row, column))
                    return false;
        return true;
    }
}
