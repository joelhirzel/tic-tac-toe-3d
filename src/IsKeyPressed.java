import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Set;

public class IsKeyPressed
implements KeyListener {
    private Drawing drawing;
    private boolean enter;
    private BigCube bigCube;
    private final Set<Character> pressed = new HashSet<Character>();

    public IsKeyPressed(Drawing drawing, BigCube bigCube) {
        this.drawing = drawing;
        this.bigCube = bigCube;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int[] select;
        double[] result = new double[]{0.0, 0.0, 0.0};
        double[] rotation = new double[]{0.0, 0.0, 0.0};
        int[] selection = new int[]{0, 0, 0};
        this.pressed.add(Character.valueOf(e.getKeyChar()));
        if (e.getKeyCode() == 37) {
            selection[0] = -1;
            this.drawing.moveSelection(selection);
        } else if (e.getKeyCode() == 39) {
            selection[0] = 1;
            this.drawing.moveSelection(selection);
        } else if (e.getKeyCode() == 38) {
            selection[1] = -1;
            this.drawing.moveSelection(selection);
        } else if (e.getKeyCode() == 40) {
            selection[1] = 1;
            this.drawing.moveSelection(selection);
        } else if (e.getKeyCode() == 44) {
            selection[2] = 1;
            this.drawing.moveSelection(selection);
        } else if (e.getKeyCode() == 46) {
            selection[2] = -1;
            this.drawing.moveSelection(selection);
        }
        this.drawing.move(result);
        this.drawing.rotate(rotation);
        if (e.getKeyCode() == 10 && (select = this.bigCube.getSelection()) != null && this.bigCube.getBigCube()[select[0]][select[1]][select[2]].getPlayer() == null) {
            this.enter = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    public boolean getEnter() {
        return this.enter;
    }

    public void resetEnter() {
        this.enter = false;
    }
}

