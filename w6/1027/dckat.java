import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int n;
    static int[] arr;

    private static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        arr = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
    }

    // 해당 순열이 몇번재인지 찾기
    private static int solution() {
        int answer = 0;

        for (int i = 0; i < n; i++) {
            int left = 0;
            int right = 0;

            double leftMax = Double.NEGATIVE_INFINITY;
            double rightMax = Double.NEGATIVE_INFINITY;

            for (int j = i-1; j >= 0; j--) {
                double cur = (double)(arr[j] - arr[i]) / (i-j);

                if (leftMax < cur) {
                    leftMax = cur;
                    left++;
                }
            }

            for (int j = i+1; j < n; j++) {
                double cur = (double)(arr[j] - arr[i]) / (j-i);

                if (rightMax < cur) {
                    rightMax = cur;
                    right++;
                }
            }
            if (answer < (left+right)) {
                answer = left+right;
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