import java.io.*;
import java.util.*;

public class BOJ1189_컴백홈 {
	
	static int destX, destY;
	static int r,c,k, cnt;
	static int[] dx = {0,1,0,-1};
	static int[] dy = {1,0,-1,0};
	static char[][] map;
	static boolean[][] v;
	
	static boolean inRange(int x, int y) {
		if((x>=0 && x<r)&&(y>=0 && y<c)) return true;
		return false;
	}
	
	static void dfs(int x, int y, int dist) {

		if (dist==k) {
			if(x==destX && y==destY) cnt++;
			return;
		}
		for(int i=0; i<4; i++) {
			int newX = x+dx[i]; int newY = y+dy[i];
			if(!inRange(newX,newY)) continue;

			if(map[newX][newY]!='T' && !v[newX][newY]) {
				v[newX][newY]=true;
				dfs(newX,newY,dist+1);
				v[newX][newY]=false;
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		map = new char[r][c];
		v = new boolean[r][c];
		for(int i=0; i<r; i++) {
			map[i] = br.readLine().toCharArray();
		}

		destX = 0;
		destY = c-1;
		
		v[r-1][0]=true;
		dfs(r-1,0,1);
		
		System.out.println(cnt);
		
	}

}
