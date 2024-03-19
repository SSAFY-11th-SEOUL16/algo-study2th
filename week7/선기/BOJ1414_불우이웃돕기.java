import java.io.*;
import java.util.*;

public class BOJ1414_불우이웃돕기 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int n;
    static int[][] adjMatrix;
    static List<Edge> edges;
    static int[] parent;
    static int totalWeight;
    static int ans;

    public static void main(String[] args) throws IOException {
        n = nextInt();
        adjMatrix = new int[n][n];
        edges = new ArrayList<>();
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            char[] line = br.readLine().toCharArray();
            for (int j = 0; j < n; j++) {
                if (line[j] == '0') {
                    adjMatrix[i][j] = 0;
                } else if ('a' <= line[j] && line[j] <= 'z') {
                    adjMatrix[i][j] = line[j] - 'a' + 1;
                } else {
                    adjMatrix[i][j] = line[j] - 'A' + 27;
                }
                if (adjMatrix[i][j] != 0 && i != j) {
                    edges.add(new Edge(i, j, adjMatrix[i][j]));
                }
                totalWeight += adjMatrix[i][j];
            }
        }
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
        kruskal();
        System.out.println(ans);
    }

    static void kruskal() {
        Collections.sort(edges);
        int cnt = 0;
        int mstWeight = 0;
        for (Edge e : edges) {
            int u = find(e.u);
            int v = find(e.v);
            if (u != v) {
                mstWeight += e.w;
                union(u, v);
                ++cnt;
            }
            if (cnt == n - 1) {
                break;
            }
        }
        if (cnt < n - 1) {
            ans = -1;
        } else {
            ans = totalWeight - mstWeight;
        }
    }

    static int find(int u) {
        if (u == parent[u]) {
            return u;
        }
        return parent[u] = find(parent[u]);
    }

    static void union(int u, int v) {
        u = find(u);
        v = find(v);
        if (u > v) {
            parent[u] = v;
        } else if (u < v) {
            parent[v] = u;
        }
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

    static class Edge implements Comparable<Edge> {
        int u;
        int v;
        int w;

        Edge(int u, int v, int w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }

        @Override
        public int compareTo(Edge o) {
            return w - o.w;
        }
    }

}