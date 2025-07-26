public class initmumu {

    public static void main(String[] args) throws Exception {
        long N = read();
        long k = read();

        long digit = 1;
        long count = 9;
        long totalLength = 0;

        while (k > digit * count) {
            k -= digit * count;
            totalLength += digit * count;
            digit++;
            count *= 10;
        }

        long numberStart = (long) Math.pow(10, digit - 1);
        long numberIndex = (k - 1) / digit;
        long number = numberStart + numberIndex;

        if (number > N) {
            System.out.println(-1);
            return;
        }

        String numStr = Long.toString(number);
        System.out.println(numStr.charAt((int) ((k - 1) % digit)));
    }

    public static int read() throws Exception {
        int c, t = 0;
        while ((c = System.in.read()) > 32) t = (t << 3) + (t << 1) + (c & 15);
        return t;
    }
}
