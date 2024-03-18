import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ11062_카드게임 {

	static int n;
	static int[] deck;
	static int[][] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		for (int testCase = 1; testCase <= T; testCase++) {
			n = Integer.parseInt(br.readLine());
			deck = new int[n];
			dp = new int[n][n];

			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < n; i++) {
				deck[i] = Integer.parseInt(st.nextToken());
			}
			
			play(0, n-1, true);
			
			sb.append(dp[0][n-1]).append('\n');
		}
		System.out.println(sb);
	}
	
	static int play (int left, int right, boolean myturn) {
		
		if (left > right) return 0;
		if (dp[left][right] != 0) return dp[left][right];
		
		//내 차례일때는 크게
		if (myturn) {
			return dp[left][right] = Math.max(play(left+1, right, !myturn) + deck[left], play(left, right-1, !myturn) + deck[right]);
		}
		//상대 차례일때는 작게
		else {
			return dp[left][right] = Math.min(play(left+1, right, !myturn), play(left, right-1, !myturn));
		}	
	}

}
