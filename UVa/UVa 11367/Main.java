import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

class UVa11367 {}

class Main {
	private static final int INF = 1000000000;
	private ArrayList<ArrayList<IntegerPair>> Adj_List;
	private int V;
	private int[] fuel_cost;
	private int[][] cheapest_cost;
	private PriorityQueue<IntegerPair> pq;
	
	void Dijkstra(int tank_capacity, int start_city, int dest) {
		
		cheapest_cost = new int[tank_capacity+1][V];
		pq = new PriorityQueue<IntegerPair>();
		
		for (int i = 0; i < tank_capacity+1; i++) {
			for (int j = 0; j < V; j++) 
				cheapest_cost[i][j] = INF;
		}
		
		cheapest_cost[0][start_city] = 0;
		IntegerPair src = new IntegerPair(start_city, 0, 0, 0);         //(vertex, cost, fuel level, dist b/w edges)
		pq.offer(src);
		
		while(!pq.isEmpty()) {
			IntegerPair current = pq.poll();
			
			if (current.city == dest) {
				System.out.println(current.cost);
				return;
			}

			if (cheapest_cost[current.fuel][current.city] < current.cost) continue;			
						
			if (current.fuel < tank_capacity && cheapest_cost[current.fuel+1][current.city] > current.cost + fuel_cost[current.city]) {
				cheapest_cost[current.fuel+1][current.city] = current.cost + fuel_cost[current.city];
				pq.offer(new IntegerPair(current.city, cheapest_cost[current.fuel+1][current.city], current.fuel+1, 0));
			}
			
			for (IntegerPair next : Adj_List.get(current.city)) {
								
				if (current.fuel >= next.dist) {
					if (cheapest_cost[current.fuel - next.dist][next.city] > current.cost) {
						cheapest_cost[current.fuel - next.dist][next.city] = current.cost;
						pq.offer(new IntegerPair(next.city, current.cost, current.fuel - next.dist, 0));
					}
				} 
				
			}
		}
		System.out.println("impossible");
	}
	
	void run() {
		Scanner scanner = new Scanner(System.in);
		int edge;
		V = scanner.nextInt();
		edge = scanner.nextInt();
		fuel_cost = new int[V];
		Adj_List = new ArrayList<ArrayList<IntegerPair>>();
		
		for (int i = 0; i < V; i++) {
			fuel_cost[i] = scanner.nextInt();
			Adj_List.add(new ArrayList<IntegerPair>());
		}
		
		for (int i = 0; i < edge; i++) {
			int u, v, dist;
			Adj_List.get(u = scanner.nextInt()).add(new IntegerPair(v = scanner.nextInt(), 0, 0, dist = scanner.nextInt()));
			Adj_List.get(v).add(new IntegerPair(u, 0, 0, dist));
		}
		
		int query = scanner.nextInt();
		
		while (query-- > 0) {
			Dijkstra(scanner.nextInt(), scanner.nextInt(), scanner.nextInt());
		}
	}
	
	public static void main(String[] args) {
		Main program = new Main();
		program.run();		
	}
}

class IntegerPair implements Comparable<IntegerPair> {
	int city;
	int dist;
	int cost;
	int fuel;
	
	public IntegerPair(int city, int cost, int fuel, int dist) {
		this.city = city;
		this.cost = cost;
		this.fuel = fuel;
		this.dist = dist;
	}
	
	@Override
	public int compareTo(IntegerPair other) {
		return this.cost - other.cost;
	}
}
