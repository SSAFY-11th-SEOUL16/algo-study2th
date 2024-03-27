import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class SW5648_원자소멸시뮬레이션 {
	
	/* 	언어 Java - 실행시간 180ms
	 *  상어 파이어볼 문제처럼 맵을 푸려고 했으니 시간초과 가능성이 높아 포기했고,
	 * 	x, y좌표와 방향을 행, 열로 변환하는 과정이 혼란스러워서 각 원자 입장에서 접근했습니다. 
	*/
	
	static int n,energysum;
	static int[][] move = {{0,1},{0,-1},{-1,0},{1,0}};
	static int[] x,y,d,k;
	static boolean[] explode;
	static ArrayList<int[]> list;
	static Set<Integer> idxset;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int t = Integer.parseInt(st.nextToken());
		for(int test=1; test<=t; test++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			x = new int[n]; y = new int[n]; 
			d = new int[n]; k = new int[n];
			explode = new boolean[n];
			list = new ArrayList<>();
			
			for(int i=0; i<n; i++) {
				st = new StringTokenizer(br.readLine());
				x[i] = Integer.parseInt(st.nextToken())*2+2000; // 0.5에서 부딪힐때 고려 모든 좌표 2배
				y[i] = Integer.parseInt(st.nextToken())*2+2000;
				d[i] = Integer.parseInt(st.nextToken());
				k[i] = Integer.parseInt(st.nextToken());
			}
			
			energysum=0;
			for(int i=0; i<n; i++) {
				for(int j=i+1; j<n; j++) {
					collide(i,j); // 충돌가능한 경우만 list에 저장
				}
			}
			
			list.sort(new Comparator<int[]>() { 
				//int[] - {시간, idx1, idx2} 구성 -> 시간 빠른순 sort
				@Override
				public int compare(int[] o1, int[] o2) {
					return o1[0]-o2[0];
				}
			});
//			for(int i=0; i<list.size(); i++)
//				System.out.println(Arrays.toString(list.get(i)));
			checkCollision(); // 시간 빠른 순서부터 실제 충돌 일어나는 경우 찾기
			
			sb.append("#").append(test).append(" ").append(energysum).append('\n');
		}
		System.out.println(sb);
	}
	// 충돌 가능한지 디버깅하는데 좀 많이 오래걸렸는데, 시간이 음수인 경우는 제외해줘야합니다 
	static void collide(int i,int j) {
		if(d[i]==0) {
			if(d[j]==1 && x[i]==x[j] && y[i]<y[j]) { //상-하
				list.add(new int[] {(y[j]-y[i])/2,i,j});
			}
			else if(d[j]==2 && x[j]-x[i]==y[j]-y[i] && y[j]>y[i]) { //상-좌
				list.add(new int[] {(y[j]-y[i]),i,j});
			}
			else if(d[j]==3 && x[i]-x[j]==y[j]-y[i] && y[j]>y[i]) { //상-우
				list.add(new int[] {(y[j]-y[i]),i,j});
			}
		}
		else if(d[i]==1) {
			if(d[j]==0 && x[i]==x[j] && y[i]>y[j]) { //하-상
				list.add(new int[] {(y[i]-y[j])/2,i,j});
			}
			else if(d[j]==2 && x[j]-x[i]==y[i]-y[j] && y[i]>y[j]) { //하-좌
				list.add(new int[] {(y[i]-y[j]),i,j});
			}
			else if(d[j]==3 && x[i]-x[j]==y[i]-y[j] && y[i]>y[j]) { //하-우
				list.add(new int[] {(y[i]-y[j]),i,j});
			}
		}
		else if(d[i]==2) {
			if(d[j]==0 && x[i]-x[j]==y[i]-y[j] && x[i]>x[j]) { //좌-상
				list.add(new int[] {(x[i]-x[j]),i,j});
			}
			else if(d[j]==1 && x[i]-x[j]==y[j]-y[i] && x[i]>x[j]) { //좌-하
				list.add(new int[] {(x[i]-x[j]),i,j});
			}
			else if(d[j]==3 && x[i]>x[j] && y[i]==y[j]) { //좌-우
				list.add(new int[] {(x[i]-x[j])/2,i,j});
			}
		}
		else {
			if(d[j]==0 && x[j]-x[i]==y[i]-y[j] && x[j]>x[i]) { //우-상
				list.add(new int[] {(x[j]-x[i]),i,j});
			}
			else if(d[j]==1 && x[j]-x[i]==y[j]-y[i] && x[j]>x[i]) { // 우-하
				list.add(new int[] {(x[j]-x[i]),i,j});
			}
			else if(d[j]==2 && x[j]>x[i] && y[i]==y[j]) { //우-좌
				list.add(new int[] {(x[j]-x[i])/2,i,j});
			}
		}
	}
	// 같은 시간에 터질수 있는것들 모두 같이 터트리고 explode true로 저장
	static void checkCollision() {
		idxset = new HashSet<>();
		for(int i=0; i<list.size(); i++) {
			int[] cur = list.get(i);
			if(!explode[cur[1]] && !explode[cur[2]]) {
				idxset.add(cur[1]);
				idxset.add(cur[2]);
				for(int j=i+1; j<list.size(); j++) {
					if(list.get(j)[0]==cur[0] && !explode[list.get(j)[1]] && !explode[list.get(j)[2]]) {
						idxset.add(list.get(j)[1]);
						idxset.add(list.get(j)[2]);
					}
				}
				for(int idx:idxset) {
//					System.out.println("explosion "+idxset);
					explode[idx]=true;
					energysum+=k[idx];
				}
			}
			idxset.clear();
		}
	}
}