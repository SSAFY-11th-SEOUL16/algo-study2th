import java.io.*;
import java.util.*;

public class BOJ6086_최대유량 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int n;
    static int[][] capacity;
    static int[][] flow;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        n = nextInt();
        capacity = new int[52][52];
        flow = new int[52][52];
        parent = new int[52];
        for (int i = 0; i < n; i++) {
            int u = next().charAt(0);
            int v = next().charAt(0);
            int c = nextInt();
            if ('A' <= u && u <= 'Z') {
                u -= 'A';
            } else {
                u = u - 'a' + 26;
            }
            if ('A' <= v && v <= 'Z') {
                v -= 'A';
            } else {
                v = v - 'a' + 26;
            }
            capacity[u][v] += c;
            capacity[v][u] += c;
        }
        int ans = maxFlow(0, 25);
        System.out.println(ans);
    }

    static int maxFlow(int source, int sink) {
        int totalFlow = 0;
        while (true) {
            Arrays.fill(parent, -1);
            Queue<Integer> q = new ArrayDeque<>();
            q.add(source);
            parent[source] = source;
            while (!q.isEmpty()) {
                int u = q.poll();
                for (int v = 0; v < 52; ++v) {
                    if (parent[v] == -1 && capacity[u][v] - flow[u][v] > 0) {
                        q.add(v);
                        parent[v] = u;
                    }
                }
            }
            if (parent[sink] == -1) {
                break;
            }
            int pathFlow = Integer.MAX_VALUE;
            for (int v = sink; v != source; v = parent[v]) {
                int u = parent[v];
                pathFlow = Math.min(pathFlow, capacity[u][v] - flow[u][v]);
            }
            for (int v = sink; v != source; v = parent[v]) {
                int u = parent[v];
                flow[u][v] += pathFlow;
                flow[v][u] -= pathFlow;
            }

            totalFlow += pathFlow;
        }
        return totalFlow;
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
