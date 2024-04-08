import java.io.*;
import java.util.*;

public class BOJ27651_벌레컷 {
	
	/*
	 *  - 492ms
	 * 
	 *  - 머리끄댕이 잡고 두번째 자르는 위치 lower/upper bound 각각 이분탐색
	 */
	
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
		int chest=0; int bae=0; int left = 0; int right = 0;
		while(arr[head]<=sum/3) {		// 머리끄댕이 고정
			left = head+1; right = n-2;
			while(left<right) {
				int mid = (left+right)/2+1;
				if(sum-arr[mid] > arr[head]) {
					left = mid;
				}
				else {
					right = mid-1;
				}
			}
			if(sum-arr[left] <= arr[head]) {
				head++;
				continue;
			}
			bae = left;
			
			left = head+1; right = n-2;
			while(left<right) {
				int mid = (left+right)/2;
				if(sum-arr[mid] < arr[mid]-arr[head]) {
					right = mid;
				}
				else {
					left = mid+1;
				}
			}
			if(sum-arr[right] >= arr[right]-arr[head]) {
				head++;
				continue;
			}
			chest = right;
			
			if(chest<=bae) {
				result += bae-chest+1;
			}
			
			head++;
		}
		System.out.println(result);
	}

}
