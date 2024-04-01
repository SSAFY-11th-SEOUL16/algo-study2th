import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ6086_최대유량 {
	/*
	 * 88ms
	 * 양방향으로 흐를 수 있으므로 양쪽에 가능한 capacity flow 추가한 후
	 * bfs로 가능한 경로를 찾고 해당 경로의 max flow를 더하고 흐를 수 있는 양 update
	 * 단, 상황에 따라 이미 물이 흐르고 있는 경로와 반대로 흘러야 더 보낼 수 있는 경우가 있으므로 고려해서 업데이트
	 * 업데이트가 진행되지 않는 경우 종료
	 */
	static int n, ans =0, before[], capflow[][], curflow[][];
	static ArrayList<Integer>[] pipenode = new ArrayList[52];
	static Queue<Integer> q = new ArrayDeque<>(); 
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		before = new int[52];
		capflow = new int[52][52];
		curflow = new int[52][52];
		
		for(int i=0; i<52; i++)
			pipenode[i] = new ArrayList<>();
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			int a = convert(st.nextToken().charAt(0));
			int b = convert(st.nextToken().charAt(0));
			int cap = Integer.parseInt(st.nextToken());
			pipenode[a].add(b);
			pipenode[b].add(a);
			capflow[a][b]+=cap;
			capflow[b][a]+=cap;
		}
		
		boolean flag=true;
		while(flag) {
			bfs();
//			System.out.println(Arrays.toString(before)+ans);
			if(before[25]==-1)
				flag=false;
			else
				updateflow();
		}
		
		System.out.println(ans);
	}
	static void bfs() {
		Arrays.fill(before, -1);
		q.offer(0);
		while(!q.isEmpty()) {
			int cur = q.poll();
			for(int next:pipenode[cur]) {
				if(capflow[cur][next]<=curflow[cur][next] || before[next]!=-1) continue;
				before[next]=cur;
//				System.out.println(cur+" "+next);
				q.offer(next);
			}
		}
		q.clear();
	}
	static void updateflow() {
		int inf=100000000;
		int min=inf;
		for(int cur=25; cur!=0; cur=before[cur]) {
//			System.out.print((char)(cur+'A')+" ");
			min = Math.min(capflow[before[cur]][cur]-curflow[before[cur]][cur], min);
		}
//		System.out.println('A');
		for(int cur=25; cur!=0; cur=before[cur]) {
			curflow[before[cur]][cur]+=min;
			curflow[cur][before[cur]]-=min;
		}
		ans+=min;
	}
	static int convert(char c) {
		if(c>='A' && c<= 'Z')
			return c-'A';
		return c-'a'+26;
	}
}
