import java.io.*;
import java.util.*;

public class BOJ12893_적의적 {
	
	/*
	 *  - 608ms
	 * 
	 *  - find로 그룹 대표번호 찾고 조건에 따라 수동으로 union
	 */
	
	static int findG(int x) {
		if(i2g[x]==x) return x;
		else return i2g[x]=findG(i2g[x]);
	}
	
	static int[] i2g;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		i2g = new int[n+1];						// id to group
		int[] e = new int[n+1]; 				// group to enemy
		
		for(int i=1; i<=n; i++) i2g[i] = i;
		
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			
			int g1 = findG(n1);					// n1의 그룹
			int g2 = findG(n2);					// n2의 그룹
			
			int e1 = e[g1];						// g1의 적그룹
			int e2 = e[g2];						// g2의 적그룹
			
			if(g1==g2 || (e1!=0 && e1==e2)) {	// 친구인데 적임 or 적의적이적
				System.out.println(0);
				return;
			}
			else {
				if(e1==0 && e2==0) {			// 둘다 적이 없으면
					e[g1] = g2;					// 서로가 적
					e[g2] = g1;
				}
				else if(e1==0) {				// 한쪽만 적이 있으면
					i2g[g1] = e2;
				}
				else if(e2==0) {				// 2:1로 편먹고 싸움
					i2g[g2] = e1;
				}
				else {							// 양쪽 다 적이 있으면
					i2g[g1] = e2;				// 2:2로 싸움
					i2g[g2] = e1;
					
					e[e2] = e1;
					e[e1] = e2;
				}
			}
			
		}
		System.out.println(1);
	}

}
