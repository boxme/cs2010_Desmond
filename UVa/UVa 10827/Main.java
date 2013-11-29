import java.util.Scanner;

class UVa10827 {
	/*
	 * Compute the sum from (0,0) to (i,j) of sub-array
	 */
	public void compute_sum_array(int[][] array, int N) {
		for (int i = 1; i <= 2*N; i++) {
			for (int j = 1; j <= 2*N; j++) 
				array[i][j] += (array[i-1][j] + array[i][j-1] - array[i-1][j-1]);
		}
	}
	
	/*
	 * Compute the total sum from (i, j) to (k, l) sub-array
	 */
	public int find_max_sum(int[][] array, int N) {
		int max_sum = -1000000;
		int sub_sum;
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				for (int k = i;  k < i+N; k++) {		// Not k <= i+N because i+N is back to the exact same row
					for (int l = j; l < j+N; l++) {		// Not l <= j+N because i+N is back to the same column
						sub_sum = array[k][l] - (array[i-1][l] + array[k][j-1] - array[i-1][j-1]);
						max_sum = Math.max(max_sum, sub_sum);
					}
				}
			}
		}
		return max_sum;
	}
}

class Main {
	
	void run() {
		Scanner scanner = new Scanner(System.in);
		UVa10827 compute = new UVa10827();
		int num_test_cases = scanner.nextInt();
		
		while (num_test_cases-- > 0) {
			int N = scanner.nextInt();
			int[][] array = new int[200][200];
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					array[i][j] = scanner.nextInt();
					array[i+N][j] = array[i][j+N] = array[i+N][j+N] = array[i][j];   // Create 4 x grid
				}
			}
			
			compute.compute_sum_array(array, N);
			System.out.println(compute.find_max_sum(array, N));
		}
		
	}
	
	public static void main(String[] args) {
		Main program = new Main();
		program.run();		
	}
}
