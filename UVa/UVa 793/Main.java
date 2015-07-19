import java.util.*;
import java.io.*;

class Main {

    int[] f;

    void run() throws Exception {
        BufferedReader scanner = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(scanner.readLine());
        scanner.readLine();

        while (TC-- > 0) {
            int success = 0, fail = 0;
            int numOfComp = Integer.parseInt(scanner.readLine());
            f = new int[numOfComp + 1];

            for (int i = 0; i < numOfComp + 1; ++i) {
                f[i] = i;
            }

            String inputs;
            while ((inputs = scanner.readLine()) != null && !inputs.isEmpty()) {
                String[] commands = inputs.split(" ");
                int comp1 = Integer.parseInt(commands[1]);
                int comp2 = Integer.parseInt(commands[2]);

                if (commands[0].equalsIgnoreCase("c")) {
                    f[find(comp1)] = find(comp2); 
                } else {
                    if (f[find(comp1)] == f[find(comp2)]) {
                        success++;
                    } else {
                        fail++;
                    }
                }
            }

            System.out.printf("%d,%d%n", success, fail);
            if (TC > 0) {
                System.out.println();
            }
        }
    }

    int find(int x) {
        if (f[x] == x) return f[x];

        return f[x] = find(f[x]);
    }

    public static void main(String[] args) throws Exception {
        Main program = new Main();
        program.run();
    }
}
