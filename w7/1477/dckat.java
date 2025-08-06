import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M, L;
    static ArrayList<Integer> arr = new ArrayList<>();

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr.add(Integer.parseInt(st.nextToken()));
        }
    }

    static int solution() {
        arr.add(0);
        arr.add(L);
        Collections.sort(arr);

        int start = 1;
        int end = L;
        while (start <= end) {
            int mid = (start+end) / 2;      // 휴게소를 추가로 설치할 간격

            // 사이마다 설치가 가능한 휴게소의 갯수 확인
            int count = 0;
            for (int i = 1; i < arr.size(); i++) {
                count += (arr.get(i) - arr.get(i-1) - 1) / mid;
            }

            // M개 보다 더 많이 설치하는 경우 (간격을 늘리기 위해 start 변경)
            if (count > M) {
                start = mid + 1;
            }
            // 그 이하인 경우 (간격을 좁히기 위해 end 변경)
            else {
                end = mid - 1;
            }
        }

        return start;
    }

    public static void main(String[] args) throws Exception {
        input();
        int answer = solution();
        System.out.println(answer);
    }
}