import java.io.*;
import java.util.*;
/*
 * 14080KB
 * 140ms
 */
public class Main_17836_공주님을_구해라 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int n, m, t;
    static int[][] grid;
    static int[] dx = { 1, -1, 0, 0 };
    static int[] dy = { 0, 0, 1, -1 };
    static String ans;

    public static void main(String[] args) throws IOException {
        n = nextInt();
        m = nextInt();
        t = nextInt();
        grid = new int[n][m];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                grid[i][j] = nextInt();
            }
        }
        bfs();
        System.out.println(ans);
    }

    static void bfs() {
        boolean[][][] visited = new boolean[2][n][m];
        Queue<Node> q = new ArrayDeque<>();
        visited[0][0][0] = true;
        q.offer(new Node(0, 0, 0, 0));
        while (!q.isEmpty()) {
            Node u = q.poll();
            if (u.time > t) {
                break;
            }
            int nTime = u.time + 1;
            for (int i = 0; i < 4; ++i) {
                int nx = u.x + dx[i];
                int ny = u.y + dy[i];
                if (isNotValid(nx, ny)) {
                    continue;
                }
                if (u.gramState == 0 && grid[nx][ny] == 1) {
                    continue;
                }
                if (visited[u.gramState][nx][ny]) {
                    continue;
                }
                if (grid[nx][ny] == 2) {
                    visited[0][nx][ny] = true;
                    visited[1][nx][ny] = true;
                    q.offer(new Node(nx, ny, nTime, 1));
                    continue;
                }
                if (nx == n - 1 && ny == m - 1) {
                    ans = String.valueOf(nTime);
                    return;
                }
                visited[u.gramState][nx][ny] = true;
                q.offer(new Node(nx, ny, nTime, u.gramState));

            }
        }
        ans = "Fail";
    }

    static boolean isNotValid(int x, int y) {
        return x < 0 || x >= n || y < 0 || y >= m;
    }

    static class Node {
        int x;
        int y;
        int time;
        int gramState;

        Node(int x, int y, int time, int gramState) {
            this.x = x;
            this.y = y;
            this.time = time;
            this.gramState = gramState;
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
