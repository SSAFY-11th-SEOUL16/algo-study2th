import java.io.*;
import java.util.StringTokenizer;

// 131ms

public class SW4008_숫자만들기 {
    static int N, min, max;
    static int[] cnt, nums;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            cnt = new int[4];
            for (int i = 0; i < 4; i++) {
                cnt[i] = Integer.parseInt(st.nextToken());
            }
            st = new StringTokenizer(br.readLine());
            nums = new int[N];
            for (int i = 0; i < N; i++) {
                nums[i] = Integer.parseInt(st.nextToken());
            }
            min = Integer.MAX_VALUE;
            max = Integer.MIN_VALUE;
            choice(1, nums[0]);
            sb.append('#').append(t).append(' ').append(max-min).append('\n');
        }
        System.out.print(sb);
    }
    static void choice(int idx, int res) {
        if (idx == N) {
            min = Math.min(min, res);
            max = Math.max(max, res);
            return;
        }
        for (int i = 0; i < 4; i++) {
            if (cnt[i] == 0) continue;
            cnt[i]--;
            if (i == 0) choice(idx+1, res+nums[idx]);
            else if (i == 1) choice(idx+1, res-nums[idx]);
            else if (i == 2) choice(idx+1, res*nums[idx]);
            else choice(idx+1, res/nums[idx]);
            cnt[i]++;
        }
    }
}