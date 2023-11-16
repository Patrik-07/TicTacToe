package Engine;

import Game.*;
import Menu.Menu;

import javax.swing.*;
import java.util.ArrayList;

/**
 * LoadEngine, leszarmaztatott Engine osztaly.
 * Ebben az osztalyban van megvalositva a jatekok visszatoltese.
 */
public class LoadEngine extends Engine {
    private int index;
    /**
     * Konstruktor, a jatek menubol valo visszatolteset es
     * torleset valositja meg. Parameterkent az elmentett jatekokat kapja.
     * @param games Lista az elmentett jatekokrol.
     */
    public LoadEngine(ArrayList<Game> games) {
        menu = new Menu(LOAD);

        JPanel content = new JPanel();
        Object[] gameNames = new Object[games.size()];
        for (int i = 0; i < games.size(); i++)
            gameNames[i] = games.get(i).toString();
        JComboBox comboBox = new JComboBox(gameNames);
        content.add(comboBox);

        comboBox.addActionListener(e -> index = comboBox.getSelectedIndex());
        menu.addContent(content);
        menu.addButton("START", e -> {
            menu.quit(GAME);
            board = new Board(games.get(index).board);
            type = games.get(index).type;
            date = games.get(index).getDate();
        });
        menu.addButton("DELETE", e -> {
            games.remove(index);
            if (games.size() != 0)
                menu.quit(LOAD);
            else menu.quit(MAIN);
        });
        menu.addButton("BACK", e -> menu.quit(MAIN));
        menu.init();
    }
}
