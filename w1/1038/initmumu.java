import java.io.*;
import java.util.*;

public class Main {
    static ArrayList<Long> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        // 감소하는 수 생성 (0~9 시작)
        for (int i = 0; i < 10; i++) {
            dfs(i, 1);
        }

        Collections.sort(list);

        if (n >= list.size()) {
            System.out.println(-1);
        } else {
            System.out.println(list.get(n));
        }
    }

    // 재귀적으로 감소하는 수 생성
    static void dfs(long num, int depth) {
        list.add(num);
        if (depth == 10) return; // 최대 10자리

        // 마지막 자릿수보다 작은 숫자만 추가
        for (int i = 0; i < num % 10; i++) {
            dfs(num * 10 + i, depth + 1);
        }
    }
}
