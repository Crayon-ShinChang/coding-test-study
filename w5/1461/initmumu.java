import java.io.*;
import java.util.*;

public class initmumu {
    public static void main(String[] args) throws Exception {
        int N = read();
        int M = read();
        
        ArrayList<Integer> plus = new ArrayList<>();
        ArrayList<Integer> minus = new ArrayList<>();
        
        for (int i = 0; i < N; i++) {
            int num = read();
            if (num > 0) plus.add(num);
            else minus.add(Math.abs(num));
        }
        
        Collections.sort(plus, Collections.reverseOrder());
        Collections.sort(minus, Collections.reverseOrder());
        
        int answer = 0;
        int max = 0;
        if (!plus.isEmpty()) max = Math.max(max, plus.get(0));
        if (!minus.isEmpty()) max = Math.max(max, minus.get(0));
        
        answer += max;

        for (int i = 0; i < plus.size(); i += M) {
            if (plus.get(i) != max) answer += plus.get(i) * 2;
        }

        for (int i = 0; i < minus.size(); i += M) {
            if (minus.get(i) != max) answer += minus.get(i) * 2;
        }
        
        System.out.println(answer);
    }

    public static int read() throws Exception {
        int c, t = 0;
        boolean neg = false;
        while ((c = System.in.read()) <= 32);
        if (c == '-') {
            neg = true;
            c = System.in.read();
        }
        do {
            t = (t << 3) + (t << 1) + (c & 15);
        } while ((c = System.in.read()) > 32);
        return neg ? -t : t;
    }
}
