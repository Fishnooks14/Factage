package Main;

import Symbols.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.util.ArrayList;

public class Factage {
    private final Square[][] program;
    private boolean active;

    public Factage(File f) throws IOException {
        active = true;
        BufferedReader reader = new BufferedReader(new FileReader(f));

        ArrayList<String> rawProgram = new ArrayList<>();
        int maxLineLen = 0;
        String line;
        while ((line = reader.readLine()) != null) {
            rawProgram.add(line);
            maxLineLen = Math.max(maxLineLen, line.length());
        }
        reader.close();
        program = new Square[rawProgram.size()][maxLineLen];

        boolean hasPrinter = false;
        for (int i = 0; i < rawProgram.size(); i++) {
            for (int j = 0; j < maxLineLen; j++) {
                char symbol = (j >= rawProgram.get(i).length()) ? ' ' : rawProgram.get(i).charAt(j);
                switch (symbol) {
                    case ('@') -> program[i][j] = new Printer(this, i, j);
                    case ('+') -> program[i][j] = new Addition(this, i, j);
                    case ('-') -> program[i][j] = new Subtraction(this, i, j);
                    case ('*') -> program[i][j] = new Multiplication(this, i, j);
                    case ('/') -> program[i][j] = new Division(this, i, j);
                    case (':') -> program[i][j] = new Duplicate(this, i, j);
                    case ('=') -> program[i][j] = new Equals(this, i, j);
                    case ('<') -> program[i][j] = new LessThan(this, i, j);
                    case ('>') -> program[i][j] = new GreaterThan(this, i, j);
                    case ('!') -> program[i][j] = new Inversion(this, i, j);
                    case ('{') -> program[i][j] = new LeftConveyor(this, i, j);
                    case ('}') -> program[i][j] = new RightConveyor(this, i, j);
                    case ('_') -> program[i][j] = new Wall(this, i, j);
                    case ('#') -> program[i][j] = new DeleteValue(this, i, j);
                    case ('^') -> program[i][j] = new UpConveyor(this, i, j);
                    case (' ') -> program[i][j] = new Gravity(this, i, j);
                    case ('\'') -> program[i][j] = new SuperUpConveyor(this, i, j);
                    case (',') -> program[i][j] = new SuperDownConveyor(this, i, j);
                    case ('[') -> program[i][j] = new SuperLeftConveyor(this, i, j);
                    case (']') -> program[i][j] = new SuperRightConveyor(this, i, j);
                    case ('?') -> program[i][j] = new LogicalConveyor(this, i, j);
                    case ('%') -> program[i][j] = new Modulus(this, i, j);
                    case ('&') -> program[i][j] = new And(this, i, j);
                    case ('|') -> program[i][j] = new Or(this, i, j);
                    default -> {
                        if (Character.isDigit(symbol)) {
                            program[i][j] = new Gravity(this, i, j);
                            program[i][j].assignDynType(new DynType<>(Character.getNumericValue(symbol)));
                        } else if (Character.isAlphabetic(symbol) || symbol == ';' || symbol == '\"' || symbol == '\\') {
                            program[i][j] = new Gravity(this, i, j);
                            program[i][j].assignDynType(new DynType<>(symbol));
                        } else {
                            throw new IllegalArgumentException(symbol + " is not a valid character");
                        }
                    }
                }

                if(symbol == '@')
                    if(hasPrinter)
                        throw new IllegalArgumentException("Your program may only have one printer('@').");
                    else
                        hasPrinter = true;
            }
        }
    }

    public void printProgram() {
        for (Square[] squares : program) {
            for (Square square : squares) {
                System.out.print(square.getIcon());
            }
            System.out.println();
        }
    }

    public void updateProgram() {
        boolean[][] updated = new boolean[program.length][program[0].length];
        for (int i = 0; i < program.length; i++) {
            for (int j = 0; j < program[i].length; j++) {
                if (!updated[i][j])
                    program[i][j].checkUpdates(updated);
            }
        }
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Square[][] getProgramArray() {
        return program;
    }
}
