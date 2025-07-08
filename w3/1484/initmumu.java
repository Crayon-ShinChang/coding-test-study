

public class initmumu {

	public static void main(String[] args) throws Exception {
		
		int G = read();
		int C = 2, M = 1, t = 3;
		StringBuilder sb = new StringBuilder();
		while (C > M) {
			if (t < G) {
				C++; 
			} else if (t == G) {
                sb.append(C++).append("\n");
                M++;
            } else {
                M++;
            }
            t = C * C - M * M;
		}
		
		if(sb.length() > 0) System.out.println(sb);
		else System.out.println(-1);
		
	}

    public static int read() throws Exception {
        int c, t = 0;
        while ((c = System.in.read()) > 32) t = (t << 3) + (t << 1) + (c & 15);
        return t;
    }

}