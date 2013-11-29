import java.util.Scanner;

class Jay_Kadane {
	private int[] array = {-1, 4, 10, -2, 10, -11};				// Sample array
	private int num_stops;
	
	public Jay_Kadane(int num_stops) {
		this.num_stops = num_stops;
	}
	
	public void find_max_range(int[] cost_of_roads, int route_num) {
		int ans = 0; int sum = 0;
		int start = 1; int start_temp = 1; int end = 1;
		
		for (int i = 1; i < num_stops; i++) {
			sum += cost_of_roads[i];
			
			if (sum > ans || (sum == ans && (i+1-start_temp) > (end-start))) {// if sum == ans but current range > recorded range, then update to current range  
				ans = sum;													// Update end index
				start = start_temp;							
				end = i+1;
			}
			if (sum < 0) {					
				sum = 0;
				start_temp = i+1;									        // Restart starting index
				start = ans <= 0 ? start_temp : start;						// if there are two of the same size and of the same sum output the one that appeared earlier 
			}
		}
		if (ans <= 0) System.out.println("Route " + route_num + " has no nice parts");
		else          System.out.println("The nicest part of route " + route_num 
				                          + " is between stops " + start + " and " + end);
	}
	
	public int find_max_range_sample() {
		int ans = 0; int sum = 0;
		for (int i = 0; i < array.length; i++) {
			sum += array[i];
			ans = Math.max(ans, sum);
			
			if (sum < 0) sum = 0;								// If sum is negative, better to restart the range and starts at 0
		}
		return ans;												// If there's no positive ans, then this will return 0
	}	
}

class Main {
	
	void run() {
		Scanner scanner = new Scanner(System.in);
		int num_test_cases = scanner.nextInt();
		int route_num = 0;
		while (num_test_cases-- > 0) {
			int num_stops = scanner.nextInt();
			Jay_Kadane jk = new Jay_Kadane(num_stops);
			int[] cost_of_roads = new int[num_stops];
			
			for (int i = 1; i < num_stops; i++) 
				cost_of_roads[i] = scanner.nextInt();
			
			jk.find_max_range(cost_of_roads, ++route_num);
		}
	}
	
	public static void main(String[] args) {
		Main program = new Main();
		program.run();		
	}
}
