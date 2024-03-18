import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ2533_사회망서비스 {

	static int n;
	static List<Integer>[] graph;
	static boolean[] visited;
	static boolean[] roots;
	static int[][] dp;
	static int result = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		dp = new int[n + 1][2];
		graph = new List[n + 1];
		visited = new boolean[n+1];

		for (int i = 0; i < n + 1; i++) {
			graph[i] = new ArrayList<>();
		}

		for (int i = 0; i < n - 1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			graph[u].add(v);
			graph[v].add(u);
		}

		dfs(1);

		System.out.println( Math.min(dp[1][0], dp[1][1]) );
	}
	
	static void dfs(int x) {

		visited[x] = true;
		dp[x][0] = 1;
		for (Integer next : graph[x]) {
			if (!visited[next]) {
				dfs(next);
				dp[x][0] += Math.min(dp[next][0], dp[next][1]);
				dp[x][1] += dp[next][0];
			}
		}

	}

}
