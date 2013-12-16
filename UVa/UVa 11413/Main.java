import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

class UVa11413{}

class Main {
    private int[] vessels;
    private int n, m;

    boolean stimulation(int cap) {
        int total = 0, cur = 0;
        for (int i = 0; i < n; ++i) {
            if (vessels[i] > cap) return false;
            if (cur + vessels[i] > cap) cur = 0;
            if (cur == 0) total++;
            cur += vessels[i];
            if (total > m) return false;
        }
        return true;
    }

    void run() throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    	//StringTokenizer scanner = new StringTokenizer(br.readLine());
    	StringTokenizer scanner = null;
    	String input = "";
    	while (br.ready() && (input = br.readLine()) != "") {
    	    scanner = new StringTokenizer(input);
    	    n = Integer.parseInt(scanner.nextToken());
    	    m = Integer.parseInt(scanner.nextToken());

    	    vessels = new int[n];
    	    scanner = new StringTokenizer(br.readLine());
    	    for (int i = 0; i < n; ++i)
    	        vessels[i] = Integer.parseInt(scanner.nextToken());

            int lo = 0, hi = 1000000000;
            while (hi - lo > 0) {
                if (stimulation(hi)) {
                    hi = ((hi - lo)/2) + lo;    //lower half
                } 
                else {
                    lo = hi;
                    hi = hi + (hi / 2);         //upper half
                }
            }
            pr.println(hi+1);
        }
        pr.close();
    }

    public static void main(String[] args) throws Exception{
        Main program = new Main();
        program.run();		
    }
}
