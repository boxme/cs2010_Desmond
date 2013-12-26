import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

class UVa10721{}

class Main {
    private int n, k, m;
    private long[][] memo;

    long solve(int N, int K) {
        if (N <= 0 || (K == 1 && N > m)) return 0;
        if (K == 1 && N <= m) return 1;

        if (memo[N][K] != -1) return memo[N][K];

        long ans = 0;
        for (int i = 1; i <= m; ++i) {
            ans += solve(N-i, K-1);
        }
        return memo[N][K] = ans;
    }

    void run() throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    	StringTokenizer scanner = null;
    	String input = "";

    	while (br.ready() && !(input = br.readLine()).equals("")) {
    		if (input.equals("")) break;
    	    scanner = new StringTokenizer(input);
    	    n = Integer.parseInt(scanner.nextToken());
    	    k = Integer.parseInt(scanner.nextToken());
    	    m = Integer.parseInt(scanner.nextToken());

            memo = new long[55][55];
            for (int i = 0; i < 55; ++i)
                Arrays.fill(memo[i], -1);

            long answer = solve(n, k);

    	    pr.println(answer);
        }
        pr.close();
    }

    public static void main(String[] args) throws Exception{
        Main program = new Main();
        program.run();		
    }
}

