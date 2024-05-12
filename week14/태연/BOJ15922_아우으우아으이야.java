import java.io.*;
import java.util.*;

public class BOJ15922_아우으우아으이야 {
	
	public static void main(String[] args) throws Exception {
		
		/*
		 * - 348ms
		 * 
		 * - 길이 쭉쭉 늘려서 끝나면 쫙
		 */

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int result = 0;
		
		st = new StringTokenizer(br.readLine());
		int curStart = Integer.parseInt(st.nextToken());
		int curEnd = Integer.parseInt(st.nextToken());
		
		if(n==1) {
			System.out.println(curEnd-curStart);
			return;
		}

		for(int i=1; i<n-1; i++) {
			st = new StringTokenizer(br.readLine());

			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			
			if(end<=curEnd) {
				continue;
			} else if(start>curEnd) {
				result += (curEnd-curStart);
				curStart = start;
				curEnd = end;
			} else {
				curEnd = end;
			}
		}
		
		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		
		if(end<=curEnd) {
			result += (curEnd-curStart);
		} else if(start>curEnd) {
			result += (curEnd-curStart) + (end-start);
		} else {
			result += (end-curStart);
		}
		
		System.out.println(result);
		
	}
}
