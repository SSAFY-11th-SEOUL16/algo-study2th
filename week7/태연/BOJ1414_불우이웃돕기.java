import java.io.*;
import java.util.*;

public class BOJ1414_불우이웃돕기 {
	
	/*
	 * - 228 ms
	 * 
	 * - 크루스칼 mst
	 */
	
	static int c2i(char c) {
		if(c=='0') return 0;
		else if(c>='a')
			return c-'a'+1;
		else return c-'A'+27;
	}
	
	static boolean union(int x, int y) {
		int p1 = find(x);
		int p2 = find(y);
		
		if(p1==p2) return false;
		else {
			int min = Math.min(p1, p2);
			p[p1] = min;
			p[p2] = min;
			return true;
		}
	}
	
	static int find(int x) {
		if(p[x]==x) return x;
		else return p[x]=find(p[x]);
	}
	
	static int[] p;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		
		p = new int[n];
		for(int i=0; i<n; i++) p[i]=i;
		
		List<int[]> edges = new ArrayList<>();
		
		int sum=0;
		int cnt=0;
		
		for(int i=0; i<n; i++) {
			String str = br.readLine();
			for(int j=0; j<n; j++) {
				int cost = c2i(str.charAt(j));
				sum+=cost;
				if(i!=j && cost!=0) edges.add(new int[]{i,j,cost});
			}
		}
		
		Collections.sort(edges, (int[] o1, int[] o2) -> o1[2]-o2[2]);
		
		if(n==1) {
			System.out.println(sum);
			return;
		}
		
		int idx=0;
		while(idx!=edges.size() && cnt!=n-1) {
			int[] cur = edges.get(idx++);
			
			if(union(cur[0],cur[1])) {
				sum -= cur[2];
				cnt++;
			}
		}
		
		if(cnt==n-1)
			System.out.println(sum);
		else
			System.out.println(-1);
	}

}
