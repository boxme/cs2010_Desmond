import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

class UVa11631{}

class Main {
    private ArrayList<Edge> edge_list;
    private int m, n, min_cost, total_cost;
    private int[] f;

    int find(int u) {
        if (f[u] == u) return f[u];
        return f[u] = find(f[u]);
    }

    void kruskal() {
        int cnt = 0;

        for (int i = 0; cnt < m-1 && i < n; ++i) {
            Edge edge = edge_list.get(i);
            int u = edge.u, v = edge.v, weight = edge.weight;
            if (find(u) != find(v)) {
                cnt++; min_cost += weight;
                f[find(u)] = find(v);
            }
        }
    }

    void run() throws Exception {
    	BufferedReader bi = new BufferedReader(new InputStreamReader(System.in));
    	PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    	StringTokenizer scanner = new StringTokenizer(bi.readLine());
    	m = Integer.parseInt(scanner.nextToken());
    	n = Integer.parseInt(scanner.nextToken());

    	while (m+n != 0) {
    	    edge_list = new ArrayList<Edge>();
    	    min_cost = 0; total_cost = 0;
    	    f = new int[m];
    	    for (int i = 0; i < m; ++i) 
    	        f[i] = i;

    	    for (int i = 0; i < n; ++i) {
    	        scanner = new StringTokenizer(bi.readLine());
    	        int u = Integer.parseInt(scanner.nextToken());
    	        int v = Integer.parseInt(scanner.nextToken());
    	        int weight = Integer.parseInt(scanner.nextToken());
    	        edge_list.add(new Edge(u, v, weight));
    	        total_cost += weight;
            }
            Collections.sort(edge_list);
            kruskal();
            pr.println(total_cost - min_cost);
            
            scanner = new StringTokenizer(bi.readLine());
            m = Integer.parseInt(scanner.nextToken());
        	n = Integer.parseInt(scanner.nextToken());
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
