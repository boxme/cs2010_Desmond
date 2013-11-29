import java.util.Scanner;

/*
 * Top-down approach DP
 */

class wedding_shopping {
	private int[][] memo;
	private int[][] price = new int[25][25];
	private int M;
	private int C;
	
	public wedding_shopping() {}
	
	int max_amt_money(int cash_left, int garment_model) {
		if (cash_left < 0) return -1000000;								    // Ran out of money
		if (garment_model > C) return M - cash_left;						// Finished choosing C models, return amt spent 
		
		if (memo[cash_left][garment_model] != -1) 							// This array stores the prev calculated states
			return memo[cash_left][garment_model];                          // The most amount of money spent at present cash and garment model
		
		int ans = -1;
		for (int model = 1; model <= price[garment_model][0]; model++) {    // Price[garment_model][0] = no. of available clothes in that cat
			ans = Math.max(ans, max_amt_money(cash_left - price[garment_model][model], garment_model+1));
		}
		return memo[cash_left][garment_model] = ans;						// Store the optimal ans for each state
	}
	
	void run() {
		Scanner scanner = new Scanner(System.in);
		int num_test_cases = scanner.nextInt();
		
		while(num_test_cases-- > 0) {
			this.M = scanner.nextInt();
			this.C = scanner.nextInt();
			this.memo = new int[210][25];
			
			for (int i = 0; i < memo.length; i++) {
				for (int j = 0; j < memo[i].length; j++) {
					memo[i][j] = -1;
				}
			}
			
			for (int model = 1; model <= C; model++) {
				price[model][0] = scanner.nextInt();
				for (int i = 1; i <= price[model][0] ; i++) {
					price[model][i] = scanner.nextInt();
				}
			}
			int ans = max_amt_money(M, 1);
			if (ans < 0) System.out.println("no solution");
			else         System.out.println(ans);
		}
	}
	public static void main(String[] args) {
		wedding_shopping program = new wedding_shopping();
		program.run();
	}

}
