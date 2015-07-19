import java.util.*;
import java.io.*;

class Main {

    int[] fibNum = new int[51];

    void calculateFibNum(int limit) {
        fibNum[0] = 0;
        fibNum[1] = 1;
        fibNum[2] = 2;
        for (int i = 3; i < limit; ++i) {
            fibNum[i] = fibNum[i -1] + fibNum[i - 2];
        }
    }

    void run() throws Exception {
        BufferedReader scanner = new BufferedReader(new InputStreamReader(System.in));
        int len = 0;
        calculateFibNum(51);

        while ((len = Integer.parseInt(scanner.readLine())) != 0) {
            System.out.println(fibNum[len]);
        }
    }

    public static void main(String[] args) throws Exception {
        Main program = new Main();
        program.run();
    }
}
