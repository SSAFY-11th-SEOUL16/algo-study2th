import java.io.*;
import java.util.StringTokenizer;

public class BOJ15661_링크와스타트 {
    static int N, ans;
    static int[][] arr;
    static boolean[] member;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        member = new boolean[N];
        ans = Integer.MAX_VALUE;
        choice(0);
        System.out.println(ans);
    }
    static void choice(int idx) {
        if (idx == N) {
            int start = 0, link = 0;
            for (int i = 0; i < N; i++) {
                for (int j = i+1; j < N; j++) {
                    if (member[i] && member[j]) start += arr[i][j]+arr[j][i];
                    else if (!member[i] && !member[j]) link += arr[i][j]+arr[j][i];
                }
            }
            ans = Math.min(ans, Math.abs(start-link));
            return;
        }
        member[idx] = true;
        choice(idx+1);
        member[idx] = false;
        choice(idx+1);
    }
}