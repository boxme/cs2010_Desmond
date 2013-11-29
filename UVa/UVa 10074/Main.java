import java.util.Scanner;

class UVa10074{}

class Main {
    private int[][] map;
    int M,N;

        void run() throws Exception {
	   Scanner scanner =  new Scanner(System.in);
	   M = scanner.nextInt();
	   N = scanner.nextInt();

	   while (M != 0 && N != 0) {
	       map = new int[M][N];
	       
	       for (int i = 0; i < M; ++i)
	    	   for (int j = 0; j < N; ++j) {
	    	       int input = scanner.nextInt();
	    	       if (input == 0)     map[i][j] = 1;
	    	       else                map[i][j] = -(M*N); //a very negative number so that taking it will put the sum to negative
	    	       
	    	       if (i > 0)          map[i][j] += map[i-1][j];
	    	       if (j > 0)          map[i][j] += map[i][j-1];
	    	       if (i > 0 && j > 0) map[i][j] -= map[i-1][j-1];
	    	   }


               int answer = -(M*N);

               for (int i = 0; i < M; ++i) for (int j = 0; j < N; ++j) 
                   for (int k = i; k < M; ++k) for (int l = j; l < N; ++l) {
                       int temp = map[k][l];

                       if (i > 0) temp -= map[i-1][l];
                       if (j > 0) temp -= map[k][j-1];
                       if (i > 0 && j > 0) temp += map[i-1][j-1];

                       answer = Math.max(answer, temp);
                   }
               
               if (answer <= 0) System.out.println(0);
               else             System.out.println(answer);
               
               M = scanner.nextInt();
               N = scanner.nextInt();
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
