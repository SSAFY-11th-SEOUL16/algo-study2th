package ps;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * 시간: 124s
 */
public class Main_14725_개미굴 {
	static class Node {
		String str;
		TreeMap<String, Node> children;
		
		public Node(String str) {
			this.str = str;
			this.children = new TreeMap<>();
		}

		public Node getChildNode(String s) {
			if(this.children.containsKey(s)) return this.children.get(s);
			Node child = new Node(s);
			this.children.put(s, child);
			return child;
		}
	}
	
	static int N;
	static Node root;
	static String[] info;
	static StringBuilder sb;
	
	private static void search(int depth, Node parent) {
		for(int i = 0; i < depth; i++) sb.append("--");
		sb.append(parent.str).append("\n");
		
		for(String s: parent.children.keySet()) {
			search(depth + 1, parent.children.get(s));
		}
	}
	
	private static void solve() {
		for(Node p: root.children.values()) search(0, p);
	}
	
	private static void add(Node parent, int index) {
		if(index == info.length) return;
		Node child = parent.getChildNode(info[index]);
		add(child, index + 1);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());

		sb = new StringBuilder();
		root = new Node("root");
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int m = Integer.parseInt(st.nextToken());
			info = new String[m];
			for(int j = 0; j < m; j++) {
				info[j] = st.nextToken();
			}
			add(root, 0);
		}
		
		solve();
		System.out.println(sb);

	}
}
