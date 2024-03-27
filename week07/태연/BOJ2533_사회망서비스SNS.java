import java.io.*;
import java.util.*;

public class BOJ2533_사회망서비스SNS {
	/*
	 *  - 2376 ms
	 *
	 *  - 각 노드를 {내가 얼리일때 서브트리의 최소값, 내가 얼리가 아닐때 서브트리의 최소값} 으로 정의
	 *  - 1. 내가 얼리면 -> 밑에 애들 상태에 상관없이 최소값 sum(min(dpT[child][0], dpT[child][1]))
	 *  - 2. 내가 얼리가 아니면 -> 밑에 애들 얼리인 값 (sum(dpT[child][0])
	 */
	
	static class Node{
		int id; 
		List<Integer> nb;
		
		Node(int id){
			this.id=id;
			nb = new ArrayList<>();
		}
	}
	
	static int n;
	static int[][] dpT;
	static Node[] nodes;
	
	static int dfs(Node node, int isEarly, int pID) {
		if(dpT[node.id][isEarly] != -1) {
			return dpT[node.id][isEarly];
		}
		if(isEarly == 0) {
			int sum = 1;
			for(int i=0; i<node.nb.size(); i++) {
				int id = node.nb.get(i);
				if(id==pID) continue;
				
				sum += Math.min(dfs(nodes[id],1, node.id), dfs(nodes[id],0, node.id));
			}
			return dpT[node.id][isEarly]=sum;
		}
		else {
			int sum=0;
			for(int i=0; i<node.nb.size(); i++) {
				int id = node.nb.get(i);
				if(id==pID) continue;
				
				sum += dfs(nodes[id],0, node.id);
			}
			return dpT[node.id][isEarly]=sum;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		
		dpT = new int[n+1][2];		// 0 얼리 1 안얼리
		for(int i=0; i<n+1; i++) {
			dpT[i][0] = -1; dpT[i][1] = -1;
		}
		
		nodes = new Node[n+1];
		for(int i=0; i<n+1; i++) nodes[i]=new Node(i);
		
		// edge 설정
		for(int i=0; i<n-1; i++) {
			st= new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			
			nodes[n1].nb.add(n2); nodes[n2].nb.add(n1);
		}
		
		//dfs
		int ret1 = dfs(nodes[1], 0, 1);	// 얼리
		
		int ret2 = dfs(nodes[1], 1, 1);	// 안얼리
		
		System.out.println(Math.min(ret1, ret2));
	}

}
