import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ25691_K개_트리노드에서_사과를_최대로_수확하기 {
    static int N;
    static int K;
    static List<List<Integer>> graph;
    static int[] apple;
    static int res;

    private static int ok(int root, int flag) {
        flag &= ~(1 << root);
        for(int child : graph.get(root)) {
            if((flag & (1 << child)) != 0) {
                flag = ok(child, flag);
            }
        }
        return flag;
    }

    private static void subset(int depth, int start, int appleCnt, int flag) {
        if(depth == K) {
            if(ok(0, flag) == 0 && res < appleCnt) res = appleCnt;
            return;
        }

        for(int i = start; i < N; i++) {
            subset(depth + 1, i + 1,appleCnt + apple[i], flag | (1 << i));
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        for (int i = 0; i < N; i++) graph.add(new ArrayList<>());

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph.get(u).add(v);
        }
        apple = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        subset(1, 1, apple[0], 1);
        System.out.println(res);
    }
}
