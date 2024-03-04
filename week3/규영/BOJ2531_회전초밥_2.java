import java.io.*;
import java.util.*;

// 첫 번째 풀이에선 시간이 좀 나와서 불필요한 부분 개선 시켰습니다
// 264ms -> 136ms

public class BOJ2531_회전초밥_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()), d = Integer.parseInt(st.nextToken()), k = Integer.parseInt(st.nextToken()), c = Integer.parseInt(st.nextToken()), cnt = 1;
        int[] count = new int[d+1], dish = new int[N];
        count[c]++;
        for (int i = 0; i < N; i++) dish[i] = Integer.parseInt(br.readLine());
        for (int i = 0; i < k; i++) {
            if (count[dish[i]]++ == 0) cnt++;
        }
        int ans = cnt;
        for (int i = k; i < N+k; i++) {
            if (count[dish[(i-k)%N]]-- == 1) cnt--;
            if (count[dish[i%N]]++ == 0) cnt++;
            ans = Math.max(ans, cnt);
        }
        System.out.println(ans);
    }
}