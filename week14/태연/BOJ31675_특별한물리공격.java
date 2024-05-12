import java.io.*;
import java.util.*;

public class BOJ31675_특별한물리공격 {
		
	/*
	 *  - 252ms
	 *  
	 *  - dp[i] i번째를 선택할때 시이에게 먹일 수 있는 최대값
	 * 
	 *  - 시이가 i번째를 선택한다고 했을때 학생입장에서 구성할수 있는 앞에 3개
	 *  
	 *  - ??? / 선택 / 대머리
	 *  - 선택  / 대머리 / 선택
	 *  - 선택  / 둘다 대머리로 만들지 않음 -> 시이가 둘중 최소값을 택함
	 */
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int[] arr = new int[n];
		
		long[] dp = new long[n];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
				
		if(n==1) {
			System.out.println(0);
			return;
		} else if(n==2) {
			System.out.println(Math.max(arr[0], arr[1]));
			return;
		} 
		
		dp[0] = arr[0];
		dp[1] = arr[1];
		dp[2] = Math.max(arr[0], arr[1]) + arr[2];
		
		for(int i=3; i<n; i++) {
			dp[i] = Math.max(dp[i-3]+Math.min(arr[i-1], arr[i-2]), Math.max(dp[i-3]+arr[i-1], dp[i-2]))
					+ arr[i];
		}
		
		System.out.println(Math.max(dp[n-3]+arr[n-1], dp[n-2]));
	}

}
