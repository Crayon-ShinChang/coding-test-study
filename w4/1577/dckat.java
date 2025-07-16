import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int n, m, k;
    static boolean[][][] roads;         // 공사중인 도로 (가로.세로 각각 별도로 저장)
    static long[][] dp;                 // 각 좌표별 이동가능한 경로의 수

    static int[] dx = {1, 0};
    static int[] dy = {0, 1};

    private static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        dp = new long[n+1][m+1];
        roads = new boolean[n+1][m+1][2];

        st = new StringTokenizer(br.readLine());
        k = Integer.parseInt(st.nextToken());

        // 공사중인 도로 정보 입력
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            // 세로 공사
            if (Math.abs(c-a) == 1) {
                roads[Math.min(a, c)][b][0] = true;
            }   // 가로 공사
            else if (Math.abs(d-b) == 1) {
                roads[a][Math.min(b, d)][1] = true;
            }
        }
    }

    private static boolean isBound(int x, int y) {
        return 0 <= x && x <= n && 0 <= y && y <= m;
    }

    private static long solution() {
        dp[0][0] = 1L;          // 출발지를 1로 초기화

        // (n, m) 까지의 이동경로의 가짓수를 dp를 활용하여 저장
        // (nx, ny) 경우의 수: (nx-1, ny) 경우의 수 + (nx, ny-1) 경우의 수
        for (int x = 0; x <= n; x++) {
            for (int y = 0; y <= m; y++) {
                for (int k = 0; k < 2; k++) {
                    int nx = x + dx[k];
                    int ny = y + dy[k];

                    // 이동가능: 범위내에 있고 공사중이 아니어야 함
                    if (isBound(nx, ny) && !roads[x][y][k]) {
                        dp[nx][ny] += dp[x][y];
                    }
                }
            }
        }

        return dp[n][m];
    }

    public static void main(String[] args) throws Exception {
        input();
        long answer = solution();
        System.out.println(answer);
    }
}