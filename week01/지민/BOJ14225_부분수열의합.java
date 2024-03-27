import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14225_부분수열의합 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;
    private static int[] S;
    private static int MAX;

    private static int solve() {
        boolean[] check = new boolean[MAX + 1];

        for(int i = 1; i < (1 << N); i++) {
            int tmpSum = 0;
            for(int j = 0; j < N; j++) {
                if((i & (1 << j)) != 0) {
                    tmpSum += S[j];
                }
            }
            check[tmpSum] = true;
        }

        int i = 1;
        while(i <= MAX && check[i]) i += 1;
        return i;
    }

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        MAX = 0;
        S = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            S[i] = Integer.parseInt(st.nextToken());
            MAX += S[i];
        }
        System.out.println(solve());
    }

}
