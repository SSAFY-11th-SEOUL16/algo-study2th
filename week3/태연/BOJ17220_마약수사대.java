import java.io.*;
import java.util.*;

class Node17220 {

	int id;
	ArrayList<Node17220> child = new ArrayList<Node17220>();
	ArrayList<Node17220> parent = new ArrayList<Node17220>();

	Node17220(int id){
		this.id=id;
	}

	void cut() {
		for(Node17220 p: parent)
			p.child.remove(this);
	}

	int getChildN() {
		return child.size();
	}
	
	int getParentN() {
		return parent.size();
	}

	boolean hasChild() {
		if (getChildN() > 0) return true;
		return false;
	}
	
	boolean hasParent() {
		if (getParentN() > 0) return true;
		return false;
	}
}

public class BOJ17220_마약수사대 {

	Node17220[] nodes;
	static Node17220 root = new Node17220(-1);			// 0번노드 삭제를 위한 더미 루트
	static Queue<Node17220> q = new LinkedList<>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int rel = Integer.parseInt(st.nextToken());
		int v=0;
		
		Node17220[] nodes = new Node17220[n];


		for (int i = 0; i < n; i++) {
			nodes[i] = new Node17220(i);
		}

		for (int i = 0; i < rel; i++) {
			char[] c = br.readLine().toCharArray();
			int n1 = c[0]-'A';
			int n2 = c[2]-'A';
			
			nodes[n1].child.add(nodes[n2]);
			nodes[n2].parent.add(nodes[n1]);
		}
		
		for(int i=0; i<n; i++) {
			if(!nodes[i].hasParent()) {root.child.add(nodes[i]); nodes[i].parent.add(root);}
		}

		// 마약 원산지를 찾기 위해 부모가 없으면 root의 child로 
		
		st = new StringTokenizer(br.readLine());
		int toCut = Integer.parseInt(st.nextToken());

		for(int i=0; i<toCut; i++) {
			nodes[st.nextToken().charAt(0)-'A'].cut();
		}

		int cnt = -1*root.child.size();		// 마약의 원산지와 루트는 cnt에서 제외
		
		q.add(root);
		while (!(q.isEmpty())) {
			Node17220 cursor = q.poll();
			cnt++;

			for (Node17220 node : cursor.child) {
				if((v&(1<<node.id))==0){
					v|=(1<<node.id);
					q.add(node);
				}
			}
		}

		System.out.println(cnt-1);
	}
}