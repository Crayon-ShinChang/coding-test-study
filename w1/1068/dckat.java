import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int n, root, node;
    static int[] a;
    static ArrayList<Integer>[] d;

    static int answer;

    private static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());

        a = new int[n];
        d = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            d[i] = new ArrayList<>();
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken());

            // 루트 노드
            if (a[i] == -1) {
                root = i;
            }

            if (a[i] != -1) {
                d[a[i]].add(i);
            }
        }
        st = new StringTokenizer(br.readLine());
        node = Integer.parseInt(st.nextToken());
    }

    private static void solution(int k) {
        int cnt = 0;        // 연결된 자식노드의 갯수를 확인하는 변수

        // 제거되는 노드인 경우 DFS 중지
        if (node == k) {
            return;
        }

        // 연결된 자식노드로 이동
        for (int i = 0; i < d[k].size(); i++) {
            // 자식노드가 제거되지 않는 경우 자식노드로 이동하고 count 증가
            if (d[k].get(i) != node) {
                solution(d[k].get(i));
                cnt++;
            }
        }

        // 리프노드르 만난 경우
        if (cnt == 0) {
            answer++;
        }
    }


    public static void main(String[] args) throws Exception {
        input();
        solution(root);
        System.out.println(answer);
    }
}