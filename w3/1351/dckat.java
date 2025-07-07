import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static long N, P, Q;
    static Map<Long, Long> m = new HashMap<>();     // 인덱스-수열 (중복 사용이 가능하므로 맵 사용)

    private static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Long.parseLong(st.nextToken());
        P = Long.parseLong(st.nextToken());
        Q = Long.parseLong(st.nextToken());
    }

    private static long solution(long num) {
        if (num == 0L) {
            return 1L;
        }

        // 맵에 저장되어 있는 경우
        if (m.containsKey(num)) {
            return m.get(num);
        }

        long first = solution((long)Math.floor(num/P));       // i/p
        long second = solution((long)Math.floor(num/Q));      // i/q
        m.put(num, first+second);                             // 구한 값을 맵에 저장
        return m.get(num);
    }

    public static void main(String[] args) throws Exception {
        input();
        m.put(0L, 1L);          // 0번째 인덱스를 우선 저장
        long answer = solution(N);
        System.out.println(answer);
    }
}