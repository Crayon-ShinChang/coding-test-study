


public class initmumu {

    public static void main(String[] args) throws Exception {
        int N = read();
        int target = read();
        int[] dp = new int[target + 1];
        dp[0] = 1;

        for (int i = 0; i < N; i++) {
            int coin = read();
            for (int j = coin; j < dp.length; j++) {
                dp[j] += dp[j - coin];
            }
        }

        System.out.println(dp[target]);
    }

    public static int read() throws Exception {
        int c, t = 0;
        while ((c = System.in.read()) > 32) t = (t << 3) + (t << 1) + (c & 15);
        return t;
    }
}