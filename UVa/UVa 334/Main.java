import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

class UVa334{}

class Main {
    private HashMap<String, Integer> map_to_index;
    private String[] map_to_string;
    private int[][] graph;

       void transitive(int index) {
           for (int k = 0; k < index; ++k)
               for (int i = 0; i < index; ++i)
                   for (int j = 0; j < index; ++j)
                       graph[i][j] |= (graph[i][k] & graph[k][j]);
       }

       void run() throws Exception {
	   Scanner scanner = new Scanner(System.in);
	   int case_num = 0;
	   int NC = scanner.nextInt();

	   while (NC != 0) {
	       case_num++;
	       map_to_index = new HashMap<String, Integer>();
	       graph = new int[400][400];
	       map_to_string = new String[400];

	       int index = 0;
	       for (int i = 0; i < NC; ++i) {
	           ArrayList<String> event_list = new ArrayList<String>();
	           int num_events = scanner.nextInt();
	           for (int j = 0; j < num_events; ++j) {
	               String event = scanner.next();
	               if (map_to_index.get(event) == null) {
                           map_to_index.put(event, index);
                           map_to_string[index++] = event;
                       }
                       event_list.add(event);
                   }

                   int size = event_list.size();
                   for (int k = 0; k < size-1; ++k)
                	   graph[map_to_index.get(event_list.get(k))][map_to_index.get(event_list.get(k+1))] = 1;
	       }

	       int NM = scanner.nextInt();
	       for (int i = 0; i < NM; ++i) {
	           String event = scanner.next();
	           String next_event = scanner.next();
	           graph[map_to_index.get(event)][map_to_index.get(next_event)] = 1;
               }

               transitive(index);
               String concurrent1 = "";
               String concurrent2 = "";
               int count = 0;

               for (int i = 0; i < index; ++i) {
                   for (int j = i+1; j < index; ++j) {
                       if (graph[i][j] == 0 && graph[j][i] == 0) {
                           count++;
                           if (concurrent1 == "") 
                               concurrent1 = "(" + map_to_string[i] + "," + map_to_string[j] + ") "; 
                           else if (concurrent2 == "") 
                               concurrent2 = "(" + map_to_string[i] + "," + map_to_string[j] + ") "; 
                       }
                   }
               }
               
               if (count != 0) {
                   System.out.printf("Case %d, %d concurrent events:\n", case_num, count);
                   if (count == 1) 
                       System.out.println(concurrent1);
                   else
                       System.out.println(concurrent1 + concurrent2);
               }
               else {
                   System.out.printf("Case %d, no concurrent events.\n", case_num);
               }
               NC = scanner.nextInt();

           }
        }
	
	public static void main(String[] args) throws Exception{
		Main program = new Main();
		program.run();		
	}
}

