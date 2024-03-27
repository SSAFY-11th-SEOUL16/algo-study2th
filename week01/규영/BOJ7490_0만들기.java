import java.io.*;

public class BOJ7490_0만들기 {
    static int N;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        while (T-->0) {
            N = Integer.parseInt(br.readLine());
            int[] arr = new int[N];
            for (int i = 0; i < N; i++) arr[i] = i+1;
            choice(1, 1, 0, true, "1");
            sb.append('\n');
        }
        System.out.println(sb);
    }
    static void choice (int idx, int num, int sum, boolean flag, String str) {
        if (idx == N) {
//            System.out.println(str);
            if (flag) sum += num;
            else sum -= num;
//            System.out.println("sum : " + sum);
            if (sum == 0) sb.append(str).append('\n');
            return;
        }
        int res = flag ? sum+num : sum-num;
        choice(idx+1, num*10+idx+1, sum, flag, str+" "+(idx+1));
        choice(idx+1, idx+1, res, true, str+"+"+(idx+1));
        choice(idx+1, idx+1, res, false, str+"-"+(idx+1));
    }
}