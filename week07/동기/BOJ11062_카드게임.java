import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 592ms
 */
public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < t; tc++) {
            int n = Integer.parseInt(br.readLine());
            int[][] dp = new int[n + 2][n + 1];
            int[] arr = new int[n + 1];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= n; i++)
                arr[i] = Integer.parseInt(st.nextToken());

            boolean geunTurn = n % 2 != 0;

            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n - i + 1; j++) {
                    int row = j;
                    int col = i + j - 1;

                    if (geunTurn) {
                        dp[row][col] = Math.max(dp[row + 1][col] + arr[row], dp[row][col - 1] + arr[col]);
                    } else {
                        dp[row][col] = Math.min(dp[row + 1][col], dp[row][col - 1]);
                    }

                }
                geunTurn = !geunTurn;
            }
            sb.append(dp[1][n]).append("\n");
        }
        System.out.println(sb);
    }


}