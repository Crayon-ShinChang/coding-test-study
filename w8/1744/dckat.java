import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
    static int[] arr;

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        arr = new int[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i] = Integer.parseInt(st.nextToken());
        }
    }

    static int solution() {
        int answer = 0;
        PriorityQueue<Integer> npq = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> ppq = new PriorityQueue<>(Collections.reverseOrder());

        for (int i = 0; i < N; i++) {
            if (arr[i] > 0) {
                ppq.add(arr[i]);
            }
            else {
                npq.add(-arr[i]);
            }
        }

        while (!ppq.isEmpty()) {
            int cur = ppq.poll();

            if (ppq.isEmpty()) {
                answer += cur;
                break;
            }

            if (cur == 1) {
                answer += cur;
            }
            else if (ppq.peek() == 1) {
                answer += (cur + ppq.poll());
            }
            else {
                answer += (cur * ppq.poll());
            }
        }

        while (!npq.isEmpty()) {
            int cur = npq.poll();

            if (npq.isEmpty()) {
                answer -= cur;
                break;
            }

            if (cur == 0) {
                npq.poll();
            }
            else {
                answer += (cur * npq.poll());
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