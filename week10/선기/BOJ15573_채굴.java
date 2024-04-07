import java.io.*;
import java.util.*;

/**
 * 302980KB
 * 1500ms
 */
public class BOJ15573_채굴 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int m, n, k;
    static int[][] grid;

    static Queue<Node> q = new ArrayDeque<>();
    static int[] dx = { 0, 0, 1, -1 };
    static int[] dy = { 1, -1, 0, 0 };

    public static void main(String[] args) throws IOException, InterruptedException {
        n = nextInt();
        m = nextInt();
        k = nextInt();
        grid = new int[n][m];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                grid[i][j] = nextInt();
            }
        }
        int low = 0;
        int high = 1000000;
        int ans = 0;
        while (low <= high) {
            int mid = (low + high) >>> 1;
            int minedCnt = mine(mid);
//            System.out.println(mid + " " + minedCnt);
            if (minedCnt >= k) {
                high = mid - 1;
                ans = mid;
            } else {
                low = mid + 1;
            }
        }
        System.out.println(ans);
    }

    static int mine(int power) {
        int cnt = 0;
        boolean[][] visited = new boolean[n][m];
        for (int i = 0; i < n; ++i) {
            cnt += bfs(i, 0, visited, power);
            cnt += bfs(i, m - 1, visited, power);
        }
        for (int j = 1; j < m - 1; ++j) {
            cnt += bfs(0, j, visited, power);
        }
        return cnt;
    }

    static int bfs(int x, int y, boolean[][] visited, int power) {
        if (visited[x][y] || grid[x][y] > power) {
            return 0;
        }
        int cnt = 1;
        visited[x][y] = true;
        q.offer(new Node(x, y));
        while (!q.isEmpty()) {
            Node u = q.poll();
            for (int i = 0; i < 4; ++i) {
                int nx = u.x + dx[i];
                int ny = u.y + dy[i];
                if (isNotValid(nx, ny) || visited[nx][ny] || grid[nx][ny] > power) {
                    continue;
                }
                visited[nx][ny] = true;
                q.offer(new Node(nx, ny));
                ++cnt;
            }
        }
        return cnt;
    }

    static boolean isNotValid(int x, int y) {
        return x < 0 || x >= n || y < 0 || y >= m;
    }

    static class Node {
        int x;
        int y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
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
