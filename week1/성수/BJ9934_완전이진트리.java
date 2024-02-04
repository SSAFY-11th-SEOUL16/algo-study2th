import java.io.*;
import java.util.StringTokenizer;


public class BJ9934_완전이진트리 {

	static int[] tree;
	static int[] binaryTree;
	static StringBuilder output = new StringBuilder();
	static int N;
	
	public static void traverse(int left, int right, int idx) {
		if(left > right) {
			return;
		}
		int mid = (left + right) / 2;
		binaryTree[idx] = tree[mid];
		traverse(left, mid - 1, idx * 2 + 1);
		traverse(mid + 1, right, idx * 2 + 2);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		int K = Integer.parseInt(input.readLine());
		N = (int) Math.pow(2, K) - 1;
		tree = new int[N];
		binaryTree = new int[N];
		StringTokenizer tokenizer = new StringTokenizer(input.readLine());
		for(int i = 0; i < N; i++) {
			tree[i] = Integer.parseInt(tokenizer.nextToken());
		}
		traverse(0, N - 1, 0);
		int levelCount = 2;
		int idx = 0;
		while(levelCount - 1 <= N) {
			for(;idx < levelCount - 1; idx++)
				output.append(binaryTree[idx]).append(" ");
			output.append("\n");
			levelCount *= 2;
		}
		System.out.println(output);
	}

}
