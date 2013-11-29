import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.lang.Math;

class UVa315 {}

class Main {
    private final int UNVISITED = -1;
    private int num_of_blocks, dfs_counter, root_children, dfs_root;
    private ArrayList<ArrayList<Integer>> adj_list;
    private int[] dfs_num, dfs_low, dfs_parent;
    private boolean[] articulation_vertex;

        void Articulation_Pt_And_Bridge(int current) {
            dfs_low[current] = dfs_num[current] = dfs_counter++;

            for (Integer next : adj_list.get(current)) {
                if (dfs_num[next] == UNVISITED) {
                    dfs_parent[next] = current;
                    if (dfs_root == current) root_children++;

                    Articulation_Pt_And_Bridge(next);

                    if (dfs_low[next] >= dfs_num[current])
                        articulation_vertex[current] = true;

                    dfs_low[current] = Math.min(dfs_low[current], dfs_low[next]);
                }
                else if (next != dfs_parent[current]) 
                    dfs_low[current] = Math.min(dfs_low[current], dfs_num[next]);
            }
        }
        
        void DFS() {
            dfs_root = root_children = dfs_counter = 0;
            dfs_num = new int[num_of_blocks+1];
            dfs_low = new int[num_of_blocks+1];
            dfs_parent = new int[num_of_blocks+1];
            articulation_vertex = new boolean[num_of_blocks+1];

            for (int i = 0; i <= num_of_blocks; ++i) {
                dfs_num[i] = UNVISITED;
                dfs_low[i] = dfs_parent[i] = 0;
                articulation_vertex[i] = false;
            }

            for (int i = 1; i <= num_of_blocks; ++i) {
                if (dfs_num[i] == UNVISITED) {
                    dfs_root = i; root_children = 0;
                    Articulation_Pt_And_Bridge(i);
                    articulation_vertex[dfs_root] = (root_children > 1);
                }
            }
        }
        
	void run() throws Exception {
	   Scanner scanner =  new Scanner(System.in);
	   num_of_blocks = scanner.nextInt();

	   while (num_of_blocks != 0) {
                scanner.nextLine();
                String[] input = scanner.nextLine().split(" ");
                adj_list = new ArrayList<ArrayList<Integer>>();

                for (int i = 0; i <= num_of_blocks; ++i) 
                    adj_list.add(new ArrayList<Integer>());

                while (true) {

                    if (Integer.parseInt(input[0]) == 0) break;
                    int array_len = input.length;
                    int point = Integer.parseInt(input[0]);
                    for (int i = 1; i < array_len; ++i) {
                        int v = Integer.parseInt(input[i]);
                        adj_list.get(point).add(v);
                        adj_list.get(v).add(point);
                    }

                    input = scanner.nextLine().split(" ");
                }

                DFS();
                int answer = 0;
                for (int i = 0; i <= num_of_blocks; ++i) 
                    if (articulation_vertex[i]) answer++;

                System.out.println(answer);
                num_of_blocks = scanner.nextInt();
           }
        }
	
	public static void main(String[] args) throws Exception{
		Main program = new Main();
		program.run();		
	}
}

class IntegerPair implements Comparable<IntegerPair> {
	Integer vertex, weight;
	
	public IntegerPair(int vertex, int weight) {
		this.vertex = vertex;
		this.weight = weight;
	}
	
	@Override
	public int compareTo(IntegerPair other) {
		return this.weight - other.weight;
	}
}
