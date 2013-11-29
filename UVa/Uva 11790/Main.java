import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

class UVa11790{}

class Main {
    private int[] building_length, building_width;
    private int[] LIS, LDS;
    private int num_buildings;
    
       int get_length(int end, int[] P) {
           int i = end;
           int len = 0;
           
           for (; P[i] >= 0; i = P[i]) len += building_width[i];
           
           len += building_width[i];
           
           return len;
       }
       void run() throws Exception {
	   IntegerScanner scanner = new IntegerScanner(System.in);
	   PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

	   int test_cases = scanner.nextInt();
	   int cases = 0;
	   while (test_cases-- > 0) {
	       ++cases;
	       num_buildings = scanner.nextInt();
	       building_length = new int[num_buildings];
	       building_width = new int[num_buildings];

	       for (int i = 0; i < num_buildings; ++i)
	           building_length[i] = scanner.nextInt();

	       for (int i = 0; i < num_buildings; ++i)
	           building_width[i] = scanner.nextInt();

	       int LIS_length = 0, LDS_length = 0;
	       int temp_lis = 0, temp_lds = 0;
	       LIS = new int[100000]; LDS = new int[100000];

	       for (int i = 0; i < num_buildings; ++i) {
	           temp_lis = temp_lds = LIS[i] = LDS[i] = building_width[i];

	           for (int j = 0; j < i; ++j) {
	               if (building_length[j] < building_length[i])
	                   LIS[i] = Math.max(LIS[i], temp_lis + LIS[j]);

                       if (building_length[j] > building_length[i])
                           LDS[i] = Math.max(LDS[i], temp_lds + LDS[j]);
                   }
                   LIS_length = Math.max(LIS_length, LIS[i]);
                   LDS_length = Math.max(LDS_length, LDS[i]);
               }

               if (LIS_length >= LDS_length)
                   pw.printf("Case %d. Increasing (%d). Decreasing (%d).\n", cases, LIS_length, LDS_length);
               else
                   pw.printf("Case %d. Decreasing (%d). Increasing (%d).\n", cases, LDS_length, LIS_length);
           }
           pw.close();
        }
	
	public static void main(String[] args) throws Exception{
		Main program = new Main();
		program.run();		
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
