import java.io.*;
import java.util.*;

class Node {
	int apple;
	int id;
	ArrayList<Node> children;
	public Node(int id) {
		this.id = id;
		children = new ArrayList<Node>();
	}
}


public class BOJ25691_K트리노드에서사과를최대로수확하기 {
	static int answer, n, k;
	static Node[] tree;
	
	public static int traverse(Node node, int visited) {
		if((visited&(1<<(node.id))) == 0) return  0;
		int sum = 0;
		for(Node child: node.children) {
			sum += traverse(child, visited);
		}
		return sum + node.apple;
	}
	
	public static void combination(int depth, int start, int visited) {
		if(depth == k) {
			int appleCount = traverse(tree[0], visited);
			if(answer < appleCount) answer = appleCount;
			return;
		}
		for(int i = start; i < n; i++) {
			combination(depth + 1, i + 1, visited | (1<<i));
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokenizer = new StringTokenizer(input.readLine());
		n = Integer.parseInt(tokenizer.nextToken());
		k = Integer.parseInt(tokenizer.nextToken());
		tree = new Node[n];
		for(int i = 0; i < n; i++) {
			tree[i] = new Node(i);
		}
		for(int i = 0; i < n - 1; i++) {
			tokenizer = new StringTokenizer(input.readLine());
			int u = Integer.parseInt(tokenizer.nextToken());
			int v = Integer.parseInt(tokenizer.nextToken());
			tree[u].children.add(tree[v]);
		}
		tokenizer = new StringTokenizer(input.readLine());
		for(int i = 0; i < n; i++) {
			tree[i].apple = tokenizer.nextToken().charAt(0) - '0';
		}
		combination(1, 1, 1); // root는 무조건 선택
		System.out.println(answer);
	}

}
