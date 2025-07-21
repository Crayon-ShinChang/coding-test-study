import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

// 연결된 간선정보
class Edges implements Comparable<Edges>{
    int des;
    int cost;

    Edges(int des, int cost) {
        this.des = des;
        this.cost = cost;
    }

    @Override
    public int compareTo(Edges o) {
        return cost - o.cost;
    }
}

public class Main {

    static int n, e;
    static int v1, v2;
    static ArrayList<Edges>[] edges;            // 정점별 연결된 간선정보

    static int[] dist;                          // 1번 정점에서 각 정점까지의 최소거리
    static int[] u;                             // v1번 정점에서 각 정점까지의 최소거리
    static int[] v;                             // v2번 정점에서 각 정점까지의 최소거리

    private static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        dist = new int[n+1];
        u = new int[n+1];
        v = new int[n+1];
        edges = new ArrayList[n+1];

        for (int i = 0; i <= n; i++) {
            edges[i] = new ArrayList<>();
        }

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            // 양방향이므로 각각 저장
            edges[a].add(new Edges(b, c));
            edges[b].add(new Edges(a, c));
        }

        st = new StringTokenizer(br.readLine());
        v1 = Integer.parseInt(st.nextToken());
        v2 = Integer.parseInt(st.nextToken());
    }

    private static int[] dijkstra(int start) {
        int[] result = new int[n+1];
        Arrays.fill(result, -1);

        PriorityQueue<Edges> pq = new PriorityQueue<>();
        pq.add(new Edges(start, 0));
        result[start] = 0;

        while (!pq.isEmpty()) {
            Edges e = pq.poll();

            int loc = e.des;
            int cost = e.cost;

            for (int i = 0; i < edges[loc].size(); i++) {
                int next = edges[loc].get(i).des;
                int nCost = edges[loc].get(i).cost;

                if (result[next] == -1 || result[next] > cost+nCost) {
                    result[next] = cost+nCost;
                    pq.add(new Edges(next, cost+nCost));
                }
            }
        }

        return result;
    }

    private static int solution() {
        int answer = -1;

        dist = dijkstra(1);         // 1번 정점에서 최소비용
        u = dijkstra(v1);           // v1번 정점에서 최소비용
        v = dijkstra(v2);           // v2번 정점에서 최소비용

        int num1 = -1;              // 1 -> v1 -> v2 -> n
        int num2 = -1;              // 1 -> v2 -> v1 -> n

        // 각각 경로가 존재할 경우 저장
        if (dist[v1] != -1 && u[v2] != -1 && v[n] != -1) {
            num1 = dist[v1] + u[v2] + v[n];
        }
        if (dist[v2] != -1 && v[v1] != -1 && u[n] != -1) {
            num2 = dist[v2] + v[v1] + u[n];
        }

        // 둘다 -1이 아닌경우 더 작은 값을 저장
        if (num1 != -1 && num2 != -1) {
            answer = Math.min(num1, num2);
        }
        else if (num1 == -1) {
            answer = num2;
        }
        else {
            answer = num1;
        }

        return answer;
    }

    public static void main(String[] args) throws Exception {
        input();
        int answer = solution();
        System.out.println(answer);
    }
}