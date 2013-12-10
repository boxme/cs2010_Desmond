import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

class UVa11235{}

class Main {
    private int[] freq, tree;
    private int size;

    void build(int p, int left, int right) {
    	if (left > right) return;
        if (left == right) {
            tree[p] = freq[left];
        }
        else {
            build(p<<1, left, (left+right)/2);
            build((p<<1)+1, ((left+right)/2)+1, right);

           tree[p] = Math.max(tree[p<<1], tree[(p<<1)+1]);
        }
    }

    int query(int p, int left, int right, int i, int j) {
        if (left > right || left > j || right < i) return -1;
        if (left >= i && right <= j) return tree[p];

        int p1 = query(p<<1, left, (left+right)/2, i, j);
        int p2 = query((p<<1)+1, ((left+right)/2)+1, right, i, j);

        return Math.max(p1, p2);
    }

    void run() throws Exception {
    	BufferedReader bi = new BufferedReader(new InputStreamReader(System.in));
//        Scanner scanner = new Scanner(System.in);
    	StringTokenizer scanner = new StringTokenizer(bi.readLine());
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        size = Integer.parseInt(scanner.nextToken());

        while (size != 0) {
            int query = Integer.parseInt(scanner.nextToken());
            int[] array = new int[size];
            int[] start = new int[size];
            int[] temp = new int[210000];
            freq = new int[size];

            scanner = new StringTokenizer(bi.readLine());
            for (int i = 0; i < size; ++i) {
                int input = Integer.parseInt(scanner.nextToken());
                if (temp[input+100000] == 0) start[i] = i;
                if (i != size-1)             start[i+1] = start[i];
                
                temp[input+100000]++;
                array[i] = input;
            }
            
            for (int i = 0; i < size; ++i)
            	freq[i] = temp[array[i]+100000];
            
            tree = new int[1000000];
            build(1, 0, size-1);

            for (int i = 0; i < query; ++i) {
            	scanner = new StringTokenizer(bi.readLine());
                int x = Integer.parseInt(scanner.nextToken()); 
                int y = Integer.parseInt(scanner.nextToken());
                x --; y --;

                if (array[x] == array[y]) {
                	pw.println(y-x+1); continue;
                }
                else {
                    int k = start[x] + freq[x] - 1;
                    int cnt1 = k - x + 1;
                    int cnt2 = y - start[y] + 1;
                    x = k+1; y = start[y] - 1;
                    int ans = 0;
                    if (x <= y) ans = Math.max(cnt1, Math.max(cnt2, query(1, 0, size-1, x, y)));
                    else        ans = Math.max(cnt1, cnt2);
                    pw.println(ans);
                }
            }
            scanner = new StringTokenizer(bi.readLine());
            size = Integer.parseInt(scanner.nextToken());
        }
        pw.close();
    }

    public static void main(String[] args) throws Exception{
        Main program = new Main();
        program.run();		
    }
}
