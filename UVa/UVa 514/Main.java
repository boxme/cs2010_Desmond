import java.util.*;
import java.io.*;

class Main {

    void run() throws Exception {
        BufferedReader scanner = new BufferedReader(new InputStreamReader(System.in));
        int N = 0;
        
        while ((N = Integer.parseInt(scanner.readLine())) != 0) {

            while (true) {
                String[] trains = scanner.readLine().split(" ");

                int size = trains.length;
                int[] target = new int[size];
                for (int i = 0; i < size; ++i) {
                    target[i] = Integer.parseInt(trains[i]);
                }

                if (target[0] == 0) break;

                Stack<Integer> station = new Stack<Integer>();
                int targetIndex = 0;
                for (int current = 1; current <= N; ++current) {
                    station.push(current);
                    while (!station.empty() && station.peek() == target[targetIndex]) {
                        station.pop();
                        targetIndex++;
                    }
                }

                if (station.empty()) {
                    System.out.println("Yes");
                } else {
                    System.out.println("No");
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) throws Exception {
        Main program = new Main();
        program.run();
    }
}
