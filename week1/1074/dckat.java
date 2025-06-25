import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, R, C;
    static int answer;

    private static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
    }

    private static void solution(int r, int c, int size) {
        // 최종 위치
        if (size == 1) {
            return;
        }
        int nextSize = size / 2;

        // 왼쪽 상단
        if (R < r + nextSize && C < c + nextSize) {
            solution(r, c, nextSize);
        }
        // 오른쪽 상단
        else if (R < r + nextSize && c + nextSize <= C) {
            answer += (size*size) / 4;
            solution(r, c+nextSize, nextSize);
        }
        // 왼쪽 하단
        else if (r + nextSize <= R && C < c+nextSize) {
            answer += ((size*size) / 4) * 2;
            solution(r+nextSize, c, nextSize);
        }
        // 오른쪽 하단
        else if (r + nextSize <= R && c + nextSize <= C) {
            answer += ((size*size) / 4) * 3;
            solution(r+nextSize, c+nextSize, nextSize);
        }
    }


    public static void main(String[] args) throws Exception {
        input();
        solution(0, 0, (int)Math.pow(2, N));
        System.out.println(answer);
    }
}