import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ15573_채굴 {

	static int n, m, k;
	static int[][] graph;
	static boolean[][] visited;
	static int[] dxs = { -1, 1, 0, 0 };
	static int[] dys = { 0, 0, -1, 1 };

	static class Pos {
		int x, y;

		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static boolean in_range(int x, int y) {
		return 0 <= x && x < n + 2 && 0 <= y && y < m + 2;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		int maxD = 0;
		graph = new int[n + 2][m + 2];
		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= m; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
				maxD = Math.max(graph[i][j], maxD);
			}
		}

		System.out.println(binary(1, maxD));
	}

	static int binary(int min, int max) {
		int left = min;
		int right = max;

		while (left < right) {
			int mid = (left + right) / 2;
			if (valid(mid))
				right = mid;
			else
				left = mid + 1;
		}
		return left;
	}

	static boolean valid(int D) {
		visited = new boolean[n + 2][m + 2];
		Arrays.fill(visited[n + 1], true);
		int cnt = bfs(D) - (m + 2 + n + n);

		if (cnt >= k)
			return true;
		else
			return false;
	}

	static int bfs(int D) {

		Queue<Pos> q = new ArrayDeque<>();
		q.add(new Pos(0, 0));
		int cnt = 1;
		visited[0][0] = true;

		while (!q.isEmpty()) {
			Pos poll = q.poll();
			for (int i = 0; i < 4; i++) {
				int nx = poll.x + dxs[i];
				int ny = poll.y + dys[i];
				if (in_range(nx, ny) && !visited[nx][ny] && graph[nx][ny] <= D) {
					visited[nx][ny] = true;
					cnt++;
					q.add(new Pos(nx, ny));
				}
			}
		}

		return cnt;
	}

}
