import java.util.*;
import java.io.*;

class Main {

    void run() throws Exception {
        BufferedReader scanner = new BufferedReader(new InputStreamReader(System.in));
        int sum = -1;
        int n = 0, a = 0;
        while ((sum = Integer.parseInt(scanner.readLine())) != -1) {
            for (n = (int) Math.sqrt(2.0 * sum); n > 0; --n) {
                if ((2 * sum + n - n * n) % (2 * n) == 0) {
                    a = ((2 * sum + n - n * n) / (2 * n));
                    break;
                }
            }

            System.out.printf("%d = %d + ... + %d%n", sum, a, a + n - 1);
        }
    }

    public static void main(String[] args) throws Exception {
        Main program = new Main();
        program.run();
    }
}
