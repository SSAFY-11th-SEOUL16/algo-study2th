package algostudy.week9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * 시간: 576ms
 */
public class Main_2611_자동차경주 {
	static class Node implements Comparable<Node>{
		int node;
		int dist;
		
		public Node(int node, int dist) {
			this.node = node;
			this.dist = dist;
		}
		
		@Override
		public int compareTo(Node o) {
			return o.dist - this.dist;
		}
	}
	static int N;
	static int M;
	static List<List<Node>> graph;
	static int[] parent;
	static int[] indegree;
	static StringBuilder sb;
	
	private static void findPath(int x) {
		if(x == 1) return;
		if(parent[x] != 1) findPath(parent[x]);
		sb.append(x).append(" ");
	}
	
	private static int solve(int start) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		int[] dist = new int[N + 1];
		
		pq.add(new Node(start, 0));
		
		while(!pq.isEmpty()) {
			Node here = pq.poll();
			
			if(here.node == 1 && indegree[1] == 0) break;
			
			for(Node next : graph.get(here.node)) {
				int nextDist = here.dist + next.dist;
				
				indegree[next.node] -= 1;
				if(dist[next.node] == -1 || dist[next.node] < nextDist) {
					dist[next.node] = nextDist;
					parent[next.node] = here.node;
				}
				if(indegree[next.node] == 0) pq.add(new Node(next.node, dist[next.node]));
			}
		}
		return dist[start];
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		
		indegree = new int[N + 1];
		parent = new int[N + 1];
		graph = new ArrayList<>();
		for(int i = 0; i <= N; i++) graph.add(new ArrayList<>());
		
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			graph.get(u).add(new Node(v, cost));
			indegree[v] += 1;
		}
		
		System.out.println(solve(1));
		sb = new StringBuilder();
		sb.append("1 ");
		findPath(parent[1]);
		sb.append("1");
		System.out.println(sb);
	}
}
