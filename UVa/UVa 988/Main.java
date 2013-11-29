import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;


class UVa00988 {}

class Main {
	private int V;
	private ArrayList<ArrayList<Integer>> adj_list;
	private int[] num_paths;
	private Stack<Integer> stack;
	private boolean[] checked;
	private ArrayList<Integer> deaths;
	
	void topo_sort(int vertex) {
		checked[vertex] = true;
		
		for (Integer next : adj_list.get(vertex)) {
			if (checked[next] == false)
				topo_sort(next);
		}
		stack.add(vertex);
	}
	
	int find_num_choices() {
		int answer = 0;
		num_paths = new int[V];
		num_paths[0] = 1;
		
		while(!stack.isEmpty()) {
			int current = stack.pop();
			
			for (Integer next : adj_list.get(current)) {
				num_paths[next] += num_paths[current];
			}
		}
		
		for (int event : deaths) 
			answer += num_paths[event];
		
		return answer;
	}
	
	void run() throws Exception {
		Scanner scanner = new Scanner(System.in);
		
		boolean line = false;
		
		while (scanner.hasNext()) {
			int num_events = scanner.nextInt();
			V = num_events;
			adj_list = new ArrayList<ArrayList<Integer>>();
			stack = new Stack<Integer>();
			checked = new boolean[V];
			deaths = new ArrayList<Integer>();

			for (int i = 0; i < V; i++) 
				adj_list.add(new ArrayList<Integer>());
			
			int parent = 0;
			while (num_events-- > 0) {
				int to_num_events = scanner.nextInt();
				
				for (int j = 0; j < to_num_events; j++) 
					adj_list.get(parent).add(scanner.nextInt());
				
				if (to_num_events == 0) 
					deaths.add(parent);
				
				++parent;
			}
			
			stack.add(V-1);
			checked[V-1] = true;
			for (int i = 0; i < V; i++) {
				if (checked[i] == false) 
					topo_sort(i);
			}
			
//			while (!stack.isEmpty()) {
//				System.out.print(stack.pop() + " ");
//			}
			
			if (line) System.out.println();
			else      line = true;
			
			System.out.println(find_num_choices());
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