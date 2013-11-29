import java.util.Scanner;


class UVa11284 {}

class Main {
	private double INF = 100000000.0;
	private int num_stores, num_opera;
	private int[] selling_stores;
	private double[] prices;
	private double[][] map, smaller_map, shortest_path;
	private double ans;
	
	double TSP(int pos, int bitmask) {
		if (bitmask == (1<<(num_opera+1))-1) 
			return -smaller_map[pos][0];
		
		if (shortest_path[pos][bitmask] != -INF)
			return shortest_path[pos][bitmask];
		
		double answer = -INF;
		for (int nxt = 0; nxt <= num_opera; nxt++) {
			if (nxt != pos && (bitmask & (1 << nxt)) == 0) {
//				double temp1 = smaller_map[pos][nxt];
//				double temp2 = prices[nxt];
//				double temp3 = TSP(nxt, bitmask  | (1 << nxt));
//				double buy = temp3 - temp1 + temp2;
//				System.out.println(buy + " = " + temp3 + " - " + temp1 + " + " + temp2 + " : pos is " + pos + " -> " + nxt);
				double buy = TSP(nxt, (bitmask | (1 << nxt))) - smaller_map[pos][nxt] + prices[nxt];
				double dont_buy = TSP(pos,(bitmask | (1 << nxt)));
				answer = Math.max(answer, Math.max(buy, dont_buy));
			}
		}
		
		return shortest_path[pos][bitmask] = answer;
	}
	
	void alternative() {
		for (int mask = (1 << (num_opera+1)) - 2; mask >= 0; --mask)
			for (int index = 0; index < num_opera+1; ++index)
                if ((mask & (1 << index)) != 0) {
                    shortest_path[index][mask] = -INF;
                    for (int nxt = 0; nxt < num_opera+1; ++nxt)
                        if ((mask & (1 << nxt)) == 0) {
                            double take = shortest_path[nxt][mask | (1 << nxt)] - smaller_map[index][nxt] + prices[nxt];
                            System.out.println(take);
                            double dont = shortest_path[index][mask | (1 << nxt)];
                            shortest_path[index][mask] = Math.max(shortest_path[index][mask], Math.max(take, dont));
                        }
                }
	}
	
	void run() throws Exception {
		Scanner scanner = new Scanner(System.in);
		int test_case = scanner.nextInt();
		
		while (test_case-- > 0) {
			num_stores = scanner.nextInt();
			int num_roads = scanner.nextInt();
			map = new double[num_stores+1][num_stores+1];
			
			for (int i = 0; i <= num_stores; i++) {
				for (int j = 0; j <= num_stores; j++) 
					map[i][j] = (i==j) ? 0.0 : INF;
			}
			
			while (num_roads-- > 0) {
				int x = scanner.nextInt(), y = scanner.nextInt();
				double weight = scanner.nextDouble();
				map[x][y] = map[y][x] = Math.min(map[x][y], weight);			// Store only the shortest path as there are multiples
			}
			
			/*Floyd warshall*/
			for (int t = 0; t <= num_stores; t++) {
				for (int u = 0; u <= num_stores; u++) 
					for (int v = 0; v <= num_stores; v++) 
						map[u][v] = Math.min(map[u][v], map[u][t] + map[t][v]);
			}
			
			num_opera = scanner.nextInt();
			prices = new double[num_opera+1];
			selling_stores = new int[num_opera];
			prices[0] = 0.0;
			for (int i = 0; i < num_opera; i++) {
				selling_stores[i] = scanner.nextInt(); 
				prices[i+1] = scanner.nextDouble();
			}
			
			/*Transfer to the new map that only contains the stores that sell*/
			smaller_map = new double[num_opera+1][num_opera+1];
			for (int i = 0; i < num_opera; i++) 
				smaller_map[0][i+1] = smaller_map[i+1][0] = map[0][selling_stores[i]];
			
			for (int i = 1; i <= num_opera; i++) {
				for (int j = 1; j <= num_opera; j++) 
					smaller_map[i][j] = map[selling_stores[i-1]][selling_stores[j-1]];
			}
			
			/*TSP*/
			shortest_path = new double[num_opera+1][1<<(num_opera+1)];
			for (int i = 0; i < (num_opera + 1); i++) {
				for (int j = 0; j < (1 << (num_opera+1)); j++) 
					shortest_path[i][j] = -INF;
			}
			
			double ans =  TSP(0,1);
//			ans = Math.round(ans*100.0)/100.0;
			if (ans > 0 && Math.abs(ans) > 1e-9)
				System.out.printf("Daniel can save $%.2f\n", ans);
			else
				System.out.println("Don't leave the house");
		}
	}
	
	public static void main(String[] args) throws Exception{
		Main program = new Main();
		program.run();		
	}
}