import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ2611_자동차경주 { // 568ms
	static int N, M;
	static ArrayList<Info>[] graph;
	static int[] prev;
	static int[] topo;
	static int[] dp;
	static StringBuilder sb;
	
	static class Info {
		int n;
		int weight;
		Info(int n, int weight) {
			this.n = n;
			this.weight = weight;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		topo = new int[N+1];
		graph = new ArrayList[N+1];
		prev = new int[N+1];
		dp = new int[N+1];
		
		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int p = Integer.parseInt(st.nextToken());
			int q = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			graph[p].add(new Info(q, r));
			topo[q]++;
		}
		
		tSort();
		System.out.println(dp[1]);
		if (N == 1) {
			System.out.println(1);
		}
		else {
			getRoute();
			System.out.println(sb);	
			System.out.println(Arrays.toString(dp));
		}
	}
	public static void tSort() {
		Queue<Integer> q = new LinkedList<>();
		q.offer(1);
		
		while (!q.isEmpty()) {
			int now = q.poll();
			for (Info next : graph[now]) {
				topo[next.n]--;
				
				if (dp[next.n] < dp[now] + next.weight) {
					dp[next.n] = dp[now] + next.weight;
					prev[next.n] = now;
				}
				if (topo[next.n] == 0) {
					q.offer(next.n);
				}
			}
		}
	}
	
	public static void getRoute() {
		Queue<Integer> q = new LinkedList<>();
		Stack<Integer> stack = new Stack<>();
		q.offer(1);
		while(!q.isEmpty()) {
			int now = q.poll();
			if (prev[now] != 1) {
				stack.add(prev[now]);
				q.offer(prev[now]);
			}
			else {
				break;
			}
		}
		sb.append(1).append(" ");
		while(!stack.isEmpty()) {
			sb.append(stack.pop()).append(" ");
		}
		sb.append(1);
	}
}
