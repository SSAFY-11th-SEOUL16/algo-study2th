import java.io.*;
import java.util.*;

public class BOJ10819_차이를최대로 {
    static int N, ans;
    static int[] arr, arr2;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        arr = new int[N];
        arr2 = new int[N];
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());
        ans = 0;
        visited = new boolean[N];
        choice(0);
        System.out.println(ans);
    }
    static void choice(int idx) {
        if (idx == N) {
            int sum = 0;
            for (int i = 1; i < N; i++) sum += Math.abs(arr2[i-1]-arr2[i]);
            ans = Math.max(ans, sum);
            return;
        }
        for (int i = 0; i < N; i++) {
            if (visited[i]) continue;
            visited[i] = true;
            arr2[idx] = arr[i];
            choice(idx+1);
            visited[i] = false;
        }
    }
}