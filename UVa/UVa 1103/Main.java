import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

class UVa1103{}

class Main {
    private int row, col, W, id, pid, can;
    private final int[] dr = {-1,0,1,0,-1,1,1,-1};          //N,E,S,W,NE,SE,SW,NW
    private final int[] dc = {0,1,0,-1,1,1,-1,-1};
    private int[][] g, used;
    private int[] hieroglyphs;

    void label_hieroglyphs(int n, int m) {
        if (n < 0 || n >= row || m < 0 || m >= col) return;
        if (used[n][m] == 1 || g[n][m] == 0) return;
        g[n][m] = id;
        used[n][m] = 1;
        for (int i = 0; i < 4; ++i) 
            label_hieroglyphs(n + dr[i], m + dc[i]);
    }
    
    void find_holes(int n, int m) {
    	if (n < 0 || n >= row || m < 0 || m >= col) {can = 0; return;}
    	if (g[n][m] == 1) return;
    	if (g[n][m] >= 2) {
    		if (pid == -1) 	{pid = g[n][m]; return;}
    		else if (pid != g[n][m]) {can = 0; return;}
    		else if (pid == g[n][m]) return;
    	}
    	g[n][m] = (g[n][m] == 0) ? 1 : g[n][m];
    	for (int i = 0; i < 4; ++i) 
            find_holes(n + dr[i], m + dc[i]);
    }
    
    void run() throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    	StringTokenizer scanner = new StringTokenizer(br.readLine());
    	int TC = 0;
    	while ((row = Integer.parseInt(scanner.nextToken())) + (W = Integer.parseInt(scanner.nextToken())) != 0) {
    		if (row + col == 0) break;
    	    TC++;
    	    col = W*4;
    	    g = new int[row][col]; used = new int[row][col];
    	    for (int i = 0; i < row; ++i) {
    	        String input = br.readLine(); int index = 0;
    	        for (int j = 0; j < W; ++j) {
    	            index = (j+1)*4 - 1;
    	            int num = (input.charAt(j) <= '9') ? input.charAt(j) - '0' : input.charAt(j) - 'a' + 10;
    	            for (int k = 0; k < 4; ++k, index--) {
    	                g[i][index] = num & 1;
    	                num = num >> 1;
                    }
    	        }
            }
    	    
//    	    for (int i = 0; i < row; ++i) {
//    	    	for (int j = 0; j < col; ++j) {
//    	    		System.out.print(g[i][j]);
//    	    	}
//    	    	System.out.println();
//    	    }
            
            hieroglyphs = new int[40000];
            //Label the hieroglyphs with an id
            id = 2;
            for (int i = 0; i < row; ++i) {
                for (int j = 0; j < col; ++j) {
                    if (g[i][j] == 1 && used[i][j] == 0) {
                        label_hieroglyphs(i, j);
                        id++;
                    }
                }
            }

            //count number of enclosed empty circle
            for (int i = 0; i < row; ++i) {
                for (int j = 0; j < col; ++j) {
                    if (g[i][j] == 0) {
                        can = 1; pid = -1;
                        find_holes(i, j);               	    
                        if (can == 1) {
                            hieroglyphs[pid]++;
                        }
                    }
                }
            }

            ArrayList<Character> list = new ArrayList<Character>();
            for (int i = 2; i < id; ++i) {
                int num = hieroglyphs[i];
                if (num == 0)      list.add('W');
                else if (num == 1) list.add('A');
                else if (num == 2) list.add('K');
                else if (num == 3) list.add('J');
                else if (num == 4) list.add('S');
                else if (num == 5) list.add('D');
            }

            Collections.sort(list);
            System.out.printf("Case %d: ", TC);
            for (Character c : list)
                System.out.print(c);

            System.out.println();
            scanner = new StringTokenizer(br.readLine());
        }
        pr.close();
    }

    public static void main(String[] args) throws Exception{
        Main program = new Main();
        program.run();		
    }
}


