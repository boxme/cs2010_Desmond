import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

class UVa452{}

class Main {
    private ArrayList<ArrayList<Integer>> adj_list;
    private int[] weights, memo, starts;
    
    //Use DP if you know the graph is a DAG
    int dp(int u) {
    	if (adj_list.get(u).isEmpty()) return weights[u];
    	if (memo[u] != 0) return memo[u];
    	
    	for (Integer next : adj_list.get(u)) 
			memo[u] = Math.max(memo[u], dp(next) + weights[u]);
    	
    	return memo[u];
    }

    void run() throws Exception {
        BufferedReader scanner = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(scanner.readLine());
        scanner.readLine();
        
        while (TC-- > 0) {
            String input = "";
            weights = new int[27]; starts = new int[27];
            adj_list = new ArrayList<ArrayList<Integer>>();
            
            for (int i = 0; i < 27; i++) 
            	adj_list.add(new ArrayList<Integer>());

            while (scanner.ready() && !(input = scanner.readLine()).equals("")) {
                if (input.compareTo("") == 0) break;
                String[] input_array = input.split(" ");
                int index = input_array[0].charAt(0) - 65; int weight = Integer.parseInt(input_array[1]);
                weights[index] = weight; 
                
                if (input_array.length == 2) { 
                	starts[index] = 1;
                	continue;              	
                }
                
                for (int i = 0; i < input_array[2].length(); ++i) 
                    adj_list.get(input_array[2].charAt(i)-65).add(index);
            }
            
            memo = new int[27];
            int ans = 0;
            for (int i = 0; i < 27; i++)
            	if (starts[i] == 1) 
                	ans = Math.max(ans, dp(i));
            
            System.out.println(ans);
            if (TC > 0) System.out.println();
        }
    }

    public static void main(String[] args) throws Exception{
        Main program = new Main();
        program.run();		
    }
}

class Edge implements Comparable<Edge> {
    Integer u, v, weight;

    public Edge(int u, int v, int weight) {
        this.u = u; this.v = v; this.weight = weight;
    }

    @Override 
    public int compareTo(Edge other) {
        return other.weight - this.weight;
    }
}

