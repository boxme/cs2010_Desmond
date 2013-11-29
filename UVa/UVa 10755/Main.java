import java.util.Scanner;


class UVa10755 {
	public long find_max_value(long[][][] array, int A, int B, int C) {
		Long max_sum = Long.MIN_VALUE;
		Long sub_sum = new Long(0);
			for (int down = 1; down <= A; down++) {
				for (int up = down; up <= A; up++) {
					
					
					for (int d = 1; d <= B; d++) {
						for (int u = d; u <= B; u++) {
							
							Long min = new Long(0);
							for (int k = 1; k <= C; k++) {
								sub_sum = array[up][u][k] - array[down-1][u][k] 
										  - array[up][d-1][k] + array[down-1][d-1][k];
								max_sum = Math.max(max_sum, sub_sum-min);
								
								if (sub_sum < min) min = sub_sum;
							}
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
		int num_test_cases = scanner.nextInt();
		UVa10755 compute = new UVa10755();
		
		while (num_test_cases-- > 0) {
			int A = scanner.nextInt();
			int B = scanner.nextInt();
			int C = scanner.nextInt();
			long[][][] array = new long[25][25][25];
			
			for (int i = 1; i <= A; i++) {
				for (int j = 1; j <= B; j++) {
					for (int k = 1; k <= C; k++) {
						array[i][j][k] = scanner.nextLong();
						
						array[i][j][k] += array[i][j-1][k] + array[i][j][k-1] - array[i][j-1][k-1] 
								          - (array[i-1][j-1][k] + array[i-1][j][k-1] - array[i-1][j-1][k-1])
								          + array[i-1][j][k];
					}
				}
			}
			
			System.out.println(compute.find_max_value(array, A, B, C));
			if (num_test_cases >= 1) System.out.println();
		}
	}
	public static void main(String[] args) {
		Main program = new Main();
		program.run();		
	}
}
