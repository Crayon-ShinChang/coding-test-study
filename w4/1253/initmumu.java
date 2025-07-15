import java.util.Arrays;

public class initmumu {
    public static void main(String[] args) throws Exception {
        int N = read();
        int[] arr = new int[N];
        int cnt = 0;

        for (int i = 0; i < N; i++) arr[i] = read();
        Arrays.sort(arr);

        for (int k = 0; k < N; k++) {
            int left = 0;
            int right = N - 1;

            while (left < right) {
                if (left == k) {
                    left++;
                    continue;
                }
                if (right == k) {
                    right--;
                    continue;
                }

                int sum = arr[left] + arr[right];
                if (sum == arr[k]) {
                    cnt++;
                    break; // arr[k]는 좋은 수임
                } else if (sum < arr[k]) {
                    left++;
                } else {
                    right--;
                }
            }
        }

        System.out.println(cnt);
    }

    public static int read() throws Exception {
        int c, t = 0;
        boolean neg = false;
        while ((c = System.in.read()) <= 32);
        if (c == '-') {
            neg = true;
            c = System.in.read();
        }
        do {
            t = (t << 3) + (t << 1) + (c & 15);
        } while ((c = System.in.read()) > 32);
        return neg ? -t : t;
    }
}
