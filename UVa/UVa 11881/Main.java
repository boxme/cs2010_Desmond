import java.util.*;
import java.io.*;
import java.lang.Math;

class Main {

    static final double EPS = 1e-9;

    void run() throws Exception {
        BufferedReader scanner = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(scanner.readLine());

        while (T != 0) {
            int[] CF = new int[T + 1];
            String[] input = scanner.readLine().split(" ");
            for (int i = 0; i < T + 1; ++i) {
                CF[i] = Integer.parseInt(input[i]);
            }

            double lo = -0.99, hi = Double.MAX_VALUE, IRR = -1.0;
            while (lo <= hi + EPS) {
                double mid = lo + (hi - lo) / 2.0;
                double npv = NPV(CF, T, mid);

                if (Math.abs(npv) <= EPS) {
                    IRR = mid;
                    break;
                }

                if (npv > EPS) {
                    lo = mid;
                } else {
                    hi = mid;
                }
            }

            if (Math.abs(IRR + 1.0) <= EPS) {
                System.out.println("No");
            } else {
                System.out.printf("%.2f%n", IRR);
            }

            T = Integer.parseInt(scanner.readLine());
        }
    }

    double NPV(int[] CF, int T, double IRR) {
        double npv = CF[0];

        for (int i = 1; i <= T; ++i) {
            npv += CF[i] / Math.pow(1.0 + IRR, i); 
        }

        return npv;
    }

    public static void main(String[] args) throws Exception {
        Main program = new Main();
        program.run();
    }
}
