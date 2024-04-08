import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ12893_적의적 {
	/*
	 * 752ms
	 * 방문 안한 사람부터 시작해 bfs로 몇번 건너 아는 사람인지 탐색
	 * loop이 홀수개로 이뤄져 있으면 적도 아군도 아닌 말도 안되는 상황
	 * loop가 없거나 모든 loop이 짝수개로 이뤄져 있으면 가능
	 */
	static int n,m;
	static int idx[];
	static boolean oddloop, visit[];
	static ArrayList<Integer>[] enemy;
	static Queue<Integer> q = new ArrayDeque<>();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		idx = new int[n+1];
		visit = new boolean[n+1];
		enemy = new ArrayList[n+1];
		for(int i=1; i<=n; i++)
			enemy[i] = new ArrayList<>();
		
		for(int i=1; i<=m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			enemy[a].add(b); enemy[b].add(a);
		}
		
		for(int i=1; i<=n; i++) {
			if(visit[i]) continue;
			visit[i]=true;
			bfs(i);
			if(oddloop) break;
		}
		
		if(oddloop) System.out.println(0);
		else System.out.println(1);
		
	}	
	static void bfs(int start) {
		idx[start]=1;
		q.offer(start);
		while(!q.isEmpty()) {
			int cur = q.poll();
			for(int next:enemy[cur]) {
				if(visit[next]) {
					if((idx[next]-idx[cur])%2==0) {
						oddloop=true;
						break;
					}
				}
				else {
					visit[next]=true;
					idx[next]=idx[cur]+1;
					q.offer(next);
				}
			}
		}
	}
}
