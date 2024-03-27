import java.io.*;
import java.util.*;

public class BOJ14725_개미굴 {
	/*
	 *  - 128ms
	 * 
	 *  - 기본적인 트라이 구조 활용
	 */
	
	static class TrieNode implements Comparable<TrieNode>{
		int depth;
		String value;
		
		TreeSet<TrieNode> next;
		
		TrieNode(int depth, String vaule){
			this.depth=depth;
			this.value = vaule;
			this.next = new TreeSet<>();
		}
		
		String print() {
			StringBuilder sb = new StringBuilder();
			for(int i=0; i<depth; i++) sb.append("--");
			sb.append(value);
			
			return sb.toString();
		}

		@Override
		public int compareTo(TrieNode o) {
			return this.value.compareTo(o.value);
		}
	}
	
	static void preorderPrint(TrieNode node) {
		if(node.depth!=-1) {
			result.append(node.print()).append("\n");
		}
		while(!node.next.isEmpty()) {
			preorderPrint(node.next.pollFirst());
		}
	}
	
	static StringBuilder result;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		result = new StringBuilder();
		
		int n = Integer.parseInt(st.nextToken());
		TrieNode root = new TrieNode(-1, "");		// 탐색 편의를 위한 더미 루트노드 	
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			
			int m = Integer.parseInt(st.nextToken());
			TrieNode cur = root;
			TrieNode newNode;
			for(int j=0; j<m; j++) {
				String value = st.nextToken();
				if(!cur.next.contains((newNode = new TrieNode(cur.depth+1, value)))) {
					cur.next.add(newNode);
					cur = newNode;
				}
				else {
					cur = cur.next.floor(newNode);
				}
			}
		}
		
		preorderPrint(root);
		
		System.out.println(result);
	}

}
