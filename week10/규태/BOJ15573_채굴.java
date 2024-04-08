import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ15573_채굴 {
	/*
	 * 1500ms
	 * 바깥 테투리를 0인 광물로 설정하고 마지막에 테두리 개수 제거
	 * 최소 1, 최대 1000000으로 시작해 이분탐색하며 채굴할 수 있는 개수 확인하며 반복 
	 */
	static int n,m,k,mine[][];
	static boolean visit[][];
	static int[][] dir = {{1,0},{0,1},{-1,0},{0,-1}};
	static Queue<int[]> q = new ArrayDeque<>();
	public static void main(String[] args) throws IOException, NumberFormatException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		mine = new int[n+1][m+2];
		
		for(int i=1; i<=n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=m; j++) {
				mine[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int start=1,end=1000000,ans=end; 
		while(start<=end) {
			int mid = (start+end)/2;
			if(bfs(mid)>=k) {
				ans=Math.min(ans, mid);
				end=mid-1;
			}
			else {
				start=mid+1;
			}
		}
		System.out.println(ans);
	}
	static int bfs(int limit) {
		visit = new boolean[n+1][m+2];
		
		int cnt=0;
		q.offer(new int[] {0,0});
		visit[0][0]=true;
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			cnt++;
			for(int[] d:dir) {
				int ni=cur[0]+d[0];
				int nj=cur[1]+d[1];
				if(inRange(ni,nj) && !visit[ni][nj] && mine[ni][nj]<=limit) {
					visit[ni][nj]=true;
					q.offer(new int[] {ni,nj});
				}
			}
		}
		return cnt-2*n-m-2;
	}
	static boolean inRange(int i,int j) {
		return (i>=0 && i<n+1 && j>=0 && j<m+2);
	}
}
