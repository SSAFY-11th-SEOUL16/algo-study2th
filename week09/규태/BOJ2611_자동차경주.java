import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ2611_자동차경주 {
	/*
	 * 1088ms
	 * bfs로 순회하며 dp 배열에 최고 점수 업데이트
	 * 최고 점수를 획득하는 서킷을 출력하기 위해 각 노드의 직전 노드를 저장, 업데이트
	 */
	static int n,m;
	static int[] before, dp;
	static ArrayList<int[]>[] list;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());
		list = new ArrayList[n+1];
		before = new int[n+1];
		dp = new int[n+1];
		
		Arrays.fill(dp, -1);
		for(int i=1; i<=n; i++)
			list[i] = new ArrayList<>();
		
		
		StringTokenizer st;
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int point = Integer.parseInt(st.nextToken());
			list[a].add(new int[] {b,point});
		}
		
		dp[1]=0;
		bfs();
		System.out.println(dp[1]);
		showCircuit();
	}
	static void bfs() {
		PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[1]-o2[1];
			}
		});
		pq.offer(new int[] {1,0});
		
		while(!pq.isEmpty()) {
			int[] cur = pq.poll();
			int curnode = cur[0];
			int curpoint = cur[1];
			if(dp[curnode]>curpoint) continue;
			
			for(int[] next:list[curnode]) {
				int nextnode = next[0];
				int point = next[1];
				if(dp[nextnode]<curpoint+point) {
					dp[nextnode]=curpoint+point;
					before[nextnode]=curnode;
					if(nextnode!=1) // 다음 노드가 출발지이면 큐에 추가하지 않음
						pq.offer(new int[] {nextnode,dp[nextnode]});
				}
			}
		}
	}
	static void showCircuit() {
		String str="1";
		int idx=before[1];
		while(idx!=1) {
			str=idx+" "+str;
			idx=before[idx];
		}
		str=idx+" "+str;
		System.out.println(str);
	}
}
