import java.io.*;
import java.util.*;

public class BOJ1275_커피숍2 {
	
	/*
	 *  - 816ms
	 * 
	 *  - segTree 연습해본다 치고 블로그에서 이론보고 구현했습니다. 
	 */

	static class SegTree{
		long[] tree;
		int depth;
		int treeSize;
		
		SegTree(int n, long[] arr){
			this.depth = (int) Math.ceil(Math.log(n)/Math.log(2));
			tree = new long[this.treeSize = (int) Math.pow(2, depth+1)];
			
			init(1,1,n,arr);
		}
		
		long init(int idx, int left, int right, long[] arr) {
			if(left==right) {	// leaf
				//System.out.println(idx);
				return tree[idx] = arr[left];
			}
			
			int mid = (left+right)/2;
			return tree[idx] = init(idx*2, left, mid, arr) + init(idx*2+1, mid+1, right, arr);
		}
		
		void update(int idx, int left, int right, int replace, long diff) {
			if(left>replace || right<replace) return;
			
			tree[idx] += diff;
			
			if(left!=right) {
				int mid = (left+right)/2;
				update(idx*2, left, mid, replace, diff);
				update(idx*2+1, mid+1, right, replace, diff);
			}
		}
		
		long sum(int idx, int left, int right, int start, int end) {
			if(start>right || end<left) return 0;
			
			if(start<=left && right<=end) return tree[idx];
			
			int mid = (left+right)/2;
			return (sum(idx*2, left, mid, start, end) + sum(idx*2+1, mid+1, right, start, end));
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int q = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		long[] arr = new long[n+1];
		for(int i=1; i<=n; i++) arr[i] = Long.parseLong(st.nextToken());
		
		SegTree seg = new SegTree(n, arr);
		
		//System.out.println(Arrays.toString(seg.tree));
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<q; i++) {
			st = new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			
			sb.append(seg.sum(1, 1, n, Math.min(n1, n2), Math.max(n1, n2))).append("\n");
			
			int n3 = Integer.parseInt(st.nextToken());
			long n4 = Long.parseLong(st.nextToken());
			
			seg.update(1, 1, n, n3, n4-arr[n3]);
			arr[n3] = n4;

			//System.out.println(Arrays.toString(seg.tree));
		}
		System.out.println(sb);
	}

}
