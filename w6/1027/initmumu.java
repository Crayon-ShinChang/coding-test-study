
public class initmumu {
    public static void main(String[] args) throws Exception {
        int N = read();
        int[] buildings = new int[N];
        for (int i = 0; i < N; i++) {
            buildings[i] = read();
        }

        int max = -1;

        for (int i = 0; i < N; i++) {
            int cnt = 0;

            for (int j = i - 1; j > -1; j--) {
                boolean f = true;
                double decline = (buildings[i] - buildings[j]) / (double)(i - j);
                double yInter = buildings[i] - decline * i;
                for (int k = j + 1; k < i; k++) {
                    if (decline * k + yInter <= (double) buildings[k]) {
                        f = false;
                        break;
                    }
                }
                if (f) cnt++;
            }

            for (int j = i + 1; j < N; j++) {
                boolean f = true;
                double decline = (buildings[i] - buildings[j]) / (double)(i - j);
                double yInter = buildings[i] - decline * i;
                for (int k = i + 1; k < j; k++) {
                    if (decline * k + yInter <= (double) buildings[k]) {
                        f = false;
                        break;
                    }
                }
                if (f) cnt++;
            }


            max = Math.max(max, cnt);

        }

        System.out.println(max);
    }

    public static int read() throws Exception {
        int c, t = 0;
        while ((c = System.in.read()) > 32) t = (t << 3) + (t << 1) + (c & 15);
        return t;
    }
}