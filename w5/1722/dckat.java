import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int n, type;
    static long k;

    static int[] inputArr;              // 입력받은 순열
    static long[] factorial;            // 수의 길에 따른 순열의 개수
    static boolean[] check;             // 선택 여부

    private static void makeFactorial() {
        factorial = new long[n+1];
        factorial[0] = 1;
        factorial[1] = 1;

        for (int i = 2; i <= n; i++) {
            factorial[i] = factorial[i-1] * i;
        }
    }

    // 해당 번째의 순열 찾기
    private static int[] solution1() {
        int[] answer = new int[n];
        int index = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 1; j <= n; j++) {
                // 선택되지 않은 경우
                if (!check[j]) {
                    // 현재 팩토리얼 값보다 뒤에 있는 경우
                    if (k - factorial[n-1-i] > 0) {
                        k -= factorial[n-1-i];
                    } else {
                        check[j] = true;
                        answer[index] = j;
                        index++;
                        break;
                    }
                }
            }
        }

        return answer;
    }

    // 해당 순열이 몇번재인지 찾기
    private static long solution2() {
        long answer = 0;

        for (int i = 0; i < n; i++) {
            // 현재 인덱스의 수 앞에 선택되지 않은 수 탐색
            for (int j = 1; j < inputArr[i]; j++) {
                // 선택이 되지 않은 경우 팩토리얼 만큼 증가
                if (!check[j]) {
                    answer += factorial[n-1-i];
                }
            }
            check[inputArr[i]] = true;
        }

        return answer + 1L;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        check = new boolean[n+1];

        makeFactorial();

        st = new StringTokenizer(br.readLine());
        type = Integer.parseInt(st.nextToken());

        if (type == 1) {
            k = Long.parseLong(st.nextToken());
            int[] answer = solution1();

            for (int i = 0; i < answer.length; i++) {
                System.out.print(answer[i] + " ");
            }
        }
        else {
            inputArr = new int[n];
            for (int i = 0; i < n; i++) {
                inputArr[i] = Integer.parseInt(st.nextToken());
            }
            long answer = solution2();
            System.out.println(answer);
        }
    }
}