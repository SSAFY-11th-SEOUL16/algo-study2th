import java.util.*;
import java.io.*;
/*
 * 84ms
 */
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        int c = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        int[] dp = new int[c + 100];
        Arrays.fill(dp, 1000001);
        dp[0] = 0;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int cost = Integer.parseInt(st.nextToken());
            int people = Integer.parseInt(st.nextToken());

            for (int j = people; j < dp.length; j++) {
                dp[j] = Math.min(dp[j], dp[j - people] + cost);
            }
        }
        int answer = dp[dp.length - 1];
        for (int i = dp.length - 1; i >= c; i--)
            answer = Math.min(answer, dp[i]);

        System.out.println(answer);
    }
}
