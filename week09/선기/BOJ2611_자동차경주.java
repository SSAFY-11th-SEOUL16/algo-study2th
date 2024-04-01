import java.io.*;
import java.util.*;

public class BOJ2611_자동차경주 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int n, m;
    static List<List<Node>> adjList;
    static int[][] maxAndBeforeNode;

    public static void main(String[] args) throws IOException {
        n = nextInt();
        m = nextInt();
        adjList = new ArrayList<>();
        for (int i = 0; i <= n; ++i) {
            adjList.add(new ArrayList<>());
        }
        for (int i = 0; i < m; ++i) {
            int p = nextInt();
            int q = nextInt();
            int r = nextInt();
            addEdge(p, q, r);
        }
        maxAndBeforeNode = new int[n + 1][2];
        bfs();
        int maxScore = maxAndBeforeNode[1][0];
        int beforeNode = maxAndBeforeNode[1][1];
        List<Integer> path = new ArrayList<>(1010);
        while (beforeNode != 1) {
            path.add(beforeNode);
            beforeNode = maxAndBeforeNode[beforeNode][1];
        }
        Collections.reverse(path);
        sb.append("1 ");
        for (Integer i : path) {
            sb.append(i).append(" ");
        }
        sb.append("1");
        System.out.println(maxScore);
        System.out.println(sb);
    }

    static void bfs() {
        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(1, 0));
        while (!q.isEmpty()) {
            Node u = q.poll();
            if (u.w < maxAndBeforeNode[u.x][0]) {
                continue;
            }
            for (Node v : adjList.get(u.x)) {
                int score = u.w + v.w;
                if (score > maxAndBeforeNode[v.x][0]) {
                    maxAndBeforeNode[v.x][0] = score;
                    maxAndBeforeNode[v.x][1] = u.x;
                    if (v.x != 1) {
                        q.add(new Node(v.x, score));
                    }
                }
            }
        }
    }

    static void addEdge(int u, int v, int w) {
        adjList.get(u).add(new Node(v, w));
    }

    static class Node {
        int x;
        int w;

        Node(int x, int w) {
            this.x = x;
            this.w = w;
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
