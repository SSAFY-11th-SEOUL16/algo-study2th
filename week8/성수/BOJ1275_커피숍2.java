/*
96464KB 840ms
*/

import java.io.*;
import java.util.*;

public class BOJ1275_커피숍2 {
	
	static void init(long[] a, long[] tree, int node, int start, int end) {
	    if (start == end) {
	        tree[node] = a[start];
	    } else {
	        init(a, tree, node*2, start, (start+end)/2);
	        init(a, tree, node*2+1, (start+end)/2+1, end);
	        tree[node] = tree[node*2] + tree[node*2+1];
	    }
	}
	
	static long query(long[] tree, int node, int start, int end, int left, int right) {
	    if (left > end || right < start) {
	        return 0;
	    }
	    if (left <= start && end <= right) {
	        return tree[node];
	    }
	    long lsum = query(tree, node*2, start, (start+end)/2, left, right);
	    long rsum = query(tree, node*2+1, (start+end)/2+1, end, left, right);
	    return lsum+rsum;
	}
	
	static void update(long[] a, long[] tree, int node, int start, int end, int index, long val) {
	    if (index < start || index > end) {
	        return;
	    }
	    if (start == end) {
	        a[index] = val;
	        tree[node] = val;
	        return;
	    }
	    update(a, tree,node*2, start, (start+end)/2, index, val);
	    update(a, tree,node*2+1, (start+end)/2+1, end, index, val);
	    tree[node] = tree[node*2] + tree[node*2+1];
	}


	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out));
		int N, Q;
		StringTokenizer tokens = new StringTokenizer(input.readLine());
		N = Integer.parseInt(tokens.nextToken());
		Q = Integer.parseInt(tokens.nextToken());
		int h = (int) Math.ceil(Math.log(N) / Math.log(2));
		int tree_size = 1<<(h + 1);
		long[] arr = new long[N];
		long[] tree = new long[tree_size];
		tokens = new StringTokenizer(input.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(tokens.nextToken());
		}
		init(arr, tree, 1, 0, N - 1);
		for(int i = 0; i < Q; i++) {
			tokens = new StringTokenizer(input.readLine());
			int x = Integer.parseInt(tokens.nextToken());
			int y = Integer.parseInt(tokens.nextToken());
			if(x > y) {
				int tmp = x;
				x = y;
				y = tmp;
			}
			int a = Integer.parseInt(tokens.nextToken());
			int b = Integer.parseInt(tokens.nextToken());
			output.write(query(tree, 1, 0, N - 1, x - 1, y - 1) + "\n");
			update(arr, tree, 1, 0, N - 1, a - 1, b);
		}
		output.close();
	}
}
