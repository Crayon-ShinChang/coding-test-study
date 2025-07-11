import java.util.*;

public class initmumu {
    static Map<Long, Long> memo = new HashMap<>();
    static long N, P, Q;

    public static long A(long i) {
        if (i == 0) return 1;
        if (memo.containsKey(i)) return memo.get(i);
        long result = A(i / P) + A(i / Q);
        memo.put(i, result);
        return result;
    }

    public static void main(String[] args) throws Exception {
        N = read();
        P = read();
        Q = read();
        System.out.println(A(N));
    }

    public static long read() throws Exception {
        long c, t = 0;
        while ((c = System.in.read()) > 32) t = (t << 3) + (t << 1) + (c & 15);
        return t;
    }
}
