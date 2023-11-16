package Engine;

import Menu.Menu;

import javax.swing.*;

/**
 * MainEngine, leszarmaztatott Engine osztaly.
 * Ebben az osztalyban van megvalositva
 * a jatek fomenuje.
 */
public class MainEngine extends Engine {
    /**
     * Konstruktor, a jatek menujet valositja meg,
     * a fo gombokkal es a kezdo szoveggel.
     * Paremeterkent a mentett jatekok szamat kapja.
     * @param size Mentett jatekok szama.
     */
    public MainEngine(int size) {
        menu = new Menu(MAIN);
        menu.addContent(new JLabel("<html><h1><strong>TIC TAC TOE</strong></h1></html>", SwingConstants.CENTER));
        menu.addButton("PLAY", e -> menu.quit(INIT));
        if (size != 0)
            menu.addButton("LOAD", e -> menu.quit(LOAD));
        menu.addButton("EXIT", e -> menu.quit(EXIT));
        menu.init();
    }
}
