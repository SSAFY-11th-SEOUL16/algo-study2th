import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class BOJ11559_PuyoPuyo {

	static char[][] graph = new char[12][6];
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
		return 0 <= x && x < 12 && 0 <= y && y < 6;
	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int i = 0; i < 12; i++) {
			String input = br.readLine();
			for (int j = 0; j < 6; j++) {
				graph[i][j] = input.charAt(j);
			}
		}

		int cnt = 0;
		
		while(true)  {
			gravity();
			List<List<Pos>> canBomb = canBomb();
			if (canBomb.isEmpty()) break;
			bomb(canBomb);
			cnt++;
		}
		
		System.out.println(cnt);

	}
	
	static void bomb(List<List<Pos>> bomblist) {
		for (List<Pos> list : bomblist) {
			for (Pos pos : list) {
				graph[pos.x][pos.y] = '.';
			}
		}
	}

	static List<Pos> bfs(int x, int y, char color) {

		Pos start = new Pos(x, y);
		Queue<Pos> q = new ArrayDeque<>();
		q.add(start);
		visited[x][y] = true;
		int cnt = 0;
		List<Pos> bomb = new ArrayList<>();

		while (!q.isEmpty()) {
			Pos poll = q.poll();
			cnt++;
			bomb.add(poll);

			for (int i = 0; i < 4; i++) {
				int nx = poll.x + dxs[i];
				int ny = poll.y + dys[i];

				if (in_range(nx, ny) && !visited[nx][ny] && graph[nx][ny] == color) {
					visited[nx][ny] = true;
					q.add(new Pos(nx, ny));
				}
			}
		}

		if (cnt >= 4) {
			for (Pos pos : bomb) {
				graph[pos.x][pos.y] = '.';
			}
		}
		
		return bomb;
	}
	
	static List<List<Pos>> canBomb() {
		
		visited = new boolean[12][6];
		
		List<List<Pos>> bomblist = new ArrayList<>();
		
		for(int i=0;i<12; i++) {
			for(int j=0; j<6; j++) {
				if (!visited[i][j] && graph[i][j] != '.') {
					List<Pos> bombs = bfs(i,j,graph[i][j]);
					if (bombs.size() >= 4) bomblist.add(bombs);
				}
			}
		}
		
		return bomblist;
	}

	static void gravity() {
		for (int i = 10; i >= 0; i--) {
			for (int j = 0; j < 6; j++) {
				if (graph[i][j] != '.') {
					char val = graph[i][j];
					Pos now = new Pos(i, j);

					while (true) {
						int nx = now.x + 1;
						int ny = now.y;
						if (in_range(nx, ny) && graph[nx][ny] == '.') {
							graph[nx][ny] = val;
							graph[now.x][now.y] = '.';
							now.x = nx;
						} else {
							break;
						}

					}

				}
			}
		}
	}

}
