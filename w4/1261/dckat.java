import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int n, m;
    static int[][] board;

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    private static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new int[m][n];

        for (int i = 0; i < m; i++) {
            String line = br.readLine();
            for (int j = 0; j < n; j++) {
                board[i][j] = line.charAt(j) - '0';
            }
        }
    }

    // 범위 확인
    private static boolean isBound(int x, int y) {
        return 0 <= x && x < m && 0 <= y && y < n;
    }

    private static int solution() {
        Deque<int[]> dq = new ArrayDeque<>();
        int[][] dist = new int[m][n];

        for (int i = 0; i < m; i++) {
            Arrays.fill(dist[i], -1);
        }

        // 출발지는 앞에 추가
        dq.addFirst(new int[]{0, 0});
        dist[0][0] = 0;

        while (!dq.isEmpty()) {
            int[] cur = dq.pollFirst();
            int x = cur[0];
            int y = cur[1];

            if (x == n-1 && y == m-1) {
                break;
            }

            for (int k = 0; k < 4; k++) {
                int nx = x + dx[k];
                int ny = y + dy[k];

                // 1. 범위 확인
                if (isBound(nx, ny)) {
                    // 2. 방문 여부 확인
                    if (dist[nx][ny] == -1) {
                        // 부수는 벽을 최소로 해야하므로 부수지 않는 경우를 우선순위를 두어야함.
                        // 3-1. 벽인 경우 (deque 뒤에 삽입)
                        if (board[nx][ny] == 1) {
                            dist[nx][ny] = dist[x][y] + 1;
                            dq.addLast(new int[] {nx, ny});
                        }   // 3-2. 빈칸인 경우 (deque 앞에 삽입)
                        else {
                            dist[nx][ny] = dist[x][y];
                            dq.addFirst(new int[] {nx, ny});
                        }
                    }
                }
            }
        }

        return dist[m-1][n-1];
    }

    public static void main(String[] args) throws Exception {
        input();
        int answer = solution();
        System.out.println(answer);
    }
}