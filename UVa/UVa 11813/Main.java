import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;


class UVa11813 {}

class Main {
	private final int INF = 1000000000;
	private int num_intersect, num_road, num_stores;
	private ArrayList<ArrayList<IntegerPair>> adj_list;
	private PriorityQueue<IntegerPair> pq;
	private int[][] memo, shortest_dist, smaller_map;
	private int[] store_loc;
	
	int TSP(int pos, int bitmask) {
		if (bitmask == (1<<(num_stores+1))-1)
			return smaller_map[pos][0];
		
		if (memo[pos][bitmask] != -1)
			return memo[pos][bitmask];
		
		int answer = INF;
		for (int i = 0; i <= num_stores; i++) {
			if (i != pos && (bitmask & (1 << i)) == 0)
				answer = Math.min(answer, smaller_map[pos][i] + TSP(i, bitmask | (1 << i)));
		}
		
		return memo[pos][bitmask] = answer;
	}
	
	void dijkstra(int src, int index) {
		pq = new PriorityQueue<IntegerPair>();
		pq.offer(new IntegerPair(src, 0));
		shortest_dist[index][src] = 0;
		
		while (!pq.isEmpty()) {
			IntegerPair start = pq.poll();
			
			if (shortest_dist[index][start.vertex] < start.weight) continue;
			
			for (IntegerPair next : adj_list.get(start.vertex)) {	
				if (shortest_dist[index][next.vertex] > next.weight + shortest_dist[index][start.vertex]) {
					shortest_dist[index][next.vertex] = next.weight + shortest_dist[index][start.vertex];
					pq.offer(new IntegerPair(next.vertex, shortest_dist[index][next.vertex]));
				}
			}
		}
	}
	
	void run() throws Exception {
		Scanner scanner = new Scanner(System.in);
		int TC = scanner.nextInt();
		
		while (TC-- > 0) {
			num_intersect = scanner.nextInt();
			num_road = scanner.nextInt();
			adj_list = new ArrayList<ArrayList<IntegerPair>>();
			
			for (int i = 0; i <= num_intersect; i++) 
				adj_list.add(new ArrayList<IntegerPair>());
			
			for (int i = 0; i < num_road; i++) {
				int from=scanner.nextInt(), to=scanner.nextInt(), weight=scanner.nextInt();
				adj_list.get(from).add(new IntegerPair(to, weight));
				adj_list.get(to).add(new IntegerPair(from, weight));
			}
			
			num_stores = scanner.nextInt();
			store_loc = new int[num_stores+1];						// Index 0 is home
			for (int i = 1; i <= num_stores; i++) 
				store_loc[i] = scanner.nextInt();
			
			/*Dijsktra*/
			shortest_dist = new int[num_stores+1][num_intersect+1];
			for (int i = 0; i <= num_stores; i++) 
				for (int j = 0; j <= num_intersect; j++) 
					shortest_dist[i][j] = INF;
				
			for (int i = 0; i <= num_stores; i++) 
				dijkstra(store_loc[i], i);
			
			/*TSP*/
			/*Transfer to a smaller graph*/
			smaller_map = new int[num_stores+1][num_stores+1];
			
			for (int i = 0; i <= num_stores; i++) {
				for (int j = 0; j <= num_stores; j++) 
					smaller_map[i][j] = shortest_dist[i][store_loc[j]];
				smaller_map[i][i] = 0;
			}
			
			memo = new int[num_stores+1][(1<<(num_stores+1))];
			for (int i = 0; i <= num_stores; i++) 
				for (int j = 0; j < (1<<(num_stores+1)); j++) 
					memo[i][j] = -1;
			
			System.out.println(TSP(0,1));
		}
	}
	
	public static void main(String[] args) throws Exception{
		Main program = new Main();
		program.run();		
	}
}

class IntegerPair implements Comparable<IntegerPair> {
	Integer vertex, weight;
	
	public IntegerPair(int vertex, int weight) {
		this.vertex = vertex;
		this.weight = weight;
	}
	
	@Override
	public int compareTo(IntegerPair other) {
		return this.weight - other.weight;
	}
}