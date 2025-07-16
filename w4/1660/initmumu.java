
import java.util.Arrays;

public class initmumu {

    public static void main(String[] args) throws Exception {
        int N = read();
        int[] dp1 = new int[122];
        int[] dp2 = new int[122];

        int[] dp = new int[N+1];
        Arrays.fill(dp, 0x7f7f7f7f);
        dp[0] = 0;
        dp[1] = 1;

        for (int i = 1; i < 122; i++) {
            dp1[i] = dp1[i-1] + i;
            dp2[i] = dp2[i-1] + dp1[i]; 
        }

        for (int i = 2; i <= N; i++) {
            for (int j = 1; j < 122; j++) {
                if (i - dp2[j] >= 0)
                    dp[i] = Math.min(dp[i], dp[i - dp2[j]] + 1);
                else break;
            }

        }

        System.out.println(dp[N]);
    }

    public static int read() throws Exception {
        int c, t = 0;
        while ((c = System.in.read()) > 32) t = (t << 3) + (t << 1) + (c & 15);
        return t;
    }

}