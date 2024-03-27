import java.io.*;
import java.util.StringTokenizer;

// 264ms

public class BOJ2531_회전초밥 {
    static int[] cnt;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()), d = Integer.parseInt(st.nextToken()), k = Integer.parseInt(st.nextToken()), c = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(br.readLine());
        cnt = new int[d+1];
        cnt[c]++;
        for (int i = 0; i < k; i++) cnt[arr[i]]++;
        int ans = count();
        for (int i = k; i < N+k; i++) {
            cnt[arr[i-k]]--;
            cnt[arr[i%N]]++;
            ans = Math.max(ans, count());
        }
        System.out.println(ans);
    }
    static int count() {
        int num = 0;
        for (int i : cnt) {
            if (i > 0) num++;
        }
        return num;
    }
}