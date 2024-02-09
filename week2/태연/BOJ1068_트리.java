import java.io.*;
import java.util.*;

class Node1068 {

	ArrayList<Node1068> child = new ArrayList<Node1068>();
	Node1068 parent = null;

	void cut() {
		this.parent.child.remove(this);
	}

	int getChildN() {
		return child.size();
	}

	boolean hasChild() {
		if (getChildN() > 0) return true;
		return false;
	}
}

public class BOJ1068_트리 {

	Node1068[] nodes;
	static Node1068 root = new Node1068();			// 0번노드 삭제를 위한 더미 루트
	static Queue<Node1068> q = new LinkedList<>();
	

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		Node1068[] nodes = new Node1068[n];
		StringTokenizer st = new StringTokenizer(br.readLine());

		for (int i = 0; i < n; i++) {
			nodes[i] = new Node1068();
		}

		for (int i = 0; i < n; i++) {
			int idx = Integer.parseInt(st.nextToken());

			if (idx != -1) {
				nodes[idx].child.add(nodes[i]);
				nodes[i].parent=nodes[idx];
			} else {
				root.child.add(nodes[i]);
				nodes[i].parent=root;
			}
		}

		nodes[Integer.parseInt(br.readLine())].cut();

		int cnt = 0;
		q.add(root);
		while (!(q.isEmpty())) {
			Node1068 cursor = q.poll();

			for (Node1068 node : cursor.child) {
				if (node.hasChild())
					q.add(node);
				else
					cnt++;
			}
		}

		System.out.println(cnt);
	}
}