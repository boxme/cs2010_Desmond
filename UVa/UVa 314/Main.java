import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class UVa314{}

class Main {
    private final int[] dx = {-1, 0, 1, 0};    //NESW Go left/right
    private final int[] dy = {0, 1, 0, -1};    //Go top/bottom
    private int[][] store;
    private int[][][] taken;
    private int b1, b2, e1, e2, d, N, M;

    void bfs() {
        if (b1 == e1 && b2 == e2) {
            System.out.println(0); return;
        }
        taken = new int[105][105][4];
        taken[b1][b2][d] = 1;
        Queue<Integer> qx = new LinkedList<Integer>();
        Queue<Integer> qy = new LinkedList<Integer>();
        Queue<Integer> qd = new LinkedList<Integer>();
        qx.offer(b1); qy.offer(b2); qd.offer(d);
        while (!qx.isEmpty()) {
            int x = qx.poll(); int y = qy.poll(); int dir = qd.poll();
            int turn_x = x + dx[dir]; int turn_y = y + dy[dir];

            for (int i = 1; i <= 3; ++i) {
                if (turn_x < 1 || turn_y < 1 || turn_x >= N || turn_y >= M) break;

                if (store[turn_x][turn_y] == 1) continue;
                if (taken[turn_x][turn_y][dir] == 0) {
                    taken[turn_x][turn_y][dir] = taken[x][y][dir] + 1;
                    qx.offer(turn_x); qy.offer(turn_y); qd.offer(dir);
                }
                turn_x += dx[dir]; turn_y += dy[dir];
            }

            turn_x = x; turn_y = y;
            int turn_dir = (dir+1)%4;    //Rotate right
            if (taken[turn_x][turn_y][turn_dir] == 0) {
                taken[turn_x][turn_y][turn_dir] = taken[x][y][dir] + 1;
                qx.offer(turn_x); qy.offer(turn_y); qd.offer(turn_dir);
            }

            turn_dir = (dir+3)%4;        //Rotate left
            if (taken[turn_x][turn_y][turn_dir] == 0) {
                taken[turn_x][turn_y][turn_dir] = taken[x][y][dir] + 1;
                qx.offer(turn_x); qy.offer(turn_y); qd.offer(turn_dir);
            }

            if (taken[e1][e2][0] > 0 || taken[e1][e2][1] > 0 || taken[e1][e2][2] > 0 || taken[e1][e2][3] > 0) 
                break;
        }

        int answer = 0;
        for (int i = 0; i < 4; ++i) {
            if (taken[e1][e2][i] > 0)
                answer = taken[e1][e2][i];
        }
        System.out.println(answer-1);
    }

    void run() throws Exception {
        Scanner scanner = new Scanner(System.in);
        N = scanner.nextInt(); M = scanner.nextInt();
        while (N != 0 && M != 0) {
            store = new int[N+1][M+1];
            for (int i = 1; i <= N; ++i) {
                for (int j = 1; j <= M; ++j) {
                    int k = scanner.nextInt();
                    if (k == 1) {
                        store[i-1][j-1] = store[i-1][j] = 1;
                        store[i][j-1] = store[i][j] = 1;
                    }
                }
            }

            b1 = scanner.nextInt(); b2 = scanner.nextInt(); e1 = scanner.nextInt(); e2 = scanner.nextInt(); 
            String dir = scanner.next();
            if (dir.equals("north")) d = 0;
            if (dir.equals("east"))  d = 1;
            if (dir.equals("south")) d = 2;
            if (dir.equals("west"))  d = 3;

            bfs();
            N = scanner.nextInt(); M = scanner.nextInt();
        }
    }

    public static void main(String[] args) throws Exception{
        Main program = new Main();
        program.run();		
    }
}

class Edge implements Comparable<Edge> {
    Integer u, v, weight;

    public Edge(int u, int v, int weight) {
        this.u = u; this.v = v; this.weight = weight;
    }

    @Override 
    public int compareTo(Edge other) {
        return this.weight - other.weight;
    }
}

