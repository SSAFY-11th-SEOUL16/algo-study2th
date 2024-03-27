import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ14725_°³¹Ì±¼ {
	static int N;
	static ArrayList<Node> tree;
	static StringBuilder sb;

	static class Node {
		String name;
		ArrayList<Node> child;
		boolean visited;

		Node(String name) {
			visited = false;
			this.name = name;
			child = new ArrayList<>();
		}

		@Override
		public String toString() {
			return "Node [name=" + name + "]";
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		sb = new StringBuilder();
		
		tree = new ArrayList<>();
		tree.add(new Node("root"));

		N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			Node now = tree.get(0);
			st = new StringTokenizer(br.readLine());
			int depth = Integer.parseInt(st.nextToken());
			for (int d = 0; d < depth; d++) {
				String s = st.nextToken();
				boolean flag = false;
				for (Node c : now.child) {
					if (c.name.equals(s)) {
						now = c;
						flag = true;
						break;
					}
				}
				if (flag)
					continue;
				Node newChild = new Node(s);
				now.child.add(newChild);
				now = newChild;
			}
		}
		dfs(tree.get(0), 0);
		

	}
	public static void dfs(Node node, int depth) {
		if (node.child.isEmpty()) {
			return;
		}
		else {
			Collections.sort(node.child, (o1, o2)->o1.name.compareTo(o2.name));
		}
		for (Node c : node.child) {
			if (c.visited)
				continue;
			c.visited = true;
			for (int i = 0; i < depth; i++)
				System.out.print("--");
			System.out.println(c.name);
			dfs(c, depth+1);
		}
	}

}
