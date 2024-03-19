import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class BOJ1414_불우이웃돕기 {
	/*
	 * 메모리 11832KB  시간 88ms
	 * Kruskal 알고리즘 적용
	 */
	static int n,total=0;
	static int[] parents;
	static class Edge{
		int from,to,val;

		public Edge(int x, int y, int val) {
			super();
			this.from = x;
			this.to = y;
			this.val = val;
		}

		@Override
		public String toString() {
			return "Edge [from=" + from + ", to=" + to + ", val=" + val + "]";
		}
		
	}
	
	static ArrayList<Edge> list = new ArrayList<>();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		parents = new int[n];
		
		for(int i=0; i<n; i++) {
			String line = br.readLine();
			for(int j=0; j<n; j++) {
				char c = line.charAt(j);
				int lan=0;
				if(c>='a' && c<='z') {
					lan=c-'a'+1;
				}
				else if(c>='A' && c<='Z') {
					lan=c-'A'+27;
				}
				total+=lan;
				if(i!=j && lan!=0) list.add(new Edge(i,j,lan));
			}
		}
		
		for(int i=0; i<n; i++)
			parents[i]=i;
		
		Collections.sort(list, new Comparator<Edge>() {
			@Override
			public int compare(Edge o1, Edge o2) {
				return o1.val-o2.val;
			}
		});
		
		int count=0; int minval=0;
		for(Edge e:list) {
			if(union(e.from,e.to)) {
				count++;
				minval+=e.val;
				if(count==n-1) break;
			}
		}
		
		if(count==n-1) System.out.println(total-minval);
		else System.out.println(-1);
	}
	static int find(int a) {
		if(parents[a]==a) return a;
		return parents[a]=find(parents[a]);
	}
	static boolean union(int a,int b) {
		int aroot=find(a);
		int broot=find(b);
		if(aroot==broot) return false;
		parents[broot]=aroot;
		return true;
	}
}
