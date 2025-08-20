import java.util.*;

public class initmumu {

	public static void main(String[] args) throws Exception {
		int N = read(), C = read();
		int[] homes = new int[N];
		for (int i = 0; i < N; i++) homes[i] = read();
		
		Arrays.sort(homes);
		
		int start = 1, end = homes[N-1] - homes[0];
		int answer = -1;
		while (start <= end) {
			int mid = (start + end) / 2;
			int prev = 0;
			int installed = 1;
			boolean flag = false;
			for (int i = 1; i < N; i++) {
				if (homes[i] - homes[prev] < mid) continue;
				installed++;
				prev = i;
				if(installed == C) {
					flag = true;
					break;
				}
			}
			if(flag) {
				start = mid+1;
				answer = mid;
			} else {
				end = mid-1;
			}
		}
		System.out.println(answer);

	}
	
	public static int read() throws Exception {
		int c, t = 0;
		while((c=System.in.read()) > 32) t = (t<<3) + (t<<1) + (c&15);
		return t;
	}

}