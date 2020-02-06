import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SplashKeys
implements KeyListener {
    private boolean pressed = false;
    private int amount;

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == 49) {
            this.amount = 1;
            this.pressed = true;
        } else if (e.getKeyCode() == 50) {
            this.amount = 2;
            this.pressed = true;
        } else if (e.getKeyCode() == 51) {
            this.amount = 3;
            this.pressed = true;
        } else if (e.getKeyCode() == 52) {
            this.amount = 4;
            this.pressed = true;
        } else if (e.getKeyCode() == 53) {
            this.amount = 5;
            this.pressed = true;
        } else if (e.getKeyCode() == 54) {
            this.amount = 6;
            this.pressed = true;
        } else if (e.getKeyCode() == 55) {
            this.amount = 7;
            this.pressed = true;
        } else if (e.getKeyCode() == 56) {
            this.amount = 8;
            this.pressed = true;
        } else if (e.getKeyCode() == 57) {
            this.amount = 9;
            this.pressed = true;
        }
    }

    public int getAmount() {
        return this.amount;
    }

    public boolean getPressed() {
        return this.pressed;
    }

    public void resetPressed() {
        this.pressed = false;
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}

