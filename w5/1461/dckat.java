import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int n, m;
    static ArrayList<Integer> pos = new ArrayList<>();          // 양수 위치
    static ArrayList<Integer> neg = new ArrayList<>();          // 음수 위치 (음수의 경우 양수값이 저장)

    private static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int dist = Integer.parseInt(st.nextToken());

            if (dist > 0) {
                pos.add(dist);
            }
            else {
                neg.add(-dist);
            }
        }
    }

    private static int solution() {
        int answer = 0;

        Collections.sort(pos);
        Collections.sort(neg);

        int size1 = pos.size();
        int size2 = neg.size();

        // 양수 위치 책 놓기 (먼곳 -> 가까운 곳)
        for (int i = size1-1; i >= 0; i -= m) {
            int d = pos.get(i);
            answer += d*2;
        }

        // 음수 위치 책 놓기 (먼곳 -> 가까운 곳)
        for (int i = size2-1; i >= 0; i -= m) {
            int d = neg.get(i);
            answer += d*2;
        }

        // 음수와 양수 중 0에서 더 먼 거리는 왕복할 필요가 없으므로 찾아서 빼기
        if (size1 > 0 && size2 > 0) {
            answer -= Math.max(pos.get(size1-1), neg.get(size2-1));
        }
        else if (size2 == 0) {
            answer -= pos.get(size1-1);
        }
        else {
            answer -= neg.get(size2-1);
        }

        return answer;
    }

    public static void main(String[] args) throws Exception {
        input();
        int answer = solution();
        System.out.println(answer);
    }
}