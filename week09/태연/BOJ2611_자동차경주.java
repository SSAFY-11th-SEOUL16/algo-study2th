import java.io.*;
import java.util.*;

public class BOJ2611_자동차경주 {

	/*
	 *  - 684ms
	 *  
	 *  - 다익스트라 BFS했다가 시간터지고 메모리터져서 위상정렬로 바꿈
	 * 
	 *  - 사실 Edge List 2개 안쓰고 개수로 풀어도 되는데 디버깅하는데 머리아파서 메모리 혹사시킴
	 */
	
	static class Edge implements Comparable<Edge>{
		int from;
		int to;
		int w;
		
		Edge(int from, int to, int w){
			this.from=from;
			this.to=to;
			this.w=w;
		}
		
		public int compareTo(Edge e) {
			return e.w-this.w;
		}

	}
	
	// prev 배열 저장해 놓고 재귀로 경로 프린트
	static void print(int[] prev, StringBuilder sb, int i) {
		if(prev[i]==1) {
			sb.append(1).append(" ");
			return;
		}
		print(prev, sb, prev[i]);
		sb.append(prev[i]).append(" ");
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		ArrayList<Edge>[] to = new ArrayList[n+1];
		ArrayList<Edge>[] from = new ArrayList[n+1];
		int[] w = new int[n+1];
		int[] prev = new int[n+1];
		
		for(int i=1; i<=n; i++) {
			to[i] = new ArrayList<>();
			from[i] = new ArrayList<>();
		}
		
		int m = Integer.parseInt(br.readLine());
		
		for(int i=0; i<m; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			int n3 = Integer.parseInt(st.nextToken());

			Edge newEdge = new Edge(n1,n2,n3);
			from[n1].add(newEdge);
			to[n2].add(newEdge);
		}
		
		Queue<Edge> q = new LinkedList<>();
		q.addAll(from[1]);
		
		while(!q.isEmpty()) {
			Edge cur = q.poll();
			
			to[cur.to].remove(cur);
			
			if(w[cur.to] < cur.w + w[cur.from]) {
				prev[cur.to]= cur.from;
				w[cur.to]= cur.w + w[cur.from];
			}
			
			if(to[cur.to].size()==0) {
				if(cur.to==1) break;
				else q.addAll(from[cur.to]);
			}
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append(w[1]).append("\n");

		print(prev, sb, 1);
		
		sb.append("1");
		System.out.println(sb);
	}

}
