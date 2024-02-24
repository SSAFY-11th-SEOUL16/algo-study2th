import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1189_컴백홈 {

    static int r, c, k;
    static char[][] graph;
    static boolean[][] visited;
    static int[] dxs = { -1, 1, 0, 0 };
    static int[] dys = { 0, 0, -1, 1 };
    static int result = 0;

    static boolean in_range(int x, int y) {
        return 0 <= x && x < r && 0 <= y && y < c;
    }

    static void dfs(int x, int y, int step) {
        if (step == k) {
            if (x == 0 && y == c - 1)
                result++;
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nx = x + dxs[i];
            int ny = y + dys[i];
            if (in_range(nx, ny) && !visited[nx][ny] && graph[nx][ny] != 'T') {
                visited[nx][ny] = true;
                dfs(nx, ny, step + 1);
                visited[nx][ny] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        graph = new char[r][c];
        visited = new boolean[r][c];

        for (int i = 0; i < r; i++) {
            String s = br.readLine();
            for (int j = 0; j < c; j++) {
                graph[i][j] = s.charAt(j);
            }
        }

        visited[r - 1][0] = true;
        dfs(r - 1, 0, 1);

        System.out.println(result);

    }

}
