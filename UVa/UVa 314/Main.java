import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class IntegerPair {
	public int row;
	public int col;
	public int turn;
	public int steps;
	public String direction;
	
	public IntegerPair(int row, int col) {
		this.row = row;
		this.col = col;
		this.turn = 0;
		this.steps = 0;
	}
}

class UVa314 {
	private int R;
	private int C;
	private IntegerPair[][] map;
	private boolean[][] bomb;
	private IntegerPair start;
	
	public UVa314(int R, int C) {
		this.R = R;
		this.C = C;
		map = new IntegerPair[R+1][C+1];
		bomb = new boolean[R+1][C+1];
	}
	
	public void insert_obstacles(int row, int col) {
		bomb[row+1][col+1] = true;
		bomb[row+1][col] = true;
		bomb[row][col] = true;
		bomb[row][col+1] = true;
	}
	
	public void BFS(int r, int c, String direction) {
		Queue<IntegerPair> q = new LinkedList<IntegerPair>();
		IntegerPair start = new IntegerPair(r, c);
		start.direction = direction;
		this.start = start;
		map[r][c] = start;
		q.offer(start);
		
		while (!q.isEmpty()) {
			IntegerPair parent = q.poll();
			
			if (parent.row < R-1 && parent.col != 0 && parent.col != C && !bomb[parent.row+1][parent.col]) {
				IntegerPair new_road = new IntegerPair(parent.row+1, parent.col);
				new_road.direction = "south";
				new_road.steps = change_direction(parent, new_road.direction);
				new_road.turn = update_turn(parent, new_road);
				if (map[parent.row+1][parent.col] != null && new_road.steps < map[parent.row+1][parent.col].steps) {
					q.offer(new_road);
					map[parent.row+1][parent.col] = new_road;
				}
				else if (map[parent.row+1][parent.col] == null) {
					q.offer(new_road);
					map[parent.row+1][parent.col] = new_road;
				}
			}
			if (parent.row > 1 && parent.col != 0 && parent.col != C && !bomb[parent.row-1][parent.col]) {
				IntegerPair new_road = new IntegerPair(parent.row-1, parent.col);
				new_road.direction = "north";
				new_road.steps = change_direction(parent, new_road.direction);
				new_road.turn = update_turn(parent, new_road);
				if (map[parent.row-1][parent.col] != null && new_road.steps < map[parent.row-1][parent.col].steps) {
					q.offer(new_road);
					map[parent.row-1][parent.col] = new_road;
				}
				else if (map[parent.row-1][parent.col] == null) {
					q.offer(new_road);
					map[parent.row-1][parent.col] = new_road;
				}
			}
			if (parent.col < C-1 && parent.row != 0 && parent.row != R && !bomb[parent.row][parent.col+1]) {
				IntegerPair new_road = new IntegerPair(parent.row, parent.col+1);
				new_road.direction = "east";
				new_road.steps = change_direction(parent, new_road.direction);
				new_road.turn = update_turn(parent, new_road);
				if (map[parent.row][parent.col+1] != null && new_road.steps < map[parent.row][parent.col+1].steps) {
					q.offer(new_road);
					map[parent.row][parent.col+1] = new_road;
				}
				else if (map[parent.row][parent.col+1] == null) {
					q.offer(new_road);
					map[parent.row][parent.col+1] = new_road;
				}
			}
			if (parent.col > 1 && parent.row != 0 && parent.row != R && !bomb[parent.row][parent.col-1]) {
				IntegerPair new_road = new IntegerPair(parent.row, parent.col-1);
				new_road.direction = "west";
				new_road.steps = change_direction(parent, new_road.direction);
				new_road.turn = update_turn(parent, new_road);
				if (map[parent.row][parent.col-1] != null && new_road.steps < map[parent.row][parent.col-1].steps) {
					q.offer(new_road);
					map[parent.row][parent.col-1] = new_road;
				}	
				else if (map[parent.row][parent.col-1] == null) {
					q.offer(new_road);
					map[parent.row][parent.col-1] = new_road;
				}
			}
		}
	}
	
	private int change_direction(IntegerPair parent, String direction) {
		if (direction.equalsIgnoreCase(parent.direction)) return check_state(parent);
		else if (parent.direction.equals("north") || parent.direction.equals("south")) {
			if (direction.equals("east") || direction.equals("west"))   return parent.steps+2;
			else											            return parent.steps+3;
		}
		else if (parent.direction.equals("east") || parent.direction.equals("west")) {
			if (direction.equals("north")|| direction.equals("south"))  return parent.steps+2;
			else											  			return parent.steps+3;
		}
		return 0;
	}
	
	private int check_state(IntegerPair parent) {
		if (parent.turn == 0) return parent.steps+1;
		else return parent.steps;
	}
	
	private int update_turn(IntegerPair parent, IntegerPair current) {
		if (!parent.direction.equalsIgnoreCase(current.direction)) return 1;
		if (parent.turn < 2) return parent.turn+1;
		else				 return 0;
	}
	
	public int get_min_steps(int r, int c) {
		if (start.row == r && start.col == c) return 0;
		else if (map[r][c] == null || bomb[r][c] || r == 0 || c == 0) return -1;
		else				   return map[r][c].steps;
	}
}

class Main {
	
	void run() throws Exception{
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	    
	    StringTokenizer st = new StringTokenizer(br.readLine());
	    int row = Integer.parseInt(st.nextToken());
	    int col = Integer.parseInt(st.nextToken());
	    
	    while (row != 0 && col != 0) {
	    	UVa314 compute = new UVa314(row, col);
	    	
	    	for (int i = 0; i < row; i++) {
		    	st = new StringTokenizer(br.readLine());
		    	for (int j = 0; j < col; j++) {
		    		int c = Integer.parseInt(st.nextToken());
		    		if (c == 1) 
			    		compute.insert_obstacles(i, j);
		    	}
			}
	    	
	    	st = new StringTokenizer(br.readLine());
	    	int start_r = Integer.parseInt(st.nextToken());
	    	int start_c = Integer.parseInt(st.nextToken());
	    	int end_r = Integer.parseInt(st.nextToken());
	    	int end_c = Integer.parseInt(st.nextToken());
	    	String direction = st.nextToken();
	    	compute.BFS(start_r, start_c, direction);
	    	pr.println(compute.get_min_steps(end_r, end_c));
	    	
	    	
	    	st = new StringTokenizer(br.readLine());
		    row = Integer.parseInt(st.nextToken());
		    col = Integer.parseInt(st.nextToken());
	    }
	    pr.close();
	}
	
	public static void main(String[] args) throws Exception {
		Main program = new Main();
		program.run();		
	}
}
