import java.io.*;
import java.util.*;

public class Main {

	/*
	 * - 112ms
	 * 
	 * - N번째 친구가 타는 시간을 기준으로 이분탐색, 이후 해당 분에 몇번째 기구가 비어있는지 연산
	 */

	static int n,m;
	static int[] arr;
	
	static boolean validate(long x) {
		long sum = m;
		
		for(int i=0; i<m; i++) {
			sum += x/arr[i];
			if(sum>=n) return true;
		}
		
		return false;
	}
	
	static int getAns(long x, int n) {
		if(x==0) return n;
		
		long sum = n;
		for(int i=0; i<m; i++) {
			sum -= ((x-1)/arr[i])+1;
		}
		
		for(int i=0; i<m; i++) {
			if(x%arr[i]==0) sum--;
			if(sum==0) return i+1;
		}
		
		return m;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		arr = new int[m];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<m; i++) arr[i] = Integer.parseInt(st.nextToken());
		
		long left = 0; long right = 60000000000L;
		
		while(left<right) {
			long mid = (left+right)/2;
			if(validate(mid)) {
				right = mid;
			}
			else {
				left = mid+1;
			}
		}
		
		System.out.println(getAns(left, n));
	}

}