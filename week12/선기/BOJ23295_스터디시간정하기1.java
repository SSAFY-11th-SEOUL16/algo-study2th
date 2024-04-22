import java.io.*;
import java.util.*;

/*
 * 85880KB
 * 480ms
 * 누적합!
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static final int MAX_TIME = 100_001;
    static int n, t;
    static int k;
    static int[] inOutInfoByTime = new int[MAX_TIME];
    static int[] psum = new int[MAX_TIME];
    static int ansStart, ansEnd;

    public static void main(String[] args) throws IOException {
        n = nextInt();
        t = nextInt();
        for (int i = 0; i < n; ++i) {
            int k = Integer.parseInt(br.readLine());
            for (int j = 0; j < k; ++j) {
                int in = nextInt();
                int out = nextInt();
                ++inOutInfoByTime[in];
                --inOutInfoByTime[out];
            }
        }
        psum[0] = inOutInfoByTime[0];
        for (int i = 1; i < MAX_TIME; ++i) {
            psum[i] = psum[i - 1] + inOutInfoByTime[i];
        }
        int sum = 0;
        for (int i = 0; i < t; ++i) {
            sum += psum[i];
        }
        ansStart = 0;
        ansEnd = t;
        int maxSum = sum;
        for (int i = 0; i < MAX_TIME - t - 1; ++i) {
            sum -= psum[i];
            sum += psum[i + t];
            if (sum > maxSum) {
                maxSum = sum;
                ansStart = i + 1;
                ansEnd = i + t + 1;
            }
        }
        System.out.println(ansStart + " " + ansEnd);
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
