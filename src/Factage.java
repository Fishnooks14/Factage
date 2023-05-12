import java.io.File;
import java.io.IOException;

public class Factage {
    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            System.out.println("Please specify a file name");
            System.out.println("Example: java Factage program.txt");
            return;
        }
        Main.Factage test = new Main.Factage(new File(args[0]));
        final long startTime = System.nanoTime();
        while (test.getActive()) {
            test.updateProgram();
        }
        final long duration = System.nanoTime() - startTime;
        final double seconds = ((long) ((duration * 1e-9) * 1e4)) / 1e4d;
        System.out.println("Program ran successfully in: " + (seconds) + " seconds");
    }
}