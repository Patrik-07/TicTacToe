package Test;

import Game.*;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class GameTest {
    @Test
    public void testGetDate() {
        Game game = new Game(new Board(5), "type", "date");
        Assert.assertEquals(game.getDate(), "date");
    }
    @Test
    public void testUpdateDate() {
        Game game = new Game(new Board(5), "type", "date");
        game.updateDate();
        Assert.assertEquals(game.getDate(), LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    }
    @Test
    public void testIsPLayerWin() {
        Game game = new Game(new Board(3), "PVP", "date");
        game.board.setCellValue(0, 0, 'X');
        game.board.setCellValue(1, 1, 'X');
        game.board.setCellValue(2, 2, 'X');
        Assert.assertTrue(game.isPlayerWin());
    }
    @Test
    public void testGetWinner() {
        Game game = new Game(new Board(3), "PVP", "date");
        game.board.setCellValue(0, 0, 'X');
        game.board.setCellValue(1, 1, 'X');
        game.board.setCellValue(2, 2, 'X');
        game.isPlayerWin();
        Assert.assertEquals(game.getWinner(), 'X');
    }
    @Test
    public void testGetNextPlayerID() {
        Game game = new Game(new Board(3), "PVP", "date");
        game.board.setCellValue(0, 0, 'X');
        game.isPlayerWin();
        Assert.assertEquals(game.getNextPlayerID(), 'O');
    }
}