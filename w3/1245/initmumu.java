
import java.util.ArrayDeque;

public class initmumu {
    static int N, M;
    static int[][] grid;
    static boolean[][] visited;
    static int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

    public static boolean isPeakBfs(int x, int y) {
        ArrayDeque<Pos> ad = new ArrayDeque<>();
        ad.add(new Pos(x, y));
        visited[x][y] = true;

        boolean isPeak = true;
        while(!ad.isEmpty()) {
            Pos cp = ad.poll();
            
            for (int i = 0; i < 8; i++) {
                int nx = cp.x + dx[i];
                int ny = cp.y + dy[i];

                if (!(nx >= 0 && nx < N && ny >= 0 && ny < M)) continue;

                if (grid[nx][ny] > grid[cp.x][cp.y]) {
                    isPeak = false;
                }

                if (!visited[nx][ny] && grid[nx][ny] == grid[cp.x][cp.y]) {
                    visited[nx][ny] = true;
                    ad.add(new Pos(nx, ny));
                }
            }
        }

        return isPeak;
    }

    public static void main(String[] args) throws Exception {
        N = read(); M = read();
        grid = new int[N][M];
        visited = new boolean[N][M];
        int cnt = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                grid[i][j] = read();
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!visited[i][j] && isPeakBfs(i, j)) cnt++;
            }
        }

        System.out.println(cnt);
    }

    public static class Pos {
        public int x;
        public int y;

        public Pos(int _x, int _y) {
            this.x = _x;
            this.y = _y;
        }
    }

    public static int read() throws Exception {
        int c, t = 0;
        while ((c = System.in.read()) > 32) t = (t << 3) + (t << 1) + (c & 15);
        return t;
    }

}