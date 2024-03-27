import java.io.*;
import java.util.StringTokenizer;

// 90216KB, 776ms
// 처음에 x > y인 경우를 고려하지 않아 오답.. 문제를 잘 보자

public class BOJ1275_커피숍2 {
    static long[] nums, tree;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(st.nextToken()), Q = Integer.parseInt(st.nextToken());
        int h = (int)Math.ceil(Math.log(N)/Math.log(2));
        nums = new long[N];
        tree = new long[(int)Math.pow(2, h+1)];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) nums[i] = Integer.parseInt(st.nextToken());
        init(1, 0, N-1);
        while (Q-->0) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken())-1, y = Integer.parseInt(st.nextToken())-1;
            sb.append(querySum(1, 0, N-1, Math.min(x, y), Math.max(x, y))).append('\n');
            update(1, 0, N-1, Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken()));
        }
        System.out.print(sb);
    }

    static long init(int node, int start, int end) {
        if (start == end) return tree[node] = nums[start];
        int mid = (start+end)/2;
        return tree[node] = init(node*2, start, mid)+init(node*2+1, mid+1, end);
    }

    static void update(int node, int start, int end, int queryIdx, int value) {
        if (queryIdx < start || end < queryIdx) return;
        if (start == end) {
            nums[queryIdx] = value;
            tree[node] = value;
            return;
        }
        int mid = (start+end)/2;
        update(node*2, start, mid, queryIdx, value);
        update(node*2+1, mid+1, end, queryIdx, value);
        tree[node] = tree[node*2]+tree[node*2+1];
    }

    static long querySum(int node, int start, int end, int queryStart, int queryEnd) {
        if (queryEnd < start || end < queryStart) return 0;
        if (queryStart <= start && end <= queryEnd) return tree[node];
        int mid = (start+end)/2;
        return querySum(node*2, start, mid, queryStart, queryEnd)+querySum(node*2+1, mid+1, end, queryStart, queryEnd);
    }
}