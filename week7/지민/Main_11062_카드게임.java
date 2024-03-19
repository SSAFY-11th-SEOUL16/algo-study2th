package ps;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 시간: 1256ms
 */
public class Main_11062_카드게임 {
	static int N;
	static int[] cards;
	static int[][][] memo;

	private static void solve(int turn, int l, int r) {
		if(l == r) {
			memo[l][r][turn] = cards[l];
			return;
		}
		
		if(memo[l][r][turn] != -1) return;
	
		solve(1 - turn, l + 1, r);
		solve(1 - turn, l, r - 1);
		
		if(l + 1 == r) {
			if(cards[l] < cards[r]) {
				memo[l][r][turn] = cards[r];
				memo[l][r][1 - turn] = memo[l][r - 1][1 - turn];
			}
			else {
				memo[l][r][turn] = cards[l];
				memo[l][r][1 - turn] = memo[l + 1][r][1 - turn];
			}
		}
		else {
			memo[l][r][turn] = Math.max(cards[l] + memo[l + 1][r][turn], cards[r] + memo[l][r - 1][turn]);
			memo[l][r][1 - turn] = Math.min(memo[l + 1][r][1 - turn],  memo[l][r - 1][1 - turn]);
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		
		while(T-- > 0) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			cards = new int[N];
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++) {
				cards[i] = Integer.parseInt(st.nextToken());
			}
			
			memo = new int[N][N][2];
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					memo[i][j][0] = memo[i][j][1] = -1;
				}
			}
			solve(0, 0, N - 1);
			System.out.println(memo[0][N - 1][0]);
		}
		

	}
}
