import java.util.*;
import java.io.*;

public class Main {
	static ArrayList<Integer>[] tree;
	static int delete;
	static int cnt;

	public static void main(String[] args) throws IOException {
		int N = readInt();
		tree = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			tree[i] = new ArrayList<>();
		}
		
		int root = -1;
		for (int i = 0; i < N; i++) {
			int p = readInt();
			if (p == -1) {
				root = i;
			} else {
				tree[p].add(i);
			}
		}
		
		delete = readInt();
		
		if (delete == root) {
			System.out.println(0);
		} else {
			countLeaves(root);
			System.out.println(cnt);
		}
	}
	
    static void countLeaves(int currentNode) {
        if (currentNode == delete) {
            return;
        }

        if (tree[currentNode].isEmpty()) {
            cnt++;
            return;
        }

        boolean isLeaf = true;
        for (int child : tree[currentNode]) {
            if (child != delete) {
                isLeaf = false;
                countLeaves(child);
            }
        }

        if (isLeaf) {
            cnt++;
        }
    }
	
	
	static int readInt() throws IOException {
		int c, t = 0, f = 1;
		c = System.in.read();
		if (c == '-') f = -1;
		else t = c&15;
		while((c=System.in.read()) > 32) t = (t<<3) + (t<<1) + (c&15);
		return t * f;
	}

}