import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

class UVa429{}

class Main {
    private ArrayList<ArrayList<Integer>> adj_list;
    private ArrayList<ArrayList<String>> word_length;
    private String start, end;
    private HashMap<String, Integer> hashmap;

    void run() throws Exception {
        BufferedReader scanner = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(scanner.readLine());

        while (TC-- > 0) {
            adj_list = new ArrayList<ArrayList<Integer>>();
            hashmap = new HashMap<String, Integer>();
            word_length = new ArrayList<ArrayList<String>>();

            for (int i = 0; i < 250; ++i) 
                adj_list.add(new ArrayList<Integer>());

            for (int i = 0; i < 11; ++i)
                word_length.add(new ArrayList<String>());

            while (true) {
                String word = scanner.readLine();
                if (word.equals("*")) break;
                int index = hashmap.size();
                hashmap.put(word, index);
                int len = word.length();
                if (word_length.get(len).isEmpty()) {
                    word_length.get(len).add(word);
                }
                else {
                    for (String next : word_length.get(len)) {
                        int diff = 0;
                        for (int i = 0; i < next.length(); ++i) {
                            if (next.charAt(i) != word.charAt(i)) diff++;
                        }
                        if (diff == 1) {
                            adj_list.get(index).add(hashmap.get(next));
                            adj_list.get(hashmap.get(next)).add(index);
                        }
                    }
                    word_length.get(len).add(word);
                }
            }
            String input = "";
            while (scanner.ready() && !(input = scanner.readLine()).equals("")) {
                if (input.compareTo("") == 0) break;
                String[] output = input.split(" ");
                start = output[0]; end = output[1];
                int source = hashmap.get(start); int dest = hashmap.get(end);
                Queue<Edge> q = new LinkedList<Edge>();
                int[] taken = new int[210];
                taken[source] = 1;
                q.offer(new Edge(0, source, 0));
                int answer = 0;
                while (!q.isEmpty()) {
                    Edge current = q.poll();
                    if (current.v == dest) {
                        answer = current.weight; break;
                    }
                    for (Integer next : adj_list.get(current.v)) {
                        if (taken[next] == 0) {
                            taken[next] = 1;
                            q.offer(new Edge(0, next, current.weight+1));
                        }
                    }
                }
                System.out.printf("%s %s %d\n", start, end, answer);
            }
            if (TC > 0) System.out.println();
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

