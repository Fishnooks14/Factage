package Symbols;

import Main.DynType;
import Main.Factage;

public class Inversion extends Square {

    public static final char icon = '!';

    public Inversion(Factage program, int y, int x) {
        super(program, y, x);
        assignDynType(new DynType<>(null));
    }

    @Override
    public void updateSquare(boolean[][] updated) {
        Square[][] programArray = program.getProgramArray();
        updated[y][x] = true;

        if(x == 0 || x == programArray[y].length - 1) {
            System.out.println("hi");
            return;
        }

        if(programArray[y][x - 1].isOperable() && !updated[y][x - 1]) {
            // duplicate from left to right
            int out = programArray[y][x - 1].getDynType().getOperableValue() == 0 ? 1:0;
            DynType<Integer> dt = new DynType<>(out);
            shiftSquare(programArray[y][x + 1], dt, updated);
            programArray[y][x - 1].assignDynType(null);
            // updated[y][x + 1] = false;
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
