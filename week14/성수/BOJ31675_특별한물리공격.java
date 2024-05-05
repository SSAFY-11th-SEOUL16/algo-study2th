import java.io.*;
import java.util.*;

public class Main {
	
	static long solution(int N, int[] arr) {
		// dp[i] = i가 대머리일때 i까지의 학생들을 대머리로 만들 때 필요한 최소 에너지
        // 연속된 대머리는 허용되지 않음
		// dp[i] = min(dp[i - 3] + arr[i] , dp[i - 2] + arr[i])
        // dp[i]가 대머리가 아닐 때의 최소 에너지는 dp[i-1]이 가지고 있음
        // 시이가 필요한 최대 에너지는 총 에너지 - 학생들이 만든 최소 에너지
		long[] dp = new long[N];
		dp[0] = arr[0];
		if(N == 1) return dp[0];
		dp[1] = arr[1];
		if(N == 2) return dp[0] < dp[1] ? dp[0] : dp[1];
		dp[2] = dp[0] + arr[2];
		if(N == 3) return dp[1] < dp[2] ? dp[1] : dp[2];
		
		for(int i = 3; i < N; i++) {
			dp[i] = (dp[i - 3] < dp[i - 2] ? dp[i - 3] : dp[i - 2]) + arr[i];
		}
		
		return dp[N - 2] < dp[N - 1] ? dp[N - 2] : dp[N - 1];
	}

	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(input.readLine());
		int[] arr = new int[N];
		long total = 0;
		StringTokenizer tokens = new StringTokenizer(input.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(tokens.nextToken());
			total += arr[i];
		}
		System.out.println(total - solution(N, arr)); 
	}

}
