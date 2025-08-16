
import java.util.ArrayDeque;

public class initmumu {

    static int N, M, timeSpent;
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};
    
    public static void main(String[] args) throws Exception {
        N = read();
        M = read();
        int[][] grid = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                grid[i][j] = read();
            }
        }

        while(true) {
            if (cntIsland(grid) == 0) {
                System.out.println(0);
                break;
            } else if (cntIsland(grid) > 1) {
                System.out.println(timeSpent);
                break;
            }
            meltdown(grid);
            timeSpent++;
        }
    }

    public static int cntIsland(int[][] grid) {
        boolean[][] visited = new boolean[N][M];
        int cnt = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (visited[i][j] || grid[i][j] == 0) continue;
                
                cnt++;
                ArrayDeque<int[]> q = new ArrayDeque<>();
                q.add(new int[]{i, j});
                visited[i][j] = true;

                while(!q.isEmpty()) {
                    int[] node = q.poll();

                    for (int d = 0; d < 4; d++) {
                        int nx = node[0] + dx[d];
                        int ny = node[1] + dy[d];

                        if (!checkBound(nx, ny)) continue;
                        if (visited[nx][ny]) continue;
                        if (grid[nx][ny] == 0) continue;

                        q.add(new int[] {nx, ny});
                        visited[nx][ny] = true;
                    }
                }

            }
        }

        return cnt;
    }

    public static void meltdown(int[][] grid) {
        int[][] meltCount = new int[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                int cnt = 0;
                for (int d = 0; d < 4; d++) {
                    int nx = i + dx[d];
                    int ny = j + dy[d];

                    if (checkBound(nx, ny)) {
                        if (grid[nx][ny] == 0) cnt++;
                    }
                }
                meltCount[i][j] = cnt;
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                grid[i][j] -= (meltCount[i][j] > grid[i][j]) ? grid[i][j] : meltCount[i][j];
            }
        }
    }

    static boolean checkBound(int n, int m) {
        return (-1 < n && n < N && -1 < m && m < M);
    }

    public static int read() throws Exception {
        int c, t = 0;
        while ((c = System.in.read()) > 32) t = (t << 3) + (t << 1) + (c & 15);
        return t;
    }
}