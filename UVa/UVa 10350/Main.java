import java.util.ArrayList;
import java.util.Scanner;


class UVa10350 {}

class Main {
	private static final int INF = 100000000;
	private int[][] building;
	private ArrayList<ArrayList<IntegerPair>> adj_list;
	private int floor_level;
	private int num_of_holes;
	
	int BFS() {
		building = new int[floor_level][num_of_holes];
		int shortest_time = INF;

		for (int i = 0; i < floor_level; i++) {
			for (int j = 0; j < num_of_holes; j++) 
				building[i][j] = i == 0 ? 0 : INF;
		}
		
		for (int i = 0; i < floor_level-1; i++) {
			for (IntegerPair next : adj_list.get(i)) {
				if (building[i+1][next.hole_num] > next.time + building[i][next.current_hole])
					building[i+1][next.hole_num] = next.time + building[i][next.current_hole];
				
				if ((i == floor_level-2) && shortest_time > building[i+1][next.hole_num])
					shortest_time = building[i+1][next.hole_num];
			}
		}
		
		shortest_time += ((floor_level-1) * 2);
		return shortest_time;
	}
	
	void run() throws Exception {
		Scanner scanner = new Scanner(System.in);
		
		while (scanner.hasNext()) {
			String sample = scanner.next();
			floor_level = scanner.nextInt();
			num_of_holes = scanner.nextInt();
			
			adj_list = new ArrayList<ArrayList<IntegerPair>>();
			
			for (int i = 0; i < floor_level; i++) 
				adj_list.add(new ArrayList<IntegerPair>());
			
			for (int i = 0; i < floor_level-1; i++) {
				for (int j = 0; j < num_of_holes; j++) {
					for (int k = 0; k < num_of_holes; k++) 
						adj_list.get(i).add(new IntegerPair(j, k, scanner.nextInt()));
				}
			}
			System.out.println(sample);
			System.out.println(BFS()); 
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