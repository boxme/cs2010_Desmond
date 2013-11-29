import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


class UFDS {
	private ArrayList<Integer> ranks;									//Height of the set
	private ArrayList<Integer> parent_of_index;
	private ArrayList<Integer> set_sizes;
	private int num_sets;
	
	public UFDS(int N) {
		num_sets = N;
		ranks = new ArrayList<Integer>();
		parent_of_index = new ArrayList<Integer>();
		set_sizes = new ArrayList<Integer>();
		
		for (int i = 0; i < N; i++) {
			ranks.add(0);
			parent_of_index.add(i);
			set_sizes.add(1);
		}
	}
	
	public int findSet(int node) {
		if (parent_of_index.get(node) == node) return node;
		else {
			int new_parent = findSet(parent_of_index.get(node));					// Compression
			parent_of_index.set(node, new_parent);
			return new_parent;
		}
	}
	
	/*Connect p to q*/
	public void union(int p, int q) {
		if (!isConnected(p, q)) {
			int i = findSet(p);
			int j = findSet(q);
			
			if (ranks.get(i) > ranks.get(j)) {										// Set i is "taller" than set j
				parent_of_index.set(j, i);
				set_sizes.set(i, set_sizes.get(i) + set_sizes.get(j));				// Combine the number of vertices
			} 
			else {
				parent_of_index.set(i, j);
				set_sizes.set(j, set_sizes.get(i) + set_sizes.get(j));
				
				if (ranks.get(i) == ranks.get(j)) {ranks.set(j, ranks.get(j)+1);}
			}
			--num_sets;															// Total # of sets decreased by 1
		}
	}
	
	public boolean isConnected(int i, int j) {return findSet(i) == findSet(j);}
	public int getNumDisjointSets()          {return num_sets;}
	public int getSizeOfSets(int i) 		 {return set_sizes.get(findSet(i));}
}

class Triple implements Comparable<Triple>{
	public double dist;
	public int u;
	public int v;
	
	public Triple(int u, int v, double dist) {
		this.u = u;
		this.v = v;
		this.dist = dist;
	}
	
	@Override
	public int compareTo(Triple other) {
		     if (dist < other.dist) return -1;
		else if (dist > other.dist) return 1;
		else                        return 0;
	}
}

class UVa11951 {
	private UFDS uf;
	
	public void find_min_cost(int test_case, int N, int threshold, ArrayList<Triple> edge_list) {
		uf = new UFDS(N);
		double min_road_cost = 0.0;
		double min_rail_cost = 0.0;
		int num_states = N;
		Collections.sort(edge_list);
		
		for (Triple triple : edge_list) {
			if (!uf.isConnected(triple.u, triple.v)) {
				uf.union(triple.u, triple.v);
				if (triple.dist < threshold) {
					min_road_cost += triple.dist;
					num_states--;
				}
				else {
					min_rail_cost += triple.dist;
				}
			}
		}
		min_rail_cost = Math.round(min_rail_cost);
		min_road_cost = Math.round(min_road_cost);
		System.out.println("Case #" + test_case + ": " + num_states + " " + (int) min_road_cost + " " + (int) min_rail_cost);
	}
}

class Main {
	private int[][] array;
	private ArrayList<Triple> edge_list;
	
	public double find_dist(int x1, int y1, int x2, int y2) {
		return Math.sqrt((Math.pow((x1 - x2),2) + Math.pow((y1 - y2),2)));
	}
	
	void run() {
		Scanner scanner = new Scanner(System.in);
		int num_test_case = scanner.nextInt();
		int test_case_num = 1;
		
		while (num_test_case-- > 0) {
			int N = scanner.nextInt();
			int threshold = scanner.nextInt();
			array = new int[N][3];
			edge_list = new ArrayList<Triple>();
			
			for (int i = 0; i < N; i++) {
				array[i][1] = scanner.nextInt();
				array[i][2] = scanner.nextInt();
			}
			
			for (int i = 0; i < N; i++) {
				for (int j = i+1; j < N; j++) 
					edge_list.add(new Triple(i, j, find_dist(array[i][1], array[i][2], array[j][1], array[j][2])));
			}
			
			UVa11951 compute = new UVa11951();
			compute.find_min_cost(test_case_num, N, threshold, edge_list);
			test_case_num++;
		}
	}
	
	public static void main(String[] args) {
		Main program = new Main();
		program.run();		
	}
}
