import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int n, s;
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

        st = new StringTokenizer(br.readLine());
        s = Integer.parseInt(st.nextToken());
    }

    static Integer[] solution() {
        Integer[] answer = new Integer[n];

        for (int i = 0; i < n; i++) {
            answer[i] = arr[i];
        }

        // 정렬 횟수가 버블소트의 최대보다 큰 경우
        // 내림차순으로 정렬된것이 정답
        if (s > n*(n-1)/2) {
            Arrays.sort(answer, Collections.reverseOrder());
            return answer;
        }

        for (int k = 0; k < n && s > 0; k++) {
            int max = answer[k];
            int maxIdx = k;

            // 현재 위치에서 오른쪽에서 봤을때 교환 가능한 최댓값 찾기
            for (int i = k+1; i < n && i <= k+s; i++) {
                if (answer[i] > max) {
                    max = answer[i];
                    maxIdx = i;
                }
            }

            // 현재 값이 최댓값인 경우 교환을 수행하지 않음
            if (maxIdx == k) {
                continue;
            }

            s -= maxIdx-k;      // 위치에서 k를 뺀값 만큼 교환횟수 차감
            for (int i = maxIdx; i > k; i--) {
                int tmp = answer[i];
                answer[i] = answer[i-1];
                answer[i-1] = tmp;
            }
        }

        return answer;
    }

    static void print(Integer[] answer) {
        for (int i = 0; i < answer.length; i++) {
            System.out.print(answer[i] + " ");
        }
    }

    public static void main(String[] args) throws Exception {
        input();
        Integer[] answer = solution();
        print(answer);
    }
}