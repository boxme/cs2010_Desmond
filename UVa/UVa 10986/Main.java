import java.util.*;
import java.io.*;

class Main {

    ArrayList<ArrayList<IntegerPair>> adjList;
    PriorityQueue<IntegerPair> pq;
    int[] dist;
    final int INF = 1000000000;

    void run() throws Exception {
        BufferedReader scanner = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(scanner.readLine());
        adjList = new ArrayList<ArrayList<IntegerPair>>();

        int caseNum = 0;
        while (N-- > 0) {
            caseNum++;
            String[] input = scanner.readLine().split(" ");
            int n = Integer.parseInt(input[0]);
            int m = Integer.parseInt(input[1]);
            int s = Integer.parseInt(input[2]);
            int t = Integer.parseInt(input[3]);

            dist = new int[n];
            Arrays.fill(dist, INF);
            for (int i = 0; i < n; ++i) {
                adjList.add(new ArrayList<IntegerPair>());
            }

            for (int i = 0; i < m; ++i) {
                input = scanner.readLine().split(" ");
                int u = Integer.parseInt(input[0]);
                int v = Integer.parseInt(input[1]);
                int w = Integer.parseInt(input[2]);
                adjList.get(u).add(new IntegerPair(v, w));
                adjList.get(v).add(new IntegerPair(u, w));
            }
            
            if (s < n) {
                Dijk(s);
            }

            if (dist[t] == INF) {
                System.out.println("Case #" + caseNum + ": unreachable");
            } else {
                System.out.println("Case #" + caseNum + ": " + dist[t]);
            }

            adjList.clear();
        }
    }

    void Dijk(int src) {
        pq = new PriorityQueue<IntegerPair>();
        dist[src] = 0;
        pq.offer(new IntegerPair(src, 0));

        while (!pq.isEmpty()) {
            IntegerPair start = pq.poll();
            int weight = start.weight();
            int u = start.vertex(); 

            if (weight > dist[u]) continue;

            for (int i = 0; i < adjList.get(u).size(); ++i) {
                IntegerPair v = adjList.get(u).get(i); 
                if (dist[u] + v.weight() < dist[v.vertex()]) {
                    dist[v.vertex()] = dist[u] + v.weight();
                    pq.offer(new IntegerPair(v.vertex, dist[v.vertex()]));
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Main program = new Main();
        program.run();
    }

    class IntegerPair implements Comparable<IntegerPair> {
        int vertex, weight;

        public IntegerPair(int v, int w) {
            vertex = v;
            weight = w;
        }

        public int compareTo(IntegerPair o) {
            if (weight != o.weight()) {
                return weight - o.weight();
            } else {
                return vertex - o.vertex();
            }
        }

        int vertex() {
            return vertex;
        }

        int weight() {
            return weight;
        }
    }
}
