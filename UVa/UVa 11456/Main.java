import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

class UVa11456{}

class Main {
    private int[] cars;
    private int num_cars;
       
       void run() throws Exception {
	   IntegerScanner scanner = new IntegerScanner(System.in);
	   PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

	   int test_cases = scanner.nextInt();

	   while (test_cases-- > 0) {
	       num_cars = scanner.nextInt();
	       cars = new int[num_cars];
	       
	       for (int i = 0; i < num_cars; ++i)
	           cars[i] = scanner.nextInt();

	       int answer = 0;

               ArrayList<Integer> LIS_list = new ArrayList<Integer>();
               ArrayList<Integer> LDS_list = new ArrayList<Integer>();

               for (int i = num_cars - 1; i >= 0; --i) {
                   int len_LIS, len_LDS;
                   int pos_LIS = Collections.binarySearch(LIS_list, cars[i]);
                   int pos_LDS = Collections.binarySearch(LDS_list, -cars[i]);


                   if (pos_LIS < 0) pos_LIS = -(pos_LIS + 1);
                   
                   if (pos_LIS == LIS_list.size()) {
                       LIS_list.add(cars[i]);
                       len_LIS = LIS_list.size();
                   } else {
                       LIS_list.set(pos_LIS, cars[i]);
                       len_LIS = pos_LIS + 1;
                   }

                   if (pos_LDS < 0) pos_LDS = -(pos_LDS + 1);

                   if (pos_LDS == LDS_list.size()) {
                       LDS_list.add(-cars[i]);
                       len_LDS = LDS_list.size();
                   } else {
                       LDS_list.set(pos_LDS, -cars[i]);
                       len_LDS = pos_LDS + 1;
                   }

                   answer = Math.max(answer, len_LDS + len_LIS - 1);
               }
               pw.println(answer);
           }
           pw.close();
        }
	
	public static void main(String[] args) throws Exception{
		Main program = new Main();
		program.run();		
	}
}

class CompareCars implements Comparator<Integer> {
    @Override
        public int compare(Integer a, Integer b) {
            return b - a;
        }
}

class IntegerScanner {
    BufferedInputStream bis;
    IntegerScanner(InputStream is) {
        bis = new BufferedInputStream(is, 1000000);
    }

    public int nextInt() {
        int result = 0;
        try {
            int cur = bis.read();
            if (cur == -1) return -1;

            while (cur < 48 || cur > 57) {
                cur = bis.read();
            }

            while (cur >= 48 && cur <= 57) {
                result = result * 10 + (cur - 48);
                cur = bis.read();
            }
            return result;
        }
        catch (IOException ios) {
            return -1;
        }
    }
}
