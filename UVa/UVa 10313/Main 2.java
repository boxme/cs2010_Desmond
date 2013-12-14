import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

class UVa10313{}

/*
 * O(n^2) http://hi.baidu.com/krdefndrsbbekmd/item/389c8d4d8ae1cfe11381daba
 * A memo table where the x-axis is the value N and y-axis the number of coins allowed
 */

class Main {
    private long[][] memo;

    void run() throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    	//StringTokenizer scanner = new StringTokenizer(br.readLine());
        memo = new long[301][301];
        memo[0][0] = 1;
    	
    	for (int value = 0; value <= 300; ++value) {
    		for (int num_coins = 1; num_coins <= 300; ++num_coins) {
    			if (num_coins <= value) 
    				memo[value][num_coins] = memo[value-num_coins][num_coins] + memo[value][num_coins-1];
    			else
    				memo[value][num_coins] = memo[value][num_coins-1];
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
    	        pr.println(memo[val][val]);
            }
            else if (input_array.length == 2) {
            	int k = Math.min(300, Integer.parseInt(input_array[1]));
                pr.println(memo[val][k]);
            } 
            else {
            	int k = Math.min(300, Integer.parseInt(input_array[2]));
            	int l = Math.min(300, Integer.parseInt(input_array[1]));
            	if (l < 2) pr.println(memo[val][k]);
            	else 	   pr.println(memo[val][k] - memo[val][l-1]);
            }
        }
        pr.close();
    }

    public static void main(String[] args) throws Exception{
        Main program = new Main();
        program.run();		
    }
}
