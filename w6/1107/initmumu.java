
public class initmumu {
    static int channel, M;
    static boolean[] broken = new boolean[10];

    public static void main(String[] args) throws Exception {
        channel = read();
        M = read();
        for (int i = 0; i < M; i++) broken[read()] = true;

        int ans = Math.abs(channel - 100);

        for (int i = 0; i <= 999999; i++) {
            int len = possible(i);
            if (len > 0) {
                ans = Math.min(ans, len + Math.abs(channel - i));
            }
        }

        System.out.println(ans);
    }

    public static int possible(int num) {
        if (num == 0) {
            return broken[0] ? 0 : 1;
        }
        int len = 0;
        while (num > 0) {
            if (broken[num%10]) return 0;
            num /= 10;
            len++;
        }
        return len;
    }

    public static int read() throws Exception {
        int c, t = 0;
        while ((c = System.in.read()) > 32) t = (t << 3) + (t << 1) + (c & 15);
        return t;
    }
}