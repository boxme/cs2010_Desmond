import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.Stack;

class UVa481{}

class Main {
    private final int MAX_N = 1000000;
    private int[] array;
    private int[] list_id, parent;
    private ArrayList<Integer> list;

        void reconstruct_print(int end) {
            Stack<Integer> stack = new Stack<Integer>();
            int i = end;
            for (; parent[i] >= 0; i = parent[i])
                stack.push(array[i]);

            System.out.println(array[i]);                               // start element of sequence

            while (!stack.isEmpty())
                System.out.println(stack.pop());
        }

        void run() throws Exception {
	   Scanner scanner =  new Scanner(System.in);
	   array = new int[MAX_N];
	   list_id = new int[MAX_N];
	   parent = new int[MAX_N];
	   list = new ArrayList<Integer>();
	   int index = 0;

	   while (scanner.hasNext()) 
	       array[index++] = scanner.nextInt();

           int end = 0;
           for (int i = 0; i < index; ++i) {
               int pos = Collections.binarySearch(list, array[i]);
               if (pos < 0)            pos = -(pos + 1);                // process to get index
               if (pos == list.size()) {list.add(array[i]); end = i;}   // end to remember the last index
               else                    list.set(pos, array[i]);

               list_id[pos] = i;                                        // rmb index for each pos in list
               parent[i] = pos > 0 ? list_id[pos-1] : -1;
           }

           System.out.println(list.size());
           System.out.println("-");
           reconstruct_print(end);
        }
	
	public static void main(String[] args) throws Exception{
		Main program = new Main();
		program.run();		
	}
}

class IntegerPair implements Comparable<IntegerPair> {
	String vertex;
	Integer weight;
	
	public IntegerPair(String vertex, int weight) {
		this.vertex = vertex;
		this.weight = weight;
	}
	
	@Override
	public int compareTo(IntegerPair other) {
		return this.weight - other.weight;
	}
}
