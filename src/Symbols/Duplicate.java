package Symbols;

import Main.DynType;
import Main.Factage;

public class Duplicate extends Square {
    public static final char icon = ':';

    public Duplicate(Factage program, int y, int x) {
        super(program, y, x);
        assignDynType(new DynType(null));
    }

    @Override
    public void updateSquare(boolean[][] updated) {
        Square[][] programArray = program.getProgramArray();
        updated[y][x] = true;

        if(x == 0 || x == programArray[y].length - 1) {
            return;
        }

        if(programArray[y][x - 1].isOperable() && !updated[y][x - 1]) {
            // duplicate from left to right
            DynType dt = programArray[y][x - 1].getDynType();
            shiftSquare(programArray[y][x + 1], dt, updated);
            //updated[y][x + 1] = false;
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
