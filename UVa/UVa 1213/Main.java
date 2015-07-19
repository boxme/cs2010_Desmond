import java.util.*;
import java.io.*;

class Main {

    int[][][] memo;
    boolean[] isPrime = new boolean[1121];
    int[] prime = new int[187];

    void run() throws Exception {
        BufferedReader scanner = new BufferedReader(new InputStreamReader(System.in));
        prime[0] = prime[1] = 1;
        Arrays.fill(prime, 0);
        
        int counter = 0;
        for (int i = 2; i*i <= 1120; ++i) {
            if (!isPrime[i]) {
                prime[counter++] = i;
                for  (int j = i*2; j <= 1120; j += i) {
                    isPrime[j] = true;
                }
            }
        }

        for (int i = (int) Math.sqrt(1120) + 1; i <= 1120; ++i) {
            if (!isPrime[i]) {
                prime[counter++] = i;
            }
        }

        memo = new int[187][1121][15];
        for (int i = 0; i < 187; ++i) {
           for (int j = 0; j < 1121; ++j) {
               Arrays.fill(memo[i][j], -1);
           }
        }

        while (true) {
            int n = 0, k = 0;
            String[] input = scanner.readLine().split(" ");
            n = Integer.parseInt(input[0]);
            k = Integer.parseInt(input[1]);

            if (n == 0 && k == 0) break;

            System.out.println(knapSack(0, n, k));
        }
    }

    int knapSack(int id, int n, int k) {
        if (n == 0 && k == 0) return 1;
        if (id >= 187 || k == 0 || n < 0) return 0;
        if (memo[id][n][k] != -1) return memo[id][n][k];

        return memo[id][n][k] = knapSack(id + 1, n, k) + knapSack(id + 1, n - prime[id], k - 1);
    }

    public static void main(String[] args) throws Exception {
        Main program = new Main();
        program.run();
    }
}
