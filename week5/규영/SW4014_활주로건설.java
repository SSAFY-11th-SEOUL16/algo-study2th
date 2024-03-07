import java.io.*;
import java.util.*;

// 오답

public class SW4014_활주로건설 {
    static int N, X;
    static int[][] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            X = Integer.parseInt(st.nextToken());
            arr = new int[N][N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            int ans = build(true)+build(false);
            sb.append('#').append(t).append(' ').append(ans).append('\n');
        }
        System.out.print(sb);
    }
    static int build(boolean flag) {
        int runway = 0;
        for (int i = 0; i < N; i++) {
            boolean canBuild = true;
            int height = flag ? arr[i][0] : arr[0][i], cnt = 1;
            for (int j = 1; j < N; j++) {
                int now = flag ? arr[i][j] : arr[j][i];
                if (height == now) cnt++;
                else {
                    if (now-height == 1) {
                        if (cnt < X) {
                            canBuild = false;
                            break;
                        }
                        height++;
                    }
                    else if (height-now == 1) {
                        int nowCnt = 0;
                        for (int k = j; k < N; k++) {
                            if (now == (flag ? arr[i][k] : arr[k][i])) nowCnt++;
                            else break;
                        }
                        if (nowCnt >= X) {
                            height--;
                            j += X-1;
                        }
                        else if (nowCnt < X) {
                            canBuild = false;
                            break;
                        }
                    }
                    else {
                        canBuild = false;
                        break;
                    }
                    cnt = 1;
                }
            }
            if (canBuild) runway++;
        }
        return runway;
    }
}