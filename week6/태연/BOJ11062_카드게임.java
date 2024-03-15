import java.io.*;
import java.util.*;

public class BOJ11062_카드게임 {
	
	/*
	 * - 884 ms
	 * 
	 * - 그리디한 투포인터 풀이 시도했다가 실패
	 * 
	 * - 카드 풀이 i~j일때의 근우와 명우의 점수를 i=j일때부터 추적
	 */
	static int[] dp(boolean gwTurn, int i, int j) {		// gw -> 0   
		
		if(i==j) {
			dpT[i][j][(gwTurn)? 0:1] = card[i];
			return dpT[i][j];
		}
			
		if(dpT[i][j][(gwTurn)? 0:1]!=0) return dpT[i][j];
		
		int[] t1 = dp(!gwTurn, i+1, j);
		int[] t2 = dp(!gwTurn, i, j-1);
		
		if(t1[(gwTurn)? 0:1]+card[i] > t2[(gwTurn)? 0:1]+card[j]) {
			dpT[i][j][(gwTurn)? 0:1] = t1[(gwTurn)? 0:1]+card[i];
			dpT[i][j][(gwTurn)? 1:0] = t1[(gwTurn)? 1:0];
		}
		else {
			dpT[i][j][(gwTurn)? 0:1] = t2[(gwTurn)? 0:1]+card[j];
			dpT[i][j][(gwTurn)? 1:0] = t2[(gwTurn)? 1:0];
		}

		return dpT[i][j];
	}
	
	static int[] card;
	static int[][][] dpT;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int tc = Integer.parseInt(st.nextToken());
		StringBuilder sb = new StringBuilder();
		
		for(int t=0; t<tc; t++) {
			int n = Integer.parseInt(br.readLine());
			
			st = new StringTokenizer(br.readLine());
			card = new int[n];
			for(int i=0; i<n; i++) {
				card[i] = Integer.parseInt(st.nextToken());
			}
			
			if(n==1) {
				sb.append(card[0]).append("\n"); continue;
			}
			else if(n==2) {
				sb.append(Math.max(card[0], card[1])).append("\n"); continue;
			}
			
			dpT = new int[n][n][2];		//  i 부터 j 까지 카드가 남았을때 최고점
			
			sb.append(dp(true,0,n-1)[0]).append("\n");
			
		}
		System.out.println(sb);
	}

}
