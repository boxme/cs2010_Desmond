import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class UVa259{}

class Main {
    private final int oo = 10000000;
    private int[][] res;
    private ArrayList<ArrayList<Integer>> adj_list;
    private ArrayList<Integer> list_app;
    private int[] p, taken;
    private int total_applications, max_flow, min_edge, source, sink;

    void augment(int v, int minimum_edge) {
        if (v == source) {
            min_edge = minimum_edge; return;
        } else if (p[v] != -1) {
            augment(p[v], Math.min(minimum_edge, res[p[v]][v]));
            res[p[v]][v] -= min_edge; res[v][p[v]] += min_edge;
        }
    }

    void run() throws Exception {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            res = new int[38][38];
            adj_list = new ArrayList<ArrayList<Integer>>();
            list_app = new ArrayList<Integer>();

            for (int i = 0; i < 37; ++i) {
                adj_list.add(new ArrayList<Integer>());
                if (i >= 27) {
                    adj_list.get(i).add(37);
                    res[i][37] = 1;
                }
            }

            total_applications = 0;

            while (true) {
                String input = scanner.nextLine();
                if (input.compareTo("") == 0) break;
                res[0][input.charAt(0) - 64] = Character.getNumericValue(input.charAt(1));
                total_applications += Character.getNumericValue(input.charAt(1));
                adj_list.get(0).add(input.charAt(0) - 64);
                list_app.add(input.charAt(0) - 64);

                for (int i = 3; i < input.length() - 1; ++i) {
                    res[input.charAt(0) - 64][Character.getNumericValue(input.charAt(i)) + 27] = oo;
                    adj_list.get(input.charAt(0) - 64).add(Character.getNumericValue(input.charAt(i)) + 27);
                }
            }

            p = new int[38];
            source = 0; sink = 37; max_flow = 0;
            while (true) {
                Arrays.fill(p, -1);
                min_edge = 0;
                taken = new int[38];
                Queue<Integer> q = new LinkedList<Integer>();
                q.offer(source);

                while (!q.isEmpty()) {
                    int u = q.poll();
                    if (u == sink) break;
                    for (Integer v : adj_list.get(u)) {
                        if (taken[v] == 0 && res[u][v] > 0) {
                            taken[v] = 1; p[v] = u; q.offer(v);
                        }
                    }
                }
                augment(sink, oo);
                if (min_edge == 0) break;
                max_flow += min_edge;
            }

            if (max_flow != total_applications) System.out.print("!");
            else {
                taken = new int[38];
                for (Integer i : list_app) {
                    for (Integer j : adj_list.get(i)) {
                        if (res[j][i] == 1 && taken[j] == 0)  {
                            System.out.print(Character.toChars(i + 64)); 
                        }
                        else if (taken[j] == 0){
                            System.out.print("_");
                        }
                        taken[j] = 1;
                    }
                }
            }
            System.out.println();
        }
    }
	
    public static void main(String[] args) throws Exception{
        Main program = new Main();
        program.run();		
    }
}

class IntegerPair implements Comparable<IntegerPair> {
    Integer v, cost;

    IntegerPair(int v, int cost) {
        this.v = v;
        this.cost = cost;
    }
    @Override
    public int compareTo(IntegerPair other) {
        return this.cost - other.cost;
    }
}

