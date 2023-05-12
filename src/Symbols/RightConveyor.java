package Symbols;

import Main.Factage;

public class RightConveyor extends Square {

    public static final char icon = '}';

    public RightConveyor(Factage program, int y, int x) {
        super(program, y, x);
    }

    @Override
    public void updateSquare(boolean[][] updated) {
        Square[][] programArray = program.getProgramArray();
        updated[y][x] = true;

        if (this.dynType == null) {
            return;
        }

        if (x == programArray[y].length - 1)
            this.assignDynType(null);
        else {
            shiftSquare(programArray[y][x + 1], updated);
        }
    }

    @Override
    public char getIcon() {
        if (isOperable())
            return dynType.getIcon();
        else
            return icon;
    }

    @Override
    public boolean isOperator() {
        return false;
    }
}
