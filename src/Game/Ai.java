package Game;

/**
 * AI osztály.
 * A felhasznalo ellen jatszo gep.
 */
public class Ai implements GameNames {
    private int opponentId;
    private int id;
    int row;
    int col;
    private Logic logic;
    private final Board board;
    /**
     * Konstruktor, inicializalja a tabla allasnak megfeleloen az adattagokat.
     * @param board Az aktualis tabla allas.
     */
    public Ai(Board board) {
        this.board = board;
        if (board.getLastRow() != -1)
            logic = new Logic(board.getCellValue(board.getLastRow(), board.getLastColumn()));
        else logic = new Logic(PLAYER2);
    }
    /**
     * Belso logika szerint elhelyezi a tablara a megadott erteket.
     * @param id Tablara elhelyezendo ertek.
     */
    public void setPosition(int id) {
        this.id = id;
        if (id == PLAYER1)
            opponentId = PLAYER2;
        else opponentId = PLAYER1;
        logic = new Logic(board.getCellValue(board.getLastRow(), board.getLastColumn()));
        max(board);
        board.setCellValue(row, col, id);
    }
    /**
     * Maximum kivalasztas,
     * A gepnek kedvezo lepeseket veszi figyelmebe,
     * eszerint valasztja a neki legkedvezobbet.
     * @param board Az aktualis tabla allas.
     * @return Egy hasznossagi ertek, az adott lepesre. (-1, 0, 1).
     */
    private int max(Board board) {
        logic = new Logic(board.getCellValue(board.getLastRow(), board.getLastColumn()));
        if (logic.isPlayerWin(board))
            return logic.getWinner();
        int value = -1;
        for (int r = 0; r < board.n; r++)
            for (int c = 0; c < board.n; c++)
                if (board.isEmptyCell(r, c)) {
                    Board b = new Board(board);
                    b.setCellValue(r, c, id);
                    int v = min(b);
                    b.setCellValue(r, c, EMPTY);
                    if (v > value) {
                        value = v;
                        row = r;
                        col = c;
                    }
                }
        return value;
    }
    /**
     * Minimum kivalasztas,
     * A felhasznalonak kedvezo lepeseket veszi figyelmebe,
     * eszerint valasztja a neki legkedvezobbet,
     * a felhasznalo szamara a legkedvezotlenebbeket.
     * @param board Az aktualis tabla allas.
     * @return Egy hasznossagi ertek, az adott lepesre. (-1, 0, 1).
     */
    private int min(Board board) {
        logic = new Logic(board.getCellValue(board.getLastRow(), board.getLastColumn()));
        if (logic.isPlayerWin(board))
            return logic.getWinner();
        int value = 1;
        for (int r = 0; r < board.n; r++)
            for (int c = 0; c < board.n; c++)
                if (board.isEmptyCell(r, c)) {
                    Board b = new Board(board);
                    b.setCellValue(r, c, opponentId);
                    int v = max(b);
                    b.setCellValue(r, c, EMPTY);
                    if (v < value) {
                        value = v;
                    }
                }
        return value;
    }
}
