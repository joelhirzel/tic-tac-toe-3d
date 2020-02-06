import java.awt.Color;
import java.util.concurrent.TimeUnit;
import javax.swing.JFrame;

public class TicTacToe
extends JFrame {
    public static void main(String[] args) throws InterruptedException {
        Frame frame = new Frame();
        int[] settings = new int[3];
        TicTacToe.setup(frame, settings);
        int winner = TicTacToe.play(frame, settings);
        System.out.println("Player " + (winner + 1) + " wins!");
    }

    public static int play(Frame frame, int[] settings) {
        BigCube bigCube = new BigCube(settings[1]);
        Player[] players = new Player[settings[0]];
        for (int i = 0; i < settings[0]; ++i) {
            players[i] = new Player(settings[1], bigCube, TicTacToe.getColor(i), settings[2]);
        }
        Drawing drawing = new Drawing(bigCube, frame, players);
        IsKeyPressed key = new IsKeyPressed(drawing, bigCube);
        frame.addComponent(drawing);
        frame.addKey(key);
        block1: while (true) {
            int i = 0;
            while (true) {
                if (i >= settings[0]) continue block1;
                do {
                    drawing.repaint();
                } while (bigCube.getSelection() == null || !key.getEnter());
                players[i].addSelected(bigCube.getSelection());
                key.resetEnter();
                if (players[i].checkRows()) {
                    System.out.println("won!");
                    frame.removeComponent(drawing);
                    frame.removeKey(key);
                    return i;
                }
                ++i;
            }
        }
    }

    public static Color getColor(int i) {
        Color[] color = new Color[]{Color.BLUE, Color.RED, Color.CYAN, Color.GREEN, Color.MAGENTA, Color.YELLOW, Color.PINK};
        return color[Math.floorMod(i, color.length)];
    }

    public static void setup(Frame frame, int[] settings) throws InterruptedException {
        Splash splash = new Splash(frame);
        SplashKeys key = new SplashKeys();
        frame.addComponent(splash);
        frame.addKey(key);
        splash.getPanel().setBackground(Color.BLACK);
        frame.getFrame().getContentPane().add(splash.getPanel());
        splash.incrementState();
        do {
            System.out.println("waiting");
        } while (!key.getPressed());
        splash.setPlayerAmount(key.getAmount());
        settings[0] = key.getAmount();
        splash.repaint();
        key.resetPressed();
        do {
            System.out.println("waiting");
        } while (!key.getPressed());
        splash.setCubesAmount(key.getAmount());
        settings[1] = key.getAmount();
        splash.repaint();
        key.resetPressed();
        do {
            System.out.println("waiting");
        } while (!key.getPressed());
        splash.setGoal(key.getAmount());
        settings[2] = key.getAmount();
        splash.repaint();
        TimeUnit.MILLISECONDS.sleep(300L);
        splash.incrementState();
        for (int i = 3; i > 0; --i) {
            splash.setCounter(i);
            splash.repaint();
            TimeUnit.SECONDS.sleep(1L);
        }
        frame.removeComponent(splash);
        frame.removeKey(key);
    }
}

