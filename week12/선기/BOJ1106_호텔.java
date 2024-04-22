import java.io.*;
import java.util.*;
/* 
 * 12056kb
 * 112ms
 * dp
 */
public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int n, c;
    static int[] dp;
    static int[] info;

    public static void main(String[] args) throws IOException {
        c = nextInt();
        n = nextInt();
        dp = new int[100_001];
        info = new int[101];
        for (int i = 0; i < n; ++i) {
            int cost = nextInt();
            int value = nextInt();
            info[cost] = Math.max(info[cost], value);
        }
        for (int i = 1; i <= 100_000; ++i) {
            for (int j = 1; j <= i && j <= 100; ++j) {
                dp[i] = Math.max(dp[i], dp[i - j] + info[j]);
            }
            if (dp[i] >= c) {
                System.out.println(i);
                return;
            }
        }
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