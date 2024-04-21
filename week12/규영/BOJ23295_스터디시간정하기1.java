import java.io.*;
import java.util.*;

/*
    82788KB, 1124ms
 */

public class BOJ23295_스터디시간정하기1 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()), T = Integer.parseInt(st.nextToken());
        int[] arr = new int[100001];
        int len = 0;
        while (N-->0) {
            int K = Integer.parseInt(br.readLine());
            while (K-->0) {
                st = new StringTokenizer(br.readLine());
                int S = Integer.parseInt(st.nextToken()), E = Integer.parseInt(st.nextToken());
                len = Math.max(S, Math.max(len, E));
                for (int i = S; i < E; i++) arr[i]++;
            }
        }
        int ans = 0, sum = 0, max = 0;
        for (int i = 0; i < T; i++) max = sum += arr[i];
        for (int i = 0; i < len-T; i++) {
            if (max < sum-arr[i]+arr[i+T]) {
                max = sum-arr[i]+arr[i+T];
                ans = i+1;
            }
            sum = sum-arr[i]+arr[i+T];
        }
        System.out.print(ans + " " + (ans+T));
    }
}