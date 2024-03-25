import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int l, k, c;
    static TreeSet<Integer> cutPoints = new TreeSet<>();

    public static void main(String[] args) throws IOException {
        l = nextInt();
        k = nextInt();
        c = nextInt();
        cutPoints.add(0);
        cutPoints.add(l);
        for (int i = 0; i < k; ++i) {
            cutPoints.add(nextInt());
        }
        Integer[] points = cutPoints.toArray(new Integer[0]);
        int left = 0;
        int right = l;
        int ans1 = l;
        int ans2 = 0;
        outer:
        while (left <= right) {
            int mid = (left + right) >>> 1;
            int cutCnt = 0;
            int length = 0;
            for (int i = points.length - 2; i >= 0; --i) {
                length += points[i + 1] - points[i];
                if (length > mid) {
                    length = points[i + 1] - points[i];
                    cutCnt++;
                    if (length > mid) {
                        left = mid + 1;
                        continue outer;
                    }
                }
            }
            if (cutCnt <= c) {
                ans1 = mid;
                ans2 = cutCnt == c ? length : points[1];
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        System.out.println(ans1 + " " + ans2);
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
