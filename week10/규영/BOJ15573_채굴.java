import java.io.*;
import java.util.*;

/*
 * 135876KB, 964ms
 */

public class BOJ15573_채굴 {
	static class Node implements Comparable<Node> {
		int r, c, solidity;
		Node (int r, int c, int solidity) {
			this.r = r;
			this.c = c;
			this.solidity = solidity;
		}
		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.solidity, o.solidity);
		}
	}
	static int N, M, K, D;
	static int[][] mineral;
	static boolean[][] mined;
	static PriorityQueue<Node> canMine;
	static int[][] dir = {{0, -1}, {-1, 0}, {0, 1}, {1, 0}};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		mineral = new int[N][M];
		mined = new boolean[N][M];
		canMine = new PriorityQueue<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				mineral[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for (int i = 0; i < N; i++) {
			mined[i][0] = mined[i][M-1] = true;
			canMine.offer(new Node(i, 0, mineral[i][0]));
			canMine.offer(new Node(i, M-1, mineral[i][M-1]));
		}
		for (int i = 1; i < M-1; i++) {
			mined[0][i] = true;
			canMine.offer(new Node(0, i, mineral[0][i]));
		}
		D = 0;
		findD();
		System.out.print(D);
    }
	
	static void findD() {
		while (K > 0) {
			Node now = canMine.poll();
			if (now.solidity > D) D = now.solidity;
			for (int d = 0; d < 4; d++) {
				int nr = now.r+dir[d][0], nc = now.c+dir[d][1];
				if (!isInRange(nr, nc) || mined[nr][nc]) continue;
				mined[nr][nc] = true;
				canMine.offer(new Node(nr, nc, mineral[nr][nc]));
			}
			K--;
		}
	}
	
	static boolean isInRange(int nr, int nc) {
		return 0 <= nr && nr < N && 0 <= nc && nc < M;
	}
}