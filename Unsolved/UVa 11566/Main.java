import java.util.Arrays;
import java.util.Scanner;

class UVa11566{}

class Main {
    private int N, x, T, K, max_cost, tea_charge, max_dishes;
    private int[] price, favour;
    private int[][] memo;

      int order_dimsum(int num_dishes, int item, int spent) {
    	  if (spent < 0) return -1000000;
          if (item == (2*K) || num_dishes == max_dishes || spent == 0) return 0;
          if (memo[num_dishes][spent] != -1) return memo[num_dishes][spent];

          return memo[num_dishes][spent] = Math.max(order_dimsum(num_dishes, item+1, spent), 
                                              favour[item]+ order_dimsum(num_dishes+1, item+1, spent-price[item]));
      }

       void run() throws Exception {
	   Scanner scanner = new Scanner(System.in);
	   N = scanner.nextInt(); x = scanner.nextInt();
	   T = scanner.nextInt(); K = scanner.nextInt();

	   while (N+x+T+K != 0) {
	       price = new int[2*K];
	       favour = new int[2*K];
	       memo = new int[25][2500];

	       for (int i=0; i<(2*K)-1; i+=2) {
	           price[i] = price[i+1] = scanner.nextInt();
	           for (int j=0; j<N+1; ++j)
	               favour[i] += scanner.nextInt();
	           favour[i+1] = favour[i];
           }

	       for (int i=0; i<25; ++i)
	    	   Arrays.fill(memo[i], -1);

               tea_charge = T*(N+1);
               max_cost = (int)(Math.floor(((N+1)*x) / 1.1 + 1e-6) - tea_charge);
               max_dishes = (N+1)*2;
               int ans = 0;
              
               double answer = (double) order_dimsum(0,0,max_cost);
               answer = answer / (N+1);
               System.out.printf("%.2f\n", answer);

               N = scanner.nextInt(); x = scanner.nextInt();
               T = scanner.nextInt(); K = scanner.nextInt();
           }
        }
	
	public static void main(String[] args) throws Exception{
		Main program = new Main();
		program.run();		
	}
}

