import java.util.Scanner;
import java.lang.Math;

class UVa821{}

class Main {
    private int case_num;
    private int[][] adj_matrix;
    private final int INF = 1000000000;
    private int num_pairs;
    private int num_nodes;
    private boolean[] nodes;

        void run() throws Exception {
	   Scanner scanner =  new Scanner(System.in);
	   String[] input = scanner.nextLine().split(" ");
           num_pairs = input.length - 2;
           case_num = 0;

           while (num_pairs > 0) {
                case_num++;
                adj_matrix = new int[101][101];
                nodes = new boolean[101];

                for (int i = 0; i < 101; ++i) {
                    for (int j = 0; j < 101; ++j)
                        adj_matrix[i][j] = INF;
                    adj_matrix[i][i] = 0;
                }
                num_nodes = 0;
                int largest_node = 0;
                for (int i = 0; i < input.length-2; i+=2) {
                    adj_matrix[Integer.parseInt(input[i])][Integer.parseInt(input[i+1])] = 1;
                    largest_node = Math.max(largest_node, Math.max(Integer.parseInt(input[i]), Integer.parseInt(input[i+1])));
                    if (nodes[Integer.parseInt(input[i])] == false) {
                        nodes[Integer.parseInt(input[i])] = true;
                        num_nodes++;
                    }
                    if (nodes[Integer.parseInt(input[i+1])] == false) {
                        nodes[Integer.parseInt(input[i+1])] = true;
                        num_nodes++;
                    }
                }
                
                double answer = 0.0;
                
                for (int k = 1; k <= largest_node; ++k)
                    for (int i = 1; i <= largest_node; ++i)
                        for (int j = 1; j <= largest_node; ++j)
                            adj_matrix[i][j] = Math.min(adj_matrix[i][j], adj_matrix[i][k] + adj_matrix[k][j]);
                
                num_pairs = (num_nodes-1) * num_nodes;

                for (int i = 1; i <= largest_node; ++i){
                    for (int j = 1; j <= largest_node; ++j) {
                        if (adj_matrix[i][j] <= 100 && i != j)
                            answer += (double) adj_matrix[i][j];
                    }
                }

                System.out.printf("Case %d: average length between pages = %.3f clicks\n", case_num, answer/num_pairs);
                input = scanner.nextLine().split(" ");
                num_pairs = input.length - 2;
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
