import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

class UVa11057{}

class Main {
    private int[] books;

    void run() throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    	//StringTokenizer scanner = new StringTokenizer(br.readLine());

        StringTokenizer scanner = null;
        String input = "";
        while (br.ready() && (input = br.readLine())!= "") {
            scanner = new StringTokenizer(input);
            int num_books = Integer.parseInt(scanner.nextToken());
            books = new int[num_books];
            scanner = new StringTokenizer(br.readLine());

            for (int i = 0; i < num_books; ++i)
                books[i] = Integer.parseInt(scanner.nextToken());

            scanner = new StringTokenizer(br.readLine());
            int money = Integer.parseInt(scanner.nextToken());
            Arrays.sort(books);
            int half = money/2;
            for (int i = 0; i < 1000000; ++i) {
                int lo = Arrays.binarySearch(books, half);
                if (lo >= 0) {
                    int hi = Arrays.binarySearch(books, lo+1, num_books, money-half);
                    if (hi >= 0) {
                        pr.printf("Peter should buy books whose prices are %d and %d.\n\n", books[lo], books[hi]);
                        break;
                    }
                }
                half--;
            }
            br.readLine();
        }
        pr.close();
    }

    public static void main(String[] args) throws Exception{
        Main program = new Main();
        program.run();		
    }
}
