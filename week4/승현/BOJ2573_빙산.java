import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2573_빙산 { // 548ms
	static int year;
	static int N, M;
	static int[][] bingsan;
	static int[][] temp;
	static int bsCnt;
	static int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		bingsan = new int[N][M];
		temp = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				bingsan[i][j] = Integer.parseInt(st.nextToken());
				if (bingsan[i][j] != 0)
					bsCnt++;
			}
		}
		melt();
		System.out.println(year);
	}
	public static void melt() {
		while (isNotDivided()) {
			if (isEmpty()) {
				year = 0;
				return;
			}
			for (int i = 0; i < N; i++) {
				temp[i] = bingsan[i].clone();
			}
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (bingsan[i][j] != 0) {
						int cnt = 0;
						for (int [] dir : dirs) {
							if (temp[i+dir[0]][j+dir[1]] == 0) {
								cnt++;
							}
						}
						if (bingsan[i][j] <= cnt) {
							bingsan[i][j] = 0;
							bsCnt--;
						}
						else {
							bingsan[i][j] -= cnt;
						}
					}
				}
			}
			year++;
		}
	}
	
	public static boolean isNotDivided() {
		Queue<int[]> q = new LinkedList<>();
		boolean[][] visited = new boolean[N][M];
		
		boolean b = false;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (bingsan[i][j] != 0) {
					q.offer(new int[] {i, j});
					visited[i][j] = true;
					b = true;
					break;
				}
				if (b)
					break;
			}
		}
		int cnt = 0;
		while (!q.isEmpty()) {
			int[] now = q.poll();
			cnt++;
			int x = now[0];
			int y = now[1];
			for (int[] dir : dirs) {
				int nx = x + dir[0];
				int ny = y + dir[1];
				if (nx < 0 || nx >= N || ny < 0 || ny >= M || visited[nx][ny] || bingsan[nx][ny] == 0)
					continue;
				visited[nx][ny] = true;
				q.offer(new int[] {nx, ny});
			}
		}
		if (cnt == bsCnt)
			return true;
		else
			return false;
	}
	
	public static boolean isEmpty() {
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (bingsan[i][j] != 0)
					cnt++;
			}
		}
		if (cnt == 0)
			return true;
		else
			return false;
	}
}
