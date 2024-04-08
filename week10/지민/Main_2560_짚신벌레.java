package ps;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 시간: 96ms
 * 풀이 보고 풀었습니다. :(
 */
public class Main_2560_짚신벌레 {
	static int N;
	static final int MOD = 1000;
	
	private static int solve(int a, int b, int d) {
		int[] dp = new int[N + 1];
		dp[0] = 1;
		
		for(int i = 1; i <= N; i++) {
			if(i < a) dp[i] = dp[i - 1];
			else if(i < b) dp[i] = (dp[i - 1] + dp[i - a] + MOD) % MOD;
			else dp[i] = (dp[i - 1] + dp[i - a] - dp[i - b] + MOD) % MOD;
		}
		
		if(N - d >= 0) dp[N] = (dp[N] - dp[N - d] + MOD) % MOD;
		
		return dp[N];
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		System.out.println(solve(a, b, d));
	}
}
