import java.io.*;
import java.util.*;

public class initmumu {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        String[] rows = new String[n];
        for (int i = 0; i < n; i++) {
            rows[i] = br.readLine();
        }
        int k = Integer.parseInt(br.readLine());

        int answer = 0;
        Map<String, Integer> patternCount = new HashMap<>();

        for (String row : rows) {
            patternCount.put(row, patternCount.getOrDefault(row, 0) + 1);
        }

        for (String row : patternCount.keySet()) {
            int zeroCount = 0;
            for (int i = 0; i < m; i++) {
                if (row.charAt(i) == '0') zeroCount++;
            }
            if (zeroCount <= k && zeroCount % 2 == k % 2) {
                answer = Math.max(answer, patternCount.get(row));
            }
        }
        System.out.println(answer);
    }
}
