import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int n, m, k;
    static int[][] board;

    private static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new int[n][m];

        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < s.length(); j++) {
                board[i][j] = s.charAt(j) - '0';
            }
        }

        st = new StringTokenizer(br.readLine());
        k = Integer.parseInt(st.nextToken());
    }

    // 같은 패턴의 행인지를 판별
    static boolean isSame(int a, int b) {
        for (int i = 0; i < board[a].length; i++) {
            if (board[a][i] != board[b][i]) {
                return false;
            }
        }
        return true;
    }

    private static int solution() {
        int answer = 0;

        for (int i = 0; i < n; i++) {
            int zero = 0;

            // 0이 들어있는 갯수 확인
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 0) {
                    zero++;
                }
            }

            // 같은 패턴을 갖는 행의 갯수 확인
            // 스위치를 눌러야 하는 횟수와 0의 갯수는 같은 홀수이거나 짝수이어야 함 (2번 눌렀을때 상태가 변하지 않음)
            int same = 0;
            if (zero <= k && zero%2 == k%2) {
                for (int j = 0; j < n; j++) {
                    if (isSame(i, j)) {
                        same++;
                    }
                }
            }

            answer = Math.max(answer, same);
        }

        return answer;
    }

    public static void main(String[] args) throws Exception {
        input();
        int answer = solution();
        System.out.println(answer);
    }
}