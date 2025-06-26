import java.io.*;

public class Main {
    static int N, M;
    static int[][] board;
    static int answer = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] nm = br.readLine().split(" ");
        N = Integer.parseInt(nm[0]);
        M = Integer.parseInt(nm[1]);
        board = new int[N][M];

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                board[i][j] = line.charAt(j) - '0';
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                for (int dx = -N; dx < N; dx++) {
                    for (int dy = -M; dy < M; dy++) {
                        if (dx == 0 && dy == 0) continue;

                        int x = i;
                        int y = j;
                        int num = 0;

                        while (x >= 0 && x < N && y >= 0 && y < M) {
                            num = num * 10 + board[x][y];
                            if (isPerfectSquare(num)) {
                                answer = Math.max(answer, num);
                            }
                            x += dx;
                            y += dy;
                        }
                    }
                }
            }
        }
        System.out.println(answer);
    }

    private static boolean isPerfectSquare(int num) {
        if (num < 0) return false;
        int sqrt = (int) Math.sqrt(num);
        return sqrt * sqrt == num;
    }
}
