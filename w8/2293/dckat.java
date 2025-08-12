import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, K;
    static int[] coin;

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        coin = new int[N+1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            coin[i] = Integer.parseInt(st.nextToken());
        }
    }

    static int solution() {
        int[] dp = new int[K+1];
        dp[0] = 1;      // 아무것도 선택하지 않는 경우는 1가지

        for (int i = 1; i <= N; i++) {
            for (int j = coin[i]; j <= K; j++) {
                dp[j] += dp[j-coin[i]];
            }
        }

        return dp[K];
    }

    public static void main(String[] args) throws Exception {
        input();
        int answer = solution();
        System.out.println(answer);
    }
}