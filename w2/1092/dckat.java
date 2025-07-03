import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int n, m;
    static ArrayList<Integer> crane = new ArrayList<>();
    static ArrayList<Integer> box = new ArrayList<>();

    private static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());

        int num;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            num = Integer.parseInt(st.nextToken());
            crane.add(num);
        }

        st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            num = Integer.parseInt(st.nextToken());
            box.add(num);
        }
    }

    private static int solution() {
        int answer = 0;

        Collections.sort(crane, Collections.reverseOrder());
        Collections.sort(box, Collections.reverseOrder());


        // 불가능한 경우 (박스가 하나라도 남는 경우 --> 크레인의 하중보다 더 큰 경우)
        if (box.get(0) > crane.get(0)) {
            return -1;
        }

        // 박스를 다 옮길때 까지 진행
        while (!box.isEmpty()) {
            answer++;

            for (int i = 0; i < crane.size(); i++) {
                // 옮길 박스가 없는 경우
                if (box.isEmpty()) {
                    break;
                }

                // 해당 크레인으로 옮길 수 있는 박스가 없는경우
                if (crane.get(i) < box.get(box.size() - 1)) {
                    continue;
                }

                // 현재 크레인으로 옮길수 있는 박스 탐색 (가장 무거운 박스부터 탐색하여 옮김)
                for (int j = 0; j < box.size(); j++) {
                    if (crane.get(i) >= box.get(j)) {
                        box.remove(j);
                        break;
                    }
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