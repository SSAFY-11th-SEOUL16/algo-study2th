import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2573_빙산 {

	static int n, m;
	static int[][] graph;
	static boolean[][] visited;
	static int result;

	static int[] dxs = { -1, 1, 0, 0 };
	static int[] dys = { 0, 0, -1, 1 };
	static List<Point> ice = new ArrayList<>();

	static class Point {
		int x, y, cnt;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public Point(int x, int y, int cnt) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}

	}

	static void bfs(int x, int y) {
		Queue<Point> q = new ArrayDeque<>();
		visited[x][y] = true;
		q.add(new Point(x, y));

		while (!q.isEmpty()) {
			Point poll = q.poll();

			for (int i = 0; i < 4; i++) {
				int nx = poll.x + dxs[i];
				int ny = poll.y + dys[i];
				if (!visited[nx][ny] && graph[nx][ny] != 0) {
					visited[nx][ny] = true;
					q.add(new Point(nx, ny));
				}
			}
		}

	}

	static void discount() {

		for (Point p : ice) {
			p.cnt = 0;

			int cnt = 0;
			for (int i = 0; i < 4; i++) {
				int nx = p.x + dxs[i];
				int ny = p.y + dys[i];
				if (graph[nx][ny] == 0)
					cnt++;
			}
			p.cnt = cnt;
		}

		for (int i = ice.size() - 1; i >= 0; i--) {
			Point p = ice.get(i);
			graph[p.x][p.y] = graph[p.x][p.y] - p.cnt > 0 ? graph[p.x][p.y] - p.cnt : 0;
			if (graph[p.x][p.y] == 0)
				ice.remove(i);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		graph = new int[n][m];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
				if (graph[i][j] != 0)
					ice.add(new Point(i, j));
			}
		}

		int nums = 1;
		int day = 0;

		while (nums == 1) {

			discount();

			nums = 0;
			visited = new boolean[n][m];
			for (int i = 1; i < n - 1; i++) {
				for (int j = 1; j < m - 1; j++) {
					if (graph[i][j] != 0 && !visited[i][j]) {
						nums++;
						bfs(i, j);
					}
				}
			}

			day += 1;
			if (nums == 0) {
				day = 0;
			}

		}

		System.out.println(day);

	}

}
