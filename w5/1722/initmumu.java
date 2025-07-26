import java.util.*;

public class initmumu {
    static int N;
    static long[] factorial = new long[21];
    static boolean[] used;

    public static void main(String[] args) throws Exception {
        N = (int) read();
        factorial[0] = 1;
        for (int i = 1; i <= 20; i++) {
            factorial[i] = factorial[i - 1] * i;
        }

        int cmd = (int) read();

        if (cmd == 1) {
            long k = read();
            used = new boolean[N + 1];
            List<Integer> result = new ArrayList<>();

            for (int i = 0; i < N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (used[j]) continue;
                    long cnt = factorial[N - i - 1];
                    if (cnt < k) {
                        k -= cnt;
                    } else {
                        result.add(j);
                        used[j] = true;
                        break;
                    }
                }
            }

            StringBuilder sb = new StringBuilder();
            for (int num : result) {
                sb.append(num).append(" ");
            }
            System.out.println(sb.toString().trim());

        } else {
            int[] arr = new int[N];
            used = new boolean[N + 1];
            for (int i = 0; i < N; i++) {
                arr[i] = (int) read();
            }

            long order = 0;
            for (int i = 0; i < N; i++) {
                int countSmaller = 0;
                for (int j = 1; j < arr[i]; j++) {
                    if (!used[j]) countSmaller++;
                }
                order += countSmaller * factorial[N - i - 1];
                used[arr[i]] = true;
            }
            System.out.println(order + 1);
        }
    }

    public static long read() throws Exception {
        long c, t = 0;
        while ((c = System.in.read()) > 32) t = (t << 3) + (t << 1) + (c & 15);
        return t;
    }
}
