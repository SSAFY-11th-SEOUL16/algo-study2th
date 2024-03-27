package 규영;

import java.io.*;
import java.util.*;

public class BOJ1189_컴백홈 {
    static int R, C, K, ans;
    static char[][] arr;
    static boolean[][] visited;
    static int[][] dir = {{0, -1}, {-1, 0}, {0, 1}, {1, 0}}; // 좌 상 우 하
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new char[R][C];
        for (int i = 0; i < R; i++) arr[i] = br.readLine().toCharArray();
        visited = new boolean[R][C]; // 한수는 똑똑하니까 방문했던 곳은 체크!
        visited[R-1][0] = true;
        ans = 0;
        dfs(R-1, 0, 1); // 왼쪽 아래부터 시작, 오른쪽 위까지 가야한다
        System.out.println(ans);
    }
    static void dfs(int r, int c, int cnt) {
        if (r == 0 && c == C-1) { // 집의 위치 (오른쪽 위)에 도달했을 때 확인
            if (cnt == K) ans++; // 거리가 K인 경우만 count
            return; // K 이상의 이동은 볼 필요가 없으므로 return
        }
        if (cnt > K) return;
        for (int d = 0; d < 4; d++) {
            int nr = r+dir[d][0], nc = c+dir[d][1];
            // 범위 벗어났을 경우, 가려는 위치에 T가 존재하는 경우, 이미 방문했던 곳일 경우 continue
            if (nr < 0 || R <= nr || nc < 0 || C <= nc || arr[nr][nc] == 'T' || visited[nr][nc]) continue;
            visited[nr][nc] = true;
            dfs(nr, nc, cnt+1);
            visited[nr][nc] = false;
        }
    }
}