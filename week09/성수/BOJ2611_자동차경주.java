// 824ms
import java.io.*;
import java.util.*;

public class Main {
	
	static class Edge implements Comparable<Edge> {
		int to, dist;

		public Edge(int to, int dist) {
			super();
			this.to = to;
			this.dist = dist;
		}

		@Override
		public int compareTo(Edge o) {
			return o.dist - this.dist;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(input.readLine());
		int M = Integer.parseInt(input.readLine());
		ArrayList<Edge>[] graph = new ArrayList[N + 1];
		ArrayList<Edge>[] graph_inv = new ArrayList[N + 1];
		
		Arrays.setAll(graph, idx -> new ArrayList<>());
		Arrays.setAll(graph_inv, idx -> new ArrayList<>());
		int[] inorder = new int[N + 1];
		for(int i = 0; i < M; i++) {
			int p, q, r;
			StringTokenizer tokens = new StringTokenizer(input.readLine());
			p = Integer.parseInt(tokens.nextToken());
			q = Integer.parseInt(tokens.nextToken());
			r = Integer.parseInt(tokens.nextToken());
			graph[p].add(new Edge(q, r));
			if(p != 1) {
				graph_inv[q].add(new Edge(p, r));
				inorder[q]++;
			}
		}
		int[] distances = new int[N + 1];
		int[] path = new int[N + 1];
		Deque<Integer> deque = new ArrayDeque<>();
		for(Edge nxt : graph[1]) {
			if(inorder[nxt.to] == 0) {
				deque.offerLast(nxt.to);
			}
			distances[nxt.to] = nxt.dist;
		}
		graph[1].clear();
		
		while(!deque.isEmpty()) {
			int cur = deque.pollFirst();
			for(Edge nxt : graph[cur]) {
				int tmp = distances[cur] + nxt.dist;
				if(tmp > distances[nxt.to]) {
					distances[nxt.to] = tmp;
					path[nxt.to] = cur;
				}
				inorder[nxt.to] --;
				if(inorder[nxt.to] == 0) {
					deque.offerLast(nxt.to);
				}
			}
		}
		
		System.out.println(distances[1]);
		deque.offerFirst(1);
		while(path[deque.peekFirst()] != 0) {
			int cur = deque.peekFirst();
			deque.offerFirst(path[cur]);
		}
		deque.offerFirst(1);
		for(int i : deque) {
			System.out.print(i + " ");
		}
		System.out.println();
	}
}
