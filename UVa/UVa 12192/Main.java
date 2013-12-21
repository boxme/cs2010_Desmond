import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

class UVa12192{}

class Main {
    private int N, M;
    private int[][] array;

    int lower_bound(int row, int key) {
        int hi = M-1; int lo = 0;
        int mid = (hi - lo)/2 + lo;
        while (true) {
            if (array[row][mid] >= key) {
                hi = mid - 1;
                if (lo > hi) return mid;
            } else {
                lo = mid + 1;
                if (lo > hi) return mid < M-1 ? mid+1 : -1;
            }
            mid = (hi - lo)/2 + lo;
        }
    }

    void run() throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    	StringTokenizer scanner = new StringTokenizer(br.readLine());
    	
    	while (((N = Integer.parseInt(scanner.nextToken())) + (M = Integer.parseInt(scanner.nextToken()))) != 0) {
    	    array = new int[N][M];
    	    for (int i = 0; i < N; ++i) {
    	        scanner = new StringTokenizer(br.readLine());
    	        for (int j = 0; j < M; ++j) 
    	            array[i][j] = Integer.parseInt(scanner.nextToken());
            }

            scanner = new StringTokenizer(br.readLine());
            int Q = Integer.parseInt(scanner.nextToken());
            for (int i = 0; i < Q; ++i) {
                scanner = new StringTokenizer(br.readLine());
                int lo = Integer.parseInt(scanner.nextToken()); int hi = Integer.parseInt(scanner.nextToken());
                int cur_max = 0;
                for (int j = 0; j < N; ++j) {
                    int index = lower_bound(j, lo);
                    if ((index != -1) && (j + cur_max < N)) {
                        for (int k = cur_max; k < M; ++k) {
                        	if ((j + k >= N) || (index + k >= M) || (array[j+k][index+k] > hi)) break;
                        	else cur_max++;
                        }
                    }
                }
                pr.println(cur_max);
            }
            pr.println("-");
    	    scanner = new StringTokenizer(br.readLine());
        }
        pr.close();
    }

    public static void main(String[] args) throws Exception{
        Main program = new Main();
        program.run();		
    }
}


