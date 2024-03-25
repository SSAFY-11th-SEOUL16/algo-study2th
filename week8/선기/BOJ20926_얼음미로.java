import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int w, h;
    static int[][] grid;// E:-1 H:-2 R:-3 T: 0
    static int[] tera;
    static int[] exit;
    static int ans = Integer.MAX_VALUE;
    static int[][] distance;

    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        w = nextInt();
        h = nextInt();
        grid = new int[h][w];
        distance = new int[h][w];
        for (int i = 0; i < h; ++i) {
            char[] line = br.readLine().toCharArray();
            for (int j = 0; j < w; ++j) {
                if (line[j] == 'E') {
                    grid[i][j] = -1;
                    exit = new int[]{i, j};
                } else if (line[j] == 'H') {
                    grid[i][j] = -2;
                } else if (line[j] == 'R') {
                    grid[i][j] = -3;
                } else if (line[j] == 'T') {
                    grid[i][j] = 0;
                    tera = new int[]{i, j};
                } else {
                    grid[i][j] = line[j] - '0';
                }
                distance[i][j] = Integer.MAX_VALUE;
            }
        }
        dijkstra();
        if (ans == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(ans);
        }
    }

    static void dijkstra() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        distance[tera[0]][tera[1]] = 0;
        pq.add(new Node(tera[0], tera[1], 0));
        while (!pq.isEmpty()) {
            Node u = pq.poll();
            if (distance[u.x][u.y] < u.w) {
                continue;
            }
            for (int i = 0; i < 4; ++i) {
                int nx = u.x + dx[i];
                int ny = u.y + dy[i];
                int nextTime = u.w;
                while (true) {
                    if (isNotValid(nx, ny)) {
                        break;
                    }
                    if (grid[nx][ny] == -1) {
                        ans = Math.min(ans, nextTime);
                        break;
                    }
                    if (grid[nx][ny] == -2) {
                        break;
                    }
                    if (grid[nx][ny] == -3) {
                        int x = nx - dx[i];
                        int y = ny - dy[i];
                        if (distance[x][y] > nextTime) {
                            distance[x][y] = nextTime;
                            pq.add(new Node(x, y, nextTime));
                        }
                        break;
                    }
                    nextTime += grid[nx][ny];
                    nx = nx + dx[i];
                    ny = ny + dy[i];
                }
            }
        }
    }

    private static boolean isNotValid(int nx, int ny) {
        return nx < 0 || nx >= h || ny < 0 || ny >= w;
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

    static class Node implements Comparable<Node> {
        int x;
        int y;
        int w;

        Node(int x, int y, int w) {
            this.x = x;
            this.y = y;
            this.w = w;
        }

        @Override
        public int compareTo(Node o) {
            return w - o.w;
        }
    }
}
