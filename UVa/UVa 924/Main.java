import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class IntegerPair {
	public int day;
	public int vertex;
	
	public IntegerPair(int day, int vertex) {
		this.day = day;
		this.vertex = vertex;
	}
}

class UVa924 {
	public ArrayList<ArrayList<Integer>> adj_list;
	private boolean[] already_knew;
	public int total_num_employees;
	public int[] days;
	public int max_boom;
	public int first_boom_day;
	
	public UVa924(int V) {
		total_num_employees = V;
		adj_list = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < total_num_employees; i++) 
			adj_list.add(new ArrayList<Integer>());
	}
	
	public void BFS(int src) {
		int num_employees_left = total_num_employees-1;
		days = new int[100000];
		already_knew = new boolean[total_num_employees+1];
		already_knew[src] = true;
		Queue<IntegerPair> q = new LinkedList<IntegerPair>();
		q.offer(new IntegerPair(0, src));
		
		while (!q.isEmpty() && num_employees_left > 0) {
			IntegerPair current_vertex = q.poll();
			
			for (Integer new_vertex : adj_list.get(current_vertex.vertex)) {
				
				if (!already_knew[new_vertex]) {
					already_knew[new_vertex] = true;
					days[current_vertex.day+1]++;
					num_employees_left--;
					q.add(new IntegerPair(current_vertex.day+1, new_vertex));
				}
			}
		}
		get_max_boom();
	}
	
	private void get_max_boom() {
		max_boom = 0;
		first_boom_day = 0;
		
		for (int i = 0; i < days.length; i++) {
			if (days[i] > max_boom) {
				max_boom = days[i];
				first_boom_day = i;
			}
		}
	}
}

class Main {
	
	void run() throws Exception{
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	    StringTokenizer st = new StringTokenizer(br.readLine());
	    int num_employees = Integer.parseInt(st.nextToken());
	    UVa924 company = new UVa924(num_employees);
	    
	    for (int i = 0; i < num_employees; i++) {
		    st = new StringTokenizer(br.readLine());
		    int num_friends = Integer.parseInt(st.nextToken());
		    
		    while (num_friends-- > 0) {
		    	company.adj_list.get(i).add(Integer.parseInt(st.nextToken()));
		    }
		}
	    
	    st = new StringTokenizer(br.readLine());
	    int num_queries = Integer.parseInt(st.nextToken());
	    
	    while (num_queries-- > 0) {
		    st = new StringTokenizer(br.readLine());
			company.BFS(Integer.parseInt(st.nextToken()));
			if (company.max_boom > 0) {
				pr.println(company.max_boom + " " + company.first_boom_day);
			} else {
				pr.println(0);
			}
		}
	    pr.close();
	}
	
	public static void main(String[] args) throws Exception {
		Main program = new Main();
		program.run();		
	}
}
