import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int r;
    static int c;
    static int k;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};
    static int answer;
    static String[][] map;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        map = new String[r][c];
        visited = new boolean[r][c];
        answer = 0;
        for (int i = 0; i < r; i++) {
            map[i] = br.readLine().split("");
        }
        visited[r - 1][0] = true;
        dfs(1, 0, r - 1);
        System.out.println(answer);

    }

    static void dfs(int depth, int x, int y) {

        if (depth == k) {
            if (x == (c - 1) && y == 0)
                answer++;
            return;
        }

        for (int i = 0; i < 4; i++) {
            int x1 = x + dx[i];
            int y1 = y + dy[i];

            if (isNullCheck(x1, y1) && !visited[y1][x1] && map[y1][x1].equals(".")) {
                visited[y1][x1] = true;
                dfs(depth + 1, x1, y1);
                visited[y1][x1] = false;
            }

        }
    }

    static boolean isNullCheck(int x, int y) {
        return x >= 0 && y >= 0 && x < c && y < r;
    }
}