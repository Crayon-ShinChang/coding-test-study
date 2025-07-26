import java.util.*;

public class initmumu {
    static final int INF = 1_000_000_000;
    static int N, E;
    static ArrayList<Edge>[] graph;

    static class Edge implements Comparable<Edge> {
        int to, cost;
        Edge(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }

        public int compareTo(Edge o) {
            return this.cost - o.cost;
        }
    }

    public static void main(String[] args) throws Exception {
        N = read();
        E = read();

        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < E; i++) {
            int a = read();
            int b = read();
            int c = read();
            graph[a].add(new Edge(b, c));
            graph[b].add(new Edge(a, c));
        }

        int v1 = read();
        int v2 = read();

        int[] distFrom1 = dijkstra(1);
        int[] distFromV1 = dijkstra(v1);
        int[] distFromV2 = dijkstra(v2);

        long path1 = (long) distFrom1[v1] + distFromV1[v2] + distFromV2[N];
        long path2 = (long) distFrom1[v2] + distFromV2[v1] + distFromV1[N];

        long result = Math.min(path1, path2);

        System.out.println(result >= INF ? -1 : result);
    }

    static int[] dijkstra(int start) {
        int[] dist = new int[N + 1];
        Arrays.fill(dist, INF);
        dist[start] = 0;

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(start, 0));

        while (!pq.isEmpty()) {
            Edge cur = pq.poll();

            if (cur.cost > dist[cur.to]) continue;

            for (Edge next : graph[cur.to]) {
                int cost = dist[cur.to] + next.cost;
                if (cost < dist[next.to]) {
                    dist[next.to] = cost;
                    pq.offer(new Edge(next.to, cost));
                }
            }
        }

        return dist;
    }

    public static int read() throws Exception {
        int c, t = 0;
        while ((c = System.in.read()) > 32) t = (t << 3) + (t << 1) + (c & 15);
        return t;
    }
}
