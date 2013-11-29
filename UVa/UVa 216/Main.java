import java.util.Scanner;


class UVa00216 {}

class Main {
	private double[][] dist;
	private double[][] memo;
	private int num_of_comps;
	private int[] x_coord, y_coord;
	
	double TSP(int pos, int bitmask) {
		if (bitmask == (1 << num_of_comps) - 1)						// All computers are connected. 3 comps => 111
			return 0.0;
		if (memo[pos][bitmask] != -1.0)
			return memo[pos][bitmask];
		
		double answer = 100000000.0;
		for (int next = 0; next < num_of_comps; next++) {
			if (next != pos && (bitmask & (1 << next)) == 0) {
				answer = Math.min(answer, dist[pos][next] + TSP(next, (bitmask | (1 << next))) + 16.0);
			}
		}
		return memo[pos][bitmask] = answer;
	}
	
	void run() throws Exception {
		Scanner scanner = new Scanner(System.in);
		num_of_comps = scanner.nextInt();
		int network_num = 1;
		
		while (num_of_comps != 0) {
			x_coord = new int[9];
			y_coord = new int[9];
			dist = new double[9][9];
			
			for (int i = 0; i < num_of_comps; i++) {
				x_coord[i] = scanner.nextInt();
				y_coord[i] = scanner.nextInt();
			}
						
			/*Getting the manhattan distance*/
			for (int i = 0; i < num_of_comps; i++) {
				for (int j = 0; j < num_of_comps; j++) 
					dist[i][j] = Math.sqrt(Math.pow((x_coord[i] - x_coord[j]),2) 
							               + Math.pow((y_coord[i] - y_coord[j]), 2));
			}
			
			/*Starts with different comp as the "root"*/
			double answer = 100000000.0;
			for (int k = 0; k < num_of_comps; k++) {   
				memo = new double[9][1<<9];
				for (int i = 0; i < 9; i++) {
					for (int j = 0; j < (1<<9); j++) 
						memo[i][j] = -1.0;
				}
				answer = Math.min(answer, TSP(k,1<<k));
			}
			
			System.out.println("**********************************************************");
			System.out.printf("Network #%d\n", network_num);
			System.out.printf("Number of feet of cable required is %.2f.\n", answer);
			
			num_of_comps = scanner.nextInt();
			network_num++;
		}
	}
	
	public static void main(String[] args) throws Exception{
		Main program = new Main();
		program.run();		
	}
}

class IntegerPair {
	int x;
	int y;
	
	public IntegerPair(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
}