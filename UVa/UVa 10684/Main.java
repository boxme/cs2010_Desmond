import java.util.Scanner;


class UVa10684 {
	public void find_max_streak(int[] bets, int N) {
		int max_streak = 0;
		int temp_max_streak = 0;
		for (int i = 0; i < N; i++) {
			temp_max_streak += bets[i];
			max_streak = Math.max(max_streak, temp_max_streak);
			
			if (temp_max_streak < 0) temp_max_streak = 0;
		}
		
		if (max_streak <= 0) System.out.println("Losing streak.");
		else				 System.out.println("The maximum winning streak is " + max_streak + ".");
	}
}

class Main {
	
	void run() {
		Scanner scanner = new Scanner(System.in);
		UVa10684 compute = new UVa10684();
		int N=0;
		while (scanner.hasNext() && (N=scanner.nextInt()) != 0) {
			int[] bets = new int[N];
			
			for (int i = 0; i < N; i++) 
				bets[i] = scanner.nextInt();
			compute.find_max_streak(bets, N);
		}
	}
	public static void main(String[] args) {
		Main program = new Main();
		program.run();		
	}
}
