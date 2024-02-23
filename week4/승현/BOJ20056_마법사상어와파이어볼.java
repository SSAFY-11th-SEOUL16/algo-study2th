import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ20056_마법사상어와파이어볼 {
	static int result;
	static int N, M, K;
	static ArrayList<FireBall>[][] board;
	static int[][] dirs = { { -1, 0 }, { -1, 1 }, { 0, 1 }, { 1, 1 }, { 1, 0 }, { 1, -1 }, { 0, -1 }, { -1, -1 } };
	static int[] same = { 0, 2, 4, 6 };
	static int[] diff = { 1, 3, 5, 7 };

	static class FireBall {
		int m;
		int s;
		int d;
		boolean moved;

		FireBall(int m, int s, int d) {
			this.m = m;
			this.s = s;
			this.d = d;
		}
	}

	public static void main(String[] args) throws Exception { //964 ms
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		board = new ArrayList[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				board[i][j] = new ArrayList<>();
			}
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			board[x - 1][y - 1].add(new FireBall(m, s, d));
		}
		
		for (int i = 0; i < K; i++) {
			simulate();
		}
		getResult();
		System.out.println(result);
	}

	public static void simulate() {
		for (int i = 0; i < N; i++) 
			for (int j = 0; j < N; j++) {
				for (FireBall fb : board[i][j]) {
					fb.moved = false;
				}
			}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!board[i][j].isEmpty()) {
					for (int k = board[i][j].size() - 1; k >= 0; k--) {
						if (board[i][j].get(k).moved)
							continue;
						int ni = (N * 250 + i + dirs[board[i][j].get(k).d][0] * board[i][j].get(k).s) % N;
						int nj = (N * 250 + j + dirs[board[i][j].get(k).d][1] * board[i][j].get(k).s) % N;
						FireBall mfb = board[i][j].get(k);
						mfb.moved = true;
						board[ni][nj].add(mfb);
						board[i][j].remove(board[i][j].get(k));
					}
				}
			}
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int mSum = 0;
				int sSum = 0;
				int evenCnt = 0;
				if (board[i][j].size() >= 2) {
					int fbCnt = board[i][j].size();
					for (FireBall fb : board[i][j]) {
						mSum += fb.m;
						sSum += fb.s;
						if (fb.d % 2 == 0)
							evenCnt++;
					}
					board[i][j].clear();
					if (mSum < 5) 
						continue;
					
					if (evenCnt == fbCnt || evenCnt == 0) 
						for (int nd : same) 
							board[i][j].add(new FireBall(mSum / 5, sSum / fbCnt, nd));
					else 
						for (int nd : diff)
							board[i][j].add(new FireBall(mSum / 5, sSum / fbCnt, nd));
					
				}
			}
		}
	}

	public static void getResult() {
		int sum = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				for (FireBall fb : board[i][j])
					sum += fb.m;
			}
		}
		result = sum;
	}
}
