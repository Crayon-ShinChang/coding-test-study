import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M;
    static int[] arr;

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
    }

    static int solution() {
        int answer = 0;

        Arrays.sort(arr);
        int min = 0;
        int max = arr[N-1];

        while (min <= max) {
            int sum = 0;
            int mid = (min+max)/2;

            for (int i = 0; i < N; i++) {
                // 상한액을 초과하는 경우
                if (arr[i] > mid) {
                    sum += mid;
                }
                else {
                    sum += arr[i];
                }
            }

            // 예산 이내
            if (sum <= M) {
                answer = mid;
                min = mid + 1;
            }
            else {
                max = mid - 1;
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