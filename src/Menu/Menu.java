package Menu;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static Menu.MenuNames.GAME;

/**
 * Menu osztaly.
 * Egy menut valosit meg. Egszeru fo tartalommal és gombokkal.
 */
public class Menu extends JPanel {
    private boolean quit = false;
    private int nextMenuID;
    public final ArrayList<JButton> buttons = new ArrayList<>();
    private final JPanel panel = new JPanel();
    /**
     * Konstruktor. Inicializalja a menu Layout-at.
     * @param menuID ez az ertek szerint valtozik a menu kinezete
     *               egyes allapotokban.
     */
    public Menu(int menuID) {
        setBorder(new EmptyBorder(20, 20, 20, 20));
        if (menuID != GAME)
            setLayout(new GridLayout(3, 1, 10, 10));
    }
    /**
     * Menu inicializalasat vegzi el.
     * Hozzadaja a felvett gombokat es a fo tartalmat a menuhoz.
     */
    public void init() {
        JPanel buttonsPanel = new JPanel(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);
        for (JButton button : buttons) buttonsPanel.add(button, gbc);

        add(panel, gbc);
        add(buttonsPanel, gbc);
    }
    /**
     * A menu fo tartalmanak beallitasat volositja meg.
     * Hozzadaja a menuhoz a parameterkent atvet tartalmat.
     * @param content Hozzaadni kivant tartalom.
     */
    public void addContent(JComponent content) {
        panel.add(content);
    }
    /**
     * Gombok hozzadasat valositja meg a menuhoz.
     * @param name A hozzaadni kivant gomb neve.
     * @param actionListener A hozzadni kivant gomb actionListener-e.
     */
    public void addButton(String name, ActionListener actionListener) {
        JButton button = new JButton(name);
        button.addActionListener(actionListener);
        buttons.add(button);
    }
    /**
     * Letiltja a gombok lathatosagat.
     * A menu dinamikus hasznalataban van jelentosege.
     */
    public void hideButtons() {
        for (JButton button : buttons) button.setVisible(false);
    }
    /**
     * A menubol valo kilepeset valositja meg.
     * @param menuID A kovetkezo menu ID-ja.
     */
    public void quit(int menuID) {
        quit = true;
        nextMenuID = menuID;
    }
    /**
     * Megadja, hogy kileptek-e a menubol.
     * @return Igaz, ha a menubol kileptek, hamis ha nem.
     */
    public boolean isQuit() {
        return quit;
    }
    /**
     * A kovetkezo menu ID-jat adja vissza.
     * @return Kovetkezo menu ID-ja.
     */
    public int getNextMenuID() {
        return nextMenuID;
    }
}
