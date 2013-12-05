import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class UVa820{}

class Main {
    private int source, sink, max_flow, flow;
    private int[][] res;
    private int[] taken, parent;
    private ArrayList<ArrayList<Integer>> adj_list;
    private final int oo = 1000000;

    void augment(int v, int min_flow) {
        if (v == source) {
            flow = min_flow; return;
        } 
        else if (parent[v] != 0){
            min_flow = Math.min(min_flow, res[parent[v]][v]);
            augment(parent[v], min_flow);
            res[parent[v]][v] -= flow; res[v][parent[v]] += flow;
        }
    }

    void run() throws Exception {
        Scanner scanner = new Scanner(System.in);
        int num_nodes = scanner.nextInt();
        int network_num = 0;

        while (num_nodes > 0) {
            network_num++;
            max_flow = 0;
            res = new int[num_nodes+1][num_nodes+1];
            adj_list = new ArrayList<ArrayList<Integer>>();
            for (int i = 0; i <= num_nodes; ++i)
                adj_list.add(new ArrayList<Integer>());

            source = scanner.nextInt(); sink = scanner.nextInt(); int num_edges = scanner.nextInt();
            for (int i = 0; i < num_edges; ++i) {
                int u = scanner.nextInt(); int v = scanner.nextInt(); int weight = scanner.nextInt();
                if (res[u][v] == 0) {
                	res[u][v] = res[v][u] = weight;
                	adj_list.get(u).add(v); adj_list.get(v).add(u);
                } 
                else {
                	res[u][v] += weight; res[v][u] += weight;
                }
            }

            while (true) {
                flow = 0;
                taken = new int[num_nodes+1]; parent = new int[num_nodes+1];
                Queue<Integer> q = new LinkedList<Integer>();
                taken[source] = 1;
                q.offer(source);
                while (!q.isEmpty()) {
                    int current = q.poll();
                    if (current == sink) break;
                    for (Integer next : adj_list.get(current)) {
                        if (taken[next] == 0 && res[current][next] > 0) {
                            q.offer(next); taken[next] = 1; parent[next] = current;
                        }
                    }
                }

                augment(sink, oo);
                if (flow == 0) break;
                max_flow += flow;
            }
            System.out.printf("Network %d\n", network_num);
            System.out.printf("The bandwidth is %d.\n\n", max_flow);
            num_nodes = scanner.nextInt();
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

