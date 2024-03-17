// 2160ms

import java.io.*;
import java.util.*;

public class BOJ2533_사회망서비스SNS {
	
	static ArrayList<Integer>[] tree;
	static int N;
	static int[] dp0, dp1;
	static boolean[] visited;
	static void postorder(int idx) {
		int zero = 0;
		int one = 0;
		visited[idx] = true;
		for(int next: tree[idx]) {
			if(!visited[next]) {
				postorder(next);
				zero += dp1[next];
				one += Math.min(dp0[next], dp1[next]);
			}
		}
		dp0[idx] = zero;
		dp1[idx] = one + 1;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(input.readLine());
		tree = new ArrayList[N + 1];
		Arrays.setAll(tree, node -> new ArrayList<>());
		dp0 = new int[N + 1];
		dp1 = new int[N + 1];
		visited = new boolean[N + 1];
		for(int i = 1; i < N; i++) {
			StringTokenizer tokens = new StringTokenizer(input.readLine());
			int u = Integer.parseInt(tokens.nextToken());
			int v = Integer.parseInt(tokens.nextToken());
			tree[u].add(v);
			tree[v].add(u);
		}
		postorder(1);
		System.out.println(Math.min(dp0[1], dp1[1]));
	}
}
