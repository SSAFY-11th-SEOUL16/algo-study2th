import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ15684_사다리조작 {

	static int n, m, h;
	static int[][] graph;
	static final int DOWN = 0;
	static final int LEFT = 1;
	static final int RIGHT = 2;
	static int[] dxs = { 1, 0, 0 };
	static int[] dys = { 0, -1, 1 };
	static Node[] select;
	static List<Node> can_add = new ArrayList<>();
	static int result = -1;

	static class Node {
		int x, y;

		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return "Node [x=" + x + ", y=" + y + "]";
		}

	}

	static boolean in_range(int x, int y) {
		return 0 <= x && x <= h + 1 && 2 <= y && y <= 2 * n;
	}

	static boolean check(int[][] board) {

		for (int i = 0; i < select.length; i++) {
			board[select[i].x][select[i].y] = 1;
		}

		for (int i = 1; i <= n; i++) {
			int dir = 0;
			int start_y = i * 2;
			int start_x = 1;

			while (start_x != h + 1) {

				// 아래로 갈때는 좌우 확인
				if (dir == DOWN) {
					int nx = start_x + dxs[LEFT];
					int ny = start_y + dys[LEFT];
					if (in_range(nx, ny) && board[nx][ny] == 1)
						dir = LEFT;

					nx = start_x + dxs[RIGHT];
					ny = start_y + dys[RIGHT];
					if (in_range(nx, ny) && board[nx][ny] == 1)
						dir = RIGHT;
				}
				// 좌우로 갈때는 아래 확인
				else {
					int nx = start_x + dxs[DOWN];
					int ny = start_y + dys[DOWN];
					if (in_range(nx, ny) && board[nx][ny] == 1)
						dir = DOWN;
				}

				if (dir != DOWN) {
					start_x += 2 * dxs[dir];
					start_y += 2 * dys[dir];
				} else {
					start_x += dxs[dir];
					start_y += dys[dir];
				}
			}

			if (start_y != i * 2)
				return false;
		}

		return true;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());

		graph = new int[h + 2][2 * n + 1];

		for (int j = 0; j <= h + 1; j++) {
			for (int i = 1; i <= n; i++) {
				graph[j][i * 2] = 1;
			}
		}

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int height = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph[height][b * 2 + 1] = 1;
		}

		for (int i = 1; i <= h; i++) {
			for (int j = 3; j <= 2 * n - 1; j += 2) {
				if (graph[i][j] == 0)
					can_add.add(new Node(i, j));
			}
		}

		select = new Node[0];
		if (check(graph)) result = 0;

		select = new Node[1];
		if (result == -1) {
			comb1(0, 0);
		}

		select = new Node[2];
		if (result == -1) {
			comb2(0, 0);
		}

		select = new Node[3];
		if (result == -1) {
			comb3(0, 0);
		}

		
		System.out.println(result);
	}

	static void comb1(int depth, int start) {

		if (result != -1)
			return;

		if (depth == 1) {
			int[][] board = new int[h + 2][2 * n + 1];
			for (int i = 0; i < h + 2; i++) {
				board[i] = graph[i].clone();
			}

			if (check(board))
				result = 1;
			return;
		}

		for (int i = start; i < can_add.size(); i++) {
			select[depth] = can_add.get(i);
			comb1(depth + 1, i + 1);
		}
	}
	
	static void comb2(int depth, int start) {

		if (result != -1)
			return;

		if (depth == 2) {
			int[][] board = new int[h + 2][2 * n + 1];
			for (int i = 0; i < h + 2; i++) {
				board[i] = graph[i].clone();
			}

			if (check(board))
				result = 2;
			return;
		}

		for (int i = start; i < can_add.size(); i++) {
			select[depth] = can_add.get(i);
			comb2(depth + 1, i + 1);
		}
	}
	
	static void comb3(int depth, int start) {

		if (result != -1)
			return;

		if (depth == 3) {
			int[][] board = new int[h + 2][2 * n + 1];
			for (int i = 0; i < h + 2; i++) {
				board[i] = graph[i].clone();
			}

			if (check(board))
				result = 3;
			return;
		}

		for (int i = start; i < can_add.size(); i++) {
			select[depth] = can_add.get(i);
			comb3(depth + 1, i + 1);
		}
	}

}
