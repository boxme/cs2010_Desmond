import java.util.Scanner;

class UVa108 {
	public void compute_array(int[][] array, int N) {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				array[i][j] += (array[i-1][j] + array[i][j-1] - array[i-1][j-1]); // Compute the sum of (0,0) to (i,j) sub-array
			}
		}
	}
	
	public int find_max_sum(int[][] array, int N){
		int max_sum = -127*100*100;
		int sub_sum;
		
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				for (int k = i; k <= N; k++) {
					for (int l = j; l <= N; l++) {
						sub_sum = array[k][l];         
						sub_sum -= (array[i-1][l] + array[k][j-1] - array[i-1][j-1]); //Find sum of (i, j) to (k,l) coordinate
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
		UVa108 compute = new UVa108();
		int N = scanner.nextInt();
		int[][] array = new int[101][101];
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) 
				array[i][j] = scanner.nextInt();
		}
		compute.compute_array(array, N);
		System.out.println(compute.find_max_sum(array, N));
	}
	public static void main(String[] args) {
		Main program = new Main();
		program.run();		
	}
}
