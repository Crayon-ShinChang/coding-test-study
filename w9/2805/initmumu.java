public class initmumu {
    public static void main(String[] args) throws Exception {
        int N = read();
        long M = (long) read();
        int[] namus = new int[N];
        long start = 0;
        long end = -1;

        for (int i = 0; i < N; i++) {
            namus[i] = read();
            end = Math.max(end, namus[i]);
        }

        long result = 0;
        while (end - start > 0) {
            long mid = (end + start) / 2;

            long namu = 0;
            for (int i = 0; i < N; i++) {
                long t = namus[i] - mid;
                namu += (t > 0)? t: 0;
            }

            if (namu >= M) {
                result = mid;
                start = mid + 1;
            }
            else end = mid;
        }

        System.out.println(result);
    }

    public static int read() throws Exception {
        int c, t = 0;
        while ((c = System.in.read()) > 32) t = (t << 3) + (t << 1) + (c & 15);
        return t;
    }
}