import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1106_호텔 {
	/*
	 * 176ms
	 * 2차원 dp를 활용한 배낭 문제 풀기
	 * 적어도 c명이라는 포인트를 생각하지 않아 뒤늦게 추가하여 해결
	 */
	static final int INF=987654321;
	static int c,n,cost[],ppl[];
	static int[][] dp;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		c = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());

		dp = new int[n+1][c+1];
		Arrays.fill(dp[0],INF);
		dp[0][0]=0;
		cost=new int[n+1]; ppl=new int[n+1];
		for(int i=1; i<=n; i++) {
			st = new StringTokenizer(br.readLine());
			cost[i] = Integer.parseInt(st.nextToken());
			ppl[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int idx=1; idx<=n; idx++) {
			for(int custo=0; custo<=c; custo++) {
				dp[idx][custo]=dp[idx-1][custo];
				int multi=1;
				for(; multi<=custo/ppl[idx]; multi++) {
					dp[idx][custo]=Math.min(dp[idx][custo], dp[idx-1][custo-ppl[idx]*multi]+cost[idx]*multi);
				}
				if(custo%ppl[idx]!=0)
					dp[idx][custo]=Math.min(dp[idx][custo], dp[idx-1][0]+cost[idx]*multi);
			}
		}
		
		System.out.println(dp[n][c]);
	}
}