import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int n, m, t, p;
    static int[] parents;                    // 각 인원별 어느 파티에 속해있는지를 확인하는 집합
    static ArrayList<Integer> truths;        // 진실을 아는 사람
    static int[][] parties;                  // 각 파티별 오는 사람

    private static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        parents = new int[n+1];
        parties = new int[m][];

        for (int i = 1; i <= n; i++) {
            parents[i] = i;
        }

        st = new StringTokenizer(br.readLine());
        t = Integer.parseInt(st.nextToken());

        truths = new ArrayList<>();
        for (int i = 0; i < t; i++) {
            truths.add(Integer.parseInt(st.nextToken()));
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            p = Integer.parseInt(st.nextToken());

            parties[i] = new int[p];
            for (int j = 0; j < p; j++) {
                parties[i][j] = Integer.parseInt(st.nextToken());

                if (j != 0) {
                    union(parties[i][0], parties[i][j]);
                }
            }
        }
    }

    private static int find(int x) {
        if (parents[x] == x) {
            return x;
        }
        return find(parents[x]);
    }

    private static void union(int x, int y) {
        int rx = find(x);
        int ry = find(y);

        if (truths.contains(ry)) {
            int tmp = rx;
            rx = ry;
            ry = tmp;
        }

        parents[ry] = rx;
    }

    private static int solution() {
        int answer = 0;

        if (t == 0) {
            return m;
        }

        for (int i = 0; i < m; i++) {
            boolean check = true;
            for (int j = 0; j < parties[i].length; j++) {
                // 파티원 중에 진실을 아는 사람이 있는 경우
                if (truths.contains(find(parties[i][j]))) {
                    check = false;
                    break;
                }
            }
            if (check) {
                answer++;
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