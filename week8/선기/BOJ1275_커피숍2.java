import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int n, q;
    static int[] nums;
    static long[] bit;

    public static void main(String[] args) throws IOException {
        n = nextInt();
        q = nextInt();
        nums = new int[n + 1];
        bit = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            nums[i] = nextInt();
            update(i, nums[i]);
        }
        for (int i = 0; i < q; i++) {
            int x = nextInt();
            int y = nextInt();
            int a = nextInt();
            int b = nextInt();
            long ans;
            if (x < y) {
                ans = sum(y) - sum(x - 1);
            } else {
                ans = sum(x) - sum(y - 1);
            }
            long diff = (long) b - nums[a];
            nums[a] = b;
            update(a, diff);
            sb.append(ans).append("\n");
        }
        System.out.println(sb);
    }

    static long sum(int idx) {
        long result = 0;
        while (idx > 0) {
            result += bit[idx];
            idx -= idx & -idx;
        }
        return result;
    }

    static void update(int idx, long value) {
        while (idx <= n) {
            bit[idx] += value;
            idx += idx & -idx;
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
