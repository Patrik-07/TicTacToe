package Engine;

import Game.Board;
import Menu.Menu;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * InitEngine, leszarmaztatott Engine osztaly.
 * Ebben az osztalyba van megvolostiva
 * a jatek inicializalasa, parametereinek beallitasa.
 */
public class InitEngine extends Engine {
    /**
     * Konstruktor, a inicializo menut
     * es annak mukodeset valositja meg.
     */
    public InitEngine() {
        menu = new Menu(INIT);

        JPanel content = new JPanel(new GridLayout(3, 1));

        Object[] boardSizes = new Object[3];
        boardSizes[0] = "3x3";
        boardSizes[1] = "5x5";
        boardSizes[2] = "10x10";

        @SuppressWarnings("unchecked")
        JComboBox comboBox = new JComboBox(boardSizes);
        JCheckBox checkBox1 = new JCheckBox("PLAYERvsPLAYER");
        JCheckBox checkBox2 = new JCheckBox("PLAYERvsAI");

        checkBox1.setSelected(true);
        comboBox.addActionListener(e -> {
            checkBox1.setSelected(true);
            if (comboBox.getSelectedIndex() != 0) {
                checkBox2.setVisible(false);
                checkBox2.setSelected(false);
            } else checkBox2.setVisible(true);
        });
        checkBox1.addActionListener(e -> {
            checkBox1.setSelected(true);
            checkBox2.setSelected(false);
        });
        checkBox2.addActionListener(e -> {
            checkBox1.setSelected(false);
            checkBox2.setSelected(true);
        });

        content.setBorder(new TitledBorder(new EmptyBorder(0, 0, 0, 0), "Set parameters!"));
        content.add(comboBox);
        content.add(checkBox1);
        content.add(checkBox2);

        menu.addContent(content);
        menu.addButton("START", e -> {
            menu.quit(GAME);
            int boardSize;
            switch (comboBox.getSelectedIndex()) {
                case 1: boardSize = 5; break;
                case 2: boardSize = 10; break;
                default: boardSize = 3; break;
            }
            type = "PVP";
            if (checkBox2.isSelected())
                type = "AI";
            board = new Board(boardSize);
            date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        });
        menu.addButton("BACK", e -> menu.quit(MAIN));
        menu.init();
    }
}
