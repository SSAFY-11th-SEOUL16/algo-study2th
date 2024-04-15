import java.util.*;

class Solution_표편집 {

	static class Node {
		int pos;
		Node prev, next;

		public Node(int pos) {
			this.pos = pos;
		}

		public String toString() {
			return "pos " + pos;
		}
	}

	public String solution(int n, int k, String[] cmd) {

		Node[] nodes = new Node[n];
		Stack<Node> delete = new Stack<>();
		boolean[] check = new boolean[n];

		for (int i = 0; i < n; i++) {
			nodes[i] = new Node(i);
		}

		for (int i = 0; i < n - 1; i++) {
			nodes[i].next = nodes[i + 1];
		}

		for (int i = 1; i < n; i++) {
			nodes[i].prev = nodes[i - 1];
		}

		Node select = nodes[k];
		int move_num = 0;

		for (int i = 0; i < cmd.length; i++) {
			String[] inputs = cmd[i].split(" ");
			char command = inputs[0].charAt(0);

			if (command == 'D') {
				int move = Integer.parseInt(inputs[1]);
				move_num += move;
			} else if (command == 'U') {
				int move = Integer.parseInt(inputs[1]);
				move_num -= move;
			} else {
				if (move_num > 0) {
					for (int j = 0; j < move_num; j++) {
						select = select.next;
					}
				} else {
					move_num = -1 * move_num;
					for (int j = 0; j < move_num; j++) {
						select = select.prev;
					}
				}
				move_num = 0;
			}
			if (command == 'C') {

				// head인 경우
				if (select.prev == null) {
					Node next = select.next;
					next.prev = null;
				}
				// tail인 경우
				else if (select.next == null) {
					Node prev = select.prev;
					prev.next = null;
				} else {
					Node prev = select.prev;
					Node next = select.next;

					prev.next = next;
					next.prev = prev;
				}

				delete.add(select);

				check[select.pos] = true;

				if (select.next == null)
					select = select.prev;
				else
					select = select.next;
			} else if (command == 'Z') {

				Node pop = delete.pop();

				// pop이 head인경우
				if (pop.prev == null) {
					Node next = pop.next;
					next.prev = pop;
				}
				// pop이 tail인 경우
				else if (pop.next == null) {
					Node prev = pop.prev;
					prev.next = pop;
				} else {
					Node prev = pop.prev;
					Node next = pop.next;

					prev.next = pop;
					next.prev = pop;
				}

				check[pop.pos] = false;
			}
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			if (check[i])
				sb.append('X');
			else
				sb.append('O');
		}
		return sb.toString();
	}
}