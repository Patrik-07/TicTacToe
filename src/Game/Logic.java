package Game;

/**
 * Logika osztaly.
 * A jatek logikajat valositja meg.
 * Ellenorzi a lepeseket, nyert-e valaki.
 */
public class Logic implements GameNames, java.io.Serializable {
    private int winner = -1;
    private int lastPlayerID;
    /**
     * Konkstruktor, az utolsa lepett jatekos ID-ja
     * szerint fogja a logikat megvalositani.
     * @param lastPlayerID utoljara lepett jatekos ID-ja
     */
    public Logic(int lastPlayerID) {
        this.lastPlayerID = lastPlayerID;
    }
    /**
     * A jatek jelenlegi allapotarol ad informaciot.
     * @return A gyoztes jatekos ID-ja, dontetlne eseten 0,
     *         egyebkent ha nincs vege jateknak -1 ertek.
     */
    public int getWinner() {
        return winner;
    }
    /**
     * Megadja a soron kovetkezo jatekos ID-jat.
     * @return Kovetkezo jatekos ID-ja.
     */
    public int getNextPlayerID() {
        if (lastPlayerID == PLAYER1)
            return PLAYER2;
        return PLAYER1;
    }
    /**
     * Ellenorzi, hogy jelenleg lepett jatekos nyert-e.
     * @param board Az aktualis tabla. Eszerint ellenoriz.
     * @return Igaz, ha az aktualisan lepett jatekos nyert
     *         vagy dontetlen lett a jatek kimenetele,
     *         egyebkent hamis.
     */
    public boolean isPlayerWin(Board board) {
        int count = Math.min(board.n, 5);
        winner = lastPlayerID = board.getCellValue(board.getLastRow(), board.getLastColumn());
        boolean win;
        // Horizontal Check
        int column = 0;
        while (column != board.getLastColumn() + 1 && board.n - column >= count) {
            win = true;
            for (int i = 0; i < count; i++)
                if (lastPlayerID != board.getCellValue(board.getLastRow(), column + i) || board.isEmptyCell(board.getLastRow(), column + i))
                    win = false;
            if (win) return true;
            column++;
        }
        // Vertical Check
        int row = 0;
        while (row != board.getLastRow() + 1 && board.n - row >= count) {
            win = true;
            for (int i = 0; i < count; i++)
                if (lastPlayerID != board.getCellValue(row + i, board.getLastColumn()) || board.isEmptyCell(row + i, board.getLastColumn()))
                    win = false;
            if (win) return true;
            row++;
        }
        // LeftDiagonal Check
        row = board.getLastRow();
        column = board.getLastColumn();
        while (row > 0 && column > 0) {
            row--;
            column--;
        }
        while ((row != board.getLastRow() + 1 && board.n - row >= count) && (column != board.getLastColumn() + 1 && board.n - column >= count)) {
            win = true;
            for (int i = 0; i < count; i++)
                if (lastPlayerID != board.getCellValue(row + i, column + i) || board.isEmptyCell(row + i, column + i))
                    win = false;
            if (win) return true;
            column++;
            row++;
        }
        // RightDiagonal Check
        row = board.getLastRow();
        column = board.getLastColumn();
        while (row < board.n-1 && column > 0) {
            row++;
            column--;
        }
        while ((row != board.getLastRow() - 1 && row + 1 >= count) && (column != board.getLastColumn() + 1 && board.n - column >= count)) {
            win = true;
            for (int i = 0; i < count; i++)
                if (lastPlayerID != board.getCellValue(row - i, column + i) || board.isEmptyCell(row - i, column + i))
                    win = false;
            if (win) return true;
            column++;
            row--;
        }
        if (board.isFull()) {
            winner = 0;
            return true;
        }
        winner = -1;
        return false;
    }
}
