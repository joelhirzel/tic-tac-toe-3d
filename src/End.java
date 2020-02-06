import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;

public class End
extends JPanel {
    private Frame frame;
    private int winner;

    public End(Frame frame, int winner) {
        this.frame = frame;
        this.winner = winner;
    }

    @Override
    public void paint(Graphics g) {
        super.setBackground(Color.BLACK);
        g.setFont(new Font("Courier New", 0, 40));
        g.setColor(Color.WHITE);
        g.drawString("Player " + (this.winner + 1) + " wins!", this.frame.getFrame().getWidth() / 2 - 160, this.frame.getFrame().getHeight() / 2 - 20);
    }
}

