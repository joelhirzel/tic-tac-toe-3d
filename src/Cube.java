import java.util.Arrays;

public class Cube {
    private double side = 100.0;
    private double[][] coordinates;
    private int indexX;
    private int indexY;
    private int indexZ;
    private BigCube parentCube;
    private Player player = null;

    public Cube(int indexX, int indexY, int indexZ, BigCube parentCube) {
        this.parentCube = parentCube;
        this.indexX = indexX;
        this.indexY = indexY;
        this.indexZ = indexZ;
        double offsetX = this.side * (double)indexX - (double)parentCube.getAmount() * this.side / 2.0;
        double offsetY = this.side * (double)indexY - (double)parentCube.getAmount() * this.side / 2.0;
        double offsetZ = this.side * (double)indexZ - (double)parentCube.getAmount() * this.side / 2.0;
        this.coordinates = new double[][]{{offsetX, offsetY, offsetZ}, {offsetX + this.side, offsetY, offsetZ}, {offsetX, offsetY + this.side, offsetZ}, {offsetX + this.side, offsetY + this.side, offsetZ}, {offsetX, offsetY, offsetZ + this.side}, {offsetX + this.side, offsetY, offsetZ + this.side}, {offsetX, offsetY + this.side, offsetZ + this.side}, {offsetX + this.side, offsetY + this.side, offsetZ + this.side}};
    }

    public double[][] getCoordinates() {
        double[][] result = new double[8][3];
        for (int i = 0; i < 8; ++i) {
            result[i] = Arrays.copyOf(this.coordinates[i], this.coordinates[i].length);
        }
        this.updateRotation(result);
        this.updateCoordinates(result);
        return result;
    }

    public double getSide() {
        return this.side;
    }

    public void updateCoordinates(double[][] result) {
        double[] center = this.parentCube.getCenter();
        for (int i = 0; i < 8; ++i) {
            result[i][0] = result[i][0] + center[0];
            result[i][1] = result[i][1] + center[1];
            result[i][2] = result[i][2] + center[2];
        }
    }

    public void updateRotation(double[][] result) {
        double tmp2;
        double tmp0;
        int i;
        double[] rotation = this.parentCube.getRotation();
        for (i = 0; i < 8; ++i) {
            tmp0 = result[i][0];
            tmp2 = result[i][2];
            result[i][0] = tmp0 * Math.cos(rotation[1]) - tmp2 * Math.sin(rotation[1]);
            result[i][2] = tmp0 * Math.sin(rotation[1]) + tmp2 * Math.cos(rotation[1]);
        }
        for (i = 0; i < 8; ++i) {
            double tmp1 = result[i][1];
            tmp2 = result[i][2];
            result[i][1] = tmp1 * Math.cos(rotation[0]) - tmp2 * Math.sin(rotation[0]);
            result[i][2] = tmp1 * Math.sin(rotation[0]) + tmp2 * Math.cos(rotation[0]);
        }
        for (i = 0; i < 8; ++i) {
            tmp0 = result[i][0];
            double tmp1 = result[i][1];
            result[i][0] = tmp0 * Math.cos(rotation[2]) - tmp1 * Math.sin(rotation[2]);
            result[i][1] = tmp0 * Math.sin(rotation[2]) + tmp1 * Math.cos(rotation[2]);
        }
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return this.player;
    }
}

