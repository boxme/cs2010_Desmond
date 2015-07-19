import java.io.*;
import java.util.*;

class Main {

    void run() throws Exception {
        BufferedReader scanner = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(scanner.readLine());
        int[] ladies = new int[N];
        String[] ladiesHeight = scanner.readLine().split(" ");

        for (int i = 0; i < N; ++i) {
            ladies[i] = Integer.parseInt(ladiesHeight[i]);
        }

        int Q = Integer.parseInt(scanner.readLine());
        String[] luchuHeight = scanner.readLine().split(" ");
        for (int i = 0; i < Q; ++i) {
            int value = Integer.parseInt(luchuHeight[i]);

            int smaller = binarySearchForLower(value, ladies);
            int taller = binarySearchForUpper(value, ladies);
            
            String ans = (smaller == -1 ? "X" : smaller) + " " + (taller == -1 ? "X" : taller);
            System.out.println(ans);
        }
    }

    int binarySearchForLower(int value, int[] array) {
        int lo = 0, hi = array.length - 1;
        int ans = -1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int val = array[mid];
            
            if (val < value) {
                ans = val;
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }

        return ans;
    }

    int binarySearchForUpper(int value, int[] array) {
        int lo = 0, hi = array.length - 1;
        int ans = -1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int val = array[mid];

            if (val > value) {
                ans = val;
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }

        return ans;
    }

    public static void main(String[] args) throws Exception {
        Main program = new Main();
        program.run();
    }
}
