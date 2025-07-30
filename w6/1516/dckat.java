import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int n;
    static int[] degrees;                      // 위상정렬을 위한 차수
    static int[] times;                        // 각 건물별 짓는데 걸리는 시간
    static ArrayList<Integer>[] buildings;     // 각 건물 다음으로 지어야 할 리스트

    private static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        degrees = new int[n+1];
        times = new int[n+1];
        buildings = new ArrayList[n+1];

        for (int i = 1; i <= n; i++) {
            buildings[i] = new ArrayList<>();
        }

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            times[i] = Integer.parseInt(st.nextToken());

            while (true) {
                int num = Integer.parseInt(st.nextToken());
                if (num == -1) {
                    break;
                }
                else {
                    buildings[num].add(i);
                    degrees[i]++;
                }
            }
        }
    }

    // 각 건물별 기다려야 하는 시간 구하기
    private static int[] solution() {
        int[] answer = new int[n+1];
        Queue<Integer> q = new LinkedList<>();

        // 차수가 0인것을 큐에 삽입
        for (int i = 1; i <= n; i++) {
            if (degrees[i] == 0) {
                q.add(i);
            }
        }

        while(!q.isEmpty()) {
            int num = q.poll();

            // 해당 건물 다음으로 지어야 할 건물을 순회
            for (int i = 0; i < buildings[num].size(); i++) {
                int next = buildings[num].get(i);
                answer[next] = Math.max(answer[next], answer[num]+times[num]);
                degrees[next]--;

                // 차수가 0이 되는 것을 큐에 추가
                if (degrees[next] == 0) {
                    q.add(next);
                }
            }
        }

        return answer;
    }

    private static void print(int[] answer) {
        for (int i = 1; i <= n; i++) {
            System.out.println(answer[i]+times[i]);
        }
    }

    public static void main(String[] args) throws Exception {
        input();
        int[] answer = solution();
        print(answer);
    }
}