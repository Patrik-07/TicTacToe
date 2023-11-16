package Test;

import Menu.Menu;
import org.junit.Assert;
import org.junit.Test;

public class MenuTest {
    @Test
    public void testAddButton() {
        Menu menu = new Menu(0);
        menu.addButton("BUTTON", e -> {});
        Assert.assertTrue(menu.buttons.size() != 0);
    }
    @Test
    public void testIsQuit() {
        Menu menu = new Menu(0);
        Assert.assertFalse(menu.isQuit());
    }
    @Test
    public void testQuit() {
        Menu menu = new Menu(0);
        menu.quit(1);
        Assert.assertTrue(menu.isQuit());
    }
    @Test
    public void testGetNextMenuID() {
        Menu menu = new Menu(0);
        menu.quit(1);
        Assert.assertEquals(menu.getNextMenuID(), 1);
    }
}
