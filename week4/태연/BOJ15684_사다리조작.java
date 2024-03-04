import java.io.*;
import java.util.*;

public class BOJ15684_사다리조작 {
	/*
	 * - 596ms
	 * 
	 * - 모든 스왑을 레벨별로 List로 저장하고 depth 를 0~3까지 완탐
	 * 
	 */
	
	static int n,m,h;
	static int[] ans;
	static boolean end=false;
	static boolean[][] v;
	
	static void swap(int[] arr, int n1, int n2) {
		int temp = arr[n1];
		arr[n1] = arr[n2];
		arr[n2] = temp;
	}
	
	static int[] getResult(List<Integer>[] swaps, int[] arr) {
		for(int i=0; i<h; i++) {
			for(int j=0; j<swaps[i].size(); j++) {
				int n=swaps[i].get(j);
				swap(arr, n, n+1);
			}
		}
		return arr;
	}
	
	static void dfs(List<Integer> []swaps, int cnt, int targetCnt, int maxIdx, int idx) {
		if(cnt==targetCnt) {
			int[] get = getResult(swaps, ans.clone());
			for(int i=1; i<n+1 ;i++) {
				if(get[i]!=ans[i]) return;
			}
			end=true;
			return;
		}
		else {
			for(int i=idx; i<maxIdx; i++) {
				int x= i/(n-1);
				int y= i%(n-1)+1;
				if(v[x][y] || v[x][y-1] || v[x][y+1]) continue;
				
				v[x][y] = true;
				swaps[x].add(y);
				dfs(swaps,cnt+1, targetCnt, maxIdx, i+1);
				swaps[x].remove(swaps[x].indexOf(y));
				v[x][y] = false;
			}
			
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());	
		m = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[n+1];
		ans = new int[n+1];
		for(int i=1; i<n+1; i++) {arr[i]=i; ans[i]=i;}
		v = new boolean[h][n+2];						// 1~n-1번 간선에 양 옆 패딩
		
		List<Integer> []swaps = new ArrayList[h];		// h 레벨에서의 스왑
		for(int i=0; i<h; i++) swaps[i] = new ArrayList<>();

		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(st.nextToken())-1;	
			int n2 = Integer.parseInt(st.nextToken());
			
			swaps[n1].add(n2);
			v[n1][n2] = true;
		}
		
		// h 레벨에 각 레벨당 n-1번 사다리( 1~n-1 ) 
		dfs(swaps, 0, 0, h*(n-1), 0);
		if(end) {System.out.println(0); return;}
		dfs(swaps, 0, 1, h*(n-1), 0);
		if(end) {System.out.println(1); return;}
		dfs(swaps, 0, 2, h*(n-1), 0);
		if(end) {System.out.println(2); return;}
		dfs(swaps, 0, 3, h*(n-1), 0);
		if(end) {System.out.println(3); return;}
		
		System.out.println(-1); return;
	}

}
/*
2 1 1
1 1

ans : -1
*/