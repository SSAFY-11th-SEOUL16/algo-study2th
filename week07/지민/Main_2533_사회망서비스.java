package ps;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 풀이 참고했습니다.
 * 시간: 2180ms
 */
public class Main_2533_사회망서비스 {
	static int N;
	static List<List<Integer>> trees;
	static int[][] dp;
	static boolean[] visited;
	
	public static void dfs(int root) {
		visited[root] = true;
		dp[root][0] = 0;
		dp[root][1] = 1;
		
		
		for(int child : trees.get(root)) {
			if(visited[child]) continue;
			dfs(child);
			dp[root][0] += dp[child][1];
			dp[root][1] += Math.min(dp[child][0], dp[child][1]);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		
		trees = new ArrayList<>();
		for(int i = 0; i <= N; i++) trees.add(new ArrayList<>());
		
		visited = new boolean[N + 1];
		dp = new int[N + 1][2];
		for(int i = 0; i <= N; i++) dp[i][0] = dp[i][1] = -1;
		for(int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			trees.get(u).add(v);
			trees.get(v).add(u);
		}
		
		dfs(1);
		System.out.println(Math.min(dp[1][0], dp[1][1]));

	}
}
