import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int n, m;
    static int[][] board;

    private static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new int[n][m];

        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < m; j++) {
                board[i][j] = line.charAt(j) - '0';
            }
        }
    }

    // 범위 확인
    private static boolean isBound(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < m;
    }

    private static int solution() {
        int answer = -1;

        for (int x = 0; x < n; x++) {
            for (int y = 0; y < m; y++) {
                for (int dx = -n; dx < n; dx++) {
                    for (int dy = -m; dy < m; dy++) {

                        if (dx == 0 && dy == 0) {
                            continue;
                        }

                        int curX = x;
                        int curY = y;
                        int now = 0;

                        while (true) {
                            if (!isBound(curX, curY)) {
                                break;
                            }

                            // 현재 만들어진 수를 기존것에 이어붙이기
                            // 이어붙일 때에는 10을 곱하여 현재 만들어진 수를 생성
                            now += board[curX][curY];
                            now *= 10;

                            // 제곱근을 구함 (정수부분만 분리)
                            int check = (int) Math.sqrt(now);

                            // 정수부분만 분리시킨 부분을 다시 제곱하여 만족하는지 체크
                            if (check * check == now) {
                                answer = Math.max(answer, now);
                            }

                            curX += dx;
                            curY += dy;
                        }
                    }
                }
            }
        }

        return answer;
    }


    public static void main(String[] args) throws Exception {
        input();
        int answer = solution();
        System.out.println(answer);
    }
}