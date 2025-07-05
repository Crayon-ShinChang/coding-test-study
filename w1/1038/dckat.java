import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int n;
    static ArrayList<Long> a = new ArrayList<>();

    private static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
    }

    private static void makeNum(long number) {
        a.add(number);
        long val = number % 10;     // 10으로 나눈 나머지

        if (val == 0) {
            return;
        }

        // 나머지-1은 기존 수의 바로 앞자리보다 작음
        // 해당되는 모든 숫자를 붙여서 리스트에 추가하는 작업을 재귀호출
        for (long i = val-1; i >= 0; i--) {
            long temp = number * 10 + i;
            makeNum(temp);
        }
    }

    private static long solution() {
        // 한자리 수 + 10은 모두 감소하는 수 이므로 숫자가 곧 해당번째를 의미
        if (n <= 10) {
            return n;
        }
        // 모든 자릿수는 달라야 하므로 최대 10자리 생성 (경우의 수는 1023가지)
        // 해당 수 이상으로는 만들 수 없는 경우이므로 -1 반환
        else if (n >= 1023) {
            return -1;
        }

        // 맨 앞자리가 0부터 감소하는 수 만들기
        for (int i = 0; i < 10; i++) {
            makeNum(i);
        }

        Collections.sort(a);
        return a.get(n);
    }


    public static void main(String[] args) throws Exception {
        input();
        long answer = solution();
        System.out.println(answer);
    }
}