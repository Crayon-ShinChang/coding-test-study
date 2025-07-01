import java.io.*;
import java.util.*;

public class Main{
	
	static int N, cnt;
	static boolean flag;
	static ArrayList<Long> store = new ArrayList<>();
	static long[] digits = new long[]{0, 1, 10, 100, 1000, 10000, 100000, 1000000, 10000000, 100000000, 1000000000};
	public static void dfs(int d, int n, long makingNum) {
		if (flag) return;
		
		makingNum += digits[d] * n;
		if (d == 1) {
			++cnt;
			store.add(makingNum);
			if(cnt == N) flag = true;
			return;
		}
		
		for (int i = 0; i < 10; i++) {
			if (n > i) dfs(d-1, i, makingNum);
		}
	}
	
	public static void main(String[] args) throws Exception {
		N = readInt();
		
		for(int i = 1; i < 11; i++) {
			for (int j = 0; j < 10; j++) {
				dfs(i, j, 0);
				if (flag) break;
			}
			if (flag) break;
		}

		if (!flag) System.out.println(-1);
		else System.out.println(store.get(N-1));

	}
	
    public static int readInt() throws Exception {
        int c, t = 0;
        while((c=System.in.read()) > 47) t = (t << 3) + (t << 1) + (c & 15);
        return t;
    }
}