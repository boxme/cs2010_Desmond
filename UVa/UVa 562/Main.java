import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

class UVa562{}

class Main {
    private int[] coin_val;
    private int[][] memo;
    private int total_value, num_coins;

    int knapsack(int index, int value) {
        if (index == num_coins) return Math.abs(total_value - value - value);
        if (memo[index][value] != -1) return memo[index][value];

        return memo[index][value] = Math.min(knapsack(index+1, value + coin_val[index]), knapsack(index+1, value));
    }

    void run() throws Exception {
    	BufferedReader bi = new BufferedReader(new InputStreamReader(System.in));
    	PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    	StringTokenizer scanner = new StringTokenizer(bi.readLine());
    	int TC = Integer.parseInt(scanner.nextToken());

    	while (TC-- > 0) {
    	    scanner = new StringTokenizer(bi.readLine());
    	    num_coins = Integer.parseInt(scanner.nextToken());
    	    coin_val = new int[num_coins];
            scanner = new StringTokenizer(bi.readLine());
            total_value = 0;
    	    for (int i = 0; i < num_coins; ++i) {
    	        coin_val[i] = Integer.parseInt(scanner.nextToken());
    	        total_value += coin_val[i];
            }

    	    memo = new int[num_coins][total_value+1];
    	    for (int i = 0; i < num_coins; ++i)
    	        Arrays.fill(memo[i], -1);

            int ans = knapsack(0, 0);
            pr.println(ans);
        }
        pr.close();
    }

    public static void main(String[] args) throws Exception{
        Main program = new Main();
        program.run();		
    }
}

class Edge implements Comparable<Edge> {
    Integer u, v, weight;

    public Edge(int u, int v, int weight) {
        this.u = u;
        this.v = v;
        this.weight = weight;
    }
    @Override
    public int compareTo(Edge other) {
        return this.weight - other.weight;
    }
}
