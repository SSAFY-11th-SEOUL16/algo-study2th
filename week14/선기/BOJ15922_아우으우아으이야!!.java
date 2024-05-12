import java.io.*;
import java.util.*;
/*
 * 364ms
 */
public class BOJ15922_아우으우아으이야 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int n;
    static int ans;
    static int prev;

    public static void main(String[] args) throws IOException {
        n = nextInt();
        prev = -1_000_000_000;
        for (int i = 0; i < n; ++i) {
            int a = nextInt();
            int b = nextInt();
            if (prev <= a) {
                ans += b - a;
                prev = b;
            } else if (prev <= b) {
                ans += b - prev;
                prev = b;
            }
        }
        System.out.println(ans);
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
