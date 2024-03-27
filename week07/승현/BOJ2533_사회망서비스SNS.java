import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ2533_사회망서비스SNS { // 2188ms
    static int N;
    static int result;
    static ArrayList<Integer>[] graph;
    static int[][] dp;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        graph = new ArrayList[N+1];
        dp = new int[N+1][2];
        visited = new boolean[N+1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < N-1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
            graph[b].add(a);
        }

        dfs(1);

        System.out.println(Math.min(dp[1][0], dp[1][1]));
    }
    public static void dfs(int v) {
        visited[v] = true;
        // 현재 노드가 얼리어답터 아님
        dp[v][0] = 0;
        // 현재 노드가 얼리어답터임
        dp[v][1] = 1;

        for (int child : graph[v]) {
            if (visited[child])
                continue;
            dfs(child);
            // 내가 얼리어답터 아니면 자식이 얼리어답터여야함
            dp[v][0] += dp[child][1];
            // 내가 얼리어답터면 상관없으므로 둘중에 작은걸로
            dp[v][1] += Math.min(dp[child][1], dp[child][0]);
        }
    }
}
