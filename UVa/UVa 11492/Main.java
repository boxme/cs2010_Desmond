import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;

class UVa11492{}

class Main {
    private final int oo = 10000000;
    private int N, start, end;
    private String start_lang, end_lang;
    private HashMap<String, Integer> words, lang_index;
    private String[] index_to_word;
    private ArrayList<Set<Integer>> graph;
    private ArrayList<ArrayList<String>> dict;

    int Dijkstra() {
        int[] shortest = new int[N+2];
        Arrays.fill(shortest, oo);
        PriorityQueue<IntegerTriple> pq = new PriorityQueue<IntegerTriple>();
        shortest[start] = 0;
        pq.offer(new IntegerTriple(start, 0));

        while (!pq.isEmpty()) {
            IntegerTriple current = pq.poll();
            if (current.v == end) return current.cost;

            if (current.cost > shortest[current.v]) continue;

            for (Integer next : graph.get(current.v)) {
                int cost = index_to_word[next].length();
                if (shortest[current.v] + cost < shortest[next]) {
                    shortest[next] = shortest[current.v] + cost;
                    pq.offer(new IntegerTriple(next, shortest[next]));
                }
            }
        }
        return -1;
    }
    void run() throws Exception {
        Scanner scanner = new Scanner(System.in);
        N = scanner.nextInt();

        while (N != 0) {
            start_lang = scanner.next(); end_lang = scanner.next();
            index_to_word = new String[N+2];

            graph = new ArrayList<Set<Integer>>();
            for (int i = 0; i < N+2; ++i)
                graph.add(new HashSet<Integer>());

            dict = new ArrayList<ArrayList<String>>();
            for (int i = 0; i < 4005; i++)
                dict.add(new ArrayList<String>());

            words = new HashMap<String, Integer>();
            lang_index = new HashMap<String, Integer>();

            int index = 0;
            int word_index = 0;
            for (int i = 0; i < N; ++i) {
                String lang1 = scanner.next(); String lang2 = scanner.next();
                if (lang_index.get(lang1) == null) lang_index.put(lang1, index++);
                if (lang_index.get(lang2) == null) lang_index.put(lang2, index++);
                String word = scanner.next();
                words.put(word, word_index);
                index_to_word[word_index++] = word;

                for (String next : dict.get(lang_index.get(lang1))) 
                    if (next.charAt(0) != word.charAt(0)) {
                        graph.get(words.get(next)).add(words.get(word));
                        graph.get(words.get(word)).add(words.get(next));
                    }

                for (String next : dict.get(lang_index.get(lang2)))
                    if (next.charAt(0) != word.charAt(0)) {
                        graph.get(words.get(word)).add(words.get(next));
                        graph.get(words.get(next)).add(words.get(word));
                    }
                
                dict.get(lang_index.get(lang1)).add(word); dict.get(lang_index.get(lang2)).add(word);
            }

            start = word_index; index_to_word[word_index++] = "";
            if (start_lang.compareTo(end_lang) != 0) {
            	end = word_index; index_to_word[word_index++] = "";
            }
            else {
            	end = start;
            }
            if (lang_index.get(start_lang) == null || lang_index.get(end_lang) == null) {
            	N = scanner.nextInt();
                System.out.println("impossivel");
                continue;
            }

            for (String next : dict.get(lang_index.get(start_lang)))
                graph.get(start).add(words.get(next));
            for (String next : dict.get(lang_index.get(end_lang)))
                graph.get(words.get(next)).add(end);

            int answer = Dijkstra();
            if (answer == -1) System.out.println("impossivel");
            else              System.out.println(answer);
            N = scanner.nextInt();
        }
    }
	
    public static void main(String[] args) throws Exception{
        Main program = new Main();
        program.run();		
    }
}

class IntegerTriple implements Comparable<IntegerTriple> {
    Integer v, cost;

    IntegerTriple(int v, int cost) {
        this.v = v;
        this.cost = cost;
    }
    @Override
    public int compareTo(IntegerTriple other) {
        return this.cost - other.cost;
    }
}

