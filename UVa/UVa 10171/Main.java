import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

class UVa10171{}

class Main {
    private final int INF = 100000000;
    private int[][] young_adj_mat;
    private int[][] old_adj_mat;
    private HashMap<String, Integer> map_to_num;
    private final String[] map_to_alpha = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
        
        void floyd(int[][] adj_mat) {
            for (int k = 0; k < 26; ++k)
                for (int i = 0; i < 26; ++i)
                    for (int j = 0; j < 26; ++j)
                        adj_mat[i][j] = Math.min(adj_mat[i][j], adj_mat[i][k] + adj_mat[k][j]);
        }

        void run() throws Exception {
	   Scanner scanner =  new Scanner(System.in);
	   map_to_num = new HashMap<String, Integer>();
	   young_adj_mat = new int[26][26];
	   old_adj_mat = new int[26][26];

	   for (int i = 0; i < 26; ++i) {
	       map_to_num.put(map_to_alpha[i],i);
           }

           int num_edges = scanner.nextInt();

           while (num_edges != 0) {
               int edges = num_edges;
               
               for (int i = 0; i < 26; ++i) {
	           Arrays.fill(young_adj_mat[i], INF);
	           Arrays.fill(old_adj_mat[i], INF);
	           young_adj_mat[i][i] = old_adj_mat[i][i] = 0;
               }

               while (edges-- > 0) {
                   String age = scanner.next();
                   String direction = scanner.next();
                   String u = scanner.next(), v = scanner.next();
                   int weight = scanner.nextInt();
                   if (age.equals("Y")) {
                       if (direction.equals("U")) {
                           young_adj_mat[map_to_num.get(u)][map_to_num.get(v)] = Math.min(weight, young_adj_mat[map_to_num.get(u)][map_to_num.get(v)]);
                       } else {
                           young_adj_mat[map_to_num.get(u)][map_to_num.get(v)] = Math.min(weight, young_adj_mat[map_to_num.get(u)][map_to_num.get(v)]);
                           young_adj_mat[map_to_num.get(v)][map_to_num.get(u)] = Math.min(weight, young_adj_mat[map_to_num.get(u)][map_to_num.get(v)]);
                       }
                   }
                   else {
                       if (direction.equals("U")) {
                           old_adj_mat[map_to_num.get(u)][map_to_num.get(v)] = Math.min(weight, old_adj_mat[map_to_num.get(u)][map_to_num.get(v)]);
                       } else {
                           old_adj_mat[map_to_num.get(u)][map_to_num.get(v)] = Math.min(weight, old_adj_mat[map_to_num.get(u)][map_to_num.get(v)]);
                           old_adj_mat[map_to_num.get(v)][map_to_num.get(u)] = Math.min(weight, old_adj_mat[map_to_num.get(v)][map_to_num.get(u)]);
                       }
                   }
               }
               floyd(young_adj_mat);
               floyd(old_adj_mat);
               String young_start = scanner.next();
               String old_start = scanner.next();
               int answer = INF;
               ArrayList<Integer> city = new ArrayList<Integer>();
               for (int i = 0; i < 26; ++i) {
                   if (young_adj_mat[map_to_num.get(young_start)][i] != INF) {
                       if (answer >= young_adj_mat[map_to_num.get(young_start)][i] + old_adj_mat[map_to_num.get(old_start)][i]) {
                           answer = young_adj_mat[map_to_num.get(young_start)][i] + old_adj_mat[map_to_num.get(old_start)][i];
                       }
                   }
               }
               for (int i = 0; i < 26; ++i) {
                   if (answer == young_adj_mat[map_to_num.get(young_start)][i] + old_adj_mat[map_to_num.get(old_start)][i]) {
                       city.add(i);
                   }
               }

               if (answer != INF) { 
                   System.out.print(answer);
                   for (int i = 0; i < city.size()-1; ++i) {
                       System.out.print(" " + map_to_alpha[city.get(i)]);
                   }
                   System.out.println(" " + map_to_alpha[city.get(city.size()-1)]);
               }
               else {
                   System.out.println("You will never meet.");
               }
               num_edges = scanner.nextInt();
           }
        }
	
	public static void main(String[] args) throws Exception{
		Main program = new Main();
		program.run();		
	}
}

class IntegerPair implements Comparable<IntegerPair> {
	String vertex;
	Integer weight;
	
	public IntegerPair(String vertex, int weight) {
		this.vertex = vertex;
		this.weight = weight;
	}
	
	@Override
	public int compareTo(IntegerPair other) {
		return this.weight - other.weight;
	}
}
