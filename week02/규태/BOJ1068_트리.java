package daily;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1068_트리 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		Tree1068 tree = new Tree1068(n);
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++) {
			tree.add(i, Integer.parseInt(st.nextToken()));
		}

		st = new StringTokenizer(br.readLine());
		int del = Integer.parseInt(st.nextToken());
		tree.delete(del);
		
		int leaf = tree.bfs();
		System.out.println(leaf);
	}

}

class Tree1068{
	int[] nodelist;
	int size;
	int root;
	
	public Tree1068(int size) {
		nodelist = new int[size];
		this.size=size;
	}
	public void add(int i,int parent) {
		if(parent==-1)
			root=i;
		nodelist[i] = parent;
	}
	public void delete(int num) {
		if(nodelist[num]!=-1)
			nodelist[num] = -1;
		else
			size=0;
	}
	public boolean isEmpty() {
		return size==0;
	}
	public int bfs() {
		int count=0;
		Queue<Integer> q = new ArrayDeque<Integer>();
		if(isEmpty()) return 0;
		q.offer(root);
		boolean leaf;
		while(!q.isEmpty()) {
			leaf=true;
			int cur = q.poll();
			for(int i=0; i<size; i++) {
				if(nodelist[i]==cur) {
					q.offer(i);
					leaf=false;
				}
			}
			if(leaf)
				count++;
		}
		return count;
	}
}