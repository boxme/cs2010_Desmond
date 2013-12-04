import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

class UVa10048{}

class Main {
    private int C, S, Q, start, end;
    private ArrayList<Edge> edge_list;
    private ArrayList<ArrayList<Integer>> min_tree;
    private int[] f, parent, taken;
    private int[][] dist_matrix;

    int UFDS(int x) {
        if (f[x] == x) return f[x];
        return f[x] = UFDS(f[x]);
    }

    void Kruskal() {
        int cnt = 0;
        for (int i = 0; cnt < C - 1 && i < edge_list.size(); ++i) {
            int u = edge_list.get(i).u;
            int v = edge_list.get(i).v;
            int weight = edge_list.get(i).weight;

            if (UFDS(u) != UFDS(v)) {
                cnt++;
                f[UFDS(u)] = UFDS(v);
                min_tree.get(u).add(v);
                min_tree.get(v).add(u);
            }
        }
    }

    int traverse(int current, int max) {
        if (current == start) return max;
        else {
            max = Math.max(max, dist_matrix[parent[current]][current]);
            return traverse(parent[current], max);
        }
    }
    
    void dfs(int current) {
    	taken[current] = 1;
    	for (Integer next : min_tree.get(current)) {
    		if (taken[next] == 0) {
    			if (next == end) {
    				parent[next] = current;
    				taken[next] = 1;
    				return;
    			}
    			dfs(next);
    			parent[next] = current;
    		}
    	}
    }

    void run() throws Exception {
        Scanner scanner = new Scanner(System.in);
        C = scanner.nextInt(); S = scanner.nextInt(); Q = scanner.nextInt();
        int case_num = 0;
        while (true) {
            case_num++;
            edge_list = new ArrayList<Edge>();
            min_tree = new ArrayList<ArrayList<Integer>>();
            f = new int[C+1];
            dist_matrix = new int[C+1][C+1];

            for (int i = 0; i <= C; ++i) {
                f[i] = i;
                min_tree.add(new ArrayList<Integer>());
            }

            for (int i = 0; i < S; ++i) {
                int u = scanner.nextInt(); int v = scanner.nextInt(); int weight = scanner.nextInt();
                edge_list.add(new Edge(u, v, weight));
                dist_matrix[u][v] = dist_matrix[v][u] = weight;
            }

            Collections.sort(edge_list);
            Kruskal();

            System.out.printf("Case #%d\n", case_num);
            for (int i = 0; i < Q; ++i) {
                start = scanner.nextInt(); end = scanner.nextInt();
                int max = -1;
                if (UFDS(start) != UFDS(end)) System.out.println("no path");
                else {
                    parent = new int[C+1];
                    taken = new int[C+1];
                    dfs(start);
                    max = traverse(end, max);
                    System.out.println(max);
                }
            }
            C = scanner.nextInt(); S = scanner.nextInt(); Q = scanner.nextInt();
            if (C+S+Q == 0) break;
            else            System.out.println();
        }
    }

    public static void main(String[] args) throws Exception{
        Main program = new Main();
        program.run();		
    }
}

class Edge implements Comparable<Edge> {
    Integer u, v, weight;

    public Edge(int u, int v, int weight) {
        this.u = u; this.v = v; this.weight = weight;
    }

    @Override 
    public int compareTo(Edge other) {
        return this.weight - other.weight;
    }
}

