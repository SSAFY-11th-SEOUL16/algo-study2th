import java.io.*;
import java.util.*;

public class BOJ15573_채굴2 {
	
	/*
	 *  - 1132ms
	 * 
	 *  - Priority Queue를 활용한 그디리한 탐색
	 */

	static int[] dx = {-1,0,1,0};
	static int[] dy = {0,1,0,-1};
	
	static boolean inRange(int x, int y, int n, int m) {
		return (x>=0 && x<n && y>=0 && y<m);
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[n][m];
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		PriorityQueue<int[]> pq = new PriorityQueue<>((int[] o1, int[] o2) -> o1[2]-o2[2]);
		boolean[][] v = new boolean[n][m];
		
		for(int j=0; j<m; j++) {
			v[0][j]=true;
			pq.add(new int[] {0,j,map[0][j]});
		}
		for(int i=1; i<n; i++) {
			v[i][0]=true;
			v[i][m-1]=true;
			pq.add(new int[] {i,0,map[i][0]});
			pq.add(new int[] {i,m-1,map[i][m-1]});
		}
		
		int max = 0;
		while(k>0) {
			int[] cur = pq.poll();				// 공기에 닿는거중에 가장 작은값
			if(cur[2]>max) max=cur[2];
			
			for(int i=0; i<4; i++) {
				int nx = cur[0] + dx[i];
				int ny = cur[1] + dy[i];
				
				if(!inRange(nx,ny,n,m) || v[nx][ny]) continue;
				
				v[nx][ny] = true;				// 공기에 닿음
				pq.add(new int[] {nx, ny, map[nx][ny]});
			}
			
			k--;
		}
		
		System.out.println(max);
	}

}
