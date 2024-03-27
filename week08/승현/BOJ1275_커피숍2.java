import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1275_커피숍2 {
    static int N, Q;
    static int[] arr;
    static long[] segTree;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        arr = new int[N + 1];
        segTree = new long[N * 4];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++)
            arr[i] = Integer.parseInt(st.nextToken());

        // 세그먼트 트리 초기화
        init(1, 1, N);


        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            // 입력값에서 x > y인 경우 swap
            if (x > y) {
                int temp = y;
                y = x;
                x = temp;
            }
            sb.append(sum(x, y, 1, 1, N)+"\n");
            // a번째 idx의 value를 b로 바꿈
            update(a, b, 1, 1, N);
        }
        System.out.println(sb);
    }

    public static long init(int node, int start, int end) {
        // 리프노드이면 값 설정
        if (start == end) {
            return segTree[node] = arr[start];
        }
        // 아닌 경우 위로 가면서 초기화
        int mid = (start + end) / 2;
        return segTree[node] = init(node * 2, start, mid) + init(node * 2 + 1, mid + 1, end);
    }

    public static long update(int idx, long value, int node, int start, int end) {
        // update하려는 idx가 범위 내에 없는 경우 return
        if (idx < start || idx > end) {
            return segTree[node];
        }
        // 리프노드인 경우 리프노드 값 변경
        if (start == end) {
            return segTree[node] = value;
        }
        int mid = (start + end) / 2;
        // segTree의 node 값은 왼쪽꺼 + 오른쪽꺼로 update
        return segTree[node] = update(idx, value, node * 2, start, mid) + update(idx, value, node * 2 + 1, mid + 1, end);
    }

    public static long sum(int left, int right, int node, int start, int end) {
        // 현재 합하려는 범위가 현재 탐색하는 노드의 범위 밖에 있을 때 return
        if (left > end || right < start) {
            return 0;
        }
        // 현재 합하려는 범위가 현재 탐색하는 노드를 포함하고 있을 때 현재 노드의 값 return
        if (left <= start && end <= right) {
            return segTree[node];
        }
        int mid = (start + end) / 2;
        // segTree의 node 값은 왼쪽꺼 + 오른쪽꺼
        return sum(left, right, node * 2, start, mid) + sum(left, right, node * 2 + 1, mid + 1, end);
    }
}