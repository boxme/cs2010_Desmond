import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

class UVa12532{}

class Main {
    private int n, k;
    private int[] array, tree, change;
    
    void build(int p, int left, int right) {
        if (left == right) tree[p] = array[left];
        else {
            build(p<<1, left, (left+right)/2);
            build((p<<1)+1, (left+right)/2 + 1, right);

            tree[p] = tree[p<<1] * tree[(p<<1)+1];
        }
    }
    
    void update(int p, int left, int right, int range_i, int range_j, int val) {
        if (change[p] != 0) {
            tree[p] = change[p];
            if (left != right) {
                change[p<<1] += change[p];
                change[(p<<1)+1] += change[p];
            }
            change[p] = 0;
        }

        if (left > right || left > range_j || right < range_i) return;

        if (left >= range_i && right <= range_j) {
            tree[p] = val;
            return;
        }
        update(p<<1, left, (left+right)/2, range_i, range_j, val);
        update((p<<1)+1, (left+right)/2 + 1, right, range_i, range_j, val);
        tree[p] = tree[p<<1] * tree[(p<<1)+1];
    }

    int query(int p, int left, int right, int range_i, int range_j) {
        if (left > right || left > range_j || right < range_i) return 1;
        if (left >= range_i && right <= range_j) return tree[p];

        int q1 = query(p<<1, left, (left+right)/2, range_i, range_j);
        int q2 = query((p<<1)+1, (left+right)/2 + 1, right, range_i, range_j);
        return q1 * q2;
    }

    void run() throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    	StringTokenizer scanner;
    	String input = null;
    	while (br.ready() && (input = br.readLine()) != null) {
    	    scanner = new StringTokenizer(input);
    	    n = Integer.parseInt(scanner.nextToken()); k = Integer.parseInt(scanner.nextToken());
    	    tree = new int[n<<3]; change = new int[n<<3]; array = new int[n];

    	    scanner = new StringTokenizer(br.readLine());
    	    for (int i = 0; i < n; ++i) {
    	    	int num = Integer.parseInt(scanner.nextToken());
    	    	if (num == 0) array[i]  = 0;
    	    	else if (num > 0) array[i] = 1;
    	    	else 			  array[i] = -1;
    	    }

            build(1, 0, n-1);
    	    for (int i = 0; i < k; ++i) {
    	        scanner = new StringTokenizer(br.readLine());
    	        String cmd = scanner.nextToken();
    	        int answer = 0;
    	        if (cmd.equals("C")) {
    	            int range = Integer.parseInt(scanner.nextToken());
    	            int val = Integer.parseInt(scanner.nextToken());
    	            if (val > 0) val = 1;
    	            else if (val < 0) val = -1;
    	            
    	            update(1, 0, n-1, range-1, range-1, val);
                } else {
                    int range_i = Integer.parseInt(scanner.nextToken());
                    int range_j = Integer.parseInt(scanner.nextToken());
                    answer = query(1, 0, n-1, range_i-1, range_j-1);
                    if (answer == 0)     pr.print(0);
                    else if (answer > 0) pr.print("+");
                    else                 pr.print("-");
                }
            }
            pr.println();
        }
        pr.close();
    }

    public static void main(String[] args) throws Exception{
        Main program = new Main();
        program.run();		
    }
}	
