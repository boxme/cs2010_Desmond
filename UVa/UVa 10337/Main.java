import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

class UVa10337{}

/*
 * Try your best to use as little memory as possible for memo
 */
class Main {
    private final int oo = 100000000;
    private int[][] matrix;
    private int[][] memo;
    private int dist;

    int dp(int i, int j) {
        if (i > 9 || i < 0 || (j == dist-1 && i < 8)) return oo;
        if (j == dist-1 && i == 8) return matrix[i][j] + 20;
        if (j == dist-1 && i == 9) return matrix[i][j] + 30; 
        
        if (memo[i][j] != -1) return memo[i][j];

        return memo[i][j] = Math.min(30+matrix[i][j] + dp(i, j+1), 
                                  Math.min(20+matrix[i][j]+dp(i+1, j+1), 
                                		   60+matrix[i][j]+dp(i-1, j+1)));
    }

    void run() throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    	StringTokenizer scanner = new StringTokenizer(br.readLine());
    	int TC = Integer.parseInt(scanner.nextToken());
    	while (TC-- > 0) {
    	    scanner = new StringTokenizer(br.readLine());
    	    scanner = new StringTokenizer(br.readLine());
    	    dist = (Integer.parseInt(scanner.nextToken()))/100;
    	    matrix = new int[10][dist];
            memo = new int[10][dist];

    	    for (int i = 0; i < 10; ++i) {
    	        scanner = new StringTokenizer(br.readLine());
	            Arrays.fill(memo[i], -1);
    	        for (int j = 0; j < dist; ++j) {
    	            matrix[i][j] = -Integer.parseInt(scanner.nextToken());
                }
            }

            pr.println(dp(9, 0));
            pr.println();
        }
        pr.close();
    }

    public static void main(String[] args) throws Exception{
        Main program = new Main();
        program.run();		
    }
}
