import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

public class Drawing
extends JPanel {
    private BigCube bigCube;
    private int cubeCount;
    private Cube[][][] cubes;
    private Frame frame;
    private Player[] players;
    int[] selection = null;

    public Drawing() {
    }

    public Drawing(BigCube bigCube, Frame frame, Player[] players) {
        this.frame = frame;
        this.bigCube = bigCube;
        this.cubeCount = bigCube.getBigCube().length;
        this.cubes = bigCube.getBigCube();
        this.players = players;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        super.setBackground(Color.BLACK);
        this.selection = this.bigCube.getSelection();
        int[][] selected = null;
        for (int x = 0; x < this.cubeCount; ++x) {
            for (int y = 0; y < this.cubeCount; ++y) {
                for (int z = 0; z < this.cubeCount; ++z) {
                    double[][] realCoordinates = this.bigCube.getBigCube()[x][y][z].getCoordinates();
                    int[][] result = this.calculateFrameCoordinates(realCoordinates);
                    this.drawCube(result, (Graphics2D)g, new Color(40, 40, 40), this.bigCube.getBigCube()[x][y][z].getPlayer());
                    if (this.selection == null || x != this.selection[0] || y != this.selection[1] || z != this.selection[2]) continue;
                    selected = result;
                }
            }
        }
        double[][] frame = this.bigCube.getCoordinatesFrame();
        int[][] result = this.calculateFrameCoordinates(frame);
        this.drawCube(result, (Graphics2D)g, Color.WHITE, null);
        if (selected != null) {
            this.drawFace(selected, (Graphics2D)g);
        }
    }

    public int[][] calculateFrameCoordinates(double[][] array) {
        int[][] result = new int[8][2];
        double offset = (double)this.cubeCount * this.bigCube.getBigCube()[0][0][0].getSide() / 2.0;
        double frameMiddleWidth = this.frame.getWidth() / 2;
        double frameMiddleHeight = this.frame.getHeight() / 2;
        double vocalLength = 8.0E-4;
        for (int i = 0; i < 8; ++i) {
            double perspective = vocalLength * array[i][2] + 1.0;
            result[i][0] = (int)(array[i][0] / perspective + frameMiddleWidth);
            result[i][1] = (int)(array[i][1] / perspective + frameMiddleHeight - 23.0);
        }
        return result;
    }

    public void drawCube(int[][] result, Graphics2D g2, Color color, Player player) {
        if (player != null) {
            this.drawPlayer((result[0][0] + result[7][0]) / 2, (result[0][1] + result[7][1]) / 2, player, g2);
        }
        g2.setColor(color);
        g2.drawLine(result[0][0], result[0][1], result[1][0], result[1][1]);
        g2.drawLine(result[0][0], result[0][1], result[2][0], result[2][1]);
        g2.drawLine(result[0][0], result[0][1], result[4][0], result[4][1]);
        g2.drawLine(result[3][0], result[3][1], result[1][0], result[1][1]);
        g2.drawLine(result[3][0], result[3][1], result[2][0], result[2][1]);
        g2.drawLine(result[3][0], result[3][1], result[7][0], result[7][1]);
        g2.drawLine(result[6][0], result[6][1], result[4][0], result[4][1]);
        g2.drawLine(result[6][0], result[6][1], result[2][0], result[2][1]);
        g2.drawLine(result[6][0], result[6][1], result[7][0], result[7][1]);
        g2.drawLine(result[5][0], result[5][1], result[1][0], result[1][1]);
        g2.drawLine(result[5][0], result[5][1], result[4][0], result[4][1]);
        g2.drawLine(result[5][0], result[5][1], result[7][0], result[7][1]);
    }

    public void drawPlayer(int x, int y, Player player, Graphics2D g2) {
        g2.setColor(player.getColor());
        g2.fillOval(x, y, 10, 10);
    }

    public void drawFace(int[][] result, Graphics2D g2) {
        Polygon[] polygons = new Polygon[6];
        Polygon polygon0 = new Polygon();
        polygon0.addPoint(result[0][0], result[0][1]);
        polygon0.addPoint(result[1][0], result[1][1]);
        polygon0.addPoint(result[3][0], result[3][1]);
        polygon0.addPoint(result[2][0], result[2][1]);
        polygons[0] = polygon0;
        Polygon polygon1 = new Polygon();
        polygon1.addPoint(result[2][0], result[2][1]);
        polygon1.addPoint(result[3][0], result[3][1]);
        polygon1.addPoint(result[7][0], result[7][1]);
        polygon1.addPoint(result[6][0], result[6][1]);
        polygons[1] = polygon1;
        Polygon polygon2 = new Polygon();
        polygon2.addPoint(result[6][0], result[6][1]);
        polygon2.addPoint(result[7][0], result[7][1]);
        polygon2.addPoint(result[5][0], result[5][1]);
        polygon2.addPoint(result[4][0], result[4][1]);
        polygons[2] = polygon2;
        Polygon polygon3 = new Polygon();
        polygon3.addPoint(result[4][0], result[4][1]);
        polygon3.addPoint(result[5][0], result[5][1]);
        polygon3.addPoint(result[1][0], result[1][1]);
        polygon3.addPoint(result[0][0], result[0][1]);
        polygons[3] = polygon3;
        Polygon polygon4 = new Polygon();
        polygon4.addPoint(result[4][0], result[4][1]);
        polygon4.addPoint(result[0][0], result[0][1]);
        polygon4.addPoint(result[2][0], result[2][1]);
        polygon4.addPoint(result[6][0], result[6][1]);
        polygons[4] = polygon4;
        Polygon polygon5 = new Polygon();
        polygon5.addPoint(result[1][0], result[1][1]);
        polygon5.addPoint(result[5][0], result[5][1]);
        polygon5.addPoint(result[7][0], result[7][1]);
        polygon5.addPoint(result[3][0], result[3][1]);
        polygons[5] = polygon5;
        double max = 0.0;
        int index = 0;
        for (int i = 0; i < 6; ++i) {
            double area = this.getArea(polygons[i]);
            if (!(area > max)) continue;
            max = area;
            index = i;
        }
        double[] vector = this.getVector(index);
        ArrayList<Integer> toDraw = new ArrayList<Integer>();
        if (!this.checkVector(vector)) {
            switch (index) {
                case 0: {
                    index = 2;
                    break;
                }
                case 1: {
                    index = 3;
                    break;
                }
                case 2: {
                    index = 0;
                    break;
                }
                case 3: {
                    index = 1;
                    break;
                }
                case 4: {
                    index = 5;
                    break;
                }
                case 5: {
                    index = 4;
                }
            }
        }
        toDraw.add(index);
        toDraw.addAll(this.otherFaces(index, polygons));
        this.drawFaces(toDraw, polygons, g2);
    }

    public void drawFaces(List<Integer> toDraw, Polygon[] polygons, Graphics2D g2) {
        g2.setComposite(AlphaComposite.getInstance(3, 0.3f));
        for (Integer i : toDraw) {
            double[] vector = this.getVector(i);
            g2.setColor(this.getColor(this.getAngle(vector)));
            g2.fillPolygon(polygons[i]);
        }
    }

    public Color determineColor() {
        if (this.bigCube.getBigCube()[this.selection[0]][this.selection[1]][this.selection[2]].getPlayer() == null) {
            return Color.WHITE;
        }
        return this.bigCube.getBigCube()[this.selection[0]][this.selection[1]][this.selection[2]].getPlayer().getColor();
    }

    public double[] getVector(int index) {
        double[] vector = new double[3];
        double[][] coordinates = this.bigCube.getBigCube()[0][0][0].getCoordinates();
        switch (index) {
            case 0: {
                vector[0] = coordinates[0][0] - coordinates[4][0];
                vector[1] = coordinates[0][1] - coordinates[4][1];
                vector[2] = coordinates[0][2] - coordinates[4][2];
                break;
            }
            case 1: {
                vector[0] = coordinates[2][0] - coordinates[0][0];
                vector[1] = coordinates[2][1] - coordinates[0][1];
                vector[2] = coordinates[2][2] - coordinates[0][2];
                break;
            }
            case 2: {
                vector[0] = coordinates[4][0] - coordinates[0][0];
                vector[1] = coordinates[4][1] - coordinates[0][1];
                vector[2] = coordinates[4][2] - coordinates[0][2];
                break;
            }
            case 3: {
                vector[0] = coordinates[0][0] - coordinates[2][0];
                vector[1] = coordinates[0][1] - coordinates[2][1];
                vector[2] = coordinates[0][2] - coordinates[2][2];
                break;
            }
            case 4: {
                vector[0] = coordinates[0][0] - coordinates[1][0];
                vector[1] = coordinates[0][1] - coordinates[1][1];
                vector[2] = coordinates[0][2] - coordinates[1][2];
                break;
            }
            case 5: {
                vector[0] = coordinates[1][0] - coordinates[0][0];
                vector[1] = coordinates[1][1] - coordinates[0][1];
                vector[2] = coordinates[1][2] - coordinates[0][2];
            }
        }
        return vector;
    }

    public double getAngle(double[] faceVector) {
        double[] sunVector = new double[]{-0.95, -1.0, -1.0};
        return Math.acos((sunVector[0] * faceVector[0] + sunVector[1] * faceVector[1] + sunVector[2] * faceVector[2]) / (Math.sqrt(sunVector[0] * sunVector[0] + sunVector[1] * sunVector[1] + sunVector[2] * sunVector[2]) * Math.sqrt(faceVector[0] * faceVector[0] + faceVector[1] * faceVector[1] + faceVector[2] * faceVector[2])));
    }

    public Color getColor(double angle) {
        double p = angle / Math.PI;
        Color color = this.determineColor();
        int blue = color.getBlue();
        int red = color.getRed();
        int green = color.getGreen();
        return new Color((int)((double)red - (double)red * p), (int)((double)green - (double)green * p), (int)((double)blue - (double)blue * p));
    }

    public List<Integer> otherFaces(int index, Polygon[] polygons) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        Polygon current = polygons[index];
        for (int k = 0; k < 6; ++k) {
            Polygon p = polygons[k];
            if (p == current) continue;
            boolean state = true;
            for (int i = 0; i < 4; ++i) {
                boolean state2 = true;
                for (int j = 0; j < 4; ++j) {
                    if (current.xpoints[j] != p.xpoints[i] || current.ypoints[j] != p.ypoints[i]) continue;
                    state2 = false;
                }
                if (!current.contains(p.xpoints[i], p.ypoints[i]) || !state2) continue;
                state = false;
            }
            if (!state) continue;
            result.add(k);
        }
        return result;
    }

    public double getArea(Polygon polygon) {
        int[] x = polygon.xpoints;
        int[] y = polygon.ypoints;
        int[] p = new int[]{x[3], y[3]};
        double distance = (double)Math.abs((y[1] - y[0]) * p[0] - (x[1] - x[0]) * p[1] + x[1] * y[0] - y[1] * x[0]) / Math.sqrt((y[1] - y[0]) * (y[1] - y[0]) + (x[1] - x[0]) * (x[1] - x[0]));
        double base = Math.sqrt((x[1] - x[0]) * (x[1] - x[0]) + (y[1] - y[0]) * (y[1] - y[0]));
        return base * distance;
    }

    public boolean checkVector(double[] vector) {
        double angle = Math.acos((vector[0] * 0.0 + vector[1] * 0.0 + vector[2] * 1.0) / (Math.sqrt(vector[0] * vector[0] + vector[1] * vector[1] + vector[2] * vector[2]) * 1.0));
        return angle > 1.5707963267948966;
    }

    public void move(double[] delta) {
        this.bigCube.move(delta[0], delta[1], delta[2]);
    }

    public void rotate(double[] delta) {
        this.bigCube.rotate(delta[0], delta[1], delta[2]);
    }

    public void moveSelection(int[] delta) {
        this.bigCube.moveSelection(delta);
    }

    public class MoveListener
    implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
        }
    }
}

