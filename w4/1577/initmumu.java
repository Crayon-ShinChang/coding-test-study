public class initmumu {
    static int N, M;
    static long[][] dp;
    static boolean[][][] blocked;

    public static void main(String[] args) throws Exception {
        N = read();
        M = read();
        int K = read();

        blocked = new boolean[M + 1][N + 1][2];

        for (int i = 0; i < K; i++) {
            int a = read(); int b = read();
            int c = read(); int d = read();

            if (a == c) {
                int minY = Math.min(b, d);
                blocked[minY + 1][a][0] = true;
            } else {
                int minX = Math.min(a, c);
                blocked[b][minX + 1][1] = true;
            }
        }

        dp = new long[M + 1][N + 1];
        dp[0][0] = 1;

        for (int y = 0; y <= M; y++) {
            for (int x = 0; x <= N; x++) {
                if (y > 0 && !blocked[y][x][0]) {
                    dp[y][x] += dp[y - 1][x];
                }
                if (x > 0 && !blocked[y][x][1]) {
                    dp[y][x] += dp[y][x - 1];
                }
            }
        }

        System.out.println(dp[M][N]);
    }

    public static int read() throws Exception {
        int c, t = 0;
        while ((c = System.in.read()) > 32)
            t = (t << 3) + (t << 1) + (c & 15);
        return t;
    }
}
