import java.util.*;

public class initmumu {
    public static void main(String[] args) throws Exception {
        int N = read(), M = read();
        DisjointSet ds = new DisjointSet(N);

        int pn = read();
        for (int i = 0; i < pn; i++) {
            ds.union(read(), 0);
        }

        ArrayList<Integer> al = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            pn = read();
            int d = read();
            al.add(d);
            for (int j = 0; j < pn - 1; j++) {
                int a = read();
                ds.union(d, a);
            }
        }

        int cnt = 0;
        for (int n: al) {
            if (!ds.isSameSet(n, 0)) cnt++;
        }

        System.out.println(cnt);

    }

    public static int read() throws Exception {
        int c, t = 0;
        while ((c = System.in.read()) > 32) t = (t << 3) + (t << 1) + (c & 15);
        return t;
    }

    public static class DisjointSet {
        private final int[] parents;

        public DisjointSet(int N) {
            parents = new int[N+1];
            for (int i = 0; i <= N; i++) parents[i] = i;
        }

        public void union(int a, int b) {
            int ap = find(a);
            int bp = find(b);

            if (ap > bp) parents[ap] = parents[bp];
            else parents[bp] = parents[ap];
        }

        public int find(int a) {
            if (a == parents[a]) return a;
            return find(parents[a]);
        }

        public boolean isSameSet(int a, int b) {
            return find(a) == find(b);
        }
    }
}