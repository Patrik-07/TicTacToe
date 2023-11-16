package Engine;

import Game.*;
import Menu.Menu;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * GameEngine, leszarmaztatott Engine osztaly.
 * Ebben az osztalyban van megvalositva
 * a jatek, felhasznalo szemszogebol valo mukodese.
 */
public class GameEngine extends Engine implements GameNames {
    private boolean gameOver = false;
    /**
     * Konstruktor, a jatek ciklikus, grafikus mukodeset valositja meg.
     * Parameterkent egy jatekListat vesz at, a mentes miatt.
     * @param games Lista az elmentett jatekokrol.
     */
    public GameEngine(ArrayList<Game> games) {
        Game game = new Game(board, type, date);
        Ai ai = new Ai(game.board);

        menu = new Menu(GAME);

        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));

        int size;
        switch (game.board.n) {
            case 5: size = 60; break;
            case 10: size = 30; break;
            default: size = 80; break;
        }
        JTable table = new JTable(game.board.n, game.board.n);
        table.setRowSelectionAllowed(false);
        table.setPreferredSize(new Dimension(size*game.board.n, size*game.board.n));
        table.setRowHeight(size);
        TableColumnModel columnModel = table.getColumnModel();
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < table.getRowCount(); i++) {
            columnModel.getColumn(i).setPreferredWidth(size);
            columnModel.getColumn(i).setCellRenderer(centerRenderer);
        }
        table.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, new Color(0x9A2121)));
        JLabel label = new JLabel();
        JPanel p = new JPanel(new BorderLayout());
        p.add(table, BorderLayout.SOUTH);
        p.add(label, BorderLayout.NORTH);
        content.add(p);

        for (int r = 0; r < game.board.n; r++)
            for (int c = 0; c < game.board.n; c++)
                table.setValueAt((char)game.board.getCellValue(r, c), r, c);

        table.setDefaultEditor(Object.class, null);
        if (game.type.equals("PVP"))
            label.setText("NEXT PLAYER: " + (char) game.getNextPlayerID());
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (!gameOver) {
                    super.mouseClicked(e);
                    int row = table.rowAtPoint(e.getPoint());
                    int col = table.columnAtPoint(e.getPoint());
                    if (game.board.isEmptyCell(row, col)) {
                        table.setValueAt((char)game.getNextPlayerID(), row, col);
                        game.board.setCellValue(row, col, game.getNextPlayerID());
                        menu.buttons.get(0).setVisible(true);
                        check(game, games);
                        if (game.type.equals("AI") && !gameOver) {
                            int id = game.getNextPlayerID();
                            ai.setPosition(id);
                            table.getModel().setValueAt((char)id, game.board.getLastRow(), game.board.getLastColumn());
                            check(game, games);
                        }
                        if (game.type.equals("PVP"))
                            label.setText("NEXT PLAYER: " + (char) game.getNextPlayerID());
                        if (gameOver)
                            label.setText("GAME IS OVER!");
                    }
                }
            }
        });
        menu.add(content);
        menu.addButton("SAVE", e -> {
            menu.quit(MAIN);
            removeSameGame(game, games);
            game.updateDate();
            games.add(game);
        });
        menu.buttons.get(0).setVisible(false);
        menu.addButton("BACK", e -> menu.quit(MAIN));
        menu.init();
    }
    /**
     * A jatek ciklusa, amit minden lepesben le kell futatni.
     * Ez ellenorzi, hogy a jateknak vege van-e,
     * hogy nyert-e valaki.
     * @param game Aktualis folyamatban levo jatek.
     * @param games Lista az elmentett jatekokrol.
     */
    private void check(Game game, ArrayList<Game> games) {
        if (game.isPlayerWin()) {
            gameOver = true;
            String text;
            switch (game.getWinner()) {
                case PLAYER1: text = "PLAYER1 WINS!"; break;
                case PLAYER2:
                    if (game.type.equals("AI"))
                        text = "AI WINS!";
                    else text = "PLAYER2 WINS!";
                    break;
                case 0: text = "DRAW!"; break;
                default: text = "BACK"; break;
            }
            removeSameGame(game, games);
            menu.hideButtons();
            menu.addButton(text, ev -> menu.quit(MAIN));
            menu.init();
        }
    }
    /**
     * Mentesnel hasznalt metodus.
     * Eltavolitja a parameterben megadott jatekot a jatekok listajabol.
     * Igy frissul a mentett jatekok listaja.
     * @param game Aktualis folyamatban levo jatek.
     * @param games Lista az elmentett jatekokrol.
     */
    private void removeSameGame(Game game, ArrayList<Game> games) {
        for (int i = 0; i < games.size(); i++) {
            Game g = games.get(i);
            if (game.board.n == g.board.n && game.type.equals(g.type) && game.getDate().equals(g.getDate())) {
                games.remove(i);
                break;
            }
        }
    }
}
