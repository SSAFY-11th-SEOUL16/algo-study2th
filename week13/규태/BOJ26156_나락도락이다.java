import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BOJ26156_나락도락이다 {
	/*
	 * 276ms
	 * 1차원 DP + 뒤에서부터 누적합 활용
	 * DP[i] : 현재 문자 기준으로 뒤의 문자열을 만들수 있는 경우의 수를 %DIV 저장
	 * K - 종료 문자이므로 항상 1가지
	 * C - CK 만들수있는 경우 누적합 저장
	 * O - OCK 만들수있는 경우 누적합 저장
	 * R - ROCK 만들수있는 경우 누적합 저장
	 * ROCK를 만들수있는 R을 리스트에 넣어 저장하고 해당 문자 앞은 모두 포함,미포함 상관없으므로
	 * dp[i]*2^i를 더해준다.
	 */
	static final int DIV = 1000000007;
	static int n;
	static long dp[];
	static List<Integer> idxlist = new ArrayList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		char[] narock = br.readLine().toCharArray();
		dp = new long[n];
		
		int rock=0, r=0, o=0, c=0, k=0;
		for(int i=n-1; i>=0; i--) {
			if(narock[i]=='K') {
				if(rock==0) rock++; 
				dp[i]=1; k++;
			}
			else if(narock[i]=='C') {
				if(rock==1) rock++; 
				dp[i]=k; c+=k; c%=DIV;
			}
			else if(narock[i]=='O') {
				if(rock==2) rock++; 
				dp[i]=c; o+=c; o%=DIV;
			}
			else if(narock[i]=='R' && rock==3) { 
				dp[i]=o;
				idxlist.add(i);
			}
		}
//		System.out.println(Arrays.toString(dp));
		long ans=0;
		for(int idx:idxlist) {
			ans+=pow(idx)*dp[idx];
			ans%=DIV;
		}
		System.out.println(ans);
	}
	static long pow(int x) {
		if(x==0) return 1;
		if(x==1) return 2;
		else if(x%2==0) {
			long half = pow(x/2); 
			return half*half%DIV;
		}
		else return pow(x-1)*2%DIV;
	}
}
