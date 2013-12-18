import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

class UVa469{}

class Main {
    private char[][] lands, temp;
    private int r, c;
    private static final int[] dr = {1,1,0,-1,-1,-1,0,1};
    private static final int[] dc = {0,1,1,1,0,-1,-1,-1};           //S,SE,E,NE,N,NW,W,SW


    void copy() {
    	temp = new char[r+1][c+1];
    	for (int i = 1; i < r; ++i) 
    		temp[i] = Arrays.copyOf(lands[i], c);
    }

    int floodfill(int row, int col, char c1, char c2) {
        if (row <= 0 || row >= r || col <= 0 || col >= c) return 0;
        if (temp[row][col] != c1) return 0;
        int ans = 1;
        temp[row][col] = c2;
        for (int i = 0; i < 8; ++i) 
            ans += floodfill(row+dr[i], col+dc[i], c1, c2);
        return ans;
    }

    void run() throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    	StringTokenizer scanner = new StringTokenizer(br.readLine());
        int TC = Integer.parseInt(scanner.nextToken());
        br.readLine();
        while (TC-- > 0) { 
            lands = new char[101][101];
            String input = "";
            r = c = 1;
            while (!(input = br.readLine()).equals("") && (input.charAt(0) == 'L' || input.charAt(0) == 'W')) {
            	c = input.length();
                for (int i = 0; i < c; ++i) {
                	lands[r][i+1] = input.charAt(i);
                }
                r++;
            }
            c++;

            scanner = new StringTokenizer(input);
            int query_r = Integer.parseInt(scanner.nextToken()); int query_c = Integer.parseInt(scanner.nextToken());
            copy();
            pr.println(floodfill(query_r, query_c, 'W', '.'));
            
            input = "";
            while (br.ready() && !(input = br.readLine()).equals("")) {
                if (input.equals("")) break;
                scanner = new StringTokenizer(input);
                query_r = Integer.parseInt(scanner.nextToken()); query_c = Integer.parseInt(scanner.nextToken());
                copy();
                pr.println(floodfill(query_r, query_c, 'W', '.'));
            }
            
            if (TC > 0) pr.println();
        }
        pr.close();
    }

    public static void main(String[] args) throws Exception{
        Main program = new Main();
        program.run();		
    }
}

class Pair implements Comparable<Pair> {
	Integer u, v;
	
	public Pair(int u, int v) {
		this.u = u;
		this.v = v;
	}
	
	@Override
	public int compareTo(Pair other) {
		return this.u - other.u;
	}
}
