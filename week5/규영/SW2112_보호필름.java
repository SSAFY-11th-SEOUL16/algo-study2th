import java.io.*;
import java.util.*;

// 643ms

public class SW2112_보호필름 {
    static int D, W, K, ans;
    static boolean[][] arr;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine().trim());
            D = Integer.parseInt(st.nextToken()); // 두께
            W = Integer.parseInt(st.nextToken()); // 가로
            K = Integer.parseInt(st.nextToken()); // 합격 기준
            arr = new boolean[D][W];
            for (int i = 0; i < D; i++) {
                st = new StringTokenizer(br.readLine().trim());
                for (int j = 0; j < W; j++) {
                    arr[i][j] = st.nextToken().charAt(0) == '1' ? true : false;
                }
            }
            ans = Integer.MAX_VALUE;
            dfs(0, 0);
            sb.append('#').append(t).append(' ').append(ans == Integer.MAX_VALUE ? 0 : ans).append('\n');
        }
        System.out.print(sb);
    }
    static void dfs(int idx, int cnt) {
        if (cnt == ans) return;
        if (idx == D) {
            for (int i = 0; i < W; i++) {
                boolean flag = false;
                int cntK = 1;
                for (int j = 1; j < D; j++) {
                    if (arr[j-1][i] == arr[j][i]) cntK++;
                    else cntK = 1;
                    if (cntK == K) {
                        flag = true;
                        break;
                    }
                }
                if (!flag) return;
            }
            ans = Math.min(ans, cnt);
            return;
        }
        boolean[] temp = new boolean[W];
        for (int i = 0; i < W; i++) temp[i] = arr[idx][i];
        dfs(idx+1, cnt);
        Arrays.fill(arr[idx], true);
        dfs(idx+1, cnt+1);
        Arrays.fill(arr[idx], false);
        dfs(idx+1, cnt+1);
        arr[idx] = temp;
    }
}