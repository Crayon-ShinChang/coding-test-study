import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        int[] ships = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            ships[i] = Integer.parseInt(st.nextToken());
        }
        
        int M = Integer.parseInt(br.readLine());
        int[] boxes = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            boxes[i] = Integer.parseInt(st.nextToken());
        }
        
        Arrays.sort(ships);
        Arrays.sort(boxes);
        
        int ship_num = 0;
        int box_num = 0;
        int ans = 0;
        
        while (ship_num < N) {
            int remaining_boxes = M - box_num;
            int remaining_ships = N - ship_num;
            
            int per_ship = (remaining_boxes + remaining_ships - 1) / remaining_ships;
            
            int now = 0;
            while (box_num < M && now < per_ship && boxes[box_num] <= ships[ship_num]) {
                now++;
                box_num++;
            }
            
            ans = Math.max(ans, now);
            ship_num++;
        }
        
        if (box_num != M) {
            System.out.println(-1);
        } else {
            System.out.println(ans);
        }
    }
}
