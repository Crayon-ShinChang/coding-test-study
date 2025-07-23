import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int n;
    static long k;

    private static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Long.parseLong(st.nextToken());
    }

    // 해당 순열이 몇번재인지 찾기
    private static int solution() {
        int answer = -1;

        long numLen = 1;
        long numCount = 9;

        // k번째 인덱스가 어떤 수에 속해있는지 탐색
        // 1. 자릿수 체크
        while (k > numCount * numLen) {
            k -= (numLen * numCount);
            numLen++;
            numCount *= 10;
        }
        k--;

        // 2. 자릿수 내에 어떤 수인지 체크
        long num = (long)Math.pow(10, (numLen-1)) + (k/numLen);

        // 해당 숫자가 입력받은 숫자 이내인 경우
        if (num <= n) {
            answer = String.valueOf(num).charAt((int)(k%numLen)) - '0';
        }

        return answer;
    }

    public static void main(String[] args) throws Exception {
        input();
        int answer = solution();
        System.out.println(answer);
    }
}