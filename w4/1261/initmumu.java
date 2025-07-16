import java.io.*;
import java.util.*;

public class initmumu {
    public static void main(String[] args) throws IOException {
        int M = read(), N = read();
        boolean[][] grid = new boolean[N][M];
        for (int i = 0; i < N; i++) grid[i] = readLine(M);
        
        if (N == 1 && M == 1) {
            System.out.println(0);
            return;
        }

        int[] dx = {-1,1,0,0};
        int[] dy = {0,0,-1,1};

        boolean[][] visited = new boolean[N][M];

        ArrayDeque<int[]> queue = new ArrayDeque<>();
        visited[0][0] = true;
        queue.add(new int[]{0, 0, 0});

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int row = curr[0], col = curr[1], destroy = curr[2];

            for (int i = 0; i < 4; i++) {
                int nx = row + dx[i], ny = col + dy[i];
                
                if (nx == N - 1 && ny == M - 1) {
                    System.out.println(destroy);
                    return;
                }
                
                if (-1 < nx && nx < N && -1 < ny && ny < M) {
                    if (!visited[nx][ny]) {
                        visited[nx][ny] = true;
                        if (grid[nx][ny]) queue.addFirst(new int[]{nx, ny, destroy});
                        else queue.addLast(new int[]{nx, ny, destroy+1});
                    }
                }
            }
        }
    }

    public static int read() throws IOException {
        int c, t = 0;
        while((c = System.in.read()) > 32) t = (t<<3) + (t<<1) + (c&15);
        return t;
    }

    public static boolean[] readLine(int n) throws IOException {
        int i = 0; boolean[] r = new boolean[n];
        while(i < n) r[i++] = System.in.read() == 48;
        System.in.read();
        return r;
    }
}