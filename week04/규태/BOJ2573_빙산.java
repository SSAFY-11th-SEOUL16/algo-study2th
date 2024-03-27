import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class BOJ2573_빙산 {
	static int n,m,group=0,time=0;
	static int[][] iceberg;
	static boolean[][] visit;
	static boolean stop=false;
	static int[][] move = {{1,0},{-1,0},{0,1},{0,-1}};
	static Deque<int[]> q = new ArrayDeque<int[]>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		iceberg = new int[n][m];

		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<m; j++) {
				iceberg[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		melt(1);
		while(!stop && group<2) {
			melt(0);
//			show();
			group=0;
			melt(1);
			time++;
//			System.out.println(group);
		} 
		
		if(stop)
			System.out.println(0);
		else
			System.out.println(time);
	}
	static void melt(int mode) {
		stop=true;
		visit = new boolean[n][m];
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				if(iceberg[i][j]!=0 && !visit[i][j]) {
					visit[i][j]=true;
					q.offer(new int[] {i,j});
					bfs(mode);
					stop=false;
				}
			}
		}
		
	}
	
	static void bfs(int mode) {
		group++;
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			for(int[] m : move) {
				int ni = cur[0]+m[0];
				int nj = cur[1]+m[1];
				if(inRange(ni,nj) && !visit[ni][nj]) {
					if(mode==0) {
						if(iceberg[ni][nj]==0 && iceberg[cur[0]][cur[1]]!=0) {
							iceberg[cur[0]][cur[1]]--;
						}
						else if (iceberg[ni][nj]!=0) {
							visit[ni][nj]=true;
							q.offer(new int[] {ni,nj});
						}
					}
					else {
						if(iceberg[ni][nj]!=0) {
							visit[ni][nj]=true;
							q.offer(new int[] {ni,nj});
						}
					}
				}
			}
		}
	}
	static boolean inRange(int i, int j) {
		if(i<0 || i>=n || j<0 || j>=m)
			return false;
		return true;
	}
//	static void show() {
//		for(int i=0; i<n; i++) {
//			for(int j=0; j<m; j++) {
//				System.out.print(iceberg[i][j]+" ");
//			}
//			System.out.println();
//		}
//	}
}
