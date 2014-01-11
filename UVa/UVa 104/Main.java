import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

class UVa104{}

class Main {
    private double[][][] table;
    private int[][][] p;
    private int N;
    private PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

    void print_path(int i, int j, int steps) {
        if (steps == 0) 
            pr.printf("%d", i+1);
        else { 
            print_path(i, p[i][j][steps], steps-1);
            pr.printf(" %d", j + 1);
        }
    }
    
    void floyd_warshall() {
        for (int steps = 1; steps < N; ++steps) {
            for (int k = 0; k < N; ++k) {
                for (int i = 0; i < N; ++i) {
                    for (int j = 0; j < N; ++j) {
                        if (table[i][j][steps+1] < table[i][k][steps] * table[k][j][1]) {	//move by 1 step
                            table[i][j][steps+1] = table[i][k][steps] * table[k][j][1];
                            p[i][j][steps+1] = k;											//Each step will have a diff k
                        }
                    }
                }
            }

            for (int i = 0; i < N; ++i) {
                if (table[i][i][steps+1] > 1.01) {
                    print_path(i, i, steps+1);
                    pr.println();
                    return;
                }
            }
        }
        pr.println("no arbitrage sequence exists");
    }

    void run() throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//    	PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    	StringTokenizer scanner = null;
    	String input = "";
    	while (br.ready() && !(input = br.readLine()).equals("")) {
            if (input.equals("")) break;
    	    N = Integer.parseInt(input);
    	    table = new double[30][30][30]; p = new int[30][30][30];
    	    
    	    for (int i = 0; i < N; ++i) {
    	        scanner = new StringTokenizer(br.readLine());
    	        for (int j = 0; j < N; ++j) {
                    if (i != j) {
                        table[i][j][1] = Double.parseDouble(scanner.nextToken());	//i -> j is one step
                        p[i][j][1] = i;
                    }
                }
            }

    	    floyd_warshall();
        }
        pr.close();
    }

    public static void main(String[] args) throws Exception{
        Main program = new Main();
        program.run();		
    }
}
