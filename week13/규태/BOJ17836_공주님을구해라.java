import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ17836_공주님을구해라 {
	/*
	 * 136ms
	 * BFS 활용
	 */
	static int n,m,t,ans=Integer.MAX_VALUE;
	static int[][] map;
	static int[][] move = {{1,0},{0,1},{-1,0},{0,-1}};
	static boolean[][][] visit;
	static Queue<int[]> q = new ArrayDeque<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		t = Integer.parseInt(st.nextToken());
		
		map = new int[n][m]; visit = new boolean[n][m][2]; //n*m*2(명검 획득/미획득)
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<m; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}
		
		bfs();
		
		if(ans!=Integer.MAX_VALUE)
			System.out.println(ans);
		else
			System.out.println("Fail");
	}
	static void bfs() {
		q.offer(new int[] {0,0,0,0}); //[행, 열, 명검획득여부, 이동시간]
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			if(cur[0]==n-1 && cur[1]==m-1) {
				if(t>=cur[3]) ans=cur[3];
				return;
			}
			for(int[] m:move) {
				int ni=cur[0]+m[0];
				int nj=cur[1]+m[1];
				if(inRange(ni,nj) && !visit[ni][nj][cur[2]]) {
					if(map[ni][nj]==0) { // 다음 위치 빈공간 경우
						visit[ni][nj][cur[2]]=true;
						q.offer(new int[] {ni,nj,cur[2],cur[3]+1});
					}
					else if(map[ni][nj]==2) { // 다음 위치가 명검인 경우
						visit[ni][nj][1]=true;
						q.offer(new int[] {ni,nj,1,cur[3]+1});
					}
					else { // 다음 위치가 벽인경우 
						if(cur[2]==1) { //명검 이미 획득했다면 이동가능
							visit[ni][nj][1]=true;
							q.offer(new int[] {ni,nj,1,cur[3]+1});
						}
					}
				}
			}
		}
	}
	static boolean inRange(int i,int j) {
		return (i>=0 && i<n && j>=0  && j<m);
	}
}