import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

class UVa10567{}

class Main {
    private ArrayList<ArrayList<Integer>> list;

    void run() throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    	//StringTokenizer scanner = new StringTokenizer(br.readLine());
    	list = new ArrayList<ArrayList<Integer>>();
    	String input = br.readLine();
    	int query = Integer.parseInt(br.readLine());
    	int len = input.length();

    	for (int i = 0; i < 256; ++i) 
    	    list.add(new ArrayList<Integer>());

    	for (int i = 0; i < len; ++i) 
    	    list.get(input.charAt(i)).add(i);

    	while (query-- > 0) {
    	    String string = br.readLine();
    	    len = string.length();
    	    int first_index = 0, last_index = -1;
    	    boolean isMatched = true;
    	    for (int i = 0; i < len; ++i) {
    	    	int low = Collections.binarySearch(list.get(string.charAt(i)), last_index+1);
    	        if (low < -1 || list.get(string.charAt(i)).isEmpty()) {
    	        	isMatched = false;
    	        	break;
    	        }
    	        if (low == -1) last_index = list.get(string.charAt(i)).get(0);
                else           last_index = list.get(string.charAt(i)).get(low);
                if (i == 0) first_index = last_index;
            }

            if (isMatched)
                pr.printf("Matched %d %d\n", first_index, last_index);
            else
                pr.printf("Not Matched\n");
        }
        pr.close();
    }

    public static void main(String[] args) throws Exception{
        Main program = new Main();
        program.run();		
    }
}
