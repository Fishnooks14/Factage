package Symbols;

import Main.Factage;

public class LeftConveyor extends Square {

    public static final char icon = '{';

    public LeftConveyor(Factage program, int y, int x) {
        super(program, y, x);
    }

    @Override
    public void updateSquare(boolean[][] updated) {
        Square[][] programArray = program.getProgramArray();
        updated[y][x] = true;

        if (this.dynType == null) {
            return;
        }

        if (x == 0)
            this.assignDynType(null);
        else {
            shiftSquare(programArray[y][x - 1], updated);
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
