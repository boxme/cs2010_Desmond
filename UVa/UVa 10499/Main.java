import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

class UVa10449{}

class Main {
    private ArrayList<ArrayList<Pair>> adj_list;
    private int[] cost, visited;
    private long[] dest;
    private int n;
    private final int oo = 10000000;
    
    void dfs(int u) {
    	visited[u] = 1;
    	for (Pair v : adj_list.get(u)) {
			if (visited[v.v] == 0)
				dfs(v.v);
		}
    }

    void run() throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    	StringTokenizer scanner = null;
    	int TC = 0;
    	String input = "";
    	while (br.ready() && (input = br.readLine()) != null) {
    	    if (input.equals("")) break;
    	    TC++;
    	    scanner = new StringTokenizer(input);
    	    n = Integer.parseInt(scanner.nextToken());
    	    cost = new int[n]; visited = new int[n];
            adj_list = new ArrayList<ArrayList<Pair>>();
            
    	    for (int i = 0; i < n; ++i) { 
    	        cost[i] = Integer.parseInt(scanner.nextToken());
    	        adj_list.add(new ArrayList<Pair>());
            }

    	    int r = Integer.parseInt(br.readLine());
    	    for (int i = 0; i < r; ++i) {
    	        scanner = new StringTokenizer(br.readLine());
    	        int u = Integer.parseInt(scanner.nextToken()) - 1;
    	        int v = Integer.parseInt(scanner.nextToken()) - 1;
    	        adj_list.get(u).add(new Pair(v, (int) Math.pow(cost[v] - cost[u], 3)));
            }

            if (n != 0) {
            	dest = new long[n];
                Arrays.fill(dest, oo);
                dest[0] = 0;
                for (int i = 0; i < n-1; ++i) {
                	boolean has_finished = true;
                    for (int u = 0; u < n; ++u) {
                        for (Pair v : adj_list.get(u)) {
                            if (dest[v.v] > dest[u] + v.weight) {
                            	has_finished = false;
                            	dest[v.v] = dest[u] + v.weight;
                            }
                        }
                    }
                    if (has_finished) break;
                }
                dfs(0);
            }
            
            pr.printf("Set #%d\n", TC);
            int q = Integer.parseInt(br.readLine());
            for (int i = 0; i < q; ++i) {
                int v = Integer.parseInt(br.readLine()) - 1;
                long ans = 0;
                if (n == 0) 	 ans = 0;
                else if (v == 0) ans = cost[v];
                else 		     ans = dest[v];
                
                if (ans < 3 || visited[v] == 0) pr.println("?");
                else         pr.println(ans);
            }
        }
        pr.close();
    }

    public static void main(String[] args) throws Exception{
        Main program = new Main();
        program.run();		
    }
}

class Pair implements Comparable<Pair> {
    Integer v; Long weight;

    public Pair(int v, long weight) {
        this.v = v;
        this.weight = weight;
    }

    @Override
    public int compareTo(Pair other) {
        return (int) (this.weight - other.weight);
    }
}
