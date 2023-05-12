package Symbols;

import Main.Factage;

public class Printer extends Square {

    public static final char icon = '@';

    public Printer(Factage program, int y, int x) {
        super(program, y, x);
    }

    @Override
    public void updateSquare(boolean[][] updated) {
        if (dynType != null)
            if (dynType.getIcon() == ';')
                program.setActive(false);
            else if (dynType.getIcon() == '\\')
                System.out.println();
            else if (dynType.getIcon() == '\"')
                System.out.print(' ');
            else
                System.out.print(dynType.value());

        Square[][] programArray = program.getProgramArray();
        updated[y][x] = true;

        if (this.dynType == null) {
            return;
        }

        if (y == programArray.length - 1)
            this.assignDynType(null);
        else {
            shiftSquare(programArray[y + 1][x], updated);
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
