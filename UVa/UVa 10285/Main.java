import java.util.Scanner;


class UVa10285 {}

class Main {
	private int[][] longest_path;
	private int[][] landscape;
	private int R;
	private int C;
	
	int find_path(int row, int col) {		
		if (longest_path[row][col] != 0) return longest_path[row][col];
		
		int[] dist = new int[4];
		if (row < R-1 && landscape[row][col] > landscape[row+1][col]) 
			dist[0] = find_path(row+1, col) + 1;
		if (row > 0 && landscape[row][col] > landscape[row-1][col])
			dist[1] = find_path(row-1, col) + 1;
		if (col < C-1 && landscape[row][col] > landscape[row][col+1])
			dist[2] = find_path(row, col+1) + 1;
		if (col > 0 && landscape[row][col] > landscape[row][col-1])
			dist[3] = find_path(row, col-1) + 1;
		
		
		for (int i=0; i < 4; ++i) {
			if (longest_path[row][col] < dist[i])
				longest_path[row][col] = dist[i];
		}
		return longest_path[row][col];
	}
	
	int find_longest_path() {
		int dist = 0;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				dist = dist < longest_path[i][j] ? longest_path[i][j]: dist;
			}
		}
		return dist+1;
	}
	
	void run() throws Exception {
		Scanner scanner = new Scanner(System.in);
		int test_cases = scanner.nextInt();
		
		while (test_cases-- > 0) {
			String name = scanner.next();
			R = scanner.nextInt();
			C = scanner.nextInt();
			landscape = new int[R][C];
			longest_path = new int[R][C];
			
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) 
					landscape[i][j] = scanner.nextInt();
			}
			
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					if (longest_path[i][j] == 0)
						find_path(i, j);
				}
			}			
			System.out.println(name + ": " + find_longest_path());
		}
	}
	
	public static void main(String[] args) throws Exception{
		Main program = new Main();
		program.run();		
	}
}

class IntegerPair implements Comparable<IntegerPair> {
	int vertice;
	String task;
	int days;
	
	public IntegerPair(String task, int days) {
		this.task = task;
		this.days = 0-days;
		this.vertice = task.charAt(0)-65;
	}
	
	@Override
	public int compareTo(IntegerPair other) {
		return this.days - other.days;
	}
}