import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1106_호텔 {			// 84ms
	static int c, n;
	static int[] dp;
	static int result;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		c = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		
		result = Integer.MAX_VALUE;
		dp = new int[c + 100];
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[0] = 0;

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int cost = Integer.parseInt(st.nextToken());
			int cusCnt = Integer.parseInt(st.nextToken());
			
			for (int j = cusCnt; j < c + 100; j++) {
				if (dp[j-cusCnt] == Integer.MAX_VALUE) {
					continue;
				}
				dp[j] = Math.min(dp[j], dp[j-cusCnt] + cost);
			}
		}
		
		for (int i = c; i < c + 100; i++) {
			result = Math.min(result, dp[i]);
		}
		
		System.out.println(result);
	}

}
