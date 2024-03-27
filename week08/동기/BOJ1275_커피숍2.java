import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * 788ms
 */
public class Main {
    static StringBuilder sb;
    static int n, q;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        long[] arr = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }
        SegmentTree sTree = new SegmentTree(n);
        sTree.init(arr, 1, 1, n);

        for (int i = 0; i < q; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            long b = Long.parseLong(st.nextToken());

            if(x>y){
                int temp =x;
                x =y;
                y=temp;
            }

            long result = sTree.sum(1, 1, n, x, y);
            sb.append(result).append("\n");

            long diff = b - arr[a];
            arr[a] = b;
            sTree.update(1, 1, n, a, diff);

        }
        System.out.println(sb);
    }

    static class SegmentTree {
        long tree[];
        int treeSize;

        public SegmentTree(int size) {
            int h = (int) Math.ceil(Math.log(size) / Math.log(2));
            this.treeSize = (int) Math.pow(2, h + 1);
            tree = new long[treeSize];
        }

        public long init(long[] arr, int node, int start, int end) {
            if (start == end) {
                return tree[node] = arr[start];
            } else {
                return tree[node] = init(arr, node * 2, start, (start + end) / 2)
                        + init(arr, node * 2 + 1, (start + end) / 2 + 1, end);
            }
        }

        long sum(int node, int start, int end, int left, int right) {
            //노드가 가지는 값의 구간이 구하려는 합의 구간에 속하지 않는 경우
            if (end < left || right < start) {
                return 0;
            } else if (left <= start && end <= right) {
                // 노드가 가지는 값의 구간이 구하려는 합의 구간에 속하는 경우 노드 값 리턴
                return tree[node];
            } else {
                // 1. 노드가 가지는 값의 구간이 구하려는 합의 구간에 일부는 속하고 일부는 속하지 않는 경우
                // 2. 노드가 가지는 값의 구간이 구하려는 합의 구간을 모두 포함하는 경우
                // 이 같은 경우에는 자식노드를 탐색해서 값을 리턴
                return sum(node * 2, start, (start + end) / 2, left, right)
                        + sum(node * 2 + 1, (start + end) / 2 + 1, end, left, right);
            }
        }


        void update(int node, int start, int end, int index, long diff) {
            // 노드가 가지는 값의 구간에 배열의 인덱스(값이 변경 될 인덱스)가 포함되지 않을 경우
            if (index < start || end < index) {
                // 아무것도 안함
                return;
            } else {
                // 노드가 가지는 값의 구간에 배열의 인덱스(값이 변경 될 인덱스)가 포함되는 경우
                // 노드의 값 + 차이값(변경할 값-기존값)
                tree[node] = tree[node] + diff;

                // 노드가 리프노드가 아닌 경우
                if (start != end) {
                    // 리프노드까지 계속 자식노드를 탐색
                    update(node * 2, start, (start + end) / 2, index, diff);
                    update(node * 2 + 1, (start + end) / 2 + 1, end, index, diff);
                }
            }
        }
    }
}