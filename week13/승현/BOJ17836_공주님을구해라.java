import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ17836_공주님을구해라 {
	static int n, m, t;
	static int result;
	static String[][] board;
	static int[][] dirs = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
	static boolean[][] visited;

	static class Info implements Comparable<Info>{
		int x;
		int y;
		int d;

		Info(int x, int y, int d) {
			this.x = x;
			this.y = y;
			this.d = d;
		}

		@Override
		public String toString() {
			return "Info [x=" + x + ", y=" + y + ", d=" + d + "]";
		}

		@Override
		public int compareTo(Info o) {
			return this.d - o.d;
		}
		
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		t = Integer.parseInt(st.nextToken());
		board = new String[n][m];
		visited = new boolean[n][m];

		for (int i = 0; i < n; i++) {
			board[i] = br.readLine().split(" ");
		}

		result = Integer.MAX_VALUE;
		bfs();

		if (result == Integer.MAX_VALUE) {
			System.out.println("Fail");
		} else {
			System.out.println(result);
		}
	}

	public static void bfs() {
		Queue<Info> q = new LinkedList<>();
		q.offer(new Info(0, 0, 0));
		visited[0][0] = true;

		while (!q.isEmpty()) {
			Info now = q.poll();
			for (int[] dir : dirs) {
				int nx = now.x + dir[0];
				int ny = now.y + dir[1];
				
				if (nx < 0 || ny < 0 || nx >= n || ny >= m || visited[nx][ny] || board[nx][ny].equals("1") || now.d + 1 > t) {
					continue;
				}
				
				// 그람 찾으면 벽 무시하고 최단거리로 갈 수 있으니까 바로 update
				if (board[nx][ny].equals("2")) {
					int gpd = now.d + (n - nx) + (m - ny) - 1;
					if (gpd <= t && gpd < result) {
						result = gpd;
					}
				}

				if (nx == n - 1 && ny == m - 1 && now.d + 1 < result) {
					result = now.d + 1;
				}

				visited[nx][ny] = true;
				q.offer(new Info(nx, ny, now.d + 1));
			}
		}

	}
}
