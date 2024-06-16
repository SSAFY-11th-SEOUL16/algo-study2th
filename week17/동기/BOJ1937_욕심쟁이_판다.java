import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
/*
 * 620ms
 */
public class Main {
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};

    static int n;
    static int[][] map;
    static int[][] dp;

    static int max = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        dp = new int[n][n];

        for (int i = 0; i < n; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(dp[i][j] == 0)
                    max = Math.max(max,dfs(j, i));
            }
        }
        System.out.println(max);
    }

    static int dfs(int x, int y) {
        int maxPath = 0;

        for (int i = 0; i < 4; i++) {
            int x1 = x + dx[i];
            int y1 = y + dy[i];

            if (isNullCheck(x1, y1) && map[y1][x1] > map[y][x]) {
                if (dp[y1][x1] != 0) {
                    maxPath = Math.max(maxPath,dp[y1][x1]);
                } else {
                    maxPath = Math.max(maxPath, dfs(x1, y1));
                }
            }
        }
        maxPath++;
        dp[y][x] = maxPath;
        return maxPath;
    }

    static boolean isNullCheck(int x, int y) {
        return x >= 0 && y >= 0 && x < n && y < n;
    }
}