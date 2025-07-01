import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

// 인접 노드 저장 클래스
class Node {
    int to;         // 위치
    int cost;        // 이동한 거리

    Node (int to, int cost) {
        this.to = to;
        this.cost = cost;
    }
}

public class Main {

    static int n, m;
    static ArrayList<Node>[] nodes;         // 각 노드별로 인접된 노드정보를 저장

    private static int solution(int from, int to) {
        int answer = Integer.MAX_VALUE;

        boolean[] visited = new boolean[n+1];
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{from, 0});
        visited[from] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int loc = cur[0];
            int cost = cur[1];

            // 도착
            if (loc == to) {
                answer = cost;
                break;
            }

            // 현재 지점에서 인접한 노드 탐색
            for (int i = 0; i < nodes[loc].size(); i++) {
                int next = nodes[loc].get(i).to;
                int nCost = cost + nodes[loc].get(i).cost;

                // 방문하지 않은 곳만 큐에 추가
                if (!visited[next]) {
                    q.add(new int[]{next, nCost});
                    visited[next] = true;
                }
            }
        }

        return answer;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        nodes = new ArrayList[n+1];

        for (int i = 1; i <= n; i++) {
            nodes[i] = new ArrayList<>();
        }

        int from, to, cost;
        for (int i = 0; i < n-1; i++) {
            st = new StringTokenizer(br.readLine());
            from = Integer.parseInt(st.nextToken());
            to = Integer.parseInt(st.nextToken());
            cost = Integer.parseInt(st.nextToken());

            // 양방향 노드이므로 각각 저장
            nodes[from].add(new Node(to, cost));
            nodes[to].add(new Node(from, cost));
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            from = Integer.parseInt(st.nextToken());
            to = Integer.parseInt(st.nextToken());

            int answer = solution(from, to);
            System.out.println(answer);
        }
    }
}