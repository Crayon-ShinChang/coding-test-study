import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int n;
    static int[] dp;
    static int[] cnt;

    private static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        dp = new int[125];          // 정사면체 크기당 필요한 대포알 수
        cnt = new int[n+1];         // 대포알 수에 따라 만들수 있는 정사면체 최소갯수
    }

    private static int solution() {
        // 정사면체 크기에 따른 필요한 대포알 수 셋팅
        // 필요한 갯수가 N개 이하일때까지 수행
        dp[0] = 0;
        dp[1] = 1;
        int num = 1;
        int idx = 1;
        while (dp[idx] <= n) {
            idx++;
            num += idx;
            dp[idx] = dp[idx-1] + num;
        }

        // 필요한 최소갯수 구하기
        Arrays.fill(cnt, -1);
        cnt[0] = 0;
        cnt[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j < idx; j++) {
                if (dp[j] > i) {
                    break;
                }

                if (cnt[i] == -1 || cnt[i] > cnt[i-dp[j]] + 1) {
                    cnt[i] = cnt[i-dp[j]] + 1;
                }
            }
        }

        return cnt[n];
    }

    public static void main(String[] args) throws Exception {
        input();
        int answer = solution();
        System.out.println(answer);
    }
}