import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
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
			--num_sets;																// Total # of sets decreased by 1
		}
	}
	
	public boolean isConnected(int i, int j) {return findSet(i) == findSet(j);}
	public int getNumDisjointSets()          {return num_sets;}
	public int getSizeOfSets(int i) 		 {return set_sizes.get(findSet(i));}
}

class Triple implements Comparable<Triple>{
	public int dist;
	public int u;
	public int v;
	
	public Triple(int u, int v, int dist) {
		this.u = u;
		this.v = v;
		this.dist = dist;
	}
	
	@Override
	public int compareTo(Triple other) {
		return this.dist - other.dist;
	}
}

class UVa11747 {
	private UFDS uf;
	private Queue<Integer> q;
	
	public void find_min_cost(int num_nodes, ArrayList<Triple> edge_list) {
		uf = new UFDS(num_nodes);
		q = new LinkedList<Integer>();
		Collections.sort(edge_list);
		
		for (Triple triple : edge_list) {
			if (!uf.isConnected(triple.u, triple.v)) {
				uf.union(triple.u, triple.v);
			}
			else {
				q.add(triple.dist);
			}
		}
		
		if (q.isEmpty()) {
			System.out.println("forest");
		} else {
			String output = "";
			while (!q.isEmpty()) {
				if (q.size() == 1) {
					output += q.poll() + "";
				} else {
					output += q.poll() + " ";
				}
			}
			System.out.println(output);
		}
	}
}

class Main {
	private ArrayList<Triple> edge_list;
	
	void run() {
		Scanner scanner = new Scanner(System.in);
		int num_nodes = scanner.nextInt();
		int num_edges = scanner.nextInt();
		
		while (num_edges != 0 && num_nodes != 0) {
			edge_list = new ArrayList<Triple>();
			while (num_edges-- > 0) {
				edge_list.add(new Triple(scanner.nextInt(), scanner.nextInt(), scanner.nextInt()));	
			}
			UVa11747 compute = new UVa11747();
			compute.find_min_cost(num_nodes, edge_list);
			num_nodes = scanner.nextInt();
			num_edges = scanner.nextInt();
		}
	}
	
	public static void main(String[] args) {
		Main program = new Main();
		program.run();		
	}
}
