import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ2610_회의준비 {
	/*
	 * 148ms
	 * 플로이드 워셜 활용하여 INF가 아닌 요소끼리 그룹화하여 저장하고
	 * 각 그룹에서 max의사연결거리가 최소인 사람을 출력 
	 */
	static final int INF=987654321;
	static int n,m;
	static int[][] ppl;
	static boolean[] visit;
	static ArrayList<ArrayList<Integer>> group = new ArrayList<>();
	static PriorityQueue<Integer> pq = new PriorityQueue<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());
		ppl = new int[n+1][n+1];
		visit = new boolean[n+1];
		for(int i=1; i<=n; i++)
			Arrays.fill(ppl[i], INF);
		
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			ppl[a][b]=ppl[b][a]=1;
		}
		
		// 플로이드 워셜
		for(int k=1; k<=n; k++) {
			for(int i=1; i<=n; i++) {
				if(i==k) continue;
				for(int j=1; j<=n; j++) {
					if(i==j || j==k) continue;
					if(ppl[i][j]>ppl[i][k]+ppl[k][j]) {
						ppl[i][j]=ppl[i][k]+ppl[k][j];
					}
				}
			}
		}
		
		// 그룹화 하여 저장
		for(int i=1; i<=n; i++) {
			if(visit[i]) continue;
			visit[i]=true;
			group.add(new ArrayList<>());
			group.get(group.size()-1).add(i);
			for(int j=i+1; j<=n; j++) {
				if(ppl[i][j]!=INF) {
					visit[j]=true;
					group.get(group.size()-1).add(j);
				}
			}
		}
		
//		for(int i=0; i<group.size(); i++)
//			System.out.println(group.get(i));
		
		// 오름차순 정렬 위해 우선순위큐 사용
		System.out.println(group.size());
		for(int i=0; i<group.size(); i++) {
			if(group.get(i).size()==1)
				pq.offer(group.get(i).get(0));
			else 
				pq.offer(check(group.get(i)));
		}
		while(!pq.isEmpty())
			System.out.println(pq.poll());
	}
	static int check(ArrayList<Integer> list) { //각 그룹에서 max의사연결거리가 최소인 사람찾기
		int max=n,idx=-1;
		for(int a:list) {
			int tmp=0;
			for(int b:list) {
				if(a==b) continue;
				tmp=Math.max(tmp, ppl[a][b]);
			}
			if(max>tmp) {
				max=tmp; idx=a;
			}
		}
		return idx;
	}
}