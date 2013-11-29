import java.util.Scanner;
import java.lang.Math;
import java.util.Arrays;

class UVa11463{}

class Main {
    private int N;
    private final int INF = 100000000;
    private int[][] map;

        void run() throws Exception {
	   Scanner scanner =  new Scanner(System.in);
	   int test_cases = scanner.nextInt();
	   int cases = 0;

	   while (test_cases-- > 0) {
	       cases++;
	       N = scanner.nextInt();
	       int R = scanner.nextInt();
	       map = new int[N][N];

	       for (int i = 0; i < N; ++i) {
	           Arrays.fill(map[i],INF);
	           map[i][i] = 0;
               }

	       while (R-- > 0) {
	           int u = scanner.nextInt();
	           int v = scanner.nextInt();
	           map[u][v] = map[v][u] = 1;
               }
               int start = scanner.nextInt();
               int end = scanner.nextInt();

               for (int k = 0; k < N; ++k)
                   for (int i = 0; i < N; ++i)
                       for (int j = 0; j < N; ++j)
                           map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);

               int min_time = -1;

               for (int i = 0; i < N; ++i) {
                   if (map[start][i] != INF && map[i][end] != INF) 
                       min_time = Math.max(min_time, map[start][i] + map[i][end]);
               }
               System.out.printf("Case %d: %d\n", cases,min_time);
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
