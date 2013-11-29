import java.util.Arrays;
import java.util.Scanner;

class UVa10819{}

class Main {
    private int items, budget;
    private int[][] memo;
    private int[] item_price, item_points;

       int knapsack(int index, int spent) {
           if (spent > budget && budget < 1800)                    return -1000; // Insufficient budget for credit
           if (spent > budget + 200)                               return -1000; // Spent >= credit limit
           if (index == items && spent > budget && spent <= 2000)  return -1000; // Impossible to trigger credit limit
           if (index == items)                                     return 0;
           if (memo[index][spent] != -1)                           return memo[index][spent];
           
           return memo[index][spent] = Math.max(knapsack(index+1, spent), 
                                                item_points[index] + knapsack(index+1, spent + item_price[index]));
       }

       void run() throws Exception {
	   Scanner scanner = new Scanner(System.in);

	   while (scanner.hasNext()) {
               budget = scanner.nextInt();
               items = scanner.nextInt();

               item_price = new int[items];
               item_points = new int[items];
               memo = new int[110][15000];

               for (int i = 0; i < 110; ++i)
                   Arrays.fill(memo[i], -1);

               for (int i = 0; i < items; ++i) {
                   item_price[i] = scanner.nextInt();
                   item_points[i] = scanner.nextInt();
               }
               int answer = knapsack(0, 0);
               System.out.println(answer);
           }
        }
	
	public static void main(String[] args) throws Exception{
		Main program = new Main();
		program.run();		
	}
}

