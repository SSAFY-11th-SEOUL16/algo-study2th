package ps;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * 시간: 864s
 */
public class Main_1275_커피숍2 {
	static class SegmentTree {
		int[] arr;
		long[] tree;
		
		public SegmentTree(int[] arr) {
			this.arr = arr;
			
			int h = (int)Math.ceil(Math.log(arr.length) / Math.log(2));
			int treeSize = (int) Math.pow(2, h + 1);
			tree = new long[treeSize];
		}
		
		public long init(int node, int start, int end) {
			if(start == end) return tree[node] = arr[start];
			
			int mid = (start + end) / 2;
			tree[node] = init(node * 2, start, mid) + init(node * 2 + 1, mid + 1, end);
			return tree[node];
		}
		
		public long query(int node, int start, int end, int left, int right) {
			if(right < start || end < left) return 0L;
			if(left <= start && end <= right) return tree[node];
			
			int mid = (start + end) / 2;
			long lSum = query(node * 2, start, mid, left, right);
			long rSum = query(node * 2 + 1, mid + 1, end, left, right);
			return lSum + rSum;
		}
		
		public void update(int node, int start, int end, int index, int val) {
			if(index < start || end < index) return;
			if(start == end) {
				arr[index] = val;
				tree[node] = val;
				return;
			}
			
			int mid = (start + end) / 2;
			update(node * 2, start, mid, index, val);
			update(node * 2 + 1, mid + 1, end, index, val);
			tree[node] = tree[node * 2] + tree[node * 2 + 1];
		}
		
		public void print(int h, int node) {
			if(node < 0 || node > tree.length - 1) return;
			String s = "";
			for(int i = 0; i < h; i++) s += "--";
			System.out.println(s + tree[node]);
			
			print(h + 1, node * 2);
			print(h + 1, node * 2 + 1);
		}
	}
	static int N;
	static int Q;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		
		int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		
		SegmentTree segment = new SegmentTree(arr);
		segment.init(1, 0, N - 1);
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0 ; i < Q; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken());
			
			if(x > y) {
				int tmp = x;
				x = y;
				y = tmp;
			}
			
			sb.append(segment.query(1, 0, N - 1, x, y)).append("\n");
			segment.update(1, 0, N - 1, a, b);
//			segment.print(0, 1);
		}
		System.out.println(sb);

	}
}
