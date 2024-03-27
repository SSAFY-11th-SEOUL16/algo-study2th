import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ9934_완전이진트리 {

	static int n;
	static List<Integer> arr = new ArrayList<>();
	static int check_depth = 0;
	static StringBuilder sb = new StringBuilder();

	static class Node {
		int start, end, depth;

		public Node(int start, int end, int depth) {
			super();
			this.start = start;
			this.end = end;
			this.depth = depth;
		}

	}

	static void printTree() {
		Queue<Node> q = new LinkedList<>();

		q.add(new Node(0, arr.size() - 1, 0));

		while (!q.isEmpty()) {
			Node poll = q.poll();

			if (check_depth != poll.depth) {
				check_depth = poll.depth;
				sb.append('\n');
			}

			int mid = (poll.start + poll.end) / 2;

			sb.append(arr.get(mid)).append(" ");

			if (poll.start < mid) {
				q.add(new Node(poll.start, mid - 1, poll.depth + 1));
			}

			if (poll.end > mid) {
				q.add(new Node(mid + 1, poll.end, poll.depth + 1));
			}
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());

		StringTokenizer st = new StringTokenizer(br.readLine());
		while (st.hasMoreTokens()) {
			arr.add(Integer.parseInt(st.nextToken()));
		}

		printTree();

		System.out.println(sb.toString());
	}

}
