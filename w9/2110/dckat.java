import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, C;
    static int[] arr;

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        arr = new int[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i] = Integer.parseInt(st.nextToken());
        }
    }

    static int solution() {
        int answer = 0;

        Arrays.sort(arr);
        int min = 1;
        int max = arr[N-1] - arr[0];

        while (min <= max) {
            int cnt = 1;
            int mid = (min+max)/2;
            int start = arr[0];

            for (int i = 1; i < N; i++) {
                if (arr[i] - start >= mid) {
                    cnt++;
                    start = arr[i];
                }
            }

            if (cnt >= C) {
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