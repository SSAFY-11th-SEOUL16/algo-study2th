import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 시간: 1,875ms
 * 풀이 참고했습니다.
 */

public class SW5648_원자_소멸_시뮬레이션 {
	static class Node {
		static final int[] dy = {-1, 1, 0, 0}; // 상, 하, 좌, 우
		static final int[] dx = {0, 0,  -1, 1};
		int y, x;
		int d;
		int k;

		public Node(int y, int x, int d, int k) {
			this.y = y;
			this.x = x;
			this.d = d;
			this.k = k;
		}

		public boolean move() {
			this.y += dy[d];
			this.x += dx[d];
			return inRange();
		}

		private boolean inRange() {
			return 0 <= this.y && this.y <= INF && 0 <= this.x && this.x <= INF;
		}

	}

	static int N;
	static Queue<Node> queue;
	static int[][] map;
	static int INF = 4000;

	public static int solve() {
		int energySum = 0;
		while(!queue.isEmpty()) {
			for(int i = 0; i < queue.size(); i++) {
				Node node = queue.poll();
				if(map[node.y][node.x] != node.k) { // 충돌
					energySum += node.k;
					map[node.y][node.x] = 0;
					continue;
				}

				map[node.y][node.x] -= node.k;

				if(node.move()) {
					queue.add(node);
					map[node.y][node.x] += node.k;
				}
			}
		}
		return energySum;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());

		StringBuilder sb = new StringBuilder();
		map = new int[INF + 1][INF + 1];
		for(int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());
			queue = new LinkedList<>();

			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				int x = (Integer.parseInt(st.nextToken()) + 1000) * 2;
				int y = INF - (Integer.parseInt(st.nextToken()) + 1000) * 2;
				int d = Integer.parseInt(st.nextToken());
				int k = Integer.parseInt(st.nextToken());
				map[y][x] = k;
				queue.offer(new Node(y, x, d, k));
			}

			sb.append("#").append(t).append(" ").append(solve()).append("\n");
		}
		System.out.println(sb);
	}
}
