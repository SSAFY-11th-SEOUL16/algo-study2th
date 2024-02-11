import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1189_컴백홈 {
    static int R;
    static int C;
    static int K;
    static char[][] map;
    static int cnt;
    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, 1, -1};

    private static boolean inRange(int y, int x) {
        return 0 <= y && y < R && 0 <= x && x < C;
    }

    private static void solve(int y, int x, int dist, boolean[][] ok) {
        if(y == 0 && x == C - 1) {
            if(dist == K) cnt += 1;
            return;
        }
        for(int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];
            if(!inRange(ny, nx)) continue;

            if(map[ny][nx] == '.' && !ok[ny][nx]) {
                ok[ny][nx] = true;
                solve(ny, nx, dist + 1, ok);
                ok[ny][nx] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        for(int i = 0; i < R; i++) {
            map[i] = br.readLine().toCharArray();
        }
        cnt = 0;
        boolean[][] ok = new boolean[R][C];
        ok[R - 1][0] = true;
        solve(R - 1, 0, 1, ok);
        System.out.println(cnt);
    }
}
