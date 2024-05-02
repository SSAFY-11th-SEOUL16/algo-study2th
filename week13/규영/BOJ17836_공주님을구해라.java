import java.io.*;
import java.util.*;

/*
    13996KB, 132ms
 */

public class BOJ17836_공주님을구해라 {
    static class Node {
        int r, c;
        Node (int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    static int N, M, T;
    static Node sword;
    static boolean[][] visited;
    static int[][] map, dist, dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        dist = new int[N][M];
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                if ((map[i][j] = Integer.parseInt(st.nextToken())) == 2) sword = new Node(i, j);
            }
        }
        bfs(0, 0);
        // withoutSword: 검 없이 공주까지의 거리
        // withSword: 검까지의 최소 거리+검부터 공주까지의 거리. 검까지 갈 수 없으면 0으로 설정해줌 (불필요한 값 저장 방지)
        int ans = 0, withoutSword = dist[N-1][M-1], withSword = dist[sword.r][sword.c] == 0 ? 0 : dist[sword.r][sword.c]+(N-sword.r-1)+(M-sword.c-1);
        if (withoutSword != 0) { // can 검없
            if (withSword == 0) ans = withoutSword; // cant 검있 -> 무조건 검없
            else ans = Math.min(withoutSword, withSword); // can 검없 검있
        } else ans = withSword; // cant 검없 -> can 검있 / cant 검있
        // ans >= N+M-2: 검 먹고 바로 공주까지 가는 게 최소인데 그거 보다 작으면 일어날 수 없는 일
        System.out.print(T >= ans && ans >= N+M-2 ? ans : "Fail");
    }

    static void bfs(int r, int c) {
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(r, c));
        visited[r][c] = true;
        while (!q.isEmpty()) {
            Node now = q.poll();
            for (int d = 0; d < 4; d++) {
                int nr = now.r+dir[d][0], nc = now.c+dir[d][1];
                if (!isInRange(nr, nc) || visited[nr][nc] || map[nr][nc] == 1) continue;
                visited[nr][nc] = true;
                dist[nr][nc] = dist[now.r][now.c]+1;
                q.offer(new Node(nr, nc));
            }
        }
    }

    static boolean isInRange(int nr, int nc) {
        return 0 <= nr && nr < N && 0 <= nc && nc < M;
    }
}