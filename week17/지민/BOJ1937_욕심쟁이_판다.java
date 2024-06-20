package BOJ.Etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1937_욕심쟁이_판다 {
	static int n;
	static int[][] map;
	static int[][] dp;
	static final int[][] dir = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

	private static boolean inRange(int y, int x) {
		return 0 <= y && y < n && 0 <= x && x < n;
	}

	private static int dfs(int y, int x) {
		if(dp[y][x] != 0) return dp[y][x];

		for (int i = 0; i < dir.length; i++) {
			int ny = y + dir[i][0];
			int nx = x + dir[i][1];
			if (!inRange(ny, nx)) continue;
			if (map[y][x] < map[ny][nx]) {
				dp[y][x] = Math.max(dp[y][x], dfs(ny, nx)) + 1;
			}
		}

		return dp[y][x] = (dp[y][x] == 0) ? 1 : dp[y][x];
	}

	private static int findMaxPath() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				dfs(i, j);
			}
		}

		return Arrays.stream(dp)
			.flatMapToInt(Arrays::stream)
			.max()
			.getAsInt();
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		map = new int[n][n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		dp = new int[n][n];
		System.out.println(findMaxPath());
	}
}
