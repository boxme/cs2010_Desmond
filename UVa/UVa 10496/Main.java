import java.util.Scanner;


class UVa10496 {}

class Main {
	private int num_beepers;
	private int[][] dist;
	private int[] x, y;													
	private int[][] memo;												// memo[pos][bitmask] bitmask -> visited vertices
	
	int TSP(int pos, int bitmask) {
		if (bitmask == (1 << (num_beepers+1)) - 1)
			return dist[pos][0];										// Round trip
		if (memo[pos][bitmask] != -1)
			return memo[pos][bitmask];
		
		int ans = 200000000;
		for (int next_vertex = 0; next_vertex <= num_beepers; next_vertex++) {
			if (next_vertex != pos && (bitmask & (1 << next_vertex)) == 0)
				ans = Math.min(ans, dist[pos][next_vertex] + TSP(next_vertex, bitmask | (1 << next_vertex)));
		}
		return memo[pos][bitmask] = ans;
	}
	
	void run() throws Exception {
		Scanner scanner = new Scanner(System.in);
		int TC = scanner.nextInt();
		
		while (TC-- > 0) {
			int x_size = scanner.nextInt();
			int y_size = scanner.nextInt();
			x = new int[11];
			y = new int[11];
			
			x[0] = scanner.nextInt(); y[0] = scanner.nextInt();          // Karel's position
			num_beepers = scanner.nextInt();
			
			for (int i = 1; i <= num_beepers; i++) {					// Starts at 1 because 0 is karel's position
				x[i] = scanner.nextInt();
				y[i] = scanner.nextInt();
			}
			
			dist = new int[11][11];
			for (int i = 0; i <= num_beepers; i++) 
				for (int j = 0; j <= num_beepers; j++) 
					dist[i][j] = Math.abs(x[i] - x[j]) + Math.abs(y[i] - y[j]);     // Manhattan distance of each beeper to other
			
			memo = new int[11][1<<11];	                               // 1 << 11 because that's the max number of bitmask combinations
			for (int i = 0; i < 11; i++) 
				for (int j = 0; j < (1<<11); j++) 
					memo[i][j] = -1;
			
			System.out.println("The shortest path has length " + TSP(0, 1));      // 1 because pos 0 is already found: karel's pos
		}
	}
	
	public static void main(String[] args) throws Exception{
		Main program = new Main();
		program.run();		
	}
}

class IntegerPair implements Comparable<IntegerPair> {
	int current_hole;
	int hole_num;
	int time;
	
	public IntegerPair(int current_hole, int hole_num, int time) {
		this.current_hole = current_hole;
		this.hole_num = hole_num;
		this.time = time;
	}
	
	@Override
	public int compareTo(IntegerPair other) {
		return this.time - other.time;
	}
}