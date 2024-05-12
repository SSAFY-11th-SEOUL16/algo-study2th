import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ26156_나락도락이다 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int divideNum = (int) (Math.pow(10, 9) + 7);

        long[] nums = new long[n + 1];
        nums[0] = 1;
        for (int i = 1; i <= n; i++) {
            nums[i] = (nums[i - 1] * 2) % divideNum;
        }

        long[][] dp = new long[n + 1][3];

        String s = br.readLine();

        long result = 0;
        for (int i = n - 1; i >= 0; i--) {
            char alphabat = s.charAt(i);
            dp[i][0] = dp[i + 1][0]; // k일때
            dp[i][1] = dp[i + 1][1]; // ck일때 저장
            dp[i][2] = dp[i + 1][2]; // ock일때 저장

            if (alphabat == 'K') {
                dp[i][0] = (dp[i][0] + 1) % divideNum;
            } else if (alphabat == 'C') {
                dp[i][1] = (dp[i][1] + dp[i][0]) % divideNum;
            } else if (alphabat == 'O') {
                dp[i][2] = (dp[i][2] + dp[i][1]) % divideNum;
            } else if (alphabat == 'R') {
                long temp = (dp[i][2] * nums[i]) % divideNum;
                result = (result + temp) % divideNum;
            }
        }

        System.out.println(result);

    }

}
