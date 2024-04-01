import java.io.*;
import java.util.*;

public class BOJ12893_적의적 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int n, m;
    static List<List<Integer>> adjList;
    static boolean[] isRed;
    static boolean[] isBlue;

    public static void main(String[] args) throws IOException {
        n = nextInt();
        m = nextInt();
        adjList = new ArrayList<>();
        isRed = new boolean[n + 1];
        isBlue = new boolean[n + 1];
        for (int i = 0; i <= n; ++i) {
            adjList.add(new ArrayList<>());
        }
        for (int i = 0; i < m; ++i) {
            int u = nextInt();
            int v = nextInt();
            adjList.get(u).add(v);
            adjList.get(v).add(u);
        }
        int ans = 1;
        for (int i = 1; i <= n; ++i) {
            if (isRed[i] || isBlue[i]) {
                continue;
            }
            ans &= bfs(i);
        }
        System.out.println(ans);
    }

    static int bfs(int i) {
        Queue<Integer> q = new ArrayDeque<>();

        q.offer(i);
        isRed[i] = true;
        while (!q.isEmpty()) {
            int u = q.poll();
            for (int v : adjList.get(u)) {
                if (isRed[u] && isRed[v] || isBlue[u] && isBlue[v]) {
                    return 0;
                }
                if (isRed[v] || isBlue[v]) {
                    continue;
                }
                if (isRed[u]) {
                    isBlue[v] = true;
                } else {
                    isRed[v] = true;
                }
                q.offer(v);
            }
        }

        return 1;
    }

    static String next() throws IOException {
        while (st == null || !st.hasMoreTokens()) {
            st = new StringTokenizer(br.readLine());
        }
        return st.nextToken();
    }

    static int nextInt() throws IOException {
        return Integer.parseInt(next());
    }

}
