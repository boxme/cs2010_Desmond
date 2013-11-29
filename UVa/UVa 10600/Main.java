import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.lang.Math;

class UVa10600 {}

class Main {
    private ArrayList<IntegerTriple> edge_list;
    private int num_of_sch;
    private int num_of_connections;
    private int[] parents, taken, valid;
    private int min_weight, next_weight;
        
        /*UFDS*/
        int find(int x) {
            if (parents[x] == x) return parents[x];
            return parents[x] = find(parents[x]);
        }

        int kruskal() {
            int count = 0;
            int min_dist = 0;
            for (int i = 0; count < num_of_sch && i < num_of_connections; ++i) {
                int u = edge_list.get(i).u;
                int v = edge_list.get(i).v;
                int weight = edge_list.get(i).weight;

                if (find(u) != find(v)) {
                    count++; min_dist += weight;
                    parents[find(u)] = find(v);
                    taken[i] = 1;
                }
            }
            return min_dist;
        }

        int find_snd_min() {
            int snd_min = 100000000;
            
            for (int i = 0; i < num_of_connections; ++i) {
                int count = 0;
                int temp = 0;
                if (taken[i] == 1) {
                    valid[i] = 1;
                    for (int j = 0; j <= num_of_sch; ++j)
                        parents[j] = j;
                    
                    for (int k = 0; count < num_of_sch && k < num_of_connections; ++k) {
                        if (valid[k] != 1) {
                            int u = edge_list.get(k).u;
                            int v = edge_list.get(k).v;
                            int weight = edge_list.get(k).weight;

                            if (find(u) != find (v)) {
                                count++; temp += weight;
                                parents[find(u)] = find(v);
                            }
                        }
                    }
                }
                if (count == num_of_sch - 1) snd_min = Math.min(snd_min, temp);
                valid[i] = 0;
            }
            return snd_min;
        }

	void run() throws Exception {
	   Scanner scanner =  new Scanner(System.in);
	   int TC = scanner.nextInt();
	   while (TC-- > 0) {
                num_of_sch = scanner.nextInt();
                int M = num_of_connections = scanner.nextInt();
                parents = new int[num_of_sch+1];
                taken = new int[M];
                valid = new int[M];

                edge_list = new ArrayList<IntegerTriple>();
                while (M-- > 0) {
                    edge_list.add(new IntegerTriple(scanner.nextInt(), scanner.nextInt(), scanner.nextInt()));
                }

                Collections.sort(edge_list);
                for (int i = 0; i <= num_of_sch; ++i) {
                    parents[i] = i;
                }
                min_weight = kruskal();
                next_weight = find_snd_min();
                System.out.println(min_weight + " " + next_weight);
           }
        }
	
	public static void main(String[] args) throws Exception{
		Main program = new Main();
		program.run();		
	}
}

class IntegerPair implements Comparable<IntegerPair> {
	Integer vertex, weight;
	
	public IntegerPair(int vertex, int weight) {
		this.vertex = vertex;
		this.weight = weight;
	}
	
	@Override
	public int compareTo(IntegerPair other) {
		return this.weight - other.weight;
	}
}

class IntegerTriple implements Comparable<IntegerTriple> {
    Integer u, v, weight;

    public IntegerTriple(int u, int v, int weight) {
        this.u = u;
        this.v = v;
        this.weight = weight;
    }

    @Override
    public int compareTo(IntegerTriple other) {
        if (this.weight != other.weight)
            return this.weight - other.weight;
        else
            return this.u - other.u;
    }
}
