import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1106_호텔 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int needPeople = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        int[] costs = new int[n];
        int[] people = new int[n];

        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            costs[i] = Integer.parseInt(st.nextToken());
            people[i] = Integer.parseInt(st.nextToken());
        }

        int INF = 100000000;
        int[] dp = new int[needPeople+101];
        Arrays.fill(dp, INF);
        dp[0] = 0;

        for(int i=1; i<=needPeople+100; i++) {
            for(int j=0; j<n; j++) {
                if (people[j] <= i) {
                    dp[i] = Math.min(dp[i], dp[i-people[j]] + costs[j]);
                }
            }
        }

        int result = Integer.MAX_VALUE;

        for(int i=needPeople; i<=needPeople+100; i++) {
            result = Math.min(result, dp[i]);
        }

        System.out.println(result);
    }

}
