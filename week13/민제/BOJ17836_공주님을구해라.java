import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ17836_공주님을구해라 {

	static int n, m, time;
	static int[][] graph;
	static boolean[][][] visited;

	static class Hero {
		int x, y, step;
		int gram;

		public Hero(int x, int y, int step, int gram) {
			this.x = x;
			this.y = y;
			this.step = step;
			this.gram = gram;
		}

		@Override
		public String toString() {
			return "Hero [x=" + x + ", y=" + y + ", step=" + step + ", gram=" + gram + "]";
		}
		
		
	}

	static boolean in_range(int x, int y) {
		return 0 <= x && x < n && 0 <= y && y < m;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		time = Integer.parseInt(st.nextToken());
		graph = new int[n][m];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		visited = new boolean[n][m][2];

		int result = bfs();
		
		if (result == -1) System.out.println("Fail");
		else System.out.println(result);
	}

	static int bfs() {

		Queue<Hero> q = new ArrayDeque<>();
		q.add(new Hero(0, 0, 0, 0));
		visited[0][0][0] = true;
		int[] dxs = { -1, 1, 0, 0 };
		int[] dys = { 0, 0, -1, 1 };

		while (!q.isEmpty()) {

			Hero poll = q.poll();

			if (poll.x == n - 1 && poll.y == m - 1) return poll.step;
			if (poll.step >= time) continue;

			for (int i = 0; i < 4; i++) {
				int nx = poll.x + dxs[i];
				int ny = poll.y + dys[i];
				
				if (!in_range(nx, ny)) continue;
				if (visited[nx][ny][poll.gram]) continue;

				visited[nx][ny][poll.gram] = true;
				// 검 있을때
				if (poll.gram == 1) {
					q.add(new Hero(nx, ny, poll.step + 1, poll.gram));
				}
				// 검 없을때
				else {
					if (graph[nx][ny] == 2) {
						q.add(new Hero(nx, ny, poll.step + 1, 1));
					} else if (graph[nx][ny] == 0) {
						q.add(new Hero(nx, ny, poll.step+1, poll.gram));
					}
				}

			}

		}

		return -1;
	}

}
