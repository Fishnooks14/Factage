package Symbols;

import Main.DynType;
import Main.Factage;

public class Wall extends Square {

    public static final char icon = '_';

    public Wall(Factage program, int y, int x) {
        super(program, y, x);
        assignDynType(new DynType<Object>(null));
    }

    @Override
    public void updateSquare(boolean[][] updated) {
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
