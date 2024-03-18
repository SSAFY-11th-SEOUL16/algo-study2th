package ps;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


/**
 * 시간: 88ms
 */
public class Main_1414_불우이웃돕기 {
	static class Edge implements Comparable<Edge>{
		int u, v;
		int dist;
		public Edge(int u, int v, int dist) {
			this.u = u;
			this.v = v;
			this.dist = dist;
		}
		@Override
		public int compareTo(Edge o) {
			return this.dist - o.dist;
		}
	}
	static int N;
	static int[] parent;
	static boolean[][] connect;
	static int totalDist;
	static PriorityQueue<Edge> edges;
	
	public static int getCost(char c) {
		if('a' <= c && c <= 'z') return (int) (c - 'a') + 1;
		return (int) (c - 'A') + 27;
	}
	
	private static int findParent(int x) {
		if(x == parent[x]) return x;
		return parent[x] = findParent(parent[x]);
	}
	
	private static void union(int uP, int vP) {
		if(uP < vP) parent[vP] = uP;
		else parent[uP] = vP;
	}
	
	private static void dfs(int curr, boolean[] visited) {
		visited[curr] = true;
		for(int next = 0; next < N; next++) {
			if(!connect[curr][next]) continue;
			if(!visited[next]) dfs(next, visited);
		}
	}
	
	private static boolean isValid() {
		boolean[] check = new boolean[N];
		dfs(0, check);
		for(int i = 0; i < N; i++) {
			if(!check[i]) return false;
		}
		return true;
	}
	
	private static int solve() {
		if(!isValid()) return -1;
		
		int sumDist = 0;
		while(!edges.isEmpty()) {
			Edge edge = edges.poll();
			
			int uP = findParent(edge.u);
			int vP = findParent(edge.v);
			if(uP != vP) {
				union(uP, vP);
				sumDist += edge.dist;
			}
		}
		
		return totalDist - sumDist;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		parent = new int[N];
		for(int i = 0; i < N; i++) parent[i] = i;
		
		connect = new boolean[N][N];
		totalDist = 0;
		edges = new PriorityQueue<>();
		for(int i = 0; i < N; i++) {
			char[] tmp = br.readLine().toCharArray();
			for(int j = 0; j < N; j++) {
				if(tmp[j] == '0') continue;
				int cost = getCost(tmp[j]);
				totalDist += cost;
				edges.add(new Edge(i, j, cost));
				connect[i][j] = true;
				connect[j][i] = true;
			}
		}
		System.out.println(solve());
	}
}
