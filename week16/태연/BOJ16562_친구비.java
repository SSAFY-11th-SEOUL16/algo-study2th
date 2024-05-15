import java.io.*;
import java.util.*;

public class BOJ16562_친구비 {
	
	/*
	 *  - 160ms
	 * 
	 *  - Union-find 합치는 조건을 친구비 기준으로 작은거 선택
	 */
	
	static int find(int x) {
		if(p[x]==x) return x;
		else return p[x]=find(p[x]);
	}
	
	static void union(int x, int y) {
		int p1 = find(x);
		int p2 = find(y);
		
		if(p1==p2) return;
		else {
			if(cost[p1]>cost[p2]) {
				p[p1] = p2;
			} else {
				p[p2] = p1;
			}
		}
	}
	
	static int[] p;
	static int[] cost;
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		p = new int[n+1];
		cost = new int[n+1];
		
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=n; i++) {
			p[i] = i;
			cost[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			
			int p1 = Integer.parseInt(st.nextToken());
			int p2 = Integer.parseInt(st.nextToken());
			
			union(p1,p2);
		}
		
		int curCost = 0;
		for(int i=1; i<=n; i++) {
			int cur = find(i);
			
			if(cur==0) {
				continue;
			} else {
				union(0,cur);
				curCost += cost[cur];
				if(curCost>k) {
					System.out.println("Oh no");
					return;
				}
			}
		}
		
		System.out.println(curCost);
	}
}
