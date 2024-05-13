import java.io.*;
import java.util.*;

/*
 * 92ms
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int n;
    static int h, d, k;
    static int[] punch;
    static int maxH = -1;

    public static void main(String[] args) throws IOException {
        n = nextInt();
        h = nextInt();
        d = nextInt();
        k = nextInt();
        punch = new int[n];
        for (int i = 0; i < n; ++i) {
            punch[i] = Integer.parseInt(br.readLine());
        }
        dfs(0, true, h, d);
        System.out.println(maxH <= 0 ? -1 : maxH);
    }

    static void dfs(int idx, boolean surprise, int tmpH, int tmpD) {
//        System.out.println(idx + " " + surprise + " " + tmpH + " " + tmpD);
        if (tmpH <= maxH) {
            return;
        }
        if (idx == n) {
            maxH = tmpH;
            return;
        }
        int damage = punch[idx] - tmpD;
        damage = damage > 0 ? damage : 0;
        // 놀라게 하기
        if (surprise && idx < n - 1) {
            int tmp = punch[idx + 1];
            punch[idx + 1] = 0;
            dfs(idx + 1, !surprise, tmpH - damage, tmpD);
            punch[idx + 1] = tmp;
        }
        // 웅크리기
        dfs(idx + 1, surprise, tmpH - damage / 2, tmpD);
        // 네발
        damage = punch[idx] - (tmpD + k);
        damage = damage > 0 ? damage : 0;
        dfs(idx + 1, surprise, tmpH - damage, tmpD + k);
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
