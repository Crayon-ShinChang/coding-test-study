import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M;
    static int[][] board;
    static boolean[][] visited;

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new int[N][M];
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static void initVisited() {
        for (int i = 0; i < N; i++) {
            Arrays.fill(visited[i], false);
        }
    }

    // 범위 확인
    static boolean isBound(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < M;
    }

    // 연결된 빙산을 탐색 (DFS)
    static void dfs(int x, int y) {
        for (int k = 0; k < 4; k++) {
            int nx = x + dx[k];
            int ny = y + dy[k];

            if (isBound(nx, ny) && board[nx][ny] > 0 && !visited[nx][ny]) {
                visited[nx][ny] = true;
                dfs(nx, ny);
            }
        }
    }

    // 빙산의 갯수 세기
    static int countIceberg() {
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                // 같은 그룹에 있는 빙산을 DFS 탐색
                if (board[i][j] > 0 && !visited[i][j]) {
                    visited[i][j] = true;
                    dfs(i, j);
                    cnt++;
                }
            }
        }
        return cnt;
    }

    // 빙산을 녹이는 함수
    static void melt() {
        int[][] prev = new int[N][M];       // 녹기 전 보드의 상태 저장을 위함

        for (int i = 0; i < N; i++) {
            System.arraycopy(board[i], 0, prev[i], 0, M);
        }

        int[][] next = new int[N][M];       // 녹은 후 보드의 상태 저장
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (prev[i][j] == 0) {
                    next[i][j] = 0;
                    continue;
                }
                int seaCnt = 0;
                for (int k = 0; k < 4; k++) {
                    int nx = i + dx[k], ny = j + dy[k];
                    if (isBound(nx, ny) && prev[nx][ny] == 0) seaCnt++;
                }
                next[i][j] = Math.max(0, prev[i][j] - seaCnt);
            }
        }
        // 원본 교체
        board = next;
    }

    static int solution() {
        int answer = 0;

        while (true) {
            initVisited();
            int cnt = countIceberg();

            // 모든 빙산이 녹은 경우
            if (cnt == 0) {
                answer = 0;
                break;
            }
            // 2개 이상의 빙산으로 분리된 경우
            else if (cnt >= 2) {
                break;
            }
            melt();
            answer++;
        }
        return answer;
    }

    public static void main(String[] args) throws Exception {
        input();
        int answer = solution();
        System.out.println(answer);
    }
}