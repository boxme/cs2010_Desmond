import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;

class UVa10313{}

class Main {
    private long[][][] memo;

    void run() throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    	//StringTokenizer scanner = new StringTokenizer(br.readLine());
        memo = new long[301][301][301];
        
    	for (int temp_val = 0; temp_val <= 300; ++temp_val) {
			Arrays.fill(memo[0][temp_val], 1);
    	}
    	
    	for (int value = 1; value <= 300; ++value) {
    		for (int temp_val = 1; temp_val <= 300; ++temp_val) {
        	    for (int num_coins = 1; num_coins <= 300; ++num_coins) {
        	    	if (value >= temp_val) memo[value][temp_val][num_coins] = memo[value-temp_val][temp_val][num_coins-1] +
        	    															  memo[value][temp_val-1][num_coins];
        	    	else memo[value][temp_val][num_coins] = memo[value][temp_val-1][num_coins];
        	    }
    		}
    	}
    	
    	String input = "";
    	while (br.ready() && !(input = br.readLine()).equals("")) {
    		if (input.compareTo("") == 0) break;
    	    String[] input_array = input.split(" ");
    	    int val = Integer.parseInt(input_array[0]);
    	    if (val == 0) {
    	    	pr.println(1);
    	    }
    	    else if (input_array.length == 1) {
    	        pr.println(memo[val][val][val]);
            }
            else if (input_array.length == 2) {
            	int k = Math.min(300, Integer.parseInt(input_array[1]));
                pr.println(memo[val][val][k]);
            } 
            else {
            	int k = Math.min(300, Integer.parseInt(input_array[2]));
            	int l = Math.min(300, Integer.parseInt(input_array[1]));
            	if (l == 0) l++;
                pr.println(memo[val][val][k] - memo[val][val][l-1]);
            }
        }
        pr.close();
    }

    public static void main(String[] args) throws Exception{
        Main program = new Main();
        program.run();		
    }
}
