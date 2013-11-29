import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

class UVa12047{}

class Main {
    private final int oo = 10000000;
    private int s, t;
    private int p;
    private int M, N;
    private ArrayList<ArrayList<IntegerPair>> graph, reverse_graph;
    private int[] from_s, from_t;

    void dijkstra(int src, ArrayList<ArrayList<IntegerPair>> graph, int[] shortest) {
        PriorityQueue<IntegerPair> pq = new PriorityQueue<IntegerPair>();
        pq.offer(new IntegerPair(src, 0));
        shortest[src] = 0;

        while (!pq.isEmpty()) {
            IntegerPair current = pq.poll();
            
            if (current.cost > shortest[current.v]) continue;

            for (IntegerPair next : graph.get(current.v)) {
                if (shortest[current.v] + next.cost < shortest[next.v]) {
                    shortest[next.v] = shortest[current.v] + next.cost;
                    pq.offer(new IntegerPair(next.v, shortest[next.v]));
                }
            }
        }
    }

    void run() throws Exception {
    	IntegerScanner scanner = new IntegerScanner(System.in);
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        
        int TC = scanner.nextInt();

        while (TC-- > 0) {
            N = scanner.nextInt(); M = scanner.nextInt();
            s = scanner.nextInt(); t = scanner.nextInt();
            p = scanner.nextInt();
            graph = new ArrayList<ArrayList<IntegerPair>>();
            reverse_graph = new ArrayList<ArrayList<IntegerPair>>();

            for (int i = 0; i < N+1; i++) {
                reverse_graph.add(new ArrayList<IntegerPair>());
                graph.add(new ArrayList<IntegerPair>());
            }

            for (int i = 0; i < M; ++i) {
                int u = scanner.nextInt(); int v = scanner.nextInt();
                int cost = scanner.nextInt();
                graph.get(u).add(new IntegerPair(v, cost));
                reverse_graph.get(v).add(new IntegerPair(u,cost));
            }
            from_s = new int[N+1];
            from_t = new int[N+1];
            Arrays.fill(from_s, oo);
            Arrays.fill(from_t, oo);

            dijkstra(s, graph, from_s);
            dijkstra(t, reverse_graph, from_t);

            int answer = -1;
            for (int i = 1; i < N+1; ++i)
                for (IntegerPair edge : graph.get(i)) {
                    if (from_s[i] + edge.cost + from_t[edge.v] <= p)
                        answer = Math.max(answer, edge.cost);
                }

            pw.println(answer);
        }
        pw.close();
    }
	
    public static void main(String[] args) throws Exception{
        Main program = new Main();
        program.run();		
    }
}

class IntegerPair implements Comparable<IntegerPair> {
    Integer v, cost;

    IntegerPair(int v, int cost) {
        this.v = v;
        this.cost = cost;
    }
    @Override
    public int compareTo(IntegerPair other) {
        return this.cost - other.cost;
    }
}

class IntegerScanner { // coded by Ian Leow for PS4
	  BufferedInputStream bis;
	  IntegerScanner(InputStream is) {
	    bis = new BufferedInputStream(is, 1000000);
	  }
	  
	  public int nextInt() {    
	    int result = 0;
	    try {
	      int cur = bis.read();
	      if(cur == -1)
	        return -1;
	      
	      while(cur < 48 || cur > 57) {
	        cur = bis.read();
	      }
	      while(cur >= 48 && cur <= 57) {
	        result = result * 10 + (cur - 48);
	        cur = bis.read();
	      }
	      return result;
	    }
	    catch(IOException ioe) {
	      return -1;
	    }
	  }
	}