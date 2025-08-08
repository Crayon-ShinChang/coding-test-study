import java.io.*;
import java.util.*;

public class initmumu {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int s = Integer.parseInt(br.readLine());

        for (int i = 0; i < n && s > 0; i++) {
            int maxIdx = i;
            for (int j = i + 1; j < n && j <= i + s; j++) {
                if (arr[j] > arr[maxIdx]) {
                    maxIdx = j;
                }
            }
            for (int j = maxIdx; j > i; j--) {
                int temp = arr[j];
                arr[j] = arr[j - 1];
                arr[j - 1] = temp;
            }
            s -= (maxIdx - i);
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) sb.append(arr[i]).append(' ');
        System.out.println(sb);
    }
}
