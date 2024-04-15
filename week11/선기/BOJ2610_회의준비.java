import java.io.*;
import java.util.*;

/*
 *  14140KB
 *  148ms
 *
 *  분리집합 + 플로이드-워셜
 *
 */
public class BOJ2610_회의준비 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static final int MAX = 987654321;
    static int n, m;
    static int[] parent;
    static int[][] adjMatrix;
    static Map<Integer, Integer> representives = new TreeMap<>();
    static int[] maxWeights;

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        parent = new int[n + 1];
        maxWeights = new int[n + 1];
        for (int i = 1; i <= n; ++i) {
            parent[i] = i;
        }
        adjMatrix = new int[n + 1][n + 1];
        for (int i = 0; i <= n; ++i) {
            for (int j = 0; j <= n; ++j) {
                adjMatrix[i][j] = MAX;
            }
            adjMatrix[i][i] = 0;
        }
        for (int i = 0; i < m; ++i) {
            int u = nextInt();
            int v = nextInt();
            adjMatrix[u][v] = 1;
            adjMatrix[v][u] = 1;
            union(u, v);
        }

        for (int k = 1; k <= n; ++k) {
            for (int a = 1; a <= n; ++a) {
                for (int b = 1; b <= n; ++b) {
                    adjMatrix[a][b] = Math.min(adjMatrix[a][b], adjMatrix[a][k] + adjMatrix[k][b]);
                }
            }
        }
        for (int i = 1; i <= n; ++i) {
            int maxWeight = 0;
            for (int j = 1; j <= n; ++j) {
                if (adjMatrix[i][j] != MAX) {
                    maxWeight = Math.max(maxWeight, adjMatrix[i][j]);
                }
            }
            maxWeights[i] = maxWeight;
        }

        for (int i = 1; i <= n; ++i) {
            Integer rep = representives.get(find(i));
            if (rep == null) {
                representives.put(parent[i], i);
            } else {
                if (maxWeights[i] < maxWeights[rep]) {
                    representives.put(parent[i], i);
                }
            }
        }
        sb.append(representives.size()).append("\n");
        ArrayList<Integer> list = new ArrayList<>(representives.values());
        Collections.sort(list);
        for (Integer u : list) {
            sb.append(u).append("\n");
        }
        System.out.println(sb);

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
        if (u < v) {
            parent[v] = u;
        } else if (u > v) {
            parent[u] = v;
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

}
