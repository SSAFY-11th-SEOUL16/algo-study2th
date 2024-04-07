import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ12893_적의적 { // 780ms
	static ArrayList<Integer>[] graph;
	static int[] dist;
	static boolean[] visited;
	static int N, M;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		graph = new ArrayList[N+1];
		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}
		dist = new int[N+1];
		visited = new boolean[N+1];
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph[a].add(b);
			graph[b].add(a);
		}
		for (int i = 1; i <= N; i++) {
			if (!visited[i]) {
				if (!bfs(i)) {
					System.out.println(0);
					return;
					}
			}
		}
		System.out.println(1);
	}

	public static boolean bfs(int start) {
		Queue<Integer> q = new LinkedList<>();
		q.offer(start);
		visited[start] = true;
		dist[start] = 0;
		
		while (!q.isEmpty()) {
			int now = q.poll();
			for (int v : graph[now]) {
				if (visited[v]) {
					if (dist[v] == dist[now]) {
						return false;
					}
				}
				else {
					visited[v] = true;
					dist[v] = dist[now] + 1;
					q.offer(v);
				}
			}
		}
		return true;
	}
}