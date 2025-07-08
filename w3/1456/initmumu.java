import java.io.*;
import java.util.*;

public class initmumu {
    static class BitArray {
        private final int[] data;
        private final int size;

        public BitArray(int size) {
            this.size = size;
            this.data = new int[(size + 31) / 32];
        }
        public void set(int idx, boolean value) {
            int arrIdx = idx / 32;
            int bitIdx = idx % 32;
            if (value) {
                data[arrIdx] |= (1 << bitIdx);
            } else {
                data[arrIdx] &= ~(1 << bitIdx);
            }
        }
        public boolean get(int idx) {
            int arrIdx = idx / 32;
            int bitIdx = idx % 32;
            return (data[arrIdx] & (1 << bitIdx)) != 0;
        }
        public int length() {
            return size;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long A = Long.parseLong(st.nextToken());
        long B = Long.parseLong(st.nextToken());

        int sqrtB = (int)Math.sqrt(B);
        BitArray isNotPrime = new BitArray(sqrtB + 1);

        isNotPrime.set(0, true);
        isNotPrime.set(1, true);
        for (int i = 2; i * i <= sqrtB; i++) {
            if (!isNotPrime.get(i)) {
                for (int j = i * i; j <= sqrtB; j += i) {
                    isNotPrime.set(j, true);
                }
            }
        }

        int count = 0;
        for (int i = 2; i <= sqrtB; i++) {
            if (!isNotPrime.get(i)) {
                long num = (long)i * i;
                while (num <= B) {
                    if (num >= A) count++;
                    if (num > Long.MAX_VALUE / i) break;
                    num *= i;
                }
            }
        }
        System.out.println(count);
    }
}
