import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

class UVa429 {
	HashMap<String, ArrayList<String>> graph;
	HashMap<String, Integer> visited;
	
	public UVa429() {
		graph = new HashMap<String, ArrayList<String>>();
	}
	
	public void insert_into_graph(String word) {
		Set<String> keys = graph.keySet();
		graph.put(word, new ArrayList<String>());			// Add the new word as a vertex by itself
		
		for (String key : keys) {
			if (key.length() == word.length()) {
				int num_diff = 0;
				for (int pos = 0; pos < key.length() && num_diff < 2;pos++) {
					if (key.charAt(pos) != word.charAt(pos)) ++num_diff;
				}
				if (num_diff == 1) {						// When a match is found, do not exit
					graph.get(key).add(word);				// as there could be other words in the hashtable
					graph.get(word).add(key);				// that could differ by 1 letter from the new word
				}
			}
		}		
	}
	
	public int BFS(String start, String end) {
		visited = new HashMap<String, Integer>();
		Queue<String> q = new LinkedList<String>();
		q.offer(start);
		visited.put(start, 0);
		
		while (!q.isEmpty()) {
			String word = q.poll();
			if (word.equals(end)) return visited.get(word);
			
			for (String string : graph.get(word)) {
				if (!string.equals(end) && !visited.containsKey(string)) {
					visited.put(string, visited.get(word)+1);
					q.offer(string);
				}
				else if (string.equals(end)) return visited.get(word) + 1;
			}
		}
		return 0;
	}
}

class Main {
	
	void run() throws Exception{
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	    StringTokenizer st = new StringTokenizer(br.readLine());
	    int num_test_cases = Integer.parseInt(st.nextToken());
	    
	    while (num_test_cases-- > 0) {
		    st = new StringTokenizer(br.readLine());
		    String word = "";
		    UVa429 compute = new UVa429();
		    st = new StringTokenizer(br.readLine());
		    word = st.nextToken();
		    
		    while (!word.equals("*")) {
			    compute.insert_into_graph(word);
			    st = new StringTokenizer(br.readLine());
			    word = st.nextToken();
		    }
		    
		    st = new StringTokenizer(br.readLine());
		    while (st.hasMoreTokens()) {
		    	String start = st.nextToken();
		    	String end = st.nextToken();
				pr.println(start + " " + end + " " + compute.BFS(start, end));
			    st = new StringTokenizer(br.readLine());
			}
		    
		    if (num_test_cases != 0) {
		    	pr.println();
		    }
	    }
	    pr.close();
	}
	
	public static void main(String[] args) throws Exception {
		Main program = new Main();
		program.run();		
	}
}
