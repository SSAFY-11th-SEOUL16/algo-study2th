package algostudy.week9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 시간: 252ms
 * 현우님 블로그 참고했습니다.
 */
public class Main_6086_최대유량 {
	static class Edge {
		int to, capacity, flow;
		Edge reverse;
		public Edge(int to, int capacity) {
			this.to = to;
			this.capacity = capacity;
			this.flow = 0;
		}
		
	}
	static int N;
	static HashMap<Character, Integer> map;
	static List<List<Edge>> adj;
	static int[] prev;
	static Edge[] path;

	private static boolean bfs(int source, int sink) {
		Queue<Integer> queue = new LinkedList<>();
		Arrays.fill(prev, -1);
		
		queue.add(source);
		
		while(!queue.isEmpty()) {
			int curr = queue.poll();
			for (Edge next : adj.get(curr)) {
				if (next.capacity > next.flow && prev[next.to] == -1) {
					queue.add(next.to);
					prev[next.to] = curr;
					path[next.to] = next;
					if (next.to == sink)
						return true;
				}
			}
		}
		return false;
	}
	
	private static int maxFlow(int source, int sink) {
		int totalFlow = 0;
		while(bfs(source, sink)) {
			int minFlow = 1001;
			for(int i = sink; i != source; i = prev[i]) {
				minFlow = Math.min(minFlow, path[i].capacity - path[i].flow);
			}
			for(int i = sink; i != source; i = prev[i]) {
				path[i].flow += minFlow;
				path[i].reverse.flow -= minFlow;
			}
			totalFlow += minFlow;
		}
		return totalFlow;
	}
	
	private static void addAdj(int from, int to, int capacity) {
		Edge forward = new Edge(to, capacity);
		Edge backward = new Edge(from, capacity);
		forward.reverse = backward;
		backward.reverse = forward;
		adj.get(from).add(forward);
		adj.get(to).add(backward);
	}
	
	private static int getNum(char ch) {
		return map.computeIfAbsent(ch, k -> map.size());
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		
		adj = new ArrayList<>();
		for(int i = 0; i < N; i++) adj.add(new ArrayList<>());
		
		map = new HashMap<>();
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int u = getNum(st.nextToken().charAt(0));
			int v = getNum(st.nextToken().charAt(0));
			int cost = Integer.parseInt(st.nextToken());
			addAdj(u, v, cost);
		}
		
		prev = new int[N];
		path = new Edge[N];
		System.out.println(maxFlow(map.get('A'), map.get('Z')));
	}

}
