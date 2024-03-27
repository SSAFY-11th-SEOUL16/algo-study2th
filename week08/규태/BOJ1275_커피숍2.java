import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1275_커피숍2 {
	/*
	 * 세그트리 학습을 위해 세그트리 관련 내용 구글링 후 작성했습니다
	 */
	static int n,q;
	static long arr[];
	static class SegTree {
		int len;
		long[] tree;
		public SegTree(long[] arr) {
			len = arr.length;
			tree = new long[len*4];
			init(0,len-1,1);
		}
		
		long init(int start,int end,int node) { //
			if(start==end) return tree[node]=arr[start];
			int mid = (start+end)/2;
			return tree[node]=init(start,mid,node*2)+init(mid+1,end,node*2+1);
		}
		long getsum(int start,int end,int node,int left,int right) {
			if(left>end || start>right) return 0;
			if(left<=start && end<=right) return tree[node];
			int mid = (start+end)/2;
			return getsum(start,mid,node*2,left,right)+getsum(mid+1,end,node*2+1,left,right);
		}
		void update(int start,int end,int node,int idx,long val) {
			if(start>idx || idx>end) return;
			tree[node]+=val;
			if(start==end) return;
			int mid = (start+end)/2;
			update(start,mid,node*2,idx,val);
			update(mid+1,end,node*2+1,idx,val);
		}
	}
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		q = Integer.parseInt(st.nextToken());
		arr = new long[n];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		SegTree tree = new SegTree(arr);
		
		int left,right;
		for(int i=0; i<q; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken())-1;
			int y = Integer.parseInt(st.nextToken())-1;
			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken());
			if(x<=y) {left=x; right=y;}
			else {left=y; right=x;}
			sb.append(tree.getsum(0, n-1, 1, left, right)).append('\n');
			tree.update(0, n-1, 1, a, b-arr[a]);
			arr[a] = b;
		}
		System.out.println(sb);
	}

}
