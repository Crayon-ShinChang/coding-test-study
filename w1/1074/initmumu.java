import java.io.*;
import java.util.*;

public class Main {
    static int R, C;
    static int cnt = 0;
    
    public static void dc(int size, int row, int col) {
        if (size == 1) return;
        
        int newSize = size / 2;
        
        // 1사분면
        if (R < row + newSize && C < col + newSize) {
            dc(newSize, row, col);
        }
        // 2사분면
        else if (R < row + newSize && C >= col + newSize) {
            cnt += newSize * newSize;
            dc(newSize, row, col + newSize);
        }
        // 3사분면
        else if (R >= row + newSize && C < col + newSize) {
            cnt += 2 * newSize * newSize;
            dc(newSize, row + newSize, col);
        }
        // 4사분면
        else {
            cnt += 3 * newSize * newSize;
            dc(newSize, row + newSize, col + newSize);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        
        int size = (int) Math.pow(2, N);
        dc(size, 0, 0);
        System.out.println(cnt);
    }
}