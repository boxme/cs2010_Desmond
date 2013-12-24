import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

class UVa12086{}

class Main {
    private int N;
    private int[] array, oftset, tree;

    void build(int p, int left, int right) {
        if (left == right) tree[p] = array[left];
        else {
            build(p<<1, left, (left+right)/2);
            build((p<<1) + 1, (left+right)/2 + 1, right);

            tree[p] = tree[p<<1] + tree[(p<<1)+1];
        }
    }

    void update(int p, int left, int right, int range_i, int range_j, int val) {
//        if (oftset[p] != 0) {
//            tree[p] = oftset[p];
//
//            if (left != right) {
//                oftset[p<<1] = oftset[(p<<1)+1] = oftset[p];
//            }
//            oftset[p] = 0;
//        }

        if (left > right || left > range_j || right < range_i) return;

        if (left >= range_i && right <= range_j) {
            tree[p] = val;
            if (left != right) {
                oftset[p<<1] = oftset[(p<<1)+1] = val;
            }
            return;
        }

        update(p<<1, left, (left+right)/2, range_i, range_j, val);
        update((p<<1)+1, (left+right)/2 +1, right, range_i, range_j, val);

        tree[p] = tree[p<<1] + tree[(p<<1)+1];
    }

    int query(int p, int left, int right, int range_i, int range_j) {
        if (left > right || left > range_j || right < range_i) return 0;

//        if (oftset[p] != 0) {
//            tree[p] = oftset[p];
//
//            if (left != right) {
//                oftset[p<<1] = oftset[(p<<1)+1] = oftset[p];
//            }
//            oftset[p] = 0;
//        }

        if (left >= range_i && right <= range_j) {
            return tree[p];
        }
        int q1 = query(p<<1, left, (left+right)/2, range_i, range_j);
        int q2 = query((p<<1)+1, (left+right)/2 + 1, right, range_i, range_j);

        return q1+q2;
    }

    void run() throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    	StringTokenizer scanner = new StringTokenizer(br.readLine());
    	int TC = 0;
    	while ((N = Integer.parseInt(scanner.nextToken())) != 0) {
    	    TC++;
    	    if (TC >= 2) pr.println();

    	    array = new int[N]; oftset = new int[N << 3];
    	    tree = new int[N << 3];
    	    for (int i = 0; i < N; ++i) {
    	        scanner = new StringTokenizer(br.readLine());
    	        array[i] = Integer.parseInt(scanner.nextToken());
            }

            build(1, 0, N-1);

            String input = "";
            pr.printf("Case %d:\n", TC);
            while (!(input = br.readLine()).equals("END")) {
                scanner = new StringTokenizer(input);
                if (scanner.nextToken().equals("M")) {
                    int ans = query(1, 0, N-1, Integer.parseInt(scanner.nextToken())-1, Integer.parseInt(scanner.nextToken())-1);
                    pr.println(ans);
                } else {
                    int range = Integer.parseInt(scanner.nextToken()) - 1;
                    update(1, 0, N-1, range, range, Integer.parseInt(scanner.nextToken()));
                }
            }
            scanner = new StringTokenizer(br.readLine());
        }
        pr.close();
    }

    public static void main(String[] args) throws Exception{
        Main program = new Main();
        program.run();		
    }
}


