import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int n;
    static int[] arr;

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        arr = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
    }

    static String solution() {
        String answer = "";

        // 길이가 1이면 항상 2개 이상의 답
        if (n == 1) {
            return "A";
        }
        else if (n == 2) {
            // 2개의 수가 같은 경우는 해당 값이 정답
            if (arr[0] == arr[1]) {
                answer = String.valueOf(arr[1]);
            }
            // 다를 경우에는 여러개의 수가 나올 수 있음
            else {
                return "A";
            }
        }
        else {
            // 점화식을 위한 a, b 구하기
            int a = 0;
            if (arr[1] - arr[0] != 0) {
                a = (arr[2] - arr[1]) / (arr[1] - arr[0]);
            }
            int b = arr[1] - arr[0] * a;

            // 수를 이동하면서 점화식을 만족하는지 체크
            for (int i = 1; i < n; i++) {
                // 입력받은 수열이 점화식을 만족하지 않는다면 B
                if (arr[i] != arr[i-1] * a + b) {
                    return "B";
                }
            }
            answer = String.valueOf(arr[n-1] * a + b);
        }

        return answer;
    }

    public static void main(String[] args) throws Exception {
        input();
        String answer = solution();
        System.out.println(answer);
    }
}