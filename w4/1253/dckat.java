import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int n;
    static long[] arr;

    private static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        arr = new long[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }
    }

    private static int solution() {
        int answer = 0;

        Arrays.sort(arr);
        for (int i = 0; i < n; i++) {
            long num = arr[i];

            int s = 0;
            int e = n-1;

            while (s < e) {
                long cur = arr[s] + arr[e];

                // 다른 2개의 수로 표현되어야 함 (타켓팅 수는 제외)
                if (s == i) {
                    s++;
                    continue;
                }
                else if (e == i) {
                    e--;
                    continue;
                }

                if (cur == num) {
                    answer++;
                    break;
                }
                else if (cur > num) {
                    e--;
                }
                else {
                    s++;
                }
            }
        }

        return answer;
    }

    public static void main(String[] args) throws Exception {
        input();
        int answer = solution();
        System.out.println(answer);
    }
}