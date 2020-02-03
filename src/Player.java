import java.awt.Color;

public class Player {
    private boolean[][][] selected;
    private BigCube bigCube;
    private Color color;
    private int amount;
    private int goal;

    public Player(int amount, BigCube bigCube, Color color, int goal) {
        this.amount = amount;
        this.goal = goal;
        this.selected = new boolean[amount][amount][amount];
        this.initializeSelected(amount);
        this.bigCube = bigCube;
        this.color = color;
    }

    private void initializeSelected(int amount) {
        for (int x = 0; x < amount; ++x) {
            for (int y = 0; y < amount; ++y) {
                for (int z = 0; z < amount; ++z) {
                    this.selected[x][y][z] = false;
                }
            }
        }
    }

    public void addSelected(int[] selection) {
        this.selected[selection[0]][selection[1]][selection[2]] = true;
        this.bigCube.getBigCube()[selection[0]][selection[1]][selection[2]].setPlayer(this);
    }

    public boolean checkRows() {
        for (int x = 0; x < this.amount; ++x) {
            for (int y = 0; y < this.amount; ++y) {
                for (int z = 0; z < this.amount; ++z) {
                    if (!this.checkCube(x, y, z)) continue;
                    return true;
                }
            }
        }
        return false;
    }

    public boolean checkCube(int x, int y, int z) {
        return this.checkX(x, y, z) || this.checkY(x, y, z) || this.checkZ(x, y, z) || this.checkDiagonal1(x, y, z) || this.checkDiagonal2(x, y, z) || this.checkDiagonal3(x, y, z) || this.checkFlatDiagonal1(x, y, z) || this.checkFlatDiagonal2(x, y, z) || this.checkFlatDiagonal3(x, y, z) || this.checkFlatDiagonal4(x, y, z) || this.checkFlatDiagonal5(x, y, z) || this.checkFlatDiagonal6(x, y, z);
    }

    public boolean checkX(int x, int y, int z) {
        for (int i = 0; i < this.goal; ++i) {
            if (i + x < this.amount) {
                if (this.bigCube.getBigCube()[x + i][y][z].getPlayer() == this) continue;
                return false;
            }
            return false;
        }
        return true;
    }

    public boolean checkY(int x, int y, int z) {
        for (int i = 0; i < this.goal; ++i) {
            if (i + y < this.amount) {
                if (this.bigCube.getBigCube()[x][y + i][z].getPlayer() == this) continue;
                return false;
            }
            return false;
        }
        return true;
    }

    public boolean checkZ(int x, int y, int z) {
        for (int i = 0; i < this.goal; ++i) {
            if (i + z < this.amount) {
                if (this.bigCube.getBigCube()[x][y][z + i].getPlayer() == this) continue;
                return false;
            }
            return false;
        }
        return true;
    }

    public boolean checkDiagonal1(int x, int y, int z) {
        for (int i = 0; i < this.goal; ++i) {
            for (int j = 0; j < this.goal; ++j) {
                for (int k = 0; k < this.goal; ++k) {
                    if (i != j || j != k) continue;
                    if (x + i < this.amount && y + j < this.amount && z + k < this.amount) {
                        if (this.bigCube.getBigCube()[x + i][y + j][z + k].getPlayer() == this) continue;
                        return false;
                    }
                    return false;
                }
            }
        }
        return true;
    }

    public boolean checkDiagonal2(int x, int y, int z) {
        for (int i = 0; i < this.goal; ++i) {
            for (int j = 0; j < this.goal; ++j) {
                for (int k = 0; k < this.goal; ++k) {
                    if (i != j || j != k) continue;
                    if (x + i < this.amount && y - j >= 0 && z + k < this.amount) {
                        if (this.bigCube.getBigCube()[x + i][y - j][z + k].getPlayer() == this) continue;
                        return false;
                    }
                    return false;
                }
            }
        }
        return true;
    }

    public boolean checkDiagonal3(int x, int y, int z) {
        for (int i = 0; i < this.goal; ++i) {
            for (int j = 0; j < this.goal; ++j) {
                for (int k = 0; k < this.goal; ++k) {
                    if (i != j || j != k) continue;
                    if (x - i >= 0 && y + j < this.amount && z + k < this.amount) {
                        if (this.bigCube.getBigCube()[x - i][y + j][z + k].getPlayer() == this) continue;
                        return false;
                    }
                    return false;
                }
            }
        }
        return true;
    }

    public boolean checkFlatDiagonal1(int x, int y, int z) {
        for (int i = 0; i < this.goal; ++i) {
            for (int j = 0; j < this.goal; ++j) {
                if (i != j) continue;
                if (x + i < this.amount && y + j < this.amount) {
                    if (this.bigCube.getBigCube()[x + i][y + j][z].getPlayer() == this) continue;
                    return false;
                }
                return false;
            }
        }
        return true;
    }

    public boolean checkFlatDiagonal2(int x, int y, int z) {
        for (int i = 0; i < this.goal; ++i) {
            for (int j = 0; j < this.goal; ++j) {
                if (i != j) continue;
                if (x - i >= 0 && y + j < this.amount) {
                    if (this.bigCube.getBigCube()[x - i][y + j][z].getPlayer() == this) continue;
                    return false;
                }
                return false;
            }
        }
        return true;
    }

    public boolean checkFlatDiagonal3(int x, int y, int z) {
        for (int i = 0; i < this.goal; ++i) {
            for (int j = 0; j < this.goal; ++j) {
                if (i != j) continue;
                if (x + i < this.amount && z + j < this.amount) {
                    if (this.bigCube.getBigCube()[x + i][y][z + j].getPlayer() == this) continue;
                    return false;
                }
                return false;
            }
        }
        return true;
    }

    public boolean checkFlatDiagonal4(int x, int y, int z) {
        for (int i = 0; i < this.goal; ++i) {
            for (int j = 0; j < this.goal; ++j) {
                if (i != j) continue;
                if (x - i >= 0 && z + j < this.amount) {
                    if (this.bigCube.getBigCube()[x - i][y][z + j].getPlayer() == this) continue;
                    return false;
                }
                return false;
            }
        }
        return true;
    }

    public boolean checkFlatDiagonal5(int x, int y, int z) {
        for (int i = 0; i < this.goal; ++i) {
            for (int j = 0; j < this.goal; ++j) {
                if (i != j) continue;
                if (y + i < this.amount && z + j < this.amount) {
                    if (this.bigCube.getBigCube()[x][y + i][z + j].getPlayer() == this) continue;
                    return false;
                }
                return false;
            }
        }
        return true;
    }

    public boolean checkFlatDiagonal6(int x, int y, int z) {
        for (int i = 0; i < this.goal; ++i) {
            for (int j = 0; j < this.goal; ++j) {
                if (i != j) continue;
                if (y - i >= 0 && z + j < this.amount) {
                    if (this.bigCube.getBigCube()[x][y - i][z + j].getPlayer() == this) continue;
                    return false;
                }
                return false;
            }
        }
        return true;
    }

    public Color getColor() {
        return this.color;
    }
}

