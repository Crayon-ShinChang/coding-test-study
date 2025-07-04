import java.util.*;
import java.io.*;

public class Main {
	static int N, M;
	static ArrayList<Edge>[] adj;
	static StringBuilder sb = new StringBuilder();
	static HashSet<Integer> s;
	
	public static void solve(int S, int E) {
		s = new HashSet<>();
		ArrayDeque<int[]> q = new ArrayDeque<>();
		q.add(new int[] {S, 0});
		s.add(S);
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			for (int i = 0; i < adj[cur[0]].size(); i++) {
				Edge e = adj[cur[0]].get(i);
				
				if (e.end == E) {
					sb.append(cur[1] + e.dist).append("\n");
					return;
				}
				
				if (!s.contains(e.end)) {
					q.add(new int[] {e.end, cur[1] + e.dist});
					s.add(e.end);
				}
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		N = read();
		M = read();
		adj = new ArrayList[N+1];
		for (int i = 0; i < N+1; i++) {
			adj[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < N-1; i++) {
			int s = read();
			int e = read();
			int d = read();
			
			adj[s].add(new Edge(e, d));
			adj[e].add(new Edge(s, d));
			
		}
		
		for (int i = 0; i < M; i++) {
			solve(read(), read());
		}
		
		System.out.println(sb);
		
	}
	
	public static int read() throws Exception {
		int c, t = 0;
		while((c=System.in.read()) > 32) t = (t << 3) + (t << 1) + (c & 15);
		return t;
	}
	
	public static class Edge {
		public int end;
		public int dist;
		
		public Edge(int e, int d) {
			end = e;
			dist = d;
		}
	}
}