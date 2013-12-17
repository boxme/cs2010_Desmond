import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Hashtable;
import java.util.StringTokenizer;

class UVa11831{}

class Main {
    private int n, m, s, start_n, start_m, stickers, cur_dir;
    private char[][] arena;
    private final char[] dir = {'N', 'L', 'S', 'O'};
    private char orient;
    private Hashtable<Character, Pair> hashtable;

    void run() throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    	StringTokenizer scanner = null;
        hashtable = new Hashtable<Character, Pair>();
        hashtable.put('N', new Pair(-1, 0));
        hashtable.put('S', new Pair(1, 0));
        hashtable.put('L', new Pair(0, 1));
        hashtable.put('O', new Pair(0, -1));

    	while (true) {
    	    scanner = new StringTokenizer(br.readLine());
    	    n = Integer.parseInt(scanner.nextToken());
    	    m = Integer.parseInt(scanner.nextToken());
    	    s = Integer.parseInt(scanner.nextToken());
    	    if (n + m + s == 0) break;

    	    arena = new char[n][m];

    	    for (int i = 0; i < n; ++i) {
    	        String input = br.readLine();
    	        for (int j = 0; j < m; ++j) {
    	            arena[i][j] = input.charAt(j);
    	            if (arena[i][j] == 'N' || arena[i][j] == 'S' || arena[i][j] == 'L' || arena[i][j] == 'O') {
    	                start_n = i; start_m = j;
    	                orient = arena[i][j];
    	                     if (orient == 'N') cur_dir = 0;
                        else if (orient == 'L') cur_dir = 1;
                        else if (orient == 'S') cur_dir = 2;
                        else                    cur_dir = 3;
                    }
                }
            }

            String instructions = br.readLine();
            stickers = 0;
            for (int i = 0; i < s; ++i) {
                Character cur = instructions.charAt(i);
                if (cur == 'F') {
                    int temp_n = hashtable.get(orient).u + start_n; int temp_m = hashtable.get(orient).v + start_m;
                    if (temp_n >= 0 && temp_n < n && temp_m >= 0 && temp_m < m) {
                        if (arena[temp_n][temp_m] == '.') {
                        	arena[start_n][start_m] = '.';
                        	start_n = temp_n; start_m = temp_m;
                        }
                        else if (arena[temp_n][temp_m] == '*') {
                        	arena[start_n][start_m] = '.';
                        	arena[temp_n][temp_m] = '.';
                        	stickers++; start_n = temp_n; start_m = temp_m;
                        }
                    }
                }
                else if (cur == 'D') {
                    cur_dir = (cur_dir + 1) % 4;
                    orient = dir[cur_dir];
                }
                else if (cur == 'E') {
                    cur_dir = (cur_dir - 1) % 4;
                    cur_dir += (cur_dir < 0) ? 4 : 0;
                    orient = dir[cur_dir];
                }
            }
            pr.println(stickers);
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
