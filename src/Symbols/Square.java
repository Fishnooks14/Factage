package Symbols;

import Main.DynType;
import Main.Factage;

public abstract class Square {
    protected final int y;
    protected final int x;
    protected Factage program;
    protected DynType dynType;

    public Square(Factage program, int y, int x) {
        this.program = program;
        this.y = y;
        this.x = x;
        this.dynType = null;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public DynType getDynType() {
        return dynType;
    }

    public void assignDynType(DynType dt) {
        this.dynType = dt;
    }

    public abstract void updateSquare(boolean[][] updated);

    public abstract char getIcon();

    protected boolean isOperable() {
        return dynType != null && dynType.value() != null;
    }

    protected void shiftSquare(Square dest, boolean[][] updated) {
        if (dest.getDynType() == null) {
            dest.assignDynType(dynType);
            this.assignDynType(null);
            updated[dest.getY()][dest.getX()] = true;
        } else if (!dest.isUpdated(updated)) {
            updated[getY()][getX()] = true;
            dest.checkUpdates(updated);
            if (dest.getDynType() == null) {
                dest.assignDynType(dynType);
                this.assignDynType(null);
                updated[dest.getY()][dest.getX()] = true;
            }
        }
    }

    protected void shiftSquare(Square dest, DynType dt, boolean[][] updated) {
        if (dest.getDynType() == null) {
            dest.assignDynType(dt);
            updated[dest.getY()][dest.getX()] = true;
        } else if (!dest.isUpdated(updated)) {
            updated[getY()][getX()] = true; // ?
            dest.checkUpdates(updated);
            if (dest.getDynType() == null) {
                dest.assignDynType(dt);
                updated[dest.getY()][dest.getX()] = true;
            }
        }
    }

    public void checkUpdates(boolean[][] updated) {
        Square[][] programArray = program.getProgramArray();
        for (int yInc = -1; yInc <= 1; yInc++) {
            for (int xInc = -1; xInc <= 1; xInc++) {
                if (y + yInc >= 0 && y + yInc < programArray.length && x + xInc >= 0 && x + xInc < programArray[y].length) {
                    if (programArray[y + yInc][x + xInc].isOperator() && !programArray[y + yInc][x + xInc].isUpdated(updated)) {
                        programArray[y + yInc][x + xInc].updateSquare(updated);
                    }
                }
            }
        }
        if (!updated[y][x])
            updateSquare(updated);
    }

    public abstract boolean isOperator();

    public boolean isUpdated(boolean[][] updated) {
        return updated[y][x];
    }

    protected void getDebugStats() {
        System.out.println(this.getClass() + ", (" + y + ", " + x + ")");
    }
}