import java.io.*;
import java.util.*;

class Node {
	List<Node> children;
	int idx;
	public Node(int idx) {
		this.idx = idx;
		children = new ArrayList<Node>();
	}
}

public class Main {
	static int answer = 0;
	
	public static int traverse(Node node) {
		if(node.idx < -1) return 0;
		int kidCount = 0;
		for(Node children: node.children) {
			kidCount += traverse(children);
		}
		if(kidCount == 0) {
			answer++;
		}
		return kidCount + 1; // 플러스 1 해서 현재 노드를 개수에 포함해 반환
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(input.readLine());
		Node[] tree = new Node[N];
		StringTokenizer tokenizer = new StringTokenizer(input.readLine());
		for(int i = 0; i < N; i++) {
			tree[i] = new Node(i);
		}
		int rootIdx = -1;
		for(int i = 0; i < N; i++) {

			int parentIdx = Integer.parseInt(tokenizer.nextToken());
			if(parentIdx == -1) {
				rootIdx = i;
				continue;
			}
			tree[parentIdx].children.add(tree[i]);
		}
		int deleteIdx = Integer.parseInt(input.readLine());
		tree[deleteIdx].idx = -999;
		traverse(tree[rootIdx]);
		System.out.println(answer);
	}

}
