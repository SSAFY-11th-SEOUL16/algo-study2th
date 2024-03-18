import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class BOJ1414_불우이웃돕기 {

	static int n;
	static int[][] adjList;
	static int total;

	static class Edge {
		int from, cost;

		public Edge(int from, int cost) {
			this.from = from;
			this.cost = cost;
		}

		@Override
		public String toString() {
			return "Edge [from=" + from + ", cost=" + cost + "]";
		}

	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		adjList = new int[n][n];

		total = 0;
		for (int i = 0; i < n; i++) {
			String s = br.readLine();
			for (int j = 0; j < n; j++) {
				char c = s.charAt(j);
				int num = 0;
				if ((int) ('A') <= (int) (c) && (int) (c) <= (int) ('Z')) {
					num = (int) (c - 'A') + 27;
				} else if ((int) ('a') <= (int) (c) && (int) (c) <= (int) ('z')) {
					num = (int) (c - 'a') + 1;
				}
				adjList[i][j] = num;
				total += num;
			}
		}

		System.out.println(minSpanning());
	}

	private static int minSpanning() {

		PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);

		int result = 0;
		boolean[] visited = new boolean[n];
		int visitNum = 0;
		pq.add(new Edge(0, 0));

		while (!pq.isEmpty()) {
			
			Edge poll = pq.poll();

			if (visited[poll.from]) continue;
			
			visited[poll.from] = true;
			visitNum++;
			result += poll.cost;
			
			for (int i = 0; i < n; i++) {
				if (poll.from != i && !visited[i]) {
					if (adjList[poll.from][i] != 0) {
						pq.add(new Edge(i, adjList[poll.from][i]));
					}
					if (adjList[i][poll.from] != 0) {
						pq.add(new Edge(i, adjList[i][poll.from]));
					}
				}
			}
		}
		

		if (visitNum != n)
			return -1;

		int min_result = Integer.MAX_VALUE;

		visited = new boolean[n];
		for (int idx = 0; idx < n; idx++) {
			result = 0;
			if (visited[idx]) continue;
			
			pq.add(new Edge(idx, 0));
			while (!pq.isEmpty()) {
				Edge poll = pq.poll();

				if (visited[poll.from]) continue;
				visited[poll.from] = true;
				result += poll.cost;

				for (int i = 0; i < n; i++) {
					if (poll.from != i && !visited[i]) {
						if (adjList[poll.from][i] != 0) {
							pq.add(new Edge(i, adjList[poll.from][i]));
						}
						if (adjList[i][poll.from] != 0) {
							pq.add(new Edge(i, adjList[i][poll.from]));
						}
					}
				}
			}
			
			min_result = Math.min(result, min_result);
		}
		if (n == 1) return total;

		if (min_result == 0) return 0;
		
		return total - min_result;

	}

}
