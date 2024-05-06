import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ31675_특별한물리공격 {
	static int n;
	static int[] arr;
	static long[][] dp;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		arr = new int[n+1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=1; i<=n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		dp = new long[n+1][2];
		dp[1][1] = arr[1]; //1번 선택o
		dp[1][0] = 0; //1번 선택x

		if (n >= 2) {
			dp[2][1] = arr[2]; //2번째 선택
			dp[2][0] = arr[1]; //2번재 선택x
		}

		for(int i=3; i<n; i++) {
			/*
			i번째를 선택하는 경우 XOO, OXO 두가지만 존재
			i번째를 선택하지 않는 경우 ?XO 존재
			 */
			dp[i][1] = Math.max(dp[i-2][0] + arr[i-1], dp[i-1][0]) + arr[i]; //i 번째 선택o
			dp[i][0] = dp[i-1][1]; //i번재 선택 x
//			print();
		}
		
		dp[n][1] = dp[n-1][0] + arr[n];
		dp[n][0] = dp[n-1][1];

		if (n == 1) System.out.println(0);
		else System.out.println( Math.max(dp[n][1], dp[n][0]));
	}

	static void print(){
		for(int i=1; i<=n; i++) {
			System.out.print(dp[i][0] + " ");
		}
		System.out.println();
		for(int i=1; i<=n; i++) {
			System.out.print(dp[i][1] + " ");
		}
		System.out.println();
		System.out.println();
	}

}