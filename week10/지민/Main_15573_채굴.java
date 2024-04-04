package ps;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 시간: 984ms
 */
public class Main_15573_채굴 {
	static class Node implements Comparable<Node> {
		int y, x, d;

		public Node(int y, int x, int d) {
			this.y = y;
			this.x = x;
			this.d = d;
		}

		@Override
		public int compareTo(Node o) {
			return this.d - o.d;
		}
	}
	static int N;
	static int M;
	static int K;
	static int[][] S;
	static int[][] dir = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

	private static PriorityQueue<Node> init() {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		for (int j = 1; j <= M; j++) {
			pq.add(new Node(1, j, S[1][j]));
			S[1][j] = 0;
		}

		for (int i = 2; i <= N; i++) {
			pq.add(new Node(i, 1, S[i][1]));
			pq.add(new Node(i, M, S[i][M]));
			S[i][1] =  S[i][M] = 0;
		}
		return pq;
	}

	private static int solve() {
		Arrays.fill(S[N + 1], -1);
		int count = 0;
		int D = 0;
		
		PriorityQueue<Node> pq = init();
		while (!pq.isEmpty() && count < K) {
			Node curr = pq.poll();
			if(curr.d > D) D = curr.d;
			count += 1;
			for (int i = 0; i < 4; i++) {
				int ny = curr.y + dir[i][0];
				int nx = curr.x + dir[i][1];
				if(S[ny][nx] > 0) {
					pq.add(new Node(ny, nx, S[ny][nx]));
					S[ny][nx] = 0;
				}
			}
		}

		return D;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		S = new int[N + 2][M + 2];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= M; j++) {
				S[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		System.out.println(solve());
	}
}
