import java.io.*;
import java.util.*;

public class BOJ1106_호텔 {

	/*
	 *  - 84ms
	 * 
	 *  - 사람을 n명 늘리는데 필요한 최소비용을 기준으로 하여 DP
	 */
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int c = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());
		
		// 비용 / 늘어나는사람수
		int[][] cost = new int[n][2];
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			cost[i][0] = Integer.parseInt(st.nextToken());
			cost[i][1] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(cost, (int[] o1, int[] o2) -> {
			double n1 = o1[1]/((double) o1[0]);
			double n2 = o2[1]/((double) o2[0]);
			
			if(n1>n2) return -1;
			else if (n1==n2) return 0;
			else return 1;
		});
		
		// 사람 n 명 늘리는데 필요한 최소비용
		int[] dp = new int[1111];
		Arrays.fill(dp, 111111);
		dp[0]=0;
		
		for(int[] cur : cost) {
			int curCost = cur[0];
			int curCust = cur[1];
			
			for(int i=0; i<1111-curCust; i++) {
				dp[i+curCust] = Math.min(dp[i+curCust], dp[i]+curCost);
			}
		}
		
		int result = Integer.MAX_VALUE;
		for(int i=c; i<1111; i++) {
			if(result>dp[i]) result = dp[i];
		}
		
		System.out.println(result);
	}

}
