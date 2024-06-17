import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1937_욕심쟁이판다 {
	/*
	 * 408ms
	 * 각 칸에서 이동할 수 있는 다음칸을 확인하며 dp를 업데이트해가는 과정으로 해결
	 */
	static int n, bamboo[][], dp[][];
	static int[][] dir = {{1,0},{0,1},{-1,0},{0,-1}};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		bamboo = new int[n][n]; dp = new int[n][n];
		StringTokenizer st;
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<n; j++) {
				bamboo[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int ans=0;
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				if(dp[i][j]!=0) continue;
				ans=Math.max(ans,dfs(i,j));
			}
		}
		System.out.println(ans);
	}
	static int dfs(int i, int j) {
		if(dp[i][j]!=0) return dp[i][j];
		dp[i][j]=1;
		for(int[] d: dir) {
			int ni=i+d[0]; int nj=j+d[1];
			if(inRange(ni,nj) && bamboo[ni][nj]>bamboo[i][j])
				dp[i][j] = Math.max(dp[i][j], dfs(ni,nj)+1);
		}
		return dp[i][j];
	}
	static boolean inRange(int i, int j) {
		return i>=0 && i<n && j>=0 && j<n;
	}
}
