import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.Arrays;

class UVa10943{}

class Main {
    private int N, K;
    private int[][] memo;

    int ways(int n, int k) {
        if (n < 0) return 0;
        if (k == 1) return 1;
        if (memo[n][k] != -1) return memo[n][k];

        int ans = 0;
        for (int i = 0; i <= N; ++i) 
            ans = (ans + ways(n-i, k-1)) % 1000000;

        return memo[n][k] = ans;
    }

    void run() throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    	StringTokenizer scanner = null;
    	while (true) {
    	    scanner = new StringTokenizer(br.readLine());
    	    N = Integer.parseInt(scanner.nextToken());
    	    K = Integer.parseInt(scanner.nextToken());
    	    if ((N + K) == 0) break;

    	    memo = new int[101][101];
    	    for (int i = 0; i < 101; ++i)
    	        Arrays.fill(memo[i], -1);

    	    pr.println(ways(N, K));
        }
        pr.close();
    }

    public static void main(String[] args) throws Exception{
        Main program = new Main();
        program.run();		
    }
}

