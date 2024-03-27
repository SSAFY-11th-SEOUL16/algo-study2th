import java.io.*;
import java.util.*;

public class BOJ2531_회전초밥 {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());	// 접시수
		int d = Integer.parseInt(st.nextToken());	// 초밥 가짓수
		int[] map = new int[d+1];
		
		int k = Integer.parseInt(st.nextToken());	// 연속 접시수
		int c = Integer.parseInt(st.nextToken());	// 쿠폰번호
		
		int[] arr = new int[n+1];	// 초밥배열
		int[] window = new int[k];	// window
		
		int cnt = 0; 	// 현재 초밥 가짓수
		
		for(int i=0; i<n; i++) {
			int curN = Integer.parseInt(br.readLine());
			arr[i] = curN;
			if(i<k) {
				window[i]=curN;
				if(map[curN]==0) cnt++;
				map[curN]++;
			}
		}
		int max=0;
		if(map[c]==0) max = cnt+1;
		else max = cnt; 	// 최대 가짓수
		
		for(int i=k; i<n+k; i++) {			// k부터 초기값까지 한바퀴 (n번) 
			int curN = arr[i%n];
			int toDelete = window[i%k];		// 삭제될 위치 
			if(curN==toDelete) continue;
			
			if(map[toDelete]==1) {
				cnt--;
			}
			
			map[toDelete]--;
			
			if(map[curN]==0) cnt++;
			map[curN]++;
			
			window[i%k] = curN;

			if(map[c]==0) max = (cnt+1 > max) ? cnt+1 : max;
			else max = (cnt > max) ? cnt : max;
		}
		
		System.out.println(max);

	}

}
