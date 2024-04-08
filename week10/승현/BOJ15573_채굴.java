import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ15573_채굴 {
	static int N, M, K;
	static int[][] mineral;
	static int[][] temp;
	static boolean[][] visited;
	static int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		mineral = new int[N+1][M+2];

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= M; j++) {
				mineral[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int left = 0;
		int right = 1000001;
		while (left < right) {
			int mid = (left + right) / 2;
			if (simulate(mid)) {
				right = mid;
			}
			else {
				left = mid + 1;
			}
		}
		System.out.println(left);
	}
	
	public static boolean simulate(int D) {
		int broken = 0;
		temp = new int[N+1][M+2];
		for (int i = 0; i < N+1; i++) {
			temp[i] = Arrays.copyOf(mineral[i], M+2);
		}
		visited = new boolean[N+1][M+2];
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {0, 0});
		visited[0][0] = true;
		
		while (!q.isEmpty()) {
			int[] now = q.poll();
			for (int[] dir : dirs) {
				int nx = now[0] + dir[0];
				int ny = now[1] + dir[1];
				if (nx < 0 || ny < 0 || nx >= N+1 || ny >= M+2 || visited[nx][ny] || temp[nx][ny] > D) {
					continue;
				}
				if (temp[nx][ny] > 0) {
					temp[nx][ny] = 0;
					broken++;
				}
				visited[nx][ny] = true;
				q.offer(new int[] {nx, ny});
			}
		}
		
		if (broken >= K) {
			return true;
		}
		else {
			return false;
		}
	}

}
