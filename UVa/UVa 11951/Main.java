import java.util.Scanner;

/*
 * http://saicheems.wordpress.com/2013/08/29/uva-11951-area/
 * This is the Kadane's Algorithm for maximum sub-array in a 2D array. O(N^3)
 * http://ihaventyetdecided.blogspot.sg/2010/10/kadanes-2d-algorithm.html
 */
class UVa11951 {
	/*
	 * Compute the total sum from (i, j) to (k, l) sub-array
	 */
	public void find_max_sum(long[][] array, int N, int M, int K, int test_case_num) {
		int max_area = 0;
		Long total_cost = Long.valueOf(0);
		for (int i = 1; i <= N; i++) {								// i & j are row indices
			for (int j = i; j <= N; j++) {
				int sub_area = 0;
				long sub_cost = 0;
				int start = 1;										// start is row index
				for (int k = 1;  k <= M; k++) {						// k is column index
					long col_sum = array[j][k] - array[i-1][k];		// col_sum = sum of col k from row i to j
					sub_cost += col_sum;							// sub_cost = sum of col_sum in row j
					
					while (sub_cost > K) {							// if sub_cost > K, minus 
						sub_cost -= (array[j][start] - array[i-1][start]);
						++start;
					}
					
					sub_area = (k-start+1)*(j-i+1);
					if (sub_area == max_area) 
						total_cost = Math.min(total_cost, sub_cost);
					if (sub_area > max_area) {
						total_cost = sub_cost;
						max_area = sub_area;
					}
				}
			}
		}
		System.out.println("Case #" + test_case_num + ": " + max_area + " " + total_cost);
	}
}

class Main {
	
	void run() {
		Scanner scanner = new Scanner(System.in);
		UVa11951 compute = new UVa11951();
		int num_test_cases = scanner.nextInt();
		int test_case_num = 1;
		while (num_test_cases-- > 0) {
			int N = scanner.nextInt();
			int M = scanner.nextInt();
			int K = scanner.nextInt();
			long[][] array = new long[101][101];
			
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= M; j++) {
					array[i][j] = scanner.nextLong();
					array[i][j] += array[i-1][j];		// Each element is stored as the sum of the elements in the columns above it
				}										// Use this to calculate the range from row i to row j as A[j] – A[i-1]
			}
			
			compute.find_max_sum(array, N, M, K, test_case_num);
			++test_case_num;
		}
		
	}
	
	public static void main(String[] args) {
		Main program = new Main();
		program.run();		
	}
}
