import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;

class UVa11235{}

class Main {
    private ArrayList<IntegerPair> tree;
    private Hashtable<Integer, Integer> hashtable;
    private int[] array;
    private int size;

    void build(int p, int left, int right) {
        if (left == right) {
            tree.get(p).num = array[left];
            tree.get(p).occ = 1;
        }
        else {
            build(p<<1, left, (left+right)/2);
            build((p<<1)+1, ((left+right)/2)+1, right);

            int p1_num = tree.get(p<<1).num, p2_num = tree.get((p<<1)+1).num;
            int p1_occ = tree.get(p<<1).occ, p2_occ = tree.get((p<<1)+1).occ;
            if (p1_num == p2_num) {
                tree.get(p).num = p1_num; tree.get(p).occ = p1_occ + p2_occ;
            }
            else if (p1_occ == p2_occ) {
                if (hashtable.get(p1_num) > hashtable.get(p2_num)) {
                    tree.get(p).num = p1_num; tree.get(p).occ = p1_occ;
                } else {
                    tree.get(p).num = p2_num; tree.get(p).occ = p2_occ;
                }
            }
            else if (p1_occ < p2_occ) {
                tree.get(p).num = p2_num; tree.get(p).occ = p2_occ;
            } 
            else {
                tree.get(p).num = p1_num; tree.get(p).occ = p1_occ;
            }
        }
    }

    int Query(int p, int left, int right, int i, int j) {
        if (left > j || right < i || left > right) return -100000;
        if (left >= i && j >= right) return tree.get(p).occ;

        return Math.max(Query(p<<1, left, (left+right)/2, i, j),
                        Query((p<<1)+1, ((left+right)/2)+1, right, i, j));
    }

    void run() throws Exception {
        Scanner scanner = new Scanner(System.in);
        size = scanner.nextInt();

        while (size != 0) {
            int query = scanner.nextInt();
            array = new int[size+1];
            hashtable = new Hashtable<Integer, Integer>();

            for (int i = 1; i <= size; ++i) {
                int input = scanner.nextInt();
                if (hashtable.get(input) == null) 
                    hashtable.put(input, 1);
                else
                    hashtable.put(input, hashtable.get(input)+1);
                array[i] = input;
            }
            tree = new ArrayList<IntegerPair>();
            for (int i = 0; i < (size<<2); ++i) 
                tree.add(new IntegerPair());

            build(1, 1, size);

            for (int i = 0; i < query; ++i) {
                System.out.println(Query(1, 1, size, scanner.nextInt(), scanner.nextInt()));
            }
            size = scanner.nextInt();
        }
    }

    public static void main(String[] args) throws Exception{
        Main program = new Main();
        program.run();		
    }
}

class IntegerPair {
    Integer occ, num;

    public IntegerPair() {
        occ = 0; num = 0;
    }
}
