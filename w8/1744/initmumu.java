import java.util.*;

public class initmumu {
    
    public static void main(String[] args) throws Exception {
        int N = read();
        PriorityQueue<Integer> nQ = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> pQ = new PriorityQueue<>(Collections.reverseOrder());
        boolean hasZero = false;
        int answer = 0;

        for (int i = 0; i < N; i++) {
            int num = read();
            if (num < 0) nQ.add(-1 * num);
            else if (num > 1) pQ.add(num);
            else if (num == 1) answer++;
            else hasZero = true;
        }

        while (!nQ.isEmpty()) {
            if (nQ.size() == 1) {
                if (!hasZero) answer -= nQ.poll();
            }
            else {
                int num1 = nQ.poll();
                int num2 = nQ.poll();

                answer += num1 * num2;
            }
        }

        while (!pQ.isEmpty()) {
            if (pQ.size() == 1) answer += pQ.poll();
            else {
                int num1 = pQ.poll();
                int num2 = pQ.poll();

                answer += num1 * num2;
            }
        }

        System.out.println(answer);
    }

    public static int read() throws Exception {
        int c, t = 0;
        boolean neg = false;
        while ((c = System.in.read()) != -1 && c <= 32);
        if (c == -1) return -1;
        if (c == 45) { neg = true; c = System.in.read(); }
        do {t = t*10 + (c & 15);}
        while ((c = System.in.read()) > 32);
        return neg ? -t : t;
    }
}