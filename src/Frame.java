import javax.swing.JFrame;

public class Frame {
    public JFrame frame = new JFrame();

    public Frame() {
        this.frame.setSize(1000, 800);
        this.frame.setDefaultCloseOperation(3);
        this.frame.setTitle("TicTacToe");
    }

    public int getWidth() {
        return this.frame.getWidth();
    }

    public int getHeight() {
        return this.frame.getHeight();
    }

    public void addComponent(Splash splash) {
        this.frame.add(splash);
        this.frame.setVisible(true);
    }

    public void removeComponent(Splash splash) {
        this.frame.remove(splash);
    }

    public void addComponent(Drawing drawing) {
        this.frame.add(drawing);
        this.frame.setVisible(true);
    }

    public void removeComponent(Drawing drawing) {
        this.frame.remove(drawing);
    }

    public void addComponent(End end) {
        this.frame.add(end);
        this.frame.setVisible(true);
    }

    public void addKey(IsKeyPressed key) {
        this.frame.addKeyListener(key);
    }

    public void addKey(SplashKeys key) {
        this.frame.addKeyListener(key);
    }

    public void removeKey(IsKeyPressed key) {
        this.frame.removeKeyListener(key);
    }

    public void removeKey(SplashKeys key) {
        this.frame.removeKeyListener(key);
    }

    public JFrame getFrame() {
        return this.frame;
    }
}

