import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ11062_카드게임 {
	/*
	 * 메모리 152584KB 시간 420ms
	 * dp에 근우의 점수를 저장해 
	 * 근우의 차례에는 두가지 선택 중 이번선택+이전결과의 max를 dp에 저장
	 * 명우의 차례에는 같은 방법이되 두가지 선택 중 근우의 점수가 최소가 되게 min을 dp에 저장 
	 */
	static int n, card[], dp[][][];
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int t = Integer.parseInt(st.nextToken());
		
		for(int test=1; test<=t; test++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			card = new int[n];
			dp = new int[2][n][n];
			
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<n; i++) {
				card[i] = Integer.parseInt(st.nextToken());
			}
			
			int ans = filldp(0,0,n-1);
			sb.append(ans).append('\n');
		}
		System.out.println(sb);
	}
	static int filldp(int i, int l, int r) {
		if(l>r) return 0;
		if(dp[i][l][r]!=0) return dp[i][l][r];
		if(i==0) dp[i][l][r] = Math.max(filldp((i+1)%2,l+1,r)+card[l], filldp((i+1)%2,l,r-1)+card[r]);
		if(i==1) dp[i][l][r] = Math.min(filldp((i+1)%2,l+1,r), filldp((i+1)%2,l,r-1));
//		System.out.println(i+"  "+l+"  "+r+"  "+dp[i][l][r]);
		return dp[i][l][r];
	}
}
