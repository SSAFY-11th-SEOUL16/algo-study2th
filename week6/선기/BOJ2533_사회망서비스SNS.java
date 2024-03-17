import java.io.*;
import java.util.*;

public class BOJ2533_사회망서비스SNS {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static List<List<Integer>> adjList;
    static int[] numOfneighbors;
    static boolean[] isEA;
    static Queue<Integer> queue = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {
        int n = nextInt();

        adjList = new ArrayList<>();
        numOfneighbors = new int[n + 1];
        isEA = new boolean[n + 1];
        for (int i = 0; i <= n; ++i) {
            adjList.add(new ArrayList<>());
        }

        for (int i = 0; i < n - 1; ++i) {
            int u = nextInt();
            int v = nextInt();
            addEdge(u, v);
        }

        for (int i = 1; i <= n; ++i) {
            if (numOfneighbors[i] == 1) {
                queue.offer(i);
            }
        }

        int ans = 0;
        while (!queue.isEmpty()) {
            int u = queue.poll();
            if (isEA(u)) {
                ++ans;
            }
        }
        System.out.println(ans);
    }

    static boolean isEA(int u) {
        int cnt = 0;
        for (int v : adjList.get(u)) {
            if (!isEA[v]) {
                ++cnt;
            }
            --numOfneighbors[v];
            if (numOfneighbors[v] == 1) {
                queue.offer(v);
            }
        }
        if (numOfneighbors[u] == 0) {
            return isEA[u] = cnt >= 1;
        }
        return isEA[u] = cnt >= 2;
    }

    static void addEdge(int u, int v) {
        adjList.get(u).add(v);
        adjList.get(v).add(u);
        ++numOfneighbors[u];
        ++numOfneighbors[v];
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