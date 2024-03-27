import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ20056_마법사상어와파이어볼 {

	static int N, M, K;
	static Queue<Fireball> fireballs = new ArrayDeque<>();

	static int[] dxs = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[] dys = { 0, 1, 1, 1, 0, -1, -1, -1 };

	static class Fireball {
		int x, y;
		int m;
		int s;
		int d;

		public Fireball(int x, int y, int m, int s, int d) {
			this.x = x;
			this.y = y;
			this.m = m; // 무게
			this.s = s; // 속도
			this.d = d; // 방향
		}

		@Override
		public String toString() {
			return "Fireball [x=" + x + ", y=" + y + ", m=" + m + ", s=" + s + ", d=" + d + "]";
		}

	}

	static void move() {

		int size = fireballs.size();

		while (size-- > 0) {
			Fireball fireball = fireballs.poll();

			int nx = ((fireball.x + fireball.s * dxs[fireball.d]) + 1000 * N) % N;
			int ny = ((fireball.y + fireball.s * dys[fireball.d]) + 1000 * N) % N;

			fireballs.add(new Fireball(nx, ny, fireball.m, fireball.s, fireball.d));
		}

	}

	static void after() {
		List<Fireball>[][] map = new List[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = new ArrayList<Fireball>();
			}
		}

		while (!fireballs.isEmpty()) {
			Fireball fireball = fireballs.poll();
			map[fireball.x][fireball.y].add(fireball);
		}

		// 겹치는지 확인
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j].size() == 1)
					fireballs.add(map[i][j].get(0));
				else if (map[i][j].size() >= 2) {
					boolean odd = true;
					boolean even = true;
					int s = 0;
					int m = 0;
					for (Fireball fireball : map[i][j]) {
						if (fireball.d % 2 != 0)
							even = false;
						if (fireball.d % 2 != 1)
							odd = false;
						s += fireball.s;
						m += fireball.m;
					}

					s = s / map[i][j].size();
					m = m / 5;

					if (m != 0) {
						if (odd || even) {
							fireballs.add(new Fireball(map[i][j].get(0).x, map[i][j].get(0).y, m, s, 0));
							fireballs.add(new Fireball(map[i][j].get(0).x, map[i][j].get(0).y, m, s, 2));
							fireballs.add(new Fireball(map[i][j].get(0).x, map[i][j].get(0).y, m, s, 4));
							fireballs.add(new Fireball(map[i][j].get(0).x, map[i][j].get(0).y, m, s, 6));
						} else {
							fireballs.add(new Fireball(map[i][j].get(0).x, map[i][j].get(0).y, m, s, 1));
							fireballs.add(new Fireball(map[i][j].get(0).x, map[i][j].get(0).y, m, s, 3));
							fireballs.add(new Fireball(map[i][j].get(0).x, map[i][j].get(0).y, m, s, 5));
							fireballs.add(new Fireball(map[i][j].get(0).x, map[i][j].get(0).y, m, s, 7));
						}
					}
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			int m = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			fireballs.add(new Fireball(x, y, m, s, d));
		}

		while (K-- > 0) {
			move();
			after();
		}

		int result = 0;
		for (Fireball fireball : fireballs) {
			result += fireball.m;
		}

		System.out.println(result);

	}

}
