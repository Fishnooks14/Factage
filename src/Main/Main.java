package Main;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        final long startTime = System.nanoTime();

            Factage test = new Factage();
            while (test.getActive()) {
                test.updateProgram();
            }
        final long duration = System.nanoTime() - startTime;
        final double seconds = ((long)((duration * 1e-9) * 1e4)) / 1e4d;
        System.out.println("Program ran successfully in: " + (seconds) + " seconds");
    }
}