import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class UVa11380{}

class Main {
    private char[][] area;
    private int[][] res;
    private int X, Y, P, max_flow, min_edge;
    private int src, sink;
    private ArrayList<ArrayList<Integer>> adj_list;
    private final int[][] dir = {{-1,0}, {1,0}, {0,1}, {0,-1}}; //NSEW
    private final int oo = 1000;
    private int[] p;

    int fin(int i, int j) {
        return (i*Y)+j+1;
    }
    int fout(int i,  int j) {
        return (X*Y)+(i*Y)+j+1;
    }

    void augment(int v, int minimum_edge) {
        if (v == src) {
            min_edge = minimum_edge; return;
        } else if (p[v] != -1) {
            augment(p[v], Math.min(minimum_edge, res[p[v]][v]));
            res[p[v]][v] -= min_edge;
            res[v][p[v]] += min_edge;
        }
    }

    void find_max_flow() {
        max_flow = 0;
        p = new int[2000];

        while (true) {
            min_edge = 0;
            Arrays.fill(p, -1);
            int[] taken = new int[2000];
            taken[src] = 1;
            Queue<Integer> q = new LinkedList<Integer>();
            q.offer(src);

            while (!q.isEmpty()) {
                int u = q.poll();
                if (u == sink) break;
                for (Integer v : adj_list.get(u)) {
                    if (taken[v] == 0 && res[u][v] > 0) {
                        taken[v] = 1;
                        p[v] = u;
                        q.offer(v);
                    }
                }
            }
            augment(sink, oo);
            if (min_edge == 0) return;
            max_flow += min_edge;
        }
    }

    void run() throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    	StringTokenizer scanner = null;
    	String input = null;

    	while (br.ready() && (input = br.readLine()) != null) {
            scanner = new StringTokenizer(input);
            X = Integer.parseInt(scanner.nextToken());
    	    Y = Integer.parseInt(scanner.nextToken());
    	    P = Integer.parseInt(scanner.nextToken());
    	    area = new char[X][Y]; res = new int[2*X*Y + 2][2*X*Y + 2];
    	    adj_list = new ArrayList<ArrayList<Integer>>();
    	    for (int i = 0; i <= (2*X*Y + 1); ++i)
    	        adj_list.add(new ArrayList<Integer>());

    	    src = 0; sink = 2*X*Y + 1;

            for (int i = 0; i < X; ++i) {
                input = br.readLine();
                for (int j = 0; j < Y; ++j) 
                    area[i][j] = input.charAt(j);
            }

            for (int i = 0; i < X; ++i) {
                for (int j = 0; j < Y; ++j) {
                    if (area[i][j] == '~') continue;

                    //NSEW direction
                    for (int k = 0; k < 4; ++k) {
                        int nx = i+dir[k][0]; int ny = j+dir[k][1];
                        if (nx < 0 || nx >= X || ny < 0 || ny >= Y) continue;
                        res[fout(i,j)][fin(nx,ny)] = oo;
                        adj_list.get(fout(i,j)).add(fin(nx,ny));
                    }
                    //Vertex Splitting
                    if (area[i][j] == '*') {
                        adj_list.get(src).add(fin(i,j));
                        adj_list.get(fin(i,j)).add(fout(i,j));
                        res[src][fin(i,j)] = 1;
                        res[fin(i,j)][fout(i,j)] = 1;
                    }
                    else if (area[i][j] == '.') {
                        res[fin(i,j)][fout(i,j)] = 1;
                        adj_list.get(fin(i,j)).add(fout(i,j));
                    }
                    else if (area[i][j] == '@') {
                        res[fin(i,j)][fout(i,j)] = oo;
                        adj_list.get(fin(i,j)).add(fout(i,j));
                    } 
                    else {
                        res[fin(i,j)][fout(i,j)] = oo;
                        adj_list.get(fin(i,j)).add(fout(i,j));
                        adj_list.get(fout(i,j)).add(sink);
                        res[fout(i,j)][sink] = P;
                    }
                }
            }
            find_max_flow();
            pr.println(max_flow);

    	    br.readLine();
        }
        pr.close();
    }

    public static void main(String[] args) throws Exception{
        Main program = new Main();
        program.run();		
    }
}

class Triple {
    Integer x, y;
    Character item;

    public Triple(int x, int y, char item) {
        this.x = x; this.y = y; this.item = item;
    }
}
