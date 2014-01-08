import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

class UVa12442{}

class Main {
    private int N, max_depth;
    private int[] visited, memo, adj_mat;

    int dfs(int u) {
        visited[u] = 1;
        int depth = 0;
        if (adj_mat[u] != 0 && visited[adj_mat[u]] == 0) 
        	depth += 1 + dfs(adj_mat[u]);
        visited[u] = 0;
        return memo[u] = depth;
    }

    void run() throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    	StringTokenizer scanner = new StringTokenizer(br.readLine());
    	int TC = Integer.parseInt(scanner.nextToken());
    	int case_num = 0;
    	while (TC-- > 0) {
    	    case_num++;
    	    scanner = new StringTokenizer(br.readLine());
    	    N = Integer.parseInt(scanner.nextToken());

    	    adj_mat = new int[N+1];
    	    for (int i = 0; i < N; ++i) {
    	    	scanner = new StringTokenizer(br.readLine());
    	    	adj_mat[Integer.parseInt(scanner.nextToken())] = Integer.parseInt(scanner.nextToken());
    	    }

            int ans = 0; max_depth = 0;
            memo = new int[N+1];
            visited = new int[N+1];
            Arrays.fill(memo, -1);
            for (int i = 1; i <= N; ++i){
                if (memo[i] == -1) dfs(i);
                if (memo[i] > max_depth) {
                	max_depth = memo[i];
                	ans = i;
                }
            }
            pr.printf("Case %d: %d\n", case_num, ans);
        }
        pr.close();
    }

    public static void main(String[] args) throws Exception{
        Main program = new Main();
        program.run();		
    }
}	
