import java.io.*;
import java.util.*;

/**
 * 11896KB
 * 140ms
 */
public class BOJ1111_IQTest {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int n;
    static int[] nums;
    static String ans;
    static int ansInt;
    static boolean ansFound;
    static boolean multipleAns;

    public static void main(String[] args) throws IOException {
        n = nextInt();
        nums = new int[n];
        for (int i = 0; i < n; ++i) {
            nums[i] = nextInt();
        }
        for (int i = -200; i < 200; ++i) {
            for (int j = -20000; j < 20000; ++j) {
                backtrack(i, j, 0);
            }
        }
        if (!ansFound) {
            ans = "B";
        }
        System.out.println(ans);
    }

    static void backtrack(int a, int b, int idx) {
        if (multipleAns) {
            return;
        }
        if (idx == n - 1) {
            if (ans == null) {
                ansInt = nums[idx] * a + b;
                ans = String.valueOf(nums[idx] * a + b);
                ansFound = true;

            } else if (ansInt == nums[idx] * a + b) {
                // do nothing
            } else {
                ans = "A";
                multipleAns = true;
            }
            return;
        }
        if (nums[idx] * a + b == nums[idx + 1]) {
            backtrack(a, b, idx + 1);
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
