import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

class UVa11402{}

class Main {
    private int[] pirates, tree, modified;
    private int size;
    private final int F = 1, E = 2, I = 3;

    void build(int p, int i, int j) {
        if (i == j) {
        	tree[p] = pirates[i];
        	return;
        }

        build(p<<1, i, (i+j)/2);
        build((p<<1)+1, ((i+j)/2)+1, j);

        tree[p] = tree[p<<1] + tree[(p<<1)+1];
    }

    void tree_set(int p, int i, int j, int range_i, int range_j) {
        propagate(p, i, j);
        if (i > range_j || j < range_i) return;
        if (i >= range_i && j <= range_j) {
            tree[p] = j-i+1;
            if (i != j) 
            	modified[p<<1] = modified[(p<<1)+1] = F;
            return;
        }
        tree_set(p<<1, i, (i+j)/2, range_i, range_j);
        tree_set((p<<1)+1, ((i+j)/2)+1, j, range_i, range_j);

        tree[p] = tree[p<<1] + tree[(p<<1)+1];
    }

    void tree_clear(int p, int i, int j, int range_i, int range_j) {
        propagate(p, i, j);
        if (i > range_j || j < range_i) return;
        if (i >= range_i && j <= range_j) {
            tree[p] = 0;
            if (i != j) {
            	modified[p<<1] = modified[(p<<1)+1] = E;
            }
            return;
        }
        tree_clear(p<<1, i, (i+j)/2, range_i, range_j);
        tree_clear((p<<1)+1, ((i+j)/2)+1, j, range_i, range_j);

        tree[p] = tree[p<<1] + tree[(p<<1)+1];
    }

    void tree_flip(int p, int i, int j, int range_i, int range_j) {
        propagate(p, i, j);
        if (i > range_j || j < range_i) return;
        if (i >= range_i && j <= range_j) {
            tree[p] = (j-i+1) - tree[p] ;
            if (i != j) {
            	modified[p<<1] = apply_flip(modified[p<<1]);
                modified[(p<<1)+1] = apply_flip(modified[(p<<1)+1]);
            }
            return;
        }
        tree_flip(p<<1, i, (i+j)/2, range_i, range_j);
        tree_flip((p<<1)+1, ((i+j)/2)+1, j, range_i, range_j);

        tree[p] = tree[p<<1] + tree[(p<<1)+1];
    }

    void propagate(int p, int i, int j) {
        if (modified[p] == 0) return;
        if (modified[p] == F) 
            tree[p] = j-i+1;
        else if (modified[p] == E)
            tree[p] = 0;
        else if (modified[p] == I)
            tree[p] = (j-i+1) - tree[p];

        if (i != j) {
            int left_child = p << 1;
            int right_child = left_child+1;
            if (modified[p] == F || modified[p] == E)
                modified[left_child] = modified[right_child] = modified[p];
            else {
                modified[left_child] = apply_flip(modified[left_child]);
                modified[right_child] = apply_flip(modified[right_child]);
            }
        }
        modified[p] = 0;
    }

    int apply_flip(int x) {
        if (x == F) return E;
        if (x == E) return F;
        if (x == I) return 0;
        return I;
    }

    int query(int p, int i, int j, int range_i, int range_j) {
        if (i > range_j || j < range_i) return 0;
        propagate(p, i, j);
        if (i >= range_i && j <= range_j) return tree[p];
        int q1 = query(p<<1, i, (i+j)/2, range_i, range_j);
        int q2 = query((p<<1)+1, ((i+j)/2)+1, j, range_i, range_j);
        return q1 + q2;
    }

    void run() throws Exception {
    	BufferedReader bi = new BufferedReader(new InputStreamReader(System.in));
    	PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    	StringTokenizer scanner = new StringTokenizer(bi.readLine());
    	int TC = Integer.parseInt(scanner.nextToken());
    	int case_num = 0;

    	while (TC-- > 0) {
    	    case_num++;
    	    scanner = new StringTokenizer(bi.readLine());
    	    int M = Integer.parseInt(scanner.nextToken());
    	    size = 0;
    	    pirates = new int[1025000];

    	    while (M-- > 0) {
    	        scanner = new StringTokenizer(bi.readLine());
    	        int num = Integer.parseInt(scanner.nextToken());
    	        scanner = new StringTokenizer(bi.readLine());
    	        String input = scanner.nextToken();
    	        for (int i = 0; i < num; ++i)
    	            for (int j = 0; j < input.length(); j++) 
    	            	pirates[size++] = Character.getNumericValue(input.charAt(j));
            }

            tree = new int[size << 2];
            modified = new int[size << 2];
            build(1, 0, size-1);
            
            scanner = new StringTokenizer(bi.readLine());
            int query = Integer.parseInt(scanner.nextToken());
            int q = 0;
            pr.println("Case " + case_num + ":");
            for (int i = 0; i < query; ++i) {
                scanner = new StringTokenizer(bi.readLine());
                String command = scanner.nextToken();
                int range_i = Integer.parseInt(scanner.nextToken());
                int range_j = Integer.parseInt(scanner.nextToken());

                switch (command) {
                    case "F": tree_set(1, 0, size-1, range_i, range_j);
                               break;
                    case "E": tree_clear(1, 0, size-1, range_i, range_j);
                               break;
                    case "I": tree_flip(1, 0, size-1, range_i, range_j);
                               break;
                    default: pr.printf("Q%d: %d\n", ++q, query(1, 0, size-1, range_i, range_j));
                             break;
                }
            }
        }
        pr.close();
    }

    public static void main(String[] args) throws Exception{
        Main program = new Main();
        program.run();		
    }
}
