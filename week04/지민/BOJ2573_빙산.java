import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 시간: 532ms
 */
public class BOJ2573_빙산 {
	static class Node {
		int y, x;
		int h;
		public Node(int y, int x, int n) {
			this.y = y;
			this.x = x;
			this.h = n;
		}
	}
	static int N;
	static int M;
	static int[][] map;
	static Queue<Node> ice;
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, 1, -1};

	private static void dfs(int y, int x, boolean[][] visit) {
		visit[y][x] = true;
		for(int i = 0; i < 4; i++) {
			int ny = y + dy[i];
			int nx = x + dx[i];
			if(ny < 0 || ny > N || nx < 0 || nx > M) continue;
			if(map[ny][nx] != 0 && !visit[ny][nx]) dfs(ny, nx, visit);
		}
	}

	private static boolean separated() { // 분리 여부
		boolean[][] visit = new boolean[N][M];
		dfs(ice.peek().y, ice.peek().x, visit);

		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(map[i][j] != 0 && !visit[i][j]) return true;
			}
		}
		return false;
	}

	private static int countZero(Node node) {
		int cnt = 0;
		for(int i = 0; i < 4; i++) {
			int ny = node.y + dy[i];
			int nx = node.x + dx[i];
			if(ny < 0 || ny >= N || nx < 0 || nx >= M) continue;
			if(map[ny][nx] == 0) cnt += 1;
		}
		return cnt;
	}

	private static int solve() {
		int time = 0;
		Queue<Node> zero = new LinkedList<>();
		while(!separated()) {
			int size = ice.size();
			for(int i = 0; i < size; i++) {
				Node node = ice.poll();
				node.h -= countZero(node);
				if(node.h <= 0) zero.add(node);
				else ice.add(node);
			}
			while(!zero.isEmpty()) {
				Node node = zero.poll();
				map[node.y][node.x] = 0;
			}
			time += 1;
			if(ice.size() == 0) return 0; // 다 녹음
		}

		return time;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		ice = new LinkedList<>();
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] > 0) ice.offer(new Node(i, j, map[i][j]));
			}
		}
		System.out.println(solve());
	}
}
