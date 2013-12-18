import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

class UVa11504{}

class Main {
    private int n, m, counter, num_SCC;
    private ArrayList<ArrayList<Integer>> adj_list;
    private int[] low, num, visited, reference;
    private Stack<Integer> stack;

    void findSCC() {
        num_SCC = 0;
        counter = 0;
        stack = new Stack<Integer>();
        for (int i = 1; i <= n; ++i) {
            if (num[i] == -1)
                dfs(i);
        }
        visited = new int[n+1];
        for (int u = 1; u <= n; ++u) {
        	for (int v : adj_list.get(u)) {
        		if (reference[u] != reference[v] && visited[reference[v]] == 0) {
        			num_SCC--; visited[reference[v]] = 1;
        		}
        	}
        }
    }

    void dfs(int u) {
        num[u] = counter; low[u] = counter++;
        visited[u] = 1;
        stack.push(u);
        for (int v : adj_list.get(u)) {
            if (num[v] == -1)
                dfs(v);

            if (visited[v] == 1)
                low[u] = Math.min(low[u], low[v]);
        }
        if (low[u] == num[u]) {
        	num_SCC++;
            while (true) {
                int v = stack.pop();
                reference[v] = num_SCC;					//Compressed the SCC into one single node
                visited[v] = -1;
                if (u == v) break;
            }
        }
    }

    void run() throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    	StringTokenizer scanner = new StringTokenizer(br.readLine());
    	int TC = Integer.parseInt(scanner.nextToken());

    	while (TC -- > 0) {
    	    scanner = new StringTokenizer(br.readLine());
    	    n = Integer.parseInt(scanner.nextToken());
    	    m = Integer.parseInt(scanner.nextToken());
    	    adj_list = new ArrayList<ArrayList<Integer>>();
    	    for (int i = 0; i <= n; ++i)
    	        adj_list.add(new ArrayList<Integer>());
    	    
    	    reference = new int[n+1];
    	    for (int i = 0; i < m; ++i) {
    	        scanner = new StringTokenizer(br.readLine());
    	        int u = Integer.parseInt(scanner.nextToken()); int v = Integer.parseInt(scanner.nextToken());
    	        adj_list.get(u).add(v);
            }

            low = new int[n+1]; num = new int[n+1]; visited = new int[n+1]; 
            Arrays.fill(low, -1); Arrays.fill(num, -1); Arrays.fill(visited, -1);
            findSCC();
            pr.println(num_SCC);
        }
        pr.close();
    }

    public static void main(String[] args) throws Exception{
        Main program = new Main();
        program.run();		
    }
}

class Pair implements Comparable<Pair> {
	Integer u, v;
	
	public Pair(int u, int v) {
		this.u = u;
		this.v = v;
	}
	
	@Override
	public int compareTo(Pair other) {
		return this.u - other.u;
	}
}
