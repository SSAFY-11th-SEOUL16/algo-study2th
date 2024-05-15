import java.io.*;
import java.util.*;

public class BOJ22856_트리순회 {
	
	/*
	 *  - 332ms
	 *  
	 *  - 중위순회중에 실질적 함수호출마다 이동횟수 집계
	 */
	
	static int moves;
	static int visits;
	static int n;
	
	static int[] left;
	static int[] right;
	static boolean[] v;
	
	static void inorder(int cur) {
		
		if(!v[left[cur]]) {
			moves++;
			inorder(left[cur]);
		}
		
		if(!v[cur]) {
			v[cur] = true;
			visits++;
		}
		
		if(visits==n) return;
		
		if(!v[right[cur]]) {
			moves++;
			inorder(right[cur]);
		}

		if(visits<n) {
			moves++;
		}
	}
			
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		
		left = new int[n+2];
		right = new int[n+2];
		v = new boolean[n+2];
				
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			// -1 처리해주기 귀찮아서 그냥 한칸 밀어버림
			int p = Integer.parseInt(st.nextToken())+1;
			int l = Integer.parseInt(st.nextToken())+1;
			int r = Integer.parseInt(st.nextToken())+1;
			
			left[p] = l;
			right[p] = r;
		}
		
		v[0] = true;
		v[1] = true;
		inorder(2);
		
		System.out.println(moves);
		
	}
}
