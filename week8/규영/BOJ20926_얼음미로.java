import java.io.*;
import java.util.*;

// 25752KB, 516ms

public class BOJ20926_얼음미로 {
    static class Node implements Comparable<Node> {
        int r, c, time;
        Node (int r, int c, int time) {
            this.r = r;
            this.c = c;
            this.time = time;
        }
        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.time, o.time);
        }
    }
    static int W, H;
    static char[][] map;
    static boolean[][] visited;
    static int[][] time, dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        map = new char[H][W];
        time = new int[H][W];
        visited = new boolean[H][W];
        int startR = 0, startC = 0, endR = 0, endC = 0;
        for (int i = 0; i < H; i++) {
            map[i] = br.readLine().toCharArray();
            for (int j = 0; j < W; j++) {
                if (map[i][j] == 'T') {
                    startR = i;
                    startC = j;
                }
                else if (map[i][j] == 'E') {
                    endR = i;
                    endC = j;
                }
            }
        }
        for (int i = 0; i < H; i++) Arrays.fill(time[i], Integer.MAX_VALUE);
        time[startR][startC] = 0;
        setTime(startR, startC);
        System.out.print(time[endR][endC] != Integer.MAX_VALUE ? time[endR][endC] : -1);
    }
    
    // 다익스트라 사용
    static void setTime(int r, int c) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        visited[r][c] = true;
        pq.offer(new Node(r, c, 0));
        while (!pq.isEmpty()) {
            Node now = pq.poll();
            for (int d = 0; d < 4; d++) {
                // block : 미끄러질 수 있는 칸의 수
                int block = calculateBlock(now.r, now.c, d), sum = 0;
                if (block == -1) continue;
                for (int i = 1; i <= block; i++) {
                    int nr = now.r+dir[d][0]*i, nc = now.c+dir[d][1]*i;
                    // 'E'의 값도 더해질 수 있기 때문에 아래와 같이 계산
                    sum += map[nr][nc] != 'E' ? map[nr][nc]-'0' : 0;
                }
                int nr = now.r+dir[d][0]*block, nc = now.c+dir[d][1]*block;
                if (time[nr][nc] > now.time+sum) {
                    time[nr][nc] = now.time+sum;
                    pq.offer(new Node(nr, nc, time[nr][nc]));
                }
            }
        }
    }

    // 이동할 칸의 수 계산하는 메소드
    // 단, 범위를 벗어나거나 구멍에 빠지면 안 되므로 -1을 return한다.
    static int calculateBlock(int r, int c, int d) {
        int block = 1;
        while (true) {
            int nr = r+dir[d][0]*block, nc = c+dir[d][1]*block;
            if (!isInRange(nr, nc) || map[nr][nc] == 'H') return -1;
            else if (map[nr][nc] == 'R') return block-1;
            else if (map[nr][nc] == 'E') return block;
            block++;
        }
    }

    static boolean isInRange(int nr, int nc) {
        return 0 <= nr && nr < H && 0 <= nc && nc < W;
    }
}