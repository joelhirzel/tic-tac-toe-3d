import java.util.Arrays;

public class BigCube {
    private Cube[][][] bigCube;
    private double[][] coordinatesFrame;
    private double[] center;
    private double[] rotation;
    private int[] selection = null;
    private int amount;

    public BigCube(int amount) {
        this.amount = amount;
        this.bigCube = this.createBigCube();
        this.center = new double[]{0.0, 0.0, 0.0};
        this.rotation = new double[]{0.7853981633974483, 0.7853981633974483, 0.0};
        double halfSide = (double)amount * this.bigCube[0][0][0].getSide() / 2.0;
        this.coordinatesFrame = new double[][]{{-halfSide, -halfSide, -halfSide}, {halfSide, -halfSide, -halfSide}, {-halfSide, halfSide, -halfSide}, {halfSide, halfSide, -halfSide}, {-halfSide, -halfSide, halfSide}, {halfSide, -halfSide, halfSide}, {-halfSide, halfSide, halfSide}, {halfSide, halfSide, halfSide}};
    }

    public void setCubes(Cube[][][] bigCube) {
        this.bigCube = bigCube;
    }

    public Cube[][][] getBigCube() {
        return this.bigCube;
    }

    public double[] getCenter() {
        return this.center;
    }

    public double[] getRotation() {
        return this.rotation;
    }

    public int getAmount() {
        return this.amount;
    }

    public double[][] getCoordinatesFrame() {
        double[][] result = new double[8][3];
        for (int i = 0; i < 8; ++i) {
            result[i] = Arrays.copyOf(this.coordinatesFrame[i], this.coordinatesFrame[i].length);
        }
        this.updateRotation(result);
        this.updateCoordinates(result);
        return result;
    }

    public void updateCoordinates(double[][] result) {
        for (int i = 0; i < 8; ++i) {
            result[i][0] = result[i][0] + this.center[0];
            result[i][1] = result[i][1] + this.center[1];
            result[i][2] = result[i][2] + this.center[2];
        }
    }

    public void updateRotation(double[][] result) {
        double tmp2;
        double tmp0;
        int i;
        for (i = 0; i < 8; ++i) {
            tmp0 = result[i][0];
            tmp2 = result[i][2];
            result[i][0] = tmp0 * Math.cos(this.rotation[1]) - tmp2 * Math.sin(this.rotation[1]);
            result[i][2] = tmp0 * Math.sin(this.rotation[1]) + tmp2 * Math.cos(this.rotation[1]);
        }
        for (i = 0; i < 8; ++i) {
            double tmp1 = result[i][1];
            tmp2 = result[i][2];
            result[i][1] = tmp1 * Math.cos(this.rotation[0]) - tmp2 * Math.sin(this.rotation[0]);
            result[i][2] = tmp1 * Math.sin(this.rotation[0]) + tmp2 * Math.cos(this.rotation[0]);
        }
        for (i = 0; i < 8; ++i) {
            tmp0 = result[i][0];
            double tmp1 = result[i][1];
            result[i][0] = tmp0 * Math.cos(this.rotation[2]) - tmp1 * Math.sin(this.rotation[2]);
            result[i][1] = tmp0 * Math.sin(this.rotation[2]) + tmp1 * Math.cos(this.rotation[2]);
        }
    }

    public void move(double deltaX, double deltaY, double deltaZ) {
        this.center[0] = this.center[0] + deltaX;
        this.center[1] = this.center[1] + deltaY;
        this.center[2] = this.center[2] + deltaZ;
    }

    public void rotate(double angleX, double angleY, double angleZ) {
        this.rotation[0] = this.rotation[0] + angleX;
        this.rotation[1] = this.rotation[1] + angleY;
        this.rotation[2] = this.rotation[2] + angleZ;
    }

    public Cube[][][] createBigCube() {
        Cube[][][] result = new Cube[this.amount][this.amount][this.amount];
        for (int x = 0; x < this.amount; ++x) {
            for (int y = 0; y < this.amount; ++y) {
                for (int z = 0; z < this.amount; ++z) {
                    result[x][y][z] = new Cube(x, y, z, this);
                }
            }
        }
        return result;
    }

    public void moveSelection(int[] delta) {
        if (this.selection == null) {
            this.selection = new int[]{0, 0, 0};
        } else {
            this.selection[0] = Math.floorMod(this.selection[0] + delta[0], this.bigCube.length);
            this.selection[1] = Math.floorMod(this.selection[1] + delta[1], this.bigCube.length);
            this.selection[2] = Math.floorMod(this.selection[2] + delta[2], this.bigCube.length);
        }
    }

    public int[] getSelection() {
        return this.selection;
    }
}

