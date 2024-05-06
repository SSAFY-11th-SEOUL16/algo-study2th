import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ31675_특별한물리공격 {
	/*
	 * 252ms
	 * 카드게임 문제를 연상시키며 처음 3차원 dp(n*2*2)로 실행했는데 복잡해서 차원수를 줄이고자 생각
	 * 1차원 dp로 i번째가 무조건 선택될때의 값을 dp[i]로 생각
	 * i번째 이전에 i-3번째를 선택하는 경우를 두가지 경우로 나누고 (시이 입장에서 최소가 되게끔 선택)
	 * i번째 이전에 i-2번째를 선택하는 경우를 고려해서 
	 * 학생 입장에서 최대가 되게끔 해를 구성
	 */
	static int n,energy[];
	static long ans,dp[];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		
		energy = new int[n]; dp = new long[n];
		StringTokenizer st = new StringTokenizer(br.readLine()); 
		for(int i=0; i<n; i++)
			energy[i] = Integer.parseInt(st.nextToken());
			
		if(n==1) ans=0;
		else if(n==2) ans=Math.max(energy[0], energy[1]);
		else {
			dp[0]=energy[0]; dp[1]=energy[1];
			dp[2]=energy[2]+Math.max(energy[0], energy[1]);
			for(int i=3; i<n; i++) {
				dp[i]=energy[i]+Math.max(dp[i-3]+Math.min(energy[i-2], energy[i-1]), dp[i-3]+energy[i-1]);
				dp[i]=Math.max(dp[i], dp[i-2]+energy[i]);
			}
			ans = Math.max(energy[n-1]+dp[n-3], dp[n-2]);
		}
//		System.out.println(Arrays.toString(dp));
		System.out.println(ans);
	}
}
