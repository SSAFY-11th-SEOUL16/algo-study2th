import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class Main {

    /*
     * - 188ms
     * 
     * - 유니온파인드도 썼다가 플로이드워셜도 썼다가 혼란스러운풀이
     */
	
	static int find(int x) {
		if(p[x]==x) return x;
		else return p[x]=find(p[x]);
	}
	
	static void union(int x, int y) {
		int p1 = find(x);
		int p2 = find(y);
		
		if(p1==p2) {

		}
		else {
			p[p2]=p1;

		}
	}
	
	static int getMin(List<Integer> list) {
		
		for(int mid : list) {
			for(int i : list) {
				for(int j: list) {
					if(i==j) continue;
					
					int v = Math.min(adj[i][j], adj[i][mid]+adj[mid][j]);
					adj[i][j] = v;
					adj[j][i] = v;
				}
			}
		}
		
		int min = Integer.MAX_VALUE;
		int minId = -1;
		
		for(int num : list) {
			int val=0;
			for(int n : list) {
				if(num==n) continue;
				if(adj[num][n]>val) val = adj[num][n];
			}
			if(min>val) {
				min=val;
				minId=num;
			}
		}
		
		return minId;
	}
	
	static int n,k;
	static int[] p;
	static int[][] adj;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());
		p = new int[n+1];
		for(int i=1; i<=n; i++) p[i]=i;
		adj = new int[n+1][n+1];
		
		for(int i=0; i<=n; i++) Arrays.fill(adj[i], 100000);
		
		StringBuilder sb = new StringBuilder();
		Map<Integer,List<Integer>> map = new HashMap<>();
		
		for(int i=0; i<m; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			
			adj[n1][n2]=1;
			adj[n2][n1]=1;
			
			union(n1,n2);
		}
		
		int cnt=0;
		
		//System.out.println(Arrays.toString(p));
		
		for(int i=1; i<=n; i++) {
			int cur = find(i);
			if(!map.containsKey(cur)) {
				map.put(cur, new ArrayList<>());
				cnt++;
			}
			map.get(cur).add(i);
		}
		
		sb.append(cnt).append("\n");
		
		List<Integer> arr = new ArrayList<>();
		
		for(Entry<Integer, List<Integer>> entry : map.entrySet()) {
			arr.add(getMin(entry.getValue()));
		}
		
		Collections.sort(arr);
		
		for(int num : arr) {
			sb.append(num).append("\n");
		}
		
		System.out.println(sb);
	}
}