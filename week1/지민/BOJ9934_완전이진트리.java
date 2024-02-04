import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ9934_완전이진트리 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int K;
    private static int[] inorder;
    private static List<List<Integer>> res;

    private static void solve(int start, int end, int depth) {
        if(start > end) return;

        int mid = (start + end) / 2;
        solve(start, mid - 1, depth + 1);
        res.get(depth).add(inorder[mid]);
        solve(mid + 1, end, depth + 1);
    }

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());
        inorder = new int[(int) (Math.pow(2, K) - 1)];
        res = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < inorder.length; i++)
            inorder[i] = Integer.parseInt(st.nextToken());
        for(int i = 0; i <= K; i++) res.add(new ArrayList<>());

        solve(0, inorder.length - 1, 0);

        for(List<Integer> level: res) {
            level.stream().forEach(s -> System.out.print(s + " "));
            System.out.println();
        }
    }
}
