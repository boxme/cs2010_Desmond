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
	
	public IntegerPair(int row, int col) {
		this.row = row;
		this.col = col;
	}
}

class UVa10653 {
	private int R;
	private int C;
	private boolean[][] taken;
	private IntegerPair[][] parents;
	private int dist;
	
	public UVa10653(int R, int C) {
		this.R = R;
		this.C = C;
		taken = new boolean[R][C];
		parents = new IntegerPair[R][C];
	}
	
	public void BFS(IntegerPair src) {										// BFS to search all the entire map
		dist = 0;
		Queue<IntegerPair> q = new LinkedList<IntegerPair>();
		q.offer(src);
		taken[src.row][src.col] = true; 
		
		while (!q.isEmpty()) {
			IntegerPair parent = q.poll();
			
			if (parent.row < R-1 && !taken[parent.row+1][parent.col]) {
				taken[parent.row+1][parent.col] = true;
				parents[parent.row+1][parent.col] = parent;
				q.offer(new IntegerPair(parent.row+1, parent.col));
			}
			if (parent.col < C-1 && !taken[parent.row][parent.col+1]) {
				taken[parent.row][parent.col+1] = true;
				parents[parent.row][parent.col+1] = parent;
				q.offer(new IntegerPair(parent.row, parent.col+1));
			}
			if (parent.row > 0 && !taken[parent.row-1][parent.col]) {
				taken[parent.row-1][parent.col] = true;
				parents[parent.row-1][parent.col] = parent;
				q.offer(new IntegerPair(parent.row-1, parent.col));
			}
			if (parent.col > 0 && !taken[parent.row][parent.col-1]) {
				taken[parent.row][parent.col-1] = true;
				parents[parent.row][parent.col-1] = parent;
				q.offer(new IntegerPair(parent.row, parent.col-1));
			}
		}
	}
	
	public void set_bomb_on_map(int row, int col) {
		taken[row][col] = true;
		parents[row][col] = new IntegerPair(-1, -1);
	}
	
	public void shortest_path(IntegerPair start, IntegerPair end) {
		if ((start.row == end.row && start.col == end.col)) {
			return;
		} else {
			dist++;
			shortest_path(start, parents[end.row][end.col]);
		}
	}
	
	public int get_dist() {return dist;}
}

class Main {
	
	void run() throws Exception{
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	    
	    StringTokenizer st = new StringTokenizer(br.readLine());
	    int row = Integer.parseInt(st.nextToken());
	    int col = Integer.parseInt(st.nextToken());
	    
	    while (row != 0 && col != 0) {
	    	UVa10653 compute = new UVa10653(row, col);
	    	st = new StringTokenizer(br.readLine());
	    	int num_bombs = Integer.parseInt(st.nextToken());
	    	
	    	for (int i = 0; i < num_bombs; i++) {
		    	st = new StringTokenizer(br.readLine());
		    	int R = Integer.parseInt(st.nextToken());
		    	int num = Integer.parseInt(st.nextToken());
		    	
		    	while (num-- > 0) {
		    		compute.set_bomb_on_map(R, Integer.parseInt(st.nextToken()));
		    	}
			}
	    	
	    	st = new StringTokenizer(br.readLine());
	    	IntegerPair src = new IntegerPair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
	    	st = new StringTokenizer(br.readLine());
	    	IntegerPair dest = new IntegerPair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
	    	
	    	compute.BFS(src);
	    	compute.shortest_path(src, dest);
	    	pr.println(compute.get_dist());
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
