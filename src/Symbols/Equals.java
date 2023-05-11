package Symbols;

import Main.DynType;
import Main.Factage;

public class Equals extends Square {

    public static final char icon = '=';

    public Equals(Factage program, int y, int x) {
        super(program, y, x);
        assignDynType(new DynType<>(null));
    }

    @Override
    public void updateSquare(boolean[][] updated) {
        Square[][] programArray = program.getProgramArray();

        if(x == 0 || x == programArray[y].length - 1 || updated[y][x - 1] || updated[y][x + 1]) {
            return;
        }

        updated[y][x] = true;

        if(programArray[y][x - 1].isOperable() && programArray[y][x + 1].isOperable()) {
            boolean equals = programArray[y][x - 1].getDynType().value() == programArray[y][x + 1].getDynType().value();
            DynType<Integer> dt = new DynType<>(equals ? 1:0);
            programArray[y][x - 1].assignDynType(null);
            programArray[y][x + 1].assignDynType(null);
            if(y != programArray.length - 1) {
                shiftSquare(programArray[y + 1][x], dt, updated);
            }
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
