import java.util.Stack;

/**
 * 효율성
 * 테스트 1 〉	통과 (237.19ms, 163MB)
 * 테스트 2 〉	통과 (208.74ms, 132MB)
 * 테스트 3 〉	통과 (220.68ms, 131MB)
 * 테스트 4 〉	통과 (301.60ms, 153MB)
 * 테스트 5 〉	통과 (292.13ms, 153MB)
 * 테스트 6 〉	통과 (299.25ms, 172MB)
 * 테스트 7 〉	통과 (146.49ms, 100MB)
 * 테스트 8 〉	통과 (195.66ms, 105MB)
 * 테스트 9 〉	통과 (295.19ms, 154MB)
 * 테스트 10 〉 통과 (288.85ms, 155MB)
 */
public class PG81303_표편집 {
	static class Node {
		int idx;
		int prev, next;
		boolean remove;
		public Node(int idx, int prev, int next, boolean remove) {
			this.idx = idx;
			this.prev = prev;
			this.next = next;
			this.remove = remove;
		}
		@Override
		public String toString() {
			return "Node [idx=" + idx + ", prev=" + prev + ", next=" + next + ", remove=" + remove + "]";
		}

	}
	static Node[] rows;
	static Node curr;
	static Stack<Integer> removes;

	private static void move(boolean up, int x) {
		while(x-- > 0) {
			curr = up ? rows[curr.prev] : rows[curr.next];
		}
	}

	private static void remove() {
		int prev = curr.prev;
		int next = curr.next;

		if(prev != -1) rows[prev].next = next;
		if(next != -1) rows[next].prev = prev;

		removes.add(curr.idx);
		curr.remove = true;
		curr = next == -1 ? rows[prev] : rows[next];
	}

	private static void restore() {
		int node = removes.pop();
		int prev = rows[node].prev;
		int next = rows[node].next;

		if(prev != -1) rows[prev].next = node;
		if(next != -1) rows[next].prev = node;

		rows[node].remove = false;
	}

	private static String command(String[] cmds) {
		for(String cmd : cmds) {
			String[] c = cmd.split(" ");
			if(c[0].equals("U")) move(true, Integer.parseInt(c[1]));
			else if(c[0].equals("D")) move(false, Integer.parseInt(c[1]));
			else if(c[0].equals("C")) remove();
			else if(c[0].equals("Z")) restore();
		}

		StringBuilder sb = new StringBuilder();
		for(Node row : rows) {
			sb.append(row.remove ? "X" : "O");
		}
		return sb.toString();
	}

	public static String solution(int n, int k, String[] cmd) {
		rows = new Node[n];
		for(int i = 1; i < n - 1; i++) {
			rows[i] = new Node(i, i - 1, i + 1, false);
		}
		rows[0] = new Node(0, -1, 1, false);
		rows[n - 1] = new Node(n - 1, n - 2, -1, false);

		curr = rows[k];
		removes = new Stack<>();
		return command(cmd);
	}
}
