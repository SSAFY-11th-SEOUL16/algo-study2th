import java.io.*;
import java.util.*;

public class BOJ15573_채굴 {
	
	/*
	 *  - 1700ms
	 * 
	 *  - 광물의 강도를 매개변수로 하여 파라메트릭 서치 + BFS
	 */

	static int[] dx = {-1,0,1,0};
	static int[] dy = {0,1,0,-1};
	
	static int n,m,k;
	
	static int[][] map;
	static Queue<int[]> firstQueue;
	
	static boolean inRange(int x, int y) {
		return (x>0 && x<n+1 && y>0 && y<m+1);
	}
	
	static boolean validate(int minS) {
		
		boolean[][] v = new boolean[n+1][m+2];
		Queue<int[]> q = new LinkedList<>(firstQueue);
		int cnt = 0;
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			
			for(int i=0; i<4; i++) {
				int nx = cur[0] + dx[i];
				int ny = cur[1] + dy[i];
				
				if(!inRange(nx, ny) || v[nx][ny] || map[nx][ny] > minS) continue;
				
				v[nx][ny] = true;
				cnt++;
				q.add(new int[] {nx,ny});
			}

			if(cnt>=k) return true;
		}
		
		return false;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		map = new int[n+1][m+2];
		
		firstQueue = new LinkedList<>();
		
		for(int j=0; j<m+2; j++) {
			map[0][j] = Integer.MAX_VALUE;
			firstQueue.add(new int[] {0,j});
		}
		
		for(int i=1; i<=n; i++) {
			st = new StringTokenizer(br.readLine());
			firstQueue.add(new int[] {i,0});
			map[i][0] = Integer.MAX_VALUE;
			for(int j=1; j<m+1; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
			firstQueue.add(new int[] {i,m+1});
			map[i][m+1] = Integer.MAX_VALUE;
		}
		
		int left = 0; int right = 1000000;
		while(left<right) {
			int mid = (left+right)/2;
			if(validate(mid)) {
				right = mid;
			}
			else {
				left = mid+1;
			}
		}
		
		System.out.println(left);
	}

}
