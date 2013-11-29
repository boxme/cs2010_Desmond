import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;

/*
 * The method below is O(N^2)
 * For O(N): 
 * consider the sequence 6 -6 -6 -7 8 
At the start, greatest positive integer: 6, Negative: N/A         *initialize       

Next number: -6                                           // If num < 0 {max_pos = max_neg*num; max_neg = Math.max(max_pos*num, num)} 
Greatest positive integer, N/A 							  
Negative, -36 

Next number: -6 
Greatest positive integer, 216 
Negative, -6 
														 // If num == 0, max_pos = max_neg = 0;
Next number: -7 
Greatest positive integer, 42 
Negative, -1512 

Next number: 8 											 // If num > 0 {max_pos = Math.max(max_pos*num, num); max_neg = max_neg*num;}
Greatest positive integer, 336 
Negative, -12096 

And of course the answer is 336 for this example... 

If the last number was 4, then the answer would be 216...
 */
class UVa787 {
	
	public BigInteger find_max(ArrayList<BigInteger> array) {
		BigInteger max = array.get(0);
		for (int i = 0; i < array.size(); i++) {
			BigInteger cur = array.get(i);
			max = cur.max(max);
			for (int j = i+1; j < array.size(); j++) {
				cur = cur.multiply(array.get(j));
				max = cur.max(max);
			}			
		}
		return max;
	}
}

class Main {
	
	void run() {
		Scanner scanner = new Scanner(System.in);
		UVa787 compute = new UVa787();
		while (scanner.hasNext()) {
			ArrayList<BigInteger> array = new ArrayList<BigInteger>();
			while (true) {
				int val = scanner.nextInt();
				if (val == -999999) break;
				BigInteger big = BigInteger.valueOf(val);
				array.add(big);
			}
			System.out.println(compute.find_max(array));
		}	
	}
	public static void main(String[] args) {
		Main program = new Main();
		program.run();		
	}
}
