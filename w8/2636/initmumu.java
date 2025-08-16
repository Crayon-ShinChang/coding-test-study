import java.util.*;

public class initmumu {
    static int N, M;

    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};

    public static void main(String[] args) throws Exception {
        N = read();
        M = read();
        int prevOneHour = 0;
        int time = 0;
        int[][] grid = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                grid[i][j] = read();
                if (grid[i][j] == 1) prevOneHour++;
                if (i == 0 || i == N-1 || j == 0 || j == M-1) grid[i][j] = 2;
            }
        }

        markInsideAir(grid);


        while (true) {
            time++;
            int cur = melt(grid);
            if (cur == 0) {
                break;
            }
            markInsideAir(grid);
            prevOneHour = cur;
        }
        
        System.out.println(time);
        System.out.println(prevOneHour);
    }

    public static int melt(int[][] grid) {
        boolean[][] meltGrid = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (grid[i][j] != 1) continue;
                
                for (int d = 0; d < 4; d++) {
                    int nx = i + dx[d];
                    int ny = j + dy[d];

                    if (!checkBound(nx, ny)) continue;
                    if (grid[nx][ny] != 2) continue;

                    meltGrid[i][j] = true;
                }
            }
        }

        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (meltGrid[i][j]) grid[i][j] = 2;
                if (grid[i][j] == 1) cnt++;
            }
        }

        return cnt;
    }

    public static void markInsideAir(int[][] grid) {
        boolean[][] visited = new boolean[N][M];
        ArrayDeque<Node> q = new ArrayDeque<>();

        q.add(new Node(0,0));
        visited[0][0] = true;

        while(!q.isEmpty()) {
            Node node = q.poll();

            for (int d = 0; d < 4; d++) {
                int nx = node.x + dx[d];
                int ny = node.y + dy[d];

                if (!checkBound(nx, ny)) continue;
                if (visited[nx][ny]) continue;
                if (grid[nx][ny] == 1) continue;

                q.add(new Node(nx, ny));
                visited[nx][ny] = true;
                grid[nx][ny] = 2;
            }
        }
    }

    public static boolean checkBound(int x, int y) {
        return (-1 < x && x < N && -1 < y && y < M);
    }

    public static void printGrid(int[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            System.out.println(Arrays.toString(grid[i]));
        }
    }

    static class Node {
        public int x;
        public int y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static int read() throws Exception {
        int c, t = 0;
        while ((c = System.in.read()) > 32) t = (t << 3) + (t << 1) + (c & 15);
        return t;
    }
}