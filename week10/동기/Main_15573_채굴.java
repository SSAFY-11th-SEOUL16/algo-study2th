import java.util.*;
import java.io.*;
import java.math.*;
/*
 * 2068ms 
 */

public class Main {
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { -1, 0, 1, 0 };

	static int n, m, k;

	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		map = new int[n][m];

		for (int i = 0; i < n; i++)
			map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

		System.out.println(bs());
	}

	static int bs() {
		int start = 1;
		int end = 1000000;

		while (start < end) {
			int mid = (start + end) / 2;
			if (bfs(mid)) {
				end = mid;
			} else {
				start = mid + 1;
			}
		}
		return start;
	}

	static boolean bfs(int d) {
		int cnt = 0;
		boolean[][] visited = new boolean[n][m];
		Queue<Pos> queue = new LinkedList();
		for (int i = 0; i < n; i++) {
			if (map[i][0] <= d) {
				queue.add(new Pos(0, i));
				visited[i][0] = true;
			}
			if (map[i][m - 1] <= d) {
				queue.add(new Pos(m - 1, i));
				visited[i][m - 1] = true;
			}
		}

		for (int i = 1; i < m - 1; i++) {
			if (map[0][i] <= d) {
				queue.add(new Pos(i, 0));
				visited[0][i] = true;
			}
		}

		while (!queue.isEmpty()) {
			Pos pos = queue.poll();
			cnt++;
			for (int i = 0; i < 4; i++) {
				int x1 = pos.x + dx[i];
				int y1 = pos.y + dy[i];

				if (isNullCheck(x1, y1) && map[y1][x1] <= d && !visited[y1][x1]) {
					visited[y1][x1] = true;
					queue.add(new Pos(x1, y1));
				}
			}
		}

		return cnt >= k;
	}

	static boolean isNullCheck(int x, int y) {
		return x >= 0 && y >= 0 && x < m && y < n;
	}

	static class Pos {
		int x;
		int y;

		Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}