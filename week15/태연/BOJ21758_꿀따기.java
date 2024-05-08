import java.io.*;
import java.util.*;

public class BOJ21758_꿀따기 {
	
	/*
	 *  - 252ms
	 *  
	 *  - 꿀통을 기준으로 벌이 왼쪽에 2개있을때, 오른쪽에 2개있을때, 양쪽에 있을때로 나눠서 계산
	 */

	static int[] prefixSum;
	static int[] arr;
	static int n;
	
	static int getMax(int id) {
		return Math.max(getFirst(id), Math.max(getSecond(id), getThird(id)));
	}
	
	//왼쪽두개 
	static int getFirst(int id) {
		if(id<2) return -1;
		
		int curVal = prefixSum[id]*2 - prefixSum[0] - prefixSum[1] - arr[1];
		int ptr = 1;
		while(ptr<id) {
			if(curVal <= prefixSum[id]*2 - prefixSum[0] - prefixSum[ptr] - arr[ptr]) {
				curVal = prefixSum[id]*2 - prefixSum[0] - prefixSum[ptr] - arr[ptr];
			}
			if(arr[ptr] <= arr[ptr+1]) break;
			ptr++;
		}
		
		return curVal;
	}
	
	//양옆
	static int getSecond(int id) {
		if(id==0 || id==n-1) return -1;
		
		return prefixSum[n-2] + arr[id] - prefixSum[0];
	}

	//오른쪽두개
	static int getThird(int id) {
		if(id>n-3) return -1;
		
		int curVal = prefixSum[n-2] - prefixSum[id] + prefixSum[n-3] - prefixSum[id] + arr[id]*2 - arr[n-2];
		int ptr = n-2;
		while(ptr>id) {
			if(curVal <= prefixSum[n-2] - prefixSum[id] + prefixSum[ptr-1] - prefixSum[id] + arr[id]*2 - arr[ptr]) {
				curVal = prefixSum[n-2] - prefixSum[id] + prefixSum[ptr-1] - prefixSum[id] + arr[id]*2 - arr[ptr];
			}
			if(arr[ptr] <= arr[ptr-1]) break;
			ptr--;
		}
		return curVal;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		arr = new int[n];
		st = new StringTokenizer(br.readLine());
		
		for(int i=0; i<n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		prefixSum = new int[n];
		
		prefixSum[0]=arr[0];
		
		for(int i=1; i<n; i++) {
			prefixSum[i] = prefixSum[i-1] + arr[i];
		}
		
		int max = Integer.MIN_VALUE;
		
		for(int i=0; i<n; i++) {
			int cur = getMax(i);
			if(max<cur) {
				max=cur;
			}
		}
		
		System.out.println(max);
		
	}

}
