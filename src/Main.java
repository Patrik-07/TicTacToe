import Engine.*;
import Game.*;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

import static Menu.MenuNames.*;

/**
 * Main osztaly.
 * Mentest, betoltest es menuk kozotti mozgast valositja meg.
 */
public class Main {
    /**
     * Ez a metodus menti a jatekok file-ba.
     * @param games Jatek soran mentesre kerult jatekok.
     * @throws IOException
     */
    public static void save(ArrayList<Game> games) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream("gameData.ser");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(games);
        objectOutputStream.close();
        fileOutputStream.close();
    }
    /**
     * Ez a metodus tolti be a jatekok file-bol.
     * @return Betoltott jatekok listaja.
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @SuppressWarnings("unchecked")
    public static ArrayList<Game> load() throws IOException, ClassNotFoundException {
        try {
            FileInputStream fileInputStream = new FileInputStream("gameData.ser");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            ArrayList<Game> games = (ArrayList<Game>) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
            return games;
        } catch (FileNotFoundException e) {
            return new ArrayList<>();
        }
    }
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ArrayList<Game> games = load();
        Engine currentEngine = new MainEngine(games.size());

        final JFrame frame = new JFrame("TicTacToe");
        frame.add(currentEngine.getMenu());
        frame.setSize(320, 480);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        boolean run = true;
        while (run) {
            System.out.flush();
            if (currentEngine.isQuit()) {
                frame.invalidate();
                frame.remove(currentEngine.getMenu());
                switch (currentEngine.getNextID()) {
                    case INIT: currentEngine = new InitEngine(); break;
                    case LOAD: currentEngine = new LoadEngine(games); break;
                    case GAME: currentEngine = new GameEngine(games); break;
                    case EXIT: run = false; break;
                    default: currentEngine = new MainEngine(games.size()); break;
                }
                frame.add(currentEngine.getMenu());
                frame.repaint();
                frame.validate();
            }
        }
        frame.setVisible(false);
        frame.dispose();
        save(games);
    }
}