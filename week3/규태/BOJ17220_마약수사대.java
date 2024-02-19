package daily;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ17220_마약수사대 {
	static int n,m;
	static int[][] drug;
	static boolean[] checkidx;
	static boolean[] visit;
	static boolean[] receive;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		drug = new int[n][n];
		checkidx = new boolean[n]; //조사대상
		visit = new boolean[n]; //조사대상 자식들 모두 true변경, 조사xroot부터 false변경
		receive = new boolean[n]; //공급받는자
		
		for(int i=0; i<m; i++) {//a->b
			String line = br.readLine();
			int a = line.charAt(0)-'A';
			int b = line.charAt(2)-'A';
			drug[a][b]=1;
			receive[b]=true;
		}

		st = new StringTokenizer(br.readLine());
		int c = Integer.parseInt(st.nextToken());
		for(int i=0; i<c; i++) {
			int a = st.nextToken().charAt(0)-'A';
			checkidx[a]=true;
			visit[a]=true;
			dfs(a,true); //조사대상 자식들 일단 모두 true
		}
		
		for(int i=0; i<n; i++) {
			if(!receive[i] && !checkidx[i]) {//조사x root의 자식들 중 조사대상 제외 모두 false
				dfs(i,false);
			}
		}
		
		int count=0;
		for(int i=0; i<n; i++) { // 조사중이 아니면서, 조사받지 않은 부모가 있으면서, 공급받는 사람
//			if(!checkidx[i] && !visit[i] && receive[i]) System.out.println((char)('A'+i));
			count = (!checkidx[i] && !visit[i] && receive[i])? count+1:count;
		}
		System.out.println(count);
	}
	static void dfs(int idx,boolean flag) {
		for(int i=0; i<n; i++) {
			if(drug[idx][i]==1) {
				if(!flag && checkidx[i]) continue;
				visit[i]=flag;
				dfs(i,flag);
			}
		}
	}
}
