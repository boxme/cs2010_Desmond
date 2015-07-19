import java.io.*;
import java.util.*;

class Main {
    
    TreeMap<String, Integer> treeMap;
    HashMap<String, Integer> hashMap;

    void run() throws Exception {
        BufferedReader scanner = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(scanner.readLine());

        scanner.readLine();
        String name;
        treeMap = new TreeMap<String, Integer>();
        hashMap = new HashMap<String, Integer>();

        while (TC-- > 0) {
            
            int total = 0;
            name = null;

            while ((name = scanner.readLine()) != null) {
                if (name.length() == 0) break;

                if (hashMap.containsKey(name)) {
                    hashMap.put(name, hashMap.get(name) + 1);
                } else {
                    hashMap.put(name, 1);
                    treeMap.put(name, 1);
                }

                total++;
            }

            for (Map.Entry<String, Integer> entry : treeMap.entrySet()) {
                System.out.printf("%s %.4f%n", entry.getKey(), (double) (hashMap.get(entry.getKey())) / (double) total * 100);
            }

            if (TC > 0) {
                System.out.println();
                treeMap.clear();
                hashMap.clear();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Main program = new Main();
        program.run();
    }
}
