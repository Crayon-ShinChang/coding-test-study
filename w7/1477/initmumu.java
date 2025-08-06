import java.io.*;
import java.util.*;

public class initmumu {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        List<Integer> list = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            list.add(Integer.parseInt(st.nextToken()));
        }
        list.add(0);
        list.add(L);
        Collections.sort(list);

        int left = 1, right = L - 1, answer = 0;

        while (left <= right) {
            int mid = (left + right) / 2;
            int count = 0;
            for (int i = 1; i < list.size(); i++) {
                int dist = list.get(i) - list.get(i - 1);
                count += (dist - 1) / mid;
            }
            if (count > M) {
                left = mid + 1;
            } else {
                answer = mid;
                right = mid - 1;
            }
        }
        System.out.println(answer);
    }
}
