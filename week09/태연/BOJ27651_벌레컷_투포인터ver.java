import java.io.*;
import java.util.*;

public class BOJ27651_벌레컷_투포인터ver {
	
	/*
	 *  - 472ms
	 * 
	 *  - 머리끄댕이 잡고 두번째 자르는 위치 lower/upper bound 각각 투포인터 이동
	 */
	
	static boolean inRange(int chest, int bae, int n) {
		return (bae>0 && chest<n);
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n=Integer.parseInt(st.nextToken());
		
		long[] arr = new long[n];
		
		st = new StringTokenizer(br.readLine());
		arr[0] = Long.parseLong(st.nextToken());
		for(int i=1; i<n; i++) {
			arr[i]=arr[i-1]+Long.parseLong(st.nextToken());
		}
		long sum = arr[n-1];
        
		long result = 0;
		int head = 0;
		int chest=1; int bae=n-2;
		
		while(chest<=bae && head<n-2) {
		
			while(sum-arr[bae]<=arr[head] && bae>0) {
				bae--;
			}
			while(sum-arr[chest] >= arr[chest]-arr[head] && chest<n) {
				chest++;
			}
			
			if(!inRange(chest, bae, n)) break;
			
			if(chest<=bae) {
				result += bae-chest+1;
			}
			
			head++;
		}
		
		System.out.println(result);
	}

}