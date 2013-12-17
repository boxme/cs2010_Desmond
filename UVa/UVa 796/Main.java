import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class UVa796{}

class Main {
    private int num_servers, counter, num_bridges;
    private ArrayList<ArrayList<Integer>> adj_list;
    private int[] parent, low, num;
    private PriorityQueue<Pair> pq;

    void find_bridge(int u) {
        num[u] = counter;
        low[u] = num[u];
        counter++;

        for (Integer v : adj_list.get(u)) {
            if (num[v] == -1) {
                parent[v] = u;
                find_bridge(v);

                if (low[v] > num[u]) {
                    num_bridges++;
                    if (u < v)
                    	pq.offer(new Pair(u, v));
                    else
                    	pq.offer(new Pair(v, u));
                }
                low[u] = Math.min(low[u], low[v]);
            } 
            else if (parent[u] != v)
                low[u] = Math.min(low[u], num[v]);
        }
    }

    void dfs() {
        counter = num_bridges = 0;
        pq = new PriorityQueue<Pair>();
        for (int u = 0; u < num_servers; ++u)
            if (num[u] == -1)
                find_bridge(u);
    }

    void run() throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    	StringTokenizer scanner = null;
    	String input = "";
        while (br.ready() && (input = br.readLine()) != "") {
        	scanner = new StringTokenizer(input);
            num_servers = Integer.parseInt(scanner.nextToken());

            adj_list = new ArrayList<ArrayList<Integer>>();
            for (int i = 0; i < num_servers; ++i) 
                adj_list.add(new ArrayList<Integer>());

            for (int i = 0; i < num_servers; ++i) {
                scanner = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(scanner.nextToken());
                String temp = scanner.nextToken();
                temp = temp.substring(1, temp.length()-1);
                int neighbours = Integer.parseInt(temp);
                for (int j = 0; j < neighbours; ++j)
                    adj_list.get(u).add(Integer.parseInt(scanner.nextToken()));
            }

            parent = new int[num_servers]; 	//Objects must be instantiated separately to prevent
            low = new int[num_servers];		//them from pointing to the same memory 
            num = new int[num_servers];
            Arrays.fill(parent, -1); Arrays.fill(low, -1); Arrays.fill(num, -1);
            dfs();
            pr.printf("%d critical links\n", num_bridges);
            while (!pq.isEmpty()) {
            	Pair cur = pq.poll();
            	pr.printf("%d - %d\n", cur.u, cur.v);
            }
            pr.println();
            br.readLine();
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
