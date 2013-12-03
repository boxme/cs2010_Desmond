import java.util.Scanner;

class UVa11957{}

class Main {
    private int[][] board;
    private final int mod = 1000007;
    private int start_x, start_y;

    void run() throws Exception {
        Scanner scanner = new Scanner(System.in);
        int TC = scanner.nextInt();
        int case_num = 0;

        while (TC-- > 0) {
            case_num++;
            int board_size = scanner.nextInt();
            board = new int[board_size][board_size];

            for (int i = 0; i < board_size; ++i) {
                String input = scanner.next();
                int len = input.length();
                for (int j = 0; j < len; ++j) {
                    if (input.charAt(j) == 'W') {
                        start_x = j; start_y = i;
                        board[i][j] = 1;
                    }
                    else if (input.charAt(j) == 'B') 
                        board[i][j] = -1;
                }
            }
            for (int y = start_y-1; y >= 0; --y) {
                for (int x = 0; x < board_size; ++x) {
                    if (board[y][x] != -1) {
                        if (y+1 < board_size && x-1 >= 0) {
                            if (board[y+1][x-1] != -1)
                                board[y][x] += (board[y+1][x-1] % mod);
                            else if (y+2 < board_size && x-2 >= 0 && board[y+2][x-2] != -1)
                                board[y][x] += (board[y+2][x-2] % mod);
                        }
                        if (y+1 < board_size && x+1 < board_size) {
                            if (board[y+1][x+1] != -1)
                                board[y][x] += (board[y+1][x+1] % mod);
                            else if (y+2 < board_size && x+2 < board_size && board[y+2][x+2] != -1)
                                board[y][x] += (board[y+2][x+2] % mod);
                        }
                    }
                }
            }

            int answer = 0;
            for (int i = 0; i < board_size; ++i) {
                if (board[0][i] != -1) {
                    answer += board[0][i];
                    answer %= mod;
                }
            }
            System.out.printf("Case %d: %d\n", case_num, answer);
        }
    }

    public static void main(String[] args) throws Exception{
        Main program = new Main();
        program.run();		
    }
}

