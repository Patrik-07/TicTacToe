package Test;

import Game.Board;
import Game.GameNames;
import org.junit.Assert;
import org.junit.Test;

public class BoardTest {
    @Test
    public void testGetCellValue() {
        Board board = new Board(5);
        Assert.assertEquals(board.getCellValue(0, 0), GameNames.EMPTY);
    }
    @Test
    public void testSetCellValue() {
        Board board = new Board(5);
        board.setCellValue(0, 0, 'X');
        Assert.assertEquals(board.getCellValue(0, 0), 'X');
    }
    @Test
    public void testGetLastRow() {
        Board board = new Board(5);
        board.setCellValue(0, 0, 'X');
        Assert.assertEquals(board.getLastRow(), 0);
    }
    @Test
    public void testGetLastColumn() {
        Board board = new Board(5);
        board.setCellValue(0, 0, 'X');
        Assert.assertEquals(board.getLastColumn(), 0);
    }
    @Test
    public void testIsEmptyCell() {
        Board board = new Board(5);
        Assert.assertTrue(board.isEmptyCell(0, 0));
    }
    @Test
    public void testIsEmpty() {
        Board board = new Board(5);
        Assert.assertTrue(board.isEmpty());
    }
    @Test
    public void testIsFull() {
        Board board = new Board(5);
        for (int r = 0; r < board.n; r++)
            for (int c = 0; c < board.n; c++)
                board.setCellValue(r, c, 'X');
        Assert.assertTrue(board.isFull());
    }
}