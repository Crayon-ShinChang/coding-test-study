import java.util.*;

public class initmumu {
    public static void main(String[] args) throws Exception {
        int N = read();
        int[] cost = new int[N+1];
        int[] time = new int[N+1];
        int[] indegree = new int[N+1];
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) graph.add(new ArrayList<>());

        for (int i = 1; i <= N; i++) {
            cost[i] = read();
            while (true) {
                int pre = read();
                if (pre == -1) break;
                graph.get(pre).add(i);
                indegree[i]++;
            }
        }

        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            if (indegree[i] == 0) {
                q.add(i);
                time[i] = cost[i];
            }
        }

        while (!q.isEmpty()) {
            int now = q.poll();
            for (int next : graph.get(now)) {
                indegree[next]--;
                time[next] = Math.max(time[next], time[now] + cost[next]);
                if (indegree[next] == 0) q.add(next);
            }
        }

        for (int i = 1; i <= N; i++) System.out.println(time[i]);
    }

    public static int read() throws Exception {
        int c, t = 0;
        boolean neg = false;
        while ((c = System.in.read()) != -1 && c <= 32);
        if (c == -1) return -1;
        if (c == 45) { neg = true; c = System.in.read(); }
        do {t = t*10 + (c & 15);}
        while ((c = System.in.read()) > 32);
        return neg ? -t : t;
    }
}
