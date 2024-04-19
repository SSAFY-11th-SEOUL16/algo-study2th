import java.io.*;
import java.util.*;

public class BOJ23295_스터디시간정하기1 {
	
	/*
	 *  - 452ms
	 *  
	 *  - 누적합 연산
	 */
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int t = Integer.parseInt(st.nextToken());
		
		int[] time = new int[100001+t];
		int maxT = -1;
		
		for(int i=0; i<n; i++) {
			int k = Integer.parseInt(br.readLine());
			for(int j=0; j<k; j++) {
				st = new StringTokenizer(br.readLine());
				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());
				
				if(end>maxT) maxT = end;
				
				time[start]++;
				time[end]--;
			}
		}
		
		int maxIdx = 0;
		int curSum = time[0];
		
		for(int i=1; i<t; i++) {
			time[i] += time[i-1];
			curSum += time[i];
		}
		
		int curBest = curSum;
		
		for(int i=t; i<=maxT; i++) {
			time[i] += time[i-1];
			curSum += time[i];
			curSum -= time[i-t];
			if(curSum>curBest) {
				maxIdx=i-t+1;
				curBest=curSum;
			}
		}
		
		System.out.println(maxIdx + " " + (maxIdx+t));
		
	}

}
