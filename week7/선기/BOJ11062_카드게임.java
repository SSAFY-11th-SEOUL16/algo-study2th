import java.io.*;
import java.util.*;

public class BOJ11062_카드게임 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int[][] dp = new int[1001][1001]; // 카드의 인덱스가 i~j일 때 근우의 최고 점수
    static int[] nums = new int[1001];

    public static void main(String[] args) throws IOException {
        int T = nextInt();
        for (int t = 0; t < T; ++t) {
            int n = nextInt();
            for (int i = 0; i < n; i++) {
                nums[i] = nextInt();
                Arrays.fill(dp[i], 0);
            }

            game(0, n - 1, 1);
            sb.append(dp[0][n - 1]).append("\n");
        }
        System.out.println(sb);
    }

    static int game(int left, int right, int turn) {
        if (left > right) {
            return 0;
        }
        if (dp[left][right] != 0) {
            return dp[left][right];
        }
        if (turn % 2 == 1) {
            return dp[left][right] = Math.max(nums[left] + game(left + 1, right, turn + 1),
                    nums[right] + game(left, right - 1, turn + 1));
        }
        return dp[left][right] = Math.min(game(left + 1, right, turn + 1), game(left, right - 1, turn + 1));
    }

    static String next() throws IOException {
        while (st == null || !st.hasMoreTokens()) {
            st = new StringTokenizer(br.readLine());
        }
        return st.nextToken();
    }

    static int nextInt() throws IOException {
        return Integer.parseInt(next());
    }

}