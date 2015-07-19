import java.util.*;
import java.io.*;

class Main {

    Triple[] array;
    int[] memo;

    void run() throws Exception {
        BufferedReader scanner = new BufferedReader(new InputStreamReader(System.in));
        int n = 0;
        int caseNum = 0;
        while ((n = Integer.parseInt(scanner.readLine())) != 0) {
            caseNum++;

            array = new Triple[6*n];
            String[] input;
            int k = 0;
            for (int i = 0; i < n; ++i) {
                input = scanner.readLine().split(" ");
                int x = Integer.parseInt(input[0]);
                int y = Integer.parseInt(input[1]);
                int z = Integer.parseInt(input[2]);

                array[k++] = new Triple(x, y, z);
                array[k++] = new Triple(x, z, y);
                array[k++] = new Triple(y, z, x);
                array[k++] = new Triple(y, x, z);
                array[k++] = new Triple(z, x, y);
                array[k++] = new Triple(z, y, x);
            }

            Arrays.sort(array);
            int ans = 0;
            memo = new int[6*n];
            for (int i = 1; i < 6*n; ++i) {
                int max = 0;
                for (int j = 0; j < i; ++j) {
                    if (array[i].x > array[j].x && array[i].y > array[j].y) {
                        max = Math.max(max, memo[j]);
                    }
                }
                memo[i] = max + array[i].z;
                ans = Math.max(ans, memo[i]);
            }

            System.out.println("Case " + caseNum + ": maximum height = " + ans);
        }
    }

    public static void main(String[] args) throws Exception {
        Main program = new Main();
        program.run();
    }

    static class Triple implements Comparable<Triple> {
        int x, y, z;

        public Triple(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        public int compareTo(Triple t) {
            if (this.x != t.x) return this.x - t.x;
            if (this.y != t.y) return this.y - t.y;
            return this.z - t.z;
        }
    }
}
