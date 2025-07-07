import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M;
    static int[][] board;
    static boolean[][] visited;

    // 인접한 8방향 (조건: x,y 차이가 모두 1이하)
    static int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

    private static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    private static boolean isBound(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < M;
    }

    private static boolean bfs(int x, int y) {
        boolean flag = true;        // 더 높은 산봉우리가 있는지 확인변수

        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{x, y});
        visited[x][y] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int cx = cur[0];
            int cy = cur[1];

            for (int k = 0; k < 8; k++) {
                int nx = cx + dx[k];
                int ny = cy + dy[k];

                if (isBound(nx, ny)) {
                    // 참고: 낮은 곳은 어떠한 작업도 하지 않음
                    // 인접한 곳에 같은 높이의 산봉우리가 있는 경우
                    if (board[nx][ny] == board[x][y] && !visited[nx][ny]) {
                        q.add(new int[]{nx, ny});
                        visited[nx][ny] = true;
                    }   // 인접한 곳에 더 높은 산봉우리가 있는 경우
                    else if (board[nx][ny] > board[x][y]) {
                        flag = false;
                    }
                }
            }
        }
        return flag;
    }

    private static int solution() {
        int answer = 0;
        visited = new boolean[N][M];            // 방문 여부

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!visited[i][j]) {
                    // 산봉우리의 조건을 만족하면 갯수 증가
                    if (bfs(i, j)) {
                        answer++;
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