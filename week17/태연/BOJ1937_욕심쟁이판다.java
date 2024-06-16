import java.io.*;
import java.util.*;

public class BOJ1937_욕심쟁이판다 {
	
	/*
	 *  - 436ms
	 * 
	 *  - DFS + Memoization
	 */
	
	static int[][] map;
	static int[][] memo;
	
	static int[] dx = {-1,0,1,0};
	static int[] dy = {0,1,0,-1};
	
	static int n;
	static int maxDist;
		
	static boolean inRange(int x, int y) {
		return (x>=0 && x<n && y>=0 && y<n);
	}
	
	static int dfs(int x, int y, int dist) {
		
		if(memo[x][y]>0) {
			return dist+memo[x][y];
		}
		
		int lastDist=0;
		boolean isLast = true;
		
		for(int i=0; i<4; i++) {
			int nx = x+dx[i];
			int ny = y+dy[i];
			
			if(!inRange(nx,ny) || map[nx][ny]>=map[x][y]) {
				continue;
			} else {
				lastDist = Math.max(lastDist, dfs(nx,ny,dist+1));
				isLast = false;
			}
		}
		
		
		if(isLast) {
			return dist;
 		} else {
 			memo[x][y] = lastDist-dist;
 			return lastDist;
		}
	}
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());

		map = new int[n][n];
		memo = new int[n][n];
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		maxDist = 0;
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				maxDist = Math.max(dfs(i,j,0), maxDist);
			}
		}
		
		System.out.println(maxDist+1);
	}
}
