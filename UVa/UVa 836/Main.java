import java.util.Scanner;
import java.lang.Math;

class UVa836{}   //2D Range Sum using Kadane O(N^3) DP

class Main {
    private int[][] matrix;
    private final int oo = 10000000;

    void run() throws Exception {
        Scanner scanner = new Scanner(System.in);
        int TC = scanner.nextInt();

        while (TC-- > 0) {
            String input = scanner.next();
            int size = input.length();
            matrix = new int[size][size];
            for (int i = 0; i < size; ++i) {
             int val = Character.getNumericValue(input.charAt(i));   
             if (val == 0) matrix[0][i] = -oo;
             else          matrix[0][i] = 1;
             if (i > 0)    matrix[0][i] += matrix[0][i-1];
            }

            for (int i = 1; i < size; ++i) {
                input = scanner.next();
                for (int j  = 0; j < size; ++j) {
                    int val = Character.getNumericValue(input.charAt(j));
                    if (val == 0) matrix[i][j] = -oo;
                    else          matrix[i][j] = 1;
                    if (j > 0) matrix[i][j] += matrix[i][j-1];
                }
            }

            int max_range_sum = -oo * 25 * 25;
            for (int l = 0; l < size; ++l) {
                for (int r = l ; r < size; ++r) {
                    int sub_range = 0;
                    for (int row = 0; row < size; ++row) {
                        if (l > 0) sub_range += matrix[row][r] - matrix[row][l-1];
                        else       sub_range += matrix[row][r];


                        if (sub_range < 0) sub_range = 0;
                        max_range_sum = Math.max(max_range_sum, sub_range);
                    }
                }
            }

            System.out.println(max_range_sum);
            if (TC > 0) System.out.println();
        }
    }

    public static void main(String[] args) throws Exception{
        Main program = new Main();
        program.run();		
    }
}

