import java.util.Scanner;
import java.lang.Math;
import java.util.Arrays;

class UVa10130{}

class Main {
    private int num_items, num_people;
    private int[] prices;
    private int[] weight;
    private int[] family;
    private int[][] memo;

       int knapsack(int index, int remaining_weight) {
           if (index == num_items || remaining_weight == 0) return 0;
           if (memo[remaining_weight][index] != -1) return memo[remaining_weight][index];

           if (remaining_weight >= weight[index]) {
               return memo[remaining_weight][index] = Math.max(knapsack(index+1, remaining_weight), 
                                                           prices[index] + knapsack(index+1, remaining_weight - weight[index]));
           }
           else 
               return memo[remaining_weight][index] = knapsack(index+1, remaining_weight);
       }

       void run() throws Exception {
	   Scanner scanner = new Scanner(System.in);
	   int test_cases = scanner.nextInt();

	   while (test_cases-- > 0) {
	       num_items = scanner.nextInt();
	       prices = new int[num_items];
	       weight = new int[num_items];

	       for (int i = 0; i < num_items; ++i) {
	           prices[i] = scanner.nextInt();
	           weight[i] = scanner.nextInt();
               }

               num_people = scanner.nextInt();
               family = new int[num_people];

               for (int i = 0; i < num_people; ++i)
                   family[i] = scanner.nextInt();

               int answer = 0;
               memo = new int[35][1001];

               for (int i = 0; i < 35; ++i)
                   Arrays.fill(memo[i], -1);

               for (int i = 0; i < num_people; ++i)
                   answer += knapsack(0, family[i]);

               System.out.println(answer);
           }
        }
	
	public static void main(String[] args) throws Exception{
		Main program = new Main();
		program.run();		
	}
}

