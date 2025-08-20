public class initmumu {
    public static void main(String[] args) throws Exception {
        int N = read();
        int[] req = new int[N];
        for (int i = 0; i < N; i++) req[i] = read();

        int M = read();

        int start = 0;
        int end = 0;
        for (int r : req) end = Math.max(end, r);

        int answer = 0;
        while (start <= end) {
            int mid = (start + end) >>> 1;
            long sum = 0;
            for (int r : req) sum += Math.min(r, mid);

            if (sum <= M) {
                answer = mid;
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        System.out.println(answer);
    }

    static int read() throws Exception {
        int c, n = 0;
        while ((c = System.in.read()) > 32) n = n * 10 + (c & 15);
        return n;
    }
}
