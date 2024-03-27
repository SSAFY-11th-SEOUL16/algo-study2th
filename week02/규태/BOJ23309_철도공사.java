package daily;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ23309_철도공사 {
	static int n,m;
	static int[] station,prev,next;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		station = new int[n]; 
		prev = new int[1000001]; 
		next = new int[1000001]; 
		for(int x=0; x<n; x++) {
			station[x]=Integer.parseInt(st.nextToken());
		}
		for(int x=0; x<n; x++) {
			prev[station[x]]= station[x-1<0? n-1:x-1];
			next[station[x]]= station[x+1>=n? 0:x+1];
		}
	
		for(int x=0; x<m; x++) { 
			st = new StringTokenizer(br.readLine());
			String gongsa = st.nextToken();
			int i,j,val;
			
			switch(gongsa) {
			case "BN":  // prev i j next(val)
				i = Integer.parseInt(st.nextToken());
				j = Integer.parseInt(st.nextToken());
				
				val = next[i];
				sb.append(val).append('\n');
				
				next[i]=j;
				prev[val]=j;
				prev[j]=i;
				next[j]=val;
				break;
				
			case "BP": //  prev(val) j i next 
				i = Integer.parseInt(st.nextToken());
				j = Integer.parseInt(st.nextToken());
				
				val = prev[i];
				sb.append(val).append('\n');
				
				prev[i]=j;
				next[val]=j;
				next[j]=i;
				prev[j]=val;
				break;
				
			case "CN": // prev i next(val) nextnext 
				i = Integer.parseInt(st.nextToken());
				
				val = next[i];
				int nextnext = next[val];
				sb.append(val).append('\n');
				
				next[i]=nextnext;
				prev[nextnext]=i;
				break;
				
			case "CP": // prevprev prev(val) i next 
				i = Integer.parseInt(st.nextToken());
				
				val = prev[i];
				int prevprev = prev[val];
				sb.append(val).append('\n');
				
				prev[i]=prevprev;
				next[prevprev]=i;
				break;
			}
		}
		System.out.println(sb);
	}

}
