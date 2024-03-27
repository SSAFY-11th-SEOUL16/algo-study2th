package daily;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1189_컴백홈 {
	static int n,m,k,count=0;
	static char map[][]; 
	static boolean visit[][];
	static int move[][] = {{1,0},{0,1},{-1,0},{0,-1}};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		map = new char[n][m];
		visit = new boolean[n][m];
		for(int i=0; i<n; i++) {
			String line = br.readLine();
			for(int j=0; j<m; j++)
				map[i][j] = line.charAt(j);	
		}
		
		visit[n-1][0]=true;
		findRoute(n-1,0,1);
		System.out.println(count);
	}
	
	static void findRoute(int i, int j, int dist) { //dfs
		if(dist==k) {
			if(i==0 && j==m-1) {
				count++;
			}
			return;
		}
		
		for(int d=0; d<4; d++) {
			int nextR = i+move[d][0];
			int nextC = j+move[d][1];
			if(inRange(nextR, nextC) && map[nextR][nextC]!='T' && !visit[nextR][nextC]) {
				visit[nextR][nextC]=true;
				findRoute(nextR,nextC,dist+1);
				visit[nextR][nextC]=false;
			}
		}
	}
	
	static boolean inRange(int i, int j) {
		if(i<0 || i>=n || j<0 || j>=m)
			return false;
		return true;
	}
}
