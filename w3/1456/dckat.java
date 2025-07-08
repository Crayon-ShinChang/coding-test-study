import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static long A, B;
    static boolean[] isPrime;

    private static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        A = Long.parseLong(st.nextToken());
        B = Long.parseLong(st.nextToken());
    }

    // 소수 판별 (에라토스테네스체 활용)
    private static void makePrimes() {
        // B의 제곱근 갯수까지의 소수판별
        int limit = (int)Math.sqrt(B);
        isPrime = new boolean[limit+1];

        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;

        for (int i = 2; i <= limit; i++) {
            if (isPrime[i]) {
                for (int j = i*2; j <= limit; j += i) {
                    isPrime[j] = false;
                }
            }
        }
    }

    private static long solution() {
        int answer = 0;

        makePrimes();

        for (int i = 2; i < isPrime.length; i++) {
            // 소수인 경우
            // 제곱을 수행하여 소수 갯수를 구함 (B보다 작을때 까지)
            if (isPrime[i]) {
                long cur = i;

                while (cur <= (double)B/i) {
                    if (cur >= (double)A/i) {
                        answer++;
                    }
                    cur *= i;
                }
            }
        }

        return answer;
    }

    public static void main(String[] args) throws Exception {
        input();
        long answer = solution();
        System.out.println(answer);
    }
}