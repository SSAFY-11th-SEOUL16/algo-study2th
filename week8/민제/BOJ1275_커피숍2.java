import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1275_커피숍2{

	static int n;
	static long[] arr;

	static class SegTree {

		long[] tree;

		public SegTree() {
			tree = new long[4 * n];
		}

		// start ~ end arr범위
		long init(int node, int start, int end) {

			if (start == end)
				return tree[node] = arr[start];

			int mid = (start + end) / 2;
			return tree[node] = init(node * 2, start, mid) + init(node * 2 + 1, mid + 1, end);
		}

		// start ~ end 범위
		// left ~ right 구간합 범위
		long sum(int node, int start, int end, int left, int right) {

			if (start > right || end < left) return 0;

			if (left <= start && end <= right) return tree[node];

			int mid = (start + end) / 2;
			return sum(node * 2, start, mid, left, right) + sum(node * 2 + 1, mid + 1, end, left, right);
		}
		
		// start ~ end arr범위
		// left ~ right 구간합 범위
		void update(int node, int start, int end, int index, long diff) {
			
			if (start > index || end < index) return;
			
			tree[node] += diff;
			//리프면 return
			if (start == end) return;
			
			int mid = (start + end) / 2;
			update(node * 2, start, mid, index, diff);
			update(node * 2  + 1, mid+1, end, index, diff);
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		int q = Integer.parseInt(st.nextToken());
		arr = new long[n];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			arr[i] = Long.parseLong(st.nextToken());
		}
		
		SegTree segTree = new SegTree();
		segTree.init(1, 0, n-1);
		
		StringBuilder sb = new StringBuilder();
		
		for(int i=0; i<q; i++) {
			st = new StringTokenizer(br.readLine());
			int left = Integer.parseInt(st.nextToken()) - 1;
			int right = Integer.parseInt(st.nextToken()) - 1;
			int index = Integer.parseInt(st.nextToken()) - 1;
			int changeval = Integer.parseInt(st.nextToken());
			
			if (left > right) {
				int temp = left;
				left = right;
				right = temp;
			}
			
			long sum = segTree.sum(1, 0, n-1, left, right);
			sb.append(sum).append('\n');
			long diff = changeval - arr[index];
			arr[index] = changeval;
			segTree.update(1, 0, n-1, index, diff);
		}
		
		System.out.println(sb);

	}

}
