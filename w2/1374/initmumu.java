import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception {
		N = read();
		
		ArrayList<Class> classes = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			classes.add(new Class(read(), read(), read()));
		}
		classes.sort(null);
		
//		System.out.println(classes);
		
		PriorityQueue<Class> pq = new PriorityQueue<>(new Comparator<Class>() {

			@Override
			public int compare(Main.Class o1, Main.Class o2) {
				return o1.end - o2.end;
			}
			
		});
		
		for (int i = 0; i < N; i++) {
			Class c = classes.get(i);
			
			if (pq.isEmpty() || pq.peek().end > c.start) {
				pq.add(c);
			} else {
				pq.poll();
				pq.add(c);
			}
		}
		
		System.out.println(pq.size());
	}
	
	public static int read() throws Exception {
		int c, t = 0;
		while((c=System.in.read()) > 32) t = (t << 3) + (t << 1) + (c & 15);
		return t;
	}
	
	public static class Class implements Comparable<Class>{
		public int num;
		public int start;
		public int end;
		
		public Class(int n, int s, int e) {
			num = n;
			start = s;
			end = e;
		}
		
		public String toString() {
			return "num: " + num + ", start= " + start + ", end= " + end + "\n";
		}


		@Override
		public int compareTo(Main.Class o) {
			if (this.start != o.start)
				return this.start - o.start;
			else
				return this.end - o.end;
		}
	}
}