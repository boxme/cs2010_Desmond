import java.util.*;
import java.io.*;

class Main {

    HashMap<Integer, Integer> hashMap;

    void run() throws Exception {
        BufferedReader scanner = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(scanner.readLine());
        
        hashMap = new HashMap<Integer, Integer>();

        while (TC -- > 0) {
            int numOfSnowFlakes = Integer.parseInt(scanner.readLine());
            int max = 0, start = 0;
            hashMap.clear();

            for (int i = 0; i < numOfSnowFlakes; ++i) {
                int snowFlake = Integer.parseInt(scanner.readLine());
                if (hashMap.containsKey(snowFlake)) {
                    int pos = hashMap.get(snowFlake);
                    if (pos >= start) {
                        start = pos + 1;
                    }
                    max = Math.max(max, i - start + 1);
                    hashMap.put(snowFlake, i);
                } else {
                    hashMap.put(snowFlake, i);
                    max = Math.max(max, i - start + 1);
                }
            }

            System.out.printf("%d%n", max);
        }
    }

    public static void main(String[] args) throws Exception {
        Main program = new Main();
        program.run();
    }
}
