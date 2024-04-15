import java.util.*;

class Solution_GPS {

    public int solution(int n, int m, int[][] edge_list, int k, int[] gps_log) {
        int[][] dp = new int[k][n+1];
        int INF = 100000000;
        for(int i=0; i<k; i++) {
            Arrays.fill(dp[i], INF);
        }

        List<Integer>[] graph = new List[n+1];
        for(int i=0; i<n+1; i++) {
            graph[i] = new ArrayList<>();
            graph[i].add(i);
        }

        for (int[] edge : edge_list) {
            int a = edge[0];
            int b = edge[1];
            graph[a].add(b);
            graph[b].add(a);
        }

        int first = gps_log[0];
        int last = gps_log[k-1];

        dp[0][first] = 0;

        for(int t=1; t<k; t++) {
            for(int start = 1; start <= n; start++) {
                if (dp[t-1][start] == INF) continue;
                int expected = gps_log[t];
                for (Integer next : graph[start]) {

                    if (t != k) {
                        if (next == expected) dp[t][next] = Math.min(dp[t][next], dp[t-1][start]);
                        else dp[t][next] = Math.min(dp[t][next], dp[t-1][start] + 1);
                    }
                    else {
                        if (next == expected) dp[t][next] = Math.min(dp[t][next], dp[t-1][start]);
                    }

                }
            }
        }

        if (dp[k-1][last] == INF) return  -1;
        return dp[k-1][last];
    }

}