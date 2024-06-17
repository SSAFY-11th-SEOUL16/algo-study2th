import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1937_욕심쟁이판다 { // 448ms
    static int n;
    static int[][] board;
    static int[][] maxDist;
    static int result;
    static int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        board = new int[n][n];
        maxDist = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                result = Math.max(result, dfs(i, j));
            }
        }

//        for (int i = 0; i < n; i++) {
//            System.out.println(Arrays.toString(maxDist[i]));
//        }

        System.out.println(result);
    }

    public static int dfs(int x, int y) {
        // 방문한 적 있는 곳은 값 가져와서 사용
        if (maxDist[x][y] != 0) {
            return maxDist[x][y];
        }
        // 방문한 적 없으면 1로 초기화하고
        maxDist[x][y] = 1;

        // 사방으로 dfs 실행
        for(int[] dir : dirs) {
            int nx = x + dir[0];
            int ny = y + dir[1];
            if (nx < 0 || nx >= n || ny < 0 || ny >= n || board[x][y] >= board[nx][ny]) {
                continue;
            }

            // 다음 방문지 값 가져와서 비교하고 갱신
            int next = dfs(nx, ny);
            maxDist[x][y] = Math.max(maxDist[x][y], next + 1);
        }
        return maxDist[x][y];
    }

}
