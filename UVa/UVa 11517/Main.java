import java.util.*;
import java.io.*;

class Main {

    int[] memo;
    int[] bills;
    int cost;
    int numOfCoins;

    static final int INF = 1000000;

    void run() throws Exception {
        BufferedReader scanner = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(scanner.readLine());

        memo = new int[10001];

        while (TC-- > 0) {
            cost = Integer.parseInt(scanner.readLine());
            numOfCoins = Integer.parseInt(scanner.readLine());
            
            bills = new int[101];
            for (int i = 0; i < numOfCoins; ++i) {
                bills[i] = Integer.parseInt(scanner.readLine());
            }

            Arrays.fill(memo, INF);
            coinChange();

            for (int i = cost; i < 10001; ++i) {
                if (memo[i] != INF) {
                    System.out.println(i + " " + memo[i]);
                    break;
                }
            }
        }
    }

    void coinChange() {
        memo[0] = 0;

        for (int i = 0; i < bills.length; ++i) {
            for (int v = 10000; v >= 0; --v) {
                if (memo[v] != INF && v + bills[i] <= 10000) {
                    memo[v + bills[i]] = Math.min(memo[v + bills[i]], memo[v] + 1);
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Main program = new Main();
        program.run();
    }
}
