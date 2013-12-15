import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

class UVa10341{}

class Main {
    private static final double eps = 1e-9;
    private int[] array;
    private double low, high, ans;
    private boolean has_decreased, has_increased;

    void compute(double x) {
        double temp = array[0]*Math.exp(-x) + array[1]*Math.sin(x) + 
                      array[2]*Math.cos(x) + array[3]*Math.tan(x) + 
                      array[4]*Math.pow(x,2) + array[5];
        if (has_decreased) {
            if (temp < ans) ans = temp;
            else { 
                ans = -1;
                return;
            }
            has_decreased = false;
        }
        else if (has_increased) {
            if (temp > ans) ans = temp;
            else  {
                ans = -1;
                return;
            }
            has_increased = false;
        } 
        else {
			ans = temp;
		}

        if (ans - 0 > eps) {
            has_decreased = true;
            compute(((low = x) + high)/2);
        }
        else if (0 - ans > eps) {
            has_increased = true;
            compute((low + (high = x))/2);
        }
        else {
        	ans = x;
        	return;
        }
    }

    void run() throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    	//StringTokenizer scanner = new StringTokenizer(br.readLine());
    	String input = "";
    	while (br.ready() && (input=br.readLine()) != "") {
    	    if (input.equals("")) break;
    	    StringTokenizer scanner = new StringTokenizer(input);
    	    array = new int[6];
    	    for (int i = 0; i < 6; ++i)
    	        array[i] = Integer.parseInt(scanner.nextToken());
    	    low = 0.0; high = 1.0;
    	    has_decreased = has_increased = false;
    	    compute((high+low)/2);
    	    if (ans == -1) pr.println("No solution");
            else           pr.printf("%.4f\n", ans);
        }
        pr.close();
    }

    public static void main(String[] args) throws Exception{
        Main program = new Main();
        program.run();		
    }
}
