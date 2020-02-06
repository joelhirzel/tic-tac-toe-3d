import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;

public class Splash
extends JPanel {
    private Frame frame;
    private JPanel panel;
    private int state = -1;
    private int playerAmount = -1;
    private int cubesAmount = -1;
    private int goal = -1;
    private int counter;

    public Splash(Frame frame) {
        this.frame = frame;
        this.panel = new JPanel();
    }

    @Override
    public void paint(Graphics g) {
        super.setBackground(Color.BLACK);
        g.setFont(new Font("Courier New", 0, 40));
        g.setColor(Color.WHITE);
        g.drawString("3D TicTacToe", this.frame.getFrame().getWidth() / 2 - 150, 60);
        if (this.state >= 0) {
            g.drawString("Player Count:", 20, 140);
        }
        if (this.state >= 1) {
            g.drawString(String.valueOf(this.playerAmount), 400, 140);
            g.drawString("Cube Size:", 20, 200);
        }
        if (this.state >= 2) {
            g.drawString(String.valueOf(this.cubesAmount), 400, 200);
            g.drawString("Winning Length:", 20, 260);
        }
        if (this.state >= 3) {
            g.drawString(String.valueOf(this.goal), 400, 260);
        }
        if (this.state >= 4) {
            g.drawString(String.valueOf(this.counter), this.frame.getFrame().getWidth() / 2 - 13, 350);
        }
    }

    public void setPlayerAmount(int playerAmount) {
        this.playerAmount = playerAmount;
        this.incrementState();
    }

    public void setCubesAmount(int cubesAmount) {
        this.cubesAmount = cubesAmount;
        this.incrementState();
    }

    public void setGoal(int goal) {
        this.goal = goal;
        this.incrementState();
    }

    public void incrementState() {
        ++this.state;
    }

    public JPanel getPanel() {
        return this.panel;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }
}

