import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ11559_PuyoPuyo {				// 84ms
	static int result;
	static boolean boomed;
	static String[][] puyo;
	static boolean[][] visited;
	static int[][] dirs = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		puyo = new String[12][6];

		for (int i = 0; i < 12; i++) {
			puyo[i] = br.readLine().split("");
		}
		simulate();

		System.out.println(result);
	}

	public static void simulate() {
		while (hasBoom()) {
			result++;
			drop();
		}
	}

	public static boolean hasBoom() {
		boomed = false;
		visited = new boolean[12][6];
		for (int i = 0; i < 12; i++) {
			for (int j = 0; j < 6; j++) {
				if (!puyo[i][j].equals(".") && !visited[i][j]) {
					bfs(i, j);
				}
			}
		}
		return boomed;
	}

	public static void bfs(int a, int b) {
		int cnt = 0;
		Queue<int[]> q = new LinkedList<>();
		ArrayList<int[]> al = new ArrayList<>();
		q.offer(new int[] {a, b});
		visited[a][b] = true;
		while (!q.isEmpty()) {
			int[] now = q.poll();
			al.add(now);
			cnt++;
			int x = now[0];
			int y = now[1];
			String s = puyo[x][y];
			for (int[] dir : dirs) {
				int nx = x + dir[0];
				int ny = y + dir[1];
				if (nx < 0 || nx >= 12 || ny < 0 || ny >= 6 || visited[nx][ny] || !puyo[nx][ny].equals(s)) {
					continue;
				}
				visited[nx][ny] = true;
				q.offer(new int[] {nx, ny});
			}
		}
		if (cnt >= 4) {
			boomed = true;
			for (int[] del : al) {
				puyo[del[0]][del[1]] = "D";
			}
		}
	}
	public static void drop() {
		for (int i = 0; i < 6; i++) {
			for (int j = 11; j >= 0; j--) {
				if (puyo[j][i].equals("D")) {
					int idx = j;
					int len = 0;
					while (puyo[idx][i].equals("D")) {
						len++;
						if (idx == 0)
							break;
						idx--;
					}
					
					for (int k = j; k >= len; k--) {
						puyo[k][i] = puyo[k-len][i];
					}
					for (int k = 0; k < len; k++) {
						puyo[k][i] = ".";
					}
				}
			}
		}
	}
}
