import java.io.*;
import java.util.*;

public class BOJ2560_짚신벌레 {
	
	/*
	 *  - 104ms
	 * 
	 *  - 누적합 + DP
	 */

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());
		
		int[] allCnt = new int[n+d+1];		// 전체 마리수 변화량(죽는거) follow
		int[] able = new int[n+d+1];		// 번식 가능한 개체수 변화량 follow
		int curCnt=1;
		int ableCnt = 0;
		
		able[a] = 1;
		able[b] = -1;
		allCnt[d] = -1;
		
		for(int i=1; i<=n; i++) {
			
			ableCnt += able[i]%1000;					// i일째 태어나는 개체수
			curCnt += (ableCnt + allCnt[i])%1000;		// i일째 총 개체수 변화 (태어나는 개체수 - 죽는 개체수)
			
			able[i+a] += ableCnt;
			able[i+b] -= ableCnt;
			allCnt[i+d] -= ableCnt;
			
		}
		
		System.out.println(curCnt%1000);
		
	}

}
