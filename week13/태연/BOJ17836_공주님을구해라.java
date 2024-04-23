import java.io.*;
import java.util.*;

public class BOJ17836_공주님을구해라 {
	
	/*
	 *  - 140ms
	 * 
	 *  - 칼이 있을때랑 없을때랑 구분해서 BFS
	 */

	static int n,m,t;
	static int[][] map;
	static boolean[][][] v;
	static int[] dx = {0,1,0,-1};
	static int[] dy = {1,0,-1,0};
	
	static boolean inRange(int x, int y) {
		return (x>=0 && x<n && y>=0 && y<m);
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		t = Integer.parseInt(st.nextToken());
		
		map = new int[n][m];
		v = new boolean[n][m][2];
		
		for(int r=0; r<n; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0; c<m; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		Queue<int[]> q = new LinkedList<>();
		
		//  x	y	time	ram	
		int[] start = {0,0,0,0};
		q.add(start);
		v[0][0][0] = true;
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			
			if(cur[2]>=t) break;
			
			for(int i=0; i<4; i++) {
				int nx = cur[0] + dx[i];
				int ny = cur[1] + dy[i];
				
				if(nx == n-1 && ny == m-1) {
					System.out.println(cur[2]+1);
					return;
				}
				
				if(!inRange(nx,ny) || v[nx][ny][cur[3]]) continue;
				
				if(map[nx][ny]==0) {
					v[nx][ny][cur[3]] = true;
					q.add(new int[] {nx,ny,cur[2]+1,cur[3]});
				} else if (map[nx][ny]==2) {
					v[nx][ny][1] = true;
					q.add(new int[] {nx,ny,cur[2]+1,1});
				} else {
					if(cur[3]>0) {
						v[nx][ny][1] = true;
						q.add(new int[] {nx,ny,cur[2]+1, 1});
					}
				}
			}
		}
		
		System.out.print("Fail");
	}

}
