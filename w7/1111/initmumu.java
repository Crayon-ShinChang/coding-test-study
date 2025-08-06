import java.io.*;
import java.util.*;

public class initmumu {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = Integer.parseInt(st.nextToken());

        if (n == 1) {
            System.out.println("A");
            return;
        }
        if (n == 2) {
            if (arr[0] == arr[1]) System.out.println(arr[0]);
            else System.out.println("A");
            return;
        }
        boolean allSame = true;
        for (int i = 1; i < n; i++) {
            if (arr[i] != arr[0]) {
                allSame = false;
                break;
            }
        }
        if (allSame) {
            System.out.println(arr[0]);
            return;
        }
        Integer a = null, b = null;
        if (arr[1] - arr[0] == 0) {
            a = 1;
            b = 0;
        } else {
            int diff1 = arr[1] - arr[0];
            int diff2 = arr[2] - arr[1];
            if ((arr[1] - arr[0]) == 0) {
                System.out.println("B");
                return;
            }
            if ((diff2 % diff1) != 0) {
                System.out.println("B");
                return;
            }
            a = diff2 / diff1;
            b = arr[1] - arr[0] * a;
        }
        boolean valid = true;
        for (int i = 1; i < n; i++) {
            if (arr[i] != arr[i - 1] * a + b) {
                valid = false;
                break;
            }
        }
        if (valid) System.out.println(arr[n - 1] * a + b);
        else System.out.println("B");
    }
}
