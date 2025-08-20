import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static long N, M;
    static long[] arr;

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Long.parseLong(st.nextToken());
        M = Long.parseLong(st.nextToken());
        arr = new long[(int)N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i] = Long.parseLong(st.nextToken());
        }
    }

    static long solution() {
        long answer = 0;

        Arrays.sort(arr);
        long min = 0;
        long max = M*arr[(int)(N-1)];

        while (min <= max) {
            long sum = 0;
            long mid = (min+max)/2;

            for (int i = 0; i < N; i++) {
                if (sum >= M) {
                    break;
                }
                sum += (mid/arr[i]);
            }

            if (sum >= M) {
                answer = mid;
                max = mid - 1;
            }
            else {
                min = mid + 1;
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