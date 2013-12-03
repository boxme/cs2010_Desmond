import java.util.Scanner;

class UVa11566{}

class Main {
    private int N, x, T, K, max_dishes;
    private int[] price, favour;
    private int[][] memo;

    int cost_with_gst(int x) {
        x *= 11;
        if (x%10 == 0) return x/10;
        else           return (x/10)+1;
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

            int tea_charge = T*(N+1);
            int total_money = (N+1)*x;
            int max_cost = total_money - tea_charge;
            max_dishes = (N+1)*2;

            for (int i = 0; i < (2*K); ++i)
                for (int j = max_cost; j >= price[i]; --j)			//Rmb bottom up dp has to visit every state
                    for (int k = 1; k <= max_dishes; ++k)
                        memo[k][j] = Math.max(memo[k][j], memo[k-1][j-price[i]] + favour[i]);

            int answer = 0;
            for (int i = 0; i <= max_cost ; ++i)
                if (cost_with_gst(i+tea_charge) <= total_money)
                    for (int j = 1; j <= max_dishes; ++j)
                        answer = Math.max(answer, memo[j][i]);
            
            System.out.printf("%.2f\n", (double) answer/(N+1));

            N = scanner.nextInt(); x = scanner.nextInt();
            T = scanner.nextInt(); K = scanner.nextInt();
        }
    }

    public static void main(String[] args) throws Exception{
        Main program = new Main();
        program.run();		
    }
}

