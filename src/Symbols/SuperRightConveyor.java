package Symbols;

import Main.Factage;

public class SuperRightConveyor extends Square {
    public static final char icon = ']';

    public SuperRightConveyor(Factage program, int y, int x) {
        super(program, y, x);
    }

    @Override
    public void updateSquare(boolean[][] updated) {
        Square[][] programArray = program.getProgramArray();
        updated[y][x] = true;

        if (this.dynType == null) {
            return;
        }

        if (x >= programArray[y].length - 2)
            this.assignDynType(null);
        else {
            shiftSquare(programArray[y][x + 2], updated);
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
