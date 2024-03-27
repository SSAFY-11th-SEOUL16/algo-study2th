import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_10819_차이를최대로 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;
    private static int[] A;
    private static int res;

    private static void solve(int index, int prev, int currSum, boolean[] check) {
        if(index == N) {
            if(res < currSum) res = currSum;
            return;
        }
        for(int i = 0; i < N; i++) {
            if(check[i]) continue;
            check[i] = true;
            int tmpSum = (index == 0) ? 0 : Math.abs(prev - A[i]);
            solve(index + 1, A[i], currSum + tmpSum, check);
            check[i] = false;
        }
    }


    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        A = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) A[i] = Integer.parseInt(st.nextToken());

        res = 0;
        solve(0, -1, 0, new boolean[N]);
        System.out.println(res);
    }

}
