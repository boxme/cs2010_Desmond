import java.util.*;
import java.io.*;

class Main {
    
    void run() throws Exception {
        BufferedReader scanner = new BufferedReader(new InputStreamReader(System.in));
        
        int caseNum = 0;
        while (true) {
            String[] input = scanner.readLine().split(" ");
            int N = Integer.parseInt(input[0]);
            int Q = Integer.parseInt(input[1]);

            if (N == 0 && Q == 0) break;
            
            caseNum++;
            
            int[] array = new int[N];
            for (int i = 0; i < N; ++i) {
                array[i] = Integer.parseInt(scanner.readLine());
            }

            Arrays.sort(array);

            System.out.println("CASE# " + caseNum + ":");

            for (int i = 0; i < Q; ++i) {
                int query = Integer.parseInt(scanner.readLine());
                int lo = 0, high = array.length - 1;
                int mid = 0;
                int pos = -1;
                while (lo <= high) {
                    mid = (high + lo) / 2;
                    int value = array[mid];

                    if (value == query) {
                        pos = mid;
                        high = mid - 1;
                    } 
                    
                    if (value > query) {
                        high = mid - 1;
                    } else if (value < query) {
                        lo = mid + 1;
                    }
                }

                if (pos == -1) {
                    System.out.println(query + " not found");
                } else {
                    System.out.println(query + " found at " + ++pos);
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Main program = new Main();
        program.run();
    }
}
