package Symbols;

import Main.DynType;
import Main.Factage;

public class LogicalConveyor extends Square {
    public static final char icon = '?';

    public LogicalConveyor(Factage program, int y, int x) {
        super(program, y, x);
        assignDynType(new DynType<>(null));
    }

    @Override
    public void updateSquare(boolean[][] updated) {
        Square[][] programArray = program.getProgramArray();
        if (y == programArray.length - 1 || y == 0 || !programArray[y - 1][x].isOperable() || !programArray[y + 1][x].isOperable() || updated[y - 1][x] || updated[y + 1][x]) {
            updated[y][x] = true;
            return;
        }

        if (programArray[y + 1][x].getDynType().getOperableValue() == 0) {
            // shift right
            DynType dt = programArray[y - 1][x].getDynType();
            programArray[y - 1][x].assignDynType(null);
            if (x != programArray[y].length - 1)
                shiftSquare(programArray[y][x + 1], dt, updated);
        } else {
            // shift left
            DynType dt = programArray[y - 1][x].getDynType();
            programArray[y - 1][x].assignDynType(null);
            if (x != 0)
                shiftSquare(programArray[y][x - 1], dt, updated);
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
        return true;
    }
}
