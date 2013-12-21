import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

class UVa12032{}

class Main {
    private int N;
    private int[] ladder;

    boolean stimulation(int k) {
        if (ladder[0] > k) return false;
        if (ladder[0] == k) k--;
        for (int i = 1; i < N; ++i) {
            int height = ladder[i] - ladder[i-1];
            if (height == k) k--;
            else if (height > k) return false;
        }
        return true;
    }

    void run() throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    	StringTokenizer scanner = new StringTokenizer(br.readLine());
    	int TC = Integer.parseInt(scanner.nextToken());
    	int case_num = 0;
    	while (TC-- > 0) {
    	    case_num++;
    	    scanner = new StringTokenizer(br.readLine());
    	    N = Integer.parseInt(scanner.nextToken());
    	    ladder = new int[N];
    	    scanner = new StringTokenizer(br.readLine());
    	    for (int i = 0; i < N; ++i)
    	        ladder[i] = Integer.parseInt(scanner.nextToken());

    	    int hi = 11000000; int lo = 0;
    	    while (hi - lo > 0) {
    	        if (stimulation(hi)) {
    	        	hi = (hi - lo)/2 + lo;
    	        } else {
					lo = hi;
					hi = hi+ hi/2;
				}
            }
            pr.printf("Case %d: %d\n", case_num, hi+1);
        }
        pr.close();
    }

    public static void main(String[] args) throws Exception{
        Main program = new Main();
        program.run();		
    }
}


