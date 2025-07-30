import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int n, m;
    static ArrayList<Integer> bList = new ArrayList<>();     // 고장난 번호

    private static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());

        // 고장난 번호가 없는 경우 종료
        if (m == 0) {
            return;
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            bList.add(Integer.parseInt(st.nextToken()));
        }
    }

    private static int solution() {
        int answer = 0;

        // 이동하려는 채널이 현재채널인 경우
        if (n == 100) {
            return answer;
        }

        // 방법1: 100번에서 +/-만 눌러 찾는 경우
        int num1 = Math.abs(100-n);

        // 방법2: 모든 채널로 이동하여 누르고 +/-를 눌러 찾는 경우
        int num2 = Integer.MAX_VALUE;
        for (int i = 0; i <= 999999; i++) {
            String s = String.valueOf(i);
            boolean check = true;

            // 이동하려는 채널에 고장난 숫자버튼이 있는지 확인
            for (int j = 0; j < s.length(); j++) {
                if (bList.contains(s.charAt(j)-'0')) {
                    check = false;
                    break;
                }
            }

            if (!check) {
                continue;
            }

            int tmp = s.length() + Math.abs(i-n);
            num2 = Math.min(num2, tmp);
        }
        answer = Math.min(num1, num2);
        return answer;
    }

    public static void main(String[] args) throws Exception {
        input();
        int answer = solution();
        System.out.println(answer);
    }
}