import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M;
    static int[][] board;

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static void input() throws Exception {
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

    // 범위 확인
    static boolean isBound(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < M;
    }

    // 빙산의 갯수 세기
    static int countCheese() {
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                // 같은 그룹에 있는 빙산을 DFS 탐색
                if (board[i][j] != 0) {
                    cnt++;
                }
            }
        }
        return cnt;
    }

    // 밖공기 표시 (BFS)
    static boolean[][] markOutsideAir(int[][] prev) {
        boolean[][] out = new boolean[N][M];
        Deque<int[]> dq = new ArrayDeque<>();
        dq.add(new int[]{0, 0});
        out[0][0] = true;

        while (!dq.isEmpty()) {
            int[] cur = dq.poll();
            int x = cur[0], y = cur[1];

            for (int k = 0; k < 4; k++) {
                int nx = x + dx[k], ny = y + dy[k];
                if (!isBound(nx, ny) || out[nx][ny]) continue;

                // 빈칸인 경우 바깥 공기가 들어올 수 있음
                if (prev[nx][ny] == 0) {
                    out[nx][ny] = true;
                    dq.add(new int[]{nx, ny});
                }
            }
        }
        return out;
    }

    static void melt() {
        int[][] prev = new int[N][M];
        for (int i = 0; i < N; i++) System.arraycopy(board[i], 0, prev[i], 0, M);

        boolean[][] outside = markOutsideAir(prev);

        int[][] next = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (prev[i][j] == 0) {
                    next[i][j] = 0;
                    continue;
                }
                // 기본은 유지(안 녹음)
                next[i][j] = 1;

                // 밖공기와 한 변이라도 맞닿으면 녹음
                for (int k = 0; k < 4; k++) {
                    int nx = i + dx[k], ny = j + dy[k];
                    if (isBound(nx, ny) && outside[nx][ny]) {
                        next[i][j] = 0;
                        break;
                    }
                }
            }
        }
        board = next;
    }

    static int[] solution() {
        int[] answer = new int[2];

        while (true) {
            int cnt = countCheese();

            // 모든 빙산이 녹은 경우
            if (cnt == 0) {
                break;
            }   // 모든 치즈가 녹기 전에 남은 치즈의 갯수 저장
            else {
                answer[1] = cnt;
            }

            melt();
            answer[0]++;
        }
        return answer;
    }

    public static void main(String[] args) throws Exception {
        input();
        int[] answer = solution();
        System.out.println(answer[0]);
        System.out.println(answer[1]);
    }
}