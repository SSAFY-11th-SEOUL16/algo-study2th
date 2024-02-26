import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ15684_사다리조작 {
	static int n,m,h,min=Integer.MAX_VALUE;
	static int[][] ladder;
	static boolean flag=false;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken()); // 세로
		m = Integer.parseInt(st.nextToken()); // 가로
		h = Integer.parseInt(st.nextToken()); // 놓을수 있는 위치
		ladder = new int[h+1][n+1];
		
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()); // a 위치에서
			int b = Integer.parseInt(st.nextToken()); // b - b+1 연결
			ladder[a][b]=1;
		}
		
		if(m==0)
			min=0;
		else {
			dfs(0,0);
		}
		
		if(min>3)
			min=-1;
		
		System.out.println(min);
	}
	static void dfs(int add,int cnt) {
		if(flag) return;
		if(cnt==3) {
			play(add);
			return;
		}
		dfs(add,cnt+1);
		for(int i=1; i<=h; i++) {
			for(int j=1; j<n; j++) {
				if(ladder[i][j]!=1 && ladder[i][j+1]!=1) {
					ladder[i][j]=1;
					dfs(add+1,cnt+1);
					ladder[i][j]=0;
				}
			}
		}
	}
	static void play(int add) {
		for(int i=1; i<=n; i++) {
			int idx = i;
			for(int lvl=1; lvl<=h; lvl++) {
				if(ladder[lvl][idx]==1)
					idx+=1;
				else if(ladder[lvl][idx-1]==1)
					idx-=1;
			}
			if(idx!=i)
				return;
		}
		flag=true;
		if(min>add)
			min=add;
	}
}
