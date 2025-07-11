import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int G;

    private static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        G = Integer.parseInt(st.nextToken());
    }

    private static ArrayList<Long> solution() {
        ArrayList<Long> answer = new ArrayList<>();

        long pre = 1L;          // 이전 몸무게
        long cur = 2L;          // 현재 몸무게

        while (true) {
            long diff = cur*cur - pre*pre;          // 문제조건에 따라 차이값을 구함

            // 같은 경우
            // 결과 배열에 추가 + 현재 몸무게를 증가
            if (diff == G) {
                answer.add(cur);
                cur++;
            }
            // G보다 큰 경우
            else if (diff > G) {
                // 현재 몸무게와 이전 몸무게 차이가 1인 경우
                // 더 작은 제곱수가 나올 수 없으므로 반복문 중단
                if (cur - pre == 1L) {
                    break;
                }
                // 그렇지 않은 경우 이전 몸무게를 증가
                pre++;
            }
            // G보다 작은 경우 현재 몸무게를 증가
            else if (diff < G) {
                cur++;
            }
        }

        return answer;
    }

    private static void print(ArrayList<Long> answer) {
        // 가능한 현재 몸무게가 없는 경우 -1 출력
        if (answer.size() == 0) {
            System.out.println(-1);
        }

        for (int i = 0; i < answer.size(); i++) {
            System.out.println(answer.get(i));
        }
    }

    public static void main(String[] args) throws Exception {
        input();
        ArrayList<Long> answer = solution();
        print(answer);
    }
}