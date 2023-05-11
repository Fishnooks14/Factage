package Symbols;

import Main.Factage;

public class DeleteValue extends Square {
    public static final char icon = '#';

    public DeleteValue(Factage program, int y, int x) {
        super(program, y, x);
    }

    @Override
    public void updateSquare(boolean[][] updated) {
        assignDynType(null);
        updated[y][x] = true;
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
