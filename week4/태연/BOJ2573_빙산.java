import java.io.*;
import java.util.*;

public class BOJ2573_빙산 {
	/*
	 *  - 608 ms
	 * 
	 *  - 빙산 위치를 기준으로 연산 한 후 bfs를 통해 연결 확인
	 */
	
	final static int[] dx = {0,1,0,-1};
	final static int[] dy = {1,0,-1,0};
	
	static int[][] map;
	static int cnt,n,m;
	static int [] first;
	static int t;
	
	static Queue<int[]> q = new LinkedList<>();
	
	static int bfs() {
		boolean[][] v= new boolean[n][m];
		v[first[0]][first[1]]=true;
		int result=0;
		
		Queue<int[]> tq = new LinkedList<>();
		tq.add(first);
		while(!tq.isEmpty()) {
			int[] cur = tq.poll();
			result++;
			for(int i=0; i<4; i++) {
				int nx = cur[0]+dx[i];
				int ny = cur[1]+dy[i];
				if(map[nx][ny]>0 && !v[nx][ny]) {
					v[nx][ny] = true;
					tq.add(new int[] {nx,ny});
				}
				
			}
		}

		return result;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<m ;j++) {
				int num = Integer.parseInt(st.nextToken());
				map[i][j] = num;
				if(num>0) {
					q.add(new int[] {i,j,num});
					cnt++;
				}
			}
		}
		
		
		do {
//			for(int i=0; i<n; i++) {
//				System.out.println(Arrays.toString(map[i]));
//			}
			t++;
			Queue<int[]> tq = new LinkedList<>();
			int[][] newMap = new int[n][m];
			boolean isFirst = false;
			while(!q.isEmpty()) {
				int[] cur = q.poll();
			
				for(int i=0; i<4; i++) {
					int nx = cur[0]+dx[i];
					int ny = cur[1]+dy[i];
					if(map[nx][ny]==0) {
						cur[2]--;
					}
				}
				
				if(cur[2]>0) {
					if(!isFirst) {
						isFirst=true;
						first = new int[] {cur[0],cur[1]};
					}
					newMap[cur[0]][cur[1]] = cur[2];
					tq.add(cur);
				}
				else {
					cnt--;
				}
			}
			map = newMap;
			q = tq;
			//System.out.println("----"+Arrays.toString(first)+"----"+cnt+"--- ");
		} while(bfs()==cnt);
		
		if(cnt==0) System.out.println(0);
		else System.out.println(t);
	}

}
