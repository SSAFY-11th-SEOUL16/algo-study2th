import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2611_자동차경주 {

	static int n;
	static List<Node>[] roads;
	static int[] degrees;
	static Node[] costs;

	static class Node {
		int to, cost;
		List<Integer> visits = new ArrayList<>();

		public Node(int to) {
			this.to = to;
		}

		public Node(int to, int cost, List<Integer> visits) {
			super();
			this.to = to;
			this.cost = cost;
			this.visits = visits;
		}

		public Node(int cost, List<Integer> visits) {
			super();
			this.cost = cost;
			this.visits = visits;
		}

		public Node(int to, int cost) {
			this.to = to;
			this.cost = cost;
		}

		@Override
		public String toString() {
			return "Node [to=" + to + ", cost=" + cost + ", visits=" + visits + "]";
		}

	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());
		roads = new List[n + 1];
		degrees = new int[n + 1];

		for (int i = 0; i < n + 1; i++) {
			roads[i] = new ArrayList<>();
		}

		for (int i = 0; i < m; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int dest = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			roads[start].add(new Node(dest, cost));
			degrees[dest]++;
		}

		bfs();

		System.out.println(costs[1].cost);
		StringBuilder sb = new StringBuilder();
		for (Integer visit : costs[1].visits) {
			sb.append(visit).append(' ');
		}
		sb.append(1);
		System.out.println(sb);
	}

	static void bfs() {

		Queue<Node> q = new ArrayDeque<>();
		q.add(new Node(1, 0));

		costs = new Node[n + 1];
		for (int i = 1; i < n + 1; i++) {
			costs[i] = new Node(i);
		}

		while (!q.isEmpty()) {

			Node poll = q.poll();
			poll.visits.add(poll.to);

			for (Node road : roads[poll.to]) {
				degrees[road.to]--;
				if (costs[road.to].cost < road.cost + poll.cost) {
					costs[road.to].cost = road.cost + poll.cost;
					costs[road.to].visits = poll.visits;
				}

				if (degrees[road.to] == 0) {
					if (road.to == 1)
						break;
					ArrayList<Integer> temp = new ArrayList<>(costs[road.to].visits);
					q.add(new Node(road.to, costs[road.to].cost, temp));
					costs[road.to] = null;
				}
			}
		}
	}

}
