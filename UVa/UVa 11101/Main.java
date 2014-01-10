import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class UVa11101{}

class Main {
    private int m1, m2;
    private int[][] area, taken, dist;
    private Queue<Integer> q;
    private final int[][] dir = {{-1,0}, {1,0}, {0,1}, {0,-1}};

    int BFS() {
        while (!q.isEmpty()) {
            int num = q.poll();
            int x = num >> 11; int y = num & 2047;

            for (int i = 0; i < 4; ++i) {
                int nx = x + dir[i][0]; int ny = y + dir[i][1];
                if (nx < 0 || nx > 2000 || ny < 0 || ny > 2000) continue;
                if (taken[nx][ny] != 0) continue;
                if (area[nx][ny] == 2) return dist[x][y] + 1;
                taken[nx][ny] = 1;
                dist[nx][ny] = dist[x][y] + 1;
                q.offer((nx<<11) + ny);
            }
        }
        return 0;
    }

    void run() throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    	StringTokenizer scanner = new StringTokenizer(br.readLine());
    	
    	while ((m1 = Integer.parseInt(scanner.nextToken())) != 0) {
    	    String input = ""; int len = m1;
    	    area = new int[2001][2001]; taken = new int[2001][2001]; dist = new int[2001][2001];
    	    q = new LinkedList<Integer>();
    	    while (len != 0) {
    	        input = br.readLine(); String[] temp = input.split(" ");
    	        len -= temp.length/2;
    	        for (int i = 0; i < temp.length; i+=2) {
    	        	int x = Integer.parseInt(temp[i]); int y = Integer.parseInt(temp[i+1]);
    	            taken[x][y] = 1;
    	            q.offer((x<<11) + y);										//Combine coordinate x and y together
                }
            }
    	    
            scanner = new StringTokenizer(br.readLine());
            m2 = Integer.parseInt(scanner.nextToken());
            len = m2;
    	    while (len != 0) {
    	        input = br.readLine(); String[] temp = input.split(" ");
    	        len -= temp.length/2;
    	        for (int i = 0; i < temp.length; i+=2) 
    	            area[Integer.parseInt(temp[i])][Integer.parseInt(temp[i+1])] = 2;
            }

            pr.println(BFS());
            scanner = new StringTokenizer(br.readLine());
        }
        pr.close();
    }

    public static void main(String[] args) throws Exception{
        Main program = new Main();
        program.run();		
    }
}

class Triple implements Comparable<Triple>{
    Integer x, y, dist;

    public Triple(int x, int y, int dist) {
        this.x = x; this.y = y; this.dist = dist;
    }

    @Override
    public int compareTo(Triple other) {
        return this.dist - other.dist;
    }
}
