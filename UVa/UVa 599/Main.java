import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

class UVa599{}

class Main {
    private int bitset;

    void run() throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    	StringTokenizer scanner = new StringTokenizer(br.readLine());
    	int TC = Integer.parseInt(scanner.nextToken());
    	while (TC-- > 0) {
    	    bitset = 1;
    	    String input = null;
    	    int num_edges = 0;
    	    while ((input = br.readLine()).charAt(0) != '*') {
    	        bitset |= (1 << (input.charAt(1) - 64));
    	        bitset |= (1 << (input.charAt(3) - 64));
    	        ++num_edges;
            }
            input = br.readLine();
            String[] array = input.split(",");
            int num_vertex = array.length;
            int num_connected = num_vertex - num_edges;
            int vertex_with_edges = 0;
            bitset--;
            while (bitset != 0) {
                int pos = Integer.numberOfTrailingZeros(bitset);
                vertex_with_edges++;
                bitset ^= (1 << pos);
            }
            num_connected -= (num_vertex - vertex_with_edges);
            pr.printf("There are %d tree(s) and %d acorn(s).\n", num_connected, (num_vertex - vertex_with_edges));

        }
        pr.close();
    }

    public static void main(String[] args) throws Exception{
        Main program = new Main();
        program.run();		
    }
}	
