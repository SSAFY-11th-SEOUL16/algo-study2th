import java.io.*;
import java.util.*;
public class BOJ9934_완전이진트리 {
    // 다른 사람 풀이
    static int K;
    static int[] tree;
    static List<Integer>[] list;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(br.readLine());
        tree = new int[(1<<K)-1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < (1<<K)-1; ++i) tree[i] = Integer.parseInt(st.nextToken());
        list = new ArrayList[K];
        for (int i = 0; i < K; ++i) list[i] = new ArrayList<>();
        inOrder(0, (1<<K)-1, 0);
        for (int i = 0; i < K; ++i) {
            for (int n : list[i]) sb.append(n).append(' ');
            sb.append('\n');
        }
        System.out.print(sb);
    }
    static void inOrder(int start, int end, int depth) {
        if (depth == K) return;
        int mid = (start+end)>>1;
        list[depth].add(tree[mid]);
        inOrder(start, mid-1, depth+1);
        inOrder(mid+1, end, depth+1);
    }
}