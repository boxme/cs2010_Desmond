import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;
import java.util.StringTokenizer;

class UVa00452 {}

class Main {
	private Stack<IntegerPair> stack;
	private HashMap<String, Boolean> hashtable;
	private ArrayList<ArrayList<IntegerPair>> adj_list;
	private ArrayList<Integer> answers = new ArrayList<Integer>();
	
	void topo(IntegerPair start) {
		hashtable.put(start.task, true);
		
		for (IntegerPair next : adj_list.get(start.vertice)) {
			if (hashtable.get(next.task) == null) 
				topo(next);
		}
		stack.add(start);
	}
	
	void dijkstra() {
		int V = stack.size();
		int[] longest_path = new int[V];
		IntegerPair src = stack.peek();
		longest_path[src.vertice] = src.days;
		
		while (!stack.isEmpty()) {
			IntegerPair current = stack.pop();
			
			for (IntegerPair next : adj_list.get(current.vertice)) {
				if (longest_path[next.vertice] > longest_path[current.vertice] + next.days) 
					longest_path[next.vertice] = longest_path[current.vertice] + next.days;
			}
		}
		answers.add(-longest_path[V-1]);
	}
	
	void run() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st = new StringTokenizer(br.readLine());
	    
	    int num_test_cases = Integer.parseInt(st.nextToken());
    	st = new StringTokenizer(br.readLine());

	    while (num_test_cases-- > 0) {
	    	stack = new Stack<IntegerPair>();
	    	hashtable = new HashMap<String, Boolean>();
	    	adj_list = new ArrayList<ArrayList<IntegerPair>>();
	    	
	    	for (int i = 0; i < 30; i++) 
				adj_list.add(new ArrayList<IntegerPair>());
			
	    	st = new StringTokenizer(br.readLine());
	    	
	    	String task = st.nextToken();
	    	IntegerPair start = new IntegerPair(task, Integer.parseInt(st.nextToken()));
	    	
    		st = new StringTokenizer(br.readLine());
    		
	    	while (st.hasMoreTokens()) {
	    		task = st.nextToken();
	    		IntegerPair new_task = new IntegerPair(task, Integer.parseInt(st.nextToken()));
	    		String parents = st.nextToken();
	    		
	    		for (int i = 0; i < parents.length(); i++) 
					adj_list.get((parents.charAt(i)-65)).add(new_task);
	    		
	    		st = new StringTokenizer(br.readLine());
			}
	    	
	    	topo(start);
	    	dijkstra();	    	
	    }
	    for (int i=0; i< answers.size()-1; ++i) {
			System.out.println(answers.get(i));
			System.out.println();
		}
	    System.out.println(answers.get(answers.size()-1));
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