import java.util.Arrays;
import java.util.Scanner;

class UVa10616{}

class Main {
    private int N, Q, M;
    private int D;
    private int[] numbers;
    private long[][][] memo;

    long find_set(int index, int sum, int M_left) {
        if (M_left == 0 && sum % D == 0) return 1;
        if (index == N || M_left == 0)   return 0;
        
    	if (sum < 0) {
    		if (memo[index][M_left][sum+D]  != -1) {
            	return memo[index][M_left][sum+D];
            }
    		return memo[index][M_left][sum+D] = find_set(index+1, sum % D, M_left) + find_set(index+1, (sum + numbers[index]) % D, M_left-1);
    	}
    	else {
    		if (memo[index][M_left][sum]  != -1) {
            	return memo[index][M_left][sum];
            }
            return memo[index][M_left][sum] = find_set(index+1, sum % D, M_left) + find_set(index+1, (sum + numbers[index]) % D, M_left-1);
    	}
    	
    }

       void run() throws Exception {
	   Scanner scanner = new Scanner(System.in);
	   N = scanner.nextInt();
	   Q = scanner.nextInt();
	   int set_num = 0;

	   while (N != 0 && Q != 0) {
	       set_num++;

               numbers = new int[N];

               for (int i = 0; i < N; ++i)
                   numbers[i] = scanner.nextInt();

               System.out.printf("SET %d:\n", set_num);

               
               for (int i = 0; i < Q; ++i) {
                   
                   D = scanner.nextInt();
                   M = scanner.nextInt();
                   
                   memo = new long[201][11][25];
                   for (int k = 0; k < 201; ++k)
                       for (int j = 0; j < 11; ++j)
                           Arrays.fill(memo[k][j], -1);
                   
                   long answer = find_set(0, 0, M);
                   System.out.printf("QUERY %d: %d\n", i+1, answer);
               }

               N = scanner.nextInt();
               Q = scanner.nextInt();
           }
        }
	
	public static void main(String[] args) throws Exception{
		Main program = new Main();
		program.run();		
	}
}

