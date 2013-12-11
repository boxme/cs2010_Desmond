import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

class UVa11951{}

class Main {
    private long[][] matrix;

    void run() throws Exception {
    	BufferedReader bi = new BufferedReader(new InputStreamReader(System.in));
    	PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    	StringTokenizer scanner = new StringTokenizer(bi.readLine());
    	int TC = Integer.parseInt(scanner.nextToken());
    	int case_num = 0;

    	while (TC-- > 0) {
    	    case_num++;
    	    scanner = new StringTokenizer(bi.readLine());
    	    int N = Integer.parseInt(scanner.nextToken());
    	    int M = Integer.parseInt(scanner.nextToken());
    	    long K = Long.parseLong(scanner.nextToken());

            matrix = new long[N][M];

    	    for (int i = 0; i < N; ++i) {
    	        scanner = new StringTokenizer(bi.readLine());
    	        for (int j = 0; j < M; ++j) {
    	            matrix[i][j] = Long.parseLong(scanner.nextToken());
    	            if (j > 0) matrix[i][j] += matrix[i][j-1];
                }
            }

            int max_area = 0;
            long lowest_cost = 0;

            for (int i = 0; i < M; ++i) {
                for (int j = i; j < M; ++j) {
                    int area = 0;
                    long cost = 0;
                    int start = 0;
                    for (int row = 0; row < N; ++row) {
                        cost += matrix[row][j];
                        if (i > 0) cost -= matrix[row][i-1];

                        while (cost > K) {
                            cost -= matrix[start][j];
                            if (i > 0) cost += matrix[start][i-1];
                            ++start;
                        }

                        area = (row - start + 1)*(j - i + 1);
                        if (area == max_area) 
                            if (cost < lowest_cost) 
                                lowest_cost = cost;
                        if (area > max_area) {
                            max_area = area;
                            lowest_cost = cost;
                        }
                    }
                }
            }

            pr.println("Case #" + case_num + ": " + max_area + " " + lowest_cost);
        }
        pr.close();
    }

    public static void main(String[] args) throws Exception{
        Main program = new Main();
        program.run();		
    }
}

class Edge implements Comparable<Edge> {
    Integer u, v, weight;

    public Edge(int u, int v, int weight) {
        this.u = u;
        this.v = v;
        this.weight = weight;
    }
    @Override
    public int compareTo(Edge other) {
        return this.weight - other.weight;
    }
}
