import java.io.*;
import java.util.*;

public class BOJ1239_차트 {
	
	/*
	 *  - 108ms
	 * 
	 *  - 수열로 모든 가능한 경우의 수에 대해서 누적합&투포인터로 연산
	 */
	
	static int maxLine;
	static int[] data;
	
	static void permutation(int[] arr, int idx, int maxSize, boolean[] v) {
		if(idx==maxSize) {
			getLine(arr);
		} else {
			for(int i=0; i<maxSize; i++) {
				if(v[i]) {
					continue;
				} else {
					v[i] = true;
					arr[idx] = i;
					permutation(arr, idx+1, maxSize, v);
					v[i] = false;
				}
			}
		}
	}
	
	static void getLine(int[] arr) {
		int[] prefixSum = new int[arr.length];
		prefixSum[0] = data[arr[0]];
		
		for(int i=1; i<arr.length; i++) {
			prefixSum[i] = data[arr[i]] + prefixSum[i-1];
		}
		
		int left = 0;
		int right = 1;
		int numLine = 0;
		
		while(left<right && right<arr.length) {
			
			int curVal = prefixSum[right] - prefixSum[left];
			
			if(curVal == 50) {
				numLine++;
				left++;
			} else if (curVal > 50) {
				left++;
			} else {
				right++;
			}
		}
		
		if(numLine>maxLine) {
			maxLine = numLine;
		}
	}
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		
		if(n==1) {
			System.out.println(0);
			return;
		}
		
		data = new int[n];
	
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++) {
			data[i] = Integer.parseInt(st.nextToken());
		}
		
		maxLine=0;
		
		permutation(new int[n], 0, n, new boolean[n]);
		
		System.out.println(maxLine);
	}
}
