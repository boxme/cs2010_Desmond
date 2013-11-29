import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;

class UVa247{}

class Main {
        private int n, m, set_num;
        private HashMap<String, Integer> map;
        private ArrayList<ArrayList<String>> adj_list;
        private final int UNVISITED = -1;
        private int[] dfs_num, dfs_low, dfs_visited;
        private int dfs_counter;
        private Stack<String> stack;
        private String[] name_index;
        
        void TarjanSCC(int current) {
            dfs_num[current] = dfs_low[current] = dfs_counter++;
            String current_person = name_index[current];
            stack.push(current_person);
            dfs_visited[current] = 1;

            for (String fren : adj_list.get(current)) {
                int index = map.get(fren);
                if (dfs_num[index] == UNVISITED)
                    TarjanSCC(index);
                if (dfs_visited[index] == 1)
                    dfs_low[current] = Math.min(dfs_low[current], dfs_low[index]);
            }

            if (dfs_low[current] == dfs_num[current]) {
                while (true) {
                    String name = stack.pop();
                    dfs_visited[map.get(name)] = 0;
                    System.out.print(name);

                    if (map.get(name) == current) { break; }
                    System.out.print(", ");
                }
                System.out.println();
            }
        }

        void findSCC() {
            dfs_num = new int[n];
            dfs_low = new int[n];
            dfs_visited = new int[n];
            dfs_counter = 0;
            stack = new Stack<String>();

            for (int i = 0; i < n; ++i) {
                dfs_low[i] = dfs_visited[i] = 0;
                dfs_num[i] = UNVISITED;
            }

            for (int i = 0; i < n; ++i) {
                if (dfs_num[i] == UNVISITED) {
                    TarjanSCC(i);
                }
            }
        }
        void run() throws Exception {
	   Scanner scanner =  new Scanner(System.in);
	   n = scanner.nextInt(); m = scanner.nextInt();
           set_num = 0;

	   while (n != 0 && m != 0) {
                map = new HashMap<String, Integer>();
                adj_list = new ArrayList<ArrayList<String>>();
                name_index = new String[n];
                set_num++;

                for (int i = 0; i < n; i++)
                    adj_list.add(new ArrayList<String>());

                int index = 0;
                for (int i = 0; i < m; ++i) {
                    String start = scanner.next();
                    String end = scanner.next();
                    
                    if (map.get(start) == null) {
                        map.put(start, index);
                        name_index[index++] = start;
                    }

                    if (map.get(end) == null) {
                        map.put(end, index);
                        name_index[index++] = end;
                    }
                    
                    adj_list.get(map.get(start)).add(end);
                }
                System.out.println("Calling circles for data set " + set_num + ":");
                findSCC();
                n = scanner.nextInt(); m = scanner.nextInt();
                if (n != 0 && m != 0) 
                    System.out.println();
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
