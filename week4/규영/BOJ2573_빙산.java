import java.io.*;
import java.util.*;

public class BOJ2573_빙산 {
    static class Node {
        int r, c;
        Node (int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    static int N, M;
    static boolean[][] visited;
    static int[][] cnt, arr, dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}}; // 상 우 하 좌
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][M]; // 빙산의 높이 저장
        cnt = new int[N][M]; // 각 칸에서 바닷물에 접한 부분의 개수 저장
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int iceberg = 0, year = 0;
        while (!isAllMelt()) {
            // 바닷물에 접한 부분의 개수를 세어주는 과정
            visited = new boolean[N][M];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (arr[i][j] != 0) cnt[i][j] = count(i, j);
                }
            }
            melt();
            year++;
            iceberg = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (arr[i][j] != 0 && !visited[i][j]) {
                        iceberg++;
                        bfs(i, j);
                    }
                }
            }
            if (iceberg >= 2) break; // 두 덩어리 이상으로 분리되는 최초의 시간을 구해야 하므로
        }
        // iceberg가 2보다 작다면 다 녹아서 while문을 탈출했다는 것이므로 0을 출력한다
        System.out.println(iceberg >= 2 ? year : 0);
    }

    // 빙산의 덩어리 수를 셀 때 필요
    static void bfs(int r, int c) {
        visited[r][c] = true;
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(r, c));
        while (!q.isEmpty()) {
            Node now = q.poll();
            for (int d = 0; d < 4; d++) {
                int nr = now.r+dir[d][0], nc = now.c+dir[d][1];
                if (!isInRange(nr, nc) || visited[nr][nc] || arr[nr][nc] == 0) continue;
                visited[nr][nc] = true;
                q.offer(new Node(nr, nc));
            }
        }
    }

    // 바닷물에 접한 부분의 개수를 세어줌
    static int count(int r, int c) {
        int cnt = 0;
        for (int d = 0; d < 4; d++) {
            int nr = r+dir[d][0], nc = c+dir[d][1];
            if (isInRange(nr, nc) && arr[nr][nc] == 0) cnt++;
        }
        return cnt;
    }

    // cnt(바닷물에 접한 부분의 개수 저장되어 있는 배열)에 저장된 수만큼 빙산의 높이에서 빼줌
    static void melt() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                arr[i][j] -= cnt[i][j];
                if (arr[i][j] < 0) arr[i][j] = 0;
            }
        }
    }

    // 모두 0인지(녹았는지) 확인
    static boolean isAllMelt() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (arr[i][j] != 0) return false;
            }
        }
        return true;
    }

    static boolean isInRange(int nr, int nc) {
        return 0 <= nr && nr < N && 0 <= nc && nc < M;
    }
}