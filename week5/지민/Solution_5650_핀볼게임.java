import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * 시간: 681ms
 */
public class Solution_5650_핀볼게임 {
	static class Ball {
		int y, x;
		int dir;
		public Ball() {
			
		}
		public Ball(int y, int x, int dir) {
			this.y = y;
			this.x = x;
			this.dir = dir;
		}
	}
	static int N;
	static int[][] map;
	static final int[] dy = {-1, 0, 0, 1}; // 상, 좌, 우, 하
	static final int[] dx = {0, -1, 1, 0};
	static HashMap<Integer, Integer> holes;
	static Ball start;
	static boolean[][][] visited;
	
	private static boolean isHole(int y, int x) {
		return 6 <= map[y][x] && map[y][x] <= 10;
	}
	
	private static boolean isBlock(int y, int x) {
		return 1 <= map[y][x] && map[y][x] <= 5;
	}
	
	private static boolean inRange(int y, int x) {
		return 0 <= y && y < N && 0 <= x && x < N;
	}

	private static int changeDirection(int dir, int block) {
		if(block == 1) {	// 직각
			if(dir == 3) return 2;
			else if(dir == 1) return 0;
		}
		else if(block == 2) {
			if(dir == 0) return 2;
			else if(dir == 1) return 3;
		}
		else if(block == 3) {
			if(dir == 2) return 3;
			else if(dir == 0) return 1;
		}
		else if(block == 4) {
			if(dir == 2) return 0;
			else if(dir == 3) return 1;
		}
		return -1; // 반대방향
	}
	
	private static int startGame() {
		Ball ball = new Ball(start.y, start.x, start.dir);
		int score = 0;
		while(true) {
			ball.y += dy[ball.dir];
			ball.x += dx[ball.dir];
			
			if(!inRange(ball.y, ball.x)) return score * 2 + 1; // 벽 종료
			if(map[ball.y][ball.x] == -1) return score; // 블랙홀 종료
			if(ball.y == start.y && ball.x == start.x) {
				visited[start.y][start.x][start.dir] = false;
				return score; // 처음 시작 위치 종료
			}
			
			if(isHole(ball.y, ball.x)) { // 웜홀
				int pair = holes.get(ball.y * N + ball.x);
				ball.y = pair / N;
				ball.x = pair % N;
			}
			else if(isBlock(ball.y, ball.x)) { // 블럭
				ball.dir = changeDirection(ball.dir, map[ball.y][ball.x]);
				if(ball.dir == -1) return score * 2 + 1; // 수평, 수직면으로 종료
				score += 1;
			}
		}
	}
	
	private static boolean prevSame(int y, int x, int d) {
		int pY = y + dy[3 - d];
		int pX = x + dx[3 - d];
		if(!inRange(pY, pX)) return false;
		return visited[pY][pX][3 - d];
	}
	
	private static int solve() {
		visited = new boolean[N][N][4];
		int maxScore = 0;
		start = new Ball();
		for(int i = 0; i < N; i++) {
			start.y = i;
			for(int j = 0; j < N; j++) {
				start.x = j;
				if(map[i][j] != 0) continue;
				for(int dir = 0; dir < 4; dir++) {
					start.dir = dir;
					if(prevSame(i, j, dir)) visited[i][j][dir] = true;
					else maxScore = Math.max(maxScore, startGame());
				}
			}
		}
		return maxScore;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		
		StringBuilder sb = new StringBuilder();
		for(int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			holes = new HashMap<>();
			int[] holeIndex = new int[5];
			Arrays.fill(holeIndex, -1);
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(isHole(i, j)) {
						int index = i * N + j;
						if(holeIndex[map[i][j] - 6] == -1) { // 처음 찾음
							holeIndex[map[i][j] - 6] = index;
						}
						else { // 쌍 찾음
							holes.put(holeIndex[map[i][j] - 6], index);
							holes.put(index, holeIndex[map[i][j] - 6]);
							holeIndex[map[i][j] - 6] = -1;
						}
					}
				}
			}
			sb.append("#").append(t).append(" ").append(solve()).append("\n");
		}
		System.out.println(sb);

	}
}
