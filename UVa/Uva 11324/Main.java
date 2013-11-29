import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

class UVa11324{}

class Main {
    private final int UNVISITED = -1;
    private int N, M, dfs_num_counter, numSCC, TC;
    private ArrayList<ArrayList<Integer>> graph;
    private ArrayList<ArrayList<Integer>> compressed;
    private Stack<Integer> stack;
    private int[] dfs_num, dfs_low, visited, weights, reference;

    void TarjanSCC(int u) {
        dfs_low[u] = dfs_num[u] = dfs_num_counter++;
        stack.push(u);
        visited[u] = 1;
        for (Integer v : graph.get(u)) {
            if (dfs_num[v] == UNVISITED)
                TarjanSCC(v);
            if (visited[v] == 1)
                dfs_low[u] = Math.min(dfs_low[u], dfs_low[v]);
        }

        if (dfs_low[u] == dfs_num[u]) {             
            ++numSCC;
            int size = 0;
            while (true) {
                int v = stack.pop(); visited[v] = 0;
                size++;
                reference[v] = numSCC; 
                if (u == v) break;
            }

            weights[numSCC] = size;
        }
    }

    void compress_graph() {
        dfs_num = new int[N+1]; dfs_low = new int[N+1]; visited = new int[N+1];
        weights = new int[N+1]; reference = new int[N+1];
        stack = new Stack<Integer>();
        Arrays.fill(dfs_num, UNVISITED);

        numSCC = dfs_num_counter = 0;
        for (int i = 1; i <= N; ++i) 
            if (dfs_num[i] == UNVISITED) TarjanSCC(i);

        for (int i = 1; i <= N; ++i) 
            for (Integer v : graph.get(i)) 
                if (reference[v] != reference[i]) compressed.get(reference[i]).add(reference[v]);

        stack = new Stack<Integer>();
        Arrays.fill(dfs_num, UNVISITED);

        for (int i = 1; i <= numSCC; ++i) 
            if (dfs_num[i] == UNVISITED) topo(i);
    }
    
    void topo(int u) {
        dfs_num[u] = 1;
        for (Integer v : compressed.get(u))
            if (dfs_num[v] == UNVISITED)
                topo(v);

        stack.push(u);
    }

    int find_largest_clique() {
        int[] largest = new int[numSCC+1];
        Arrays.fill(largest, 1);
        largest[stack.peek()] = weights[stack.peek()];
        int answer = weights[stack.peek()];
        
        while (!stack.isEmpty()) {
            int current = stack.pop();
             
            for (Integer next : compressed.get(current)) {
                largest[next] = Math.max(largest[next], largest[current] + weights[next]);
                answer = Math.max(answer, largest[next]);
            }
        }

        return answer;
    }
    void run() throws Exception {
        Scanner scanner = new Scanner(System.in);
        TC = scanner.nextInt();

        while (TC-- > 0) {
            N = scanner.nextInt(); M = scanner.nextInt();
            graph = new ArrayList<ArrayList<Integer>>();
            compressed = new ArrayList<ArrayList<Integer>>();

            for (int i = 0; i <= N; ++i) {
                graph.add(new ArrayList<Integer>());
                compressed.add(new ArrayList<Integer>());
            }

            for (int i = 0; i < M; ++i)
                graph.get(scanner.nextInt()).add(scanner.nextInt());
            
            if (N == 0) {
            	System.out.println(0);
            	continue;
            }
            compress_graph();
            System.out.println(find_largest_clique());
        }
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

