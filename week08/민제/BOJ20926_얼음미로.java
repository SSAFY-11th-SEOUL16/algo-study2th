import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ20926_얼음미로 {

    static int w, h;
    static int[][] graph;
    static boolean[][] visited;
    static Node start;
    static Node end;
    static int[] dxs = {-1, 1, 0, 0};
    static int[] dys = {0, 0, -1, 1};

    static class Node {
        int x, y, time;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Node(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "x=" + x +
                    ", y=" + y +
                    ", time=" + time +
                    '}';
        }
    }

    static boolean in_range(int x, int y) {
        return 0 <= x && x < h && 0 <= y && y < w;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        w = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        graph = new int[h][w];
        visited = new boolean[h][w];

        //T 테라 R바위 -1 H구멍 -2 E출구
        for (int i = 0; i < h; i++) {
            String s = br.readLine();
            for (int j = 0; j < w; j++) {
                char pos_val = s.charAt(j);
                if (pos_val == 'T') {
                    start = new Node(i, j, 0);
                    graph[i][j] = 0;
                } else if (pos_val == 'R') {
                    graph[i][j] = -1;
                } else if (pos_val == 'H') {
                    graph[i][j] = -2;
                } else if (pos_val == 'E') {
                    end = new Node(i, j);
                    graph[i][j] = 0;
                } else {
                    graph[i][j] = Integer.parseInt(String.valueOf(pos_val));
                }
            }
        }

        System.out.println(bfs());

    }

    static int bfs() {
        int result = -1;
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.time - o2.time);
        pq.add(start);


        while (!pq.isEmpty()) {

            Node poll = pq.poll();

            if (poll.x == end.x && poll.y == end.y) {
                result = poll.time;
                break;
            }

            if (visited[poll.x][poll.y]) continue;
            visited[poll.x][poll.y] = true;

            for (int i = 0; i < 4; i++) {

                if (!cango(poll.x, poll.y, i)) continue;

                int time = 0;
                int nx = poll.x;
                int ny = poll.y;
                boolean escape = false;
                while (true) {
                    nx += dxs[i];
                    ny += dys[i];
                    if (graph[nx][ny] == -1) break;

                    if (nx == end.x && ny == end.y) {
                        pq.add(new Node(nx, ny, poll.time + time));
                        escape = true;
                        break;
                    }

                    time += graph[nx][ny];
                }
                if (!escape) {
                    nx -= dxs[i];
                    ny -= dys[i];
                    pq.add(new Node(nx, ny, poll.time + time));
                }

            }

        }

        return result;
    }

    private static boolean cango(int x, int y, int dir) {

        int nx = x;
        int ny = y;

        while (true) {
            nx += dxs[dir];
            ny += dys[dir];

            if (nx == end.x && ny == end.y) return true;
            if (!in_range(nx, ny)) return false;
            if (graph[nx][ny] == -2) return false;
            if (graph[nx][ny] == -1) break;
        }
        nx -= dxs[dir];
        ny -= dys[dir];

        if (visited[nx][ny]) return false;
        return true;
    }
}
