import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class BOJ14725_개미굴 {
	/*
	 * 트라이 개념을 잘 모르지만 알아보고 싶어서 관련 개념 학습 후 코드를 작성했습니다.
	 */
	static int n;
	static class Node {
		Map<String,Node> childnode = new HashMap<>();
	}
	static class Trie{
		Node root = new Node();
		void insert(String[] list) {
			Node curnode = root;
			for(int lvl=0; lvl<list.length; lvl++) {
				String food = list[lvl];
				if(curnode.childnode.get(food)==null)
					curnode.childnode.put(food, new Node());
				curnode = curnode.childnode.get(food);
			}
		}
		void show(int lvl, Node curnode) {
			String[] children = new String[curnode.childnode.keySet().size()];
			children = curnode.childnode.keySet().toArray(children);
			
			if(children.length==0) return;
			Arrays.sort(children);
			for(String food:children) {
//				System.out.print("--".repeat(lvl-1)); -> java11기준 잘 작동됨
				System.out.println(food);
				show(lvl+1,curnode.childnode.get(food));
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());

		Trie trie = new Trie();
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			int k = Integer.parseInt(st.nextToken());
			String[] foodlist = new String[k];
			for(int j=0; j<k; j++)
				foodlist[j] = st.nextToken();
			trie.insert(foodlist);
		}
		
		trie.show(1, trie.root);
	}
}