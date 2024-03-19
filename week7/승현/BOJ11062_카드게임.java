import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ11062_카드게임 { // 328ms
    static int N;
    static int[] cards;
    static int[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            N = Integer.parseInt(br.readLine());
            cards = new int[N + 1];
            // 시작지점, 끝지점
            dp = new int[N + 2][N + 1];

            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                cards[i] = Integer.parseInt(st.nextToken());
            }
            getResult();
            sb.append(dp[1][N]).append("\n");
        }
        System.out.print(sb);
    }

    public static void getResult() {
        boolean myTurn = false;
        // N이 홀수이면 나부터 뽑음
        if (N % 2 == 1) {
            myTurn = true;
        }
        // cnt : 카드 갯수
        for (int cnt = 1; cnt <= N; cnt++) {
            for (int start = 1; start <= N - cnt + 1; start++) {
                int end = start + cnt - 1;
                // 내턴이면 최대값
                if (myTurn) {
                    dp[start][end] = Math.max(dp[start][end-1] + cards[end], cards[start] + dp[start+1][end]);
                }
                // 상대 턴이면 최소값
                else {
                    dp[start][end] = Math.min(dp[start][end - 1], dp[start + 1][end]);
                }
            }
            // 카드 갯수가 1개 늘어나면 시작하는 사람 바뀜
            myTurn = !myTurn;
        }
    }
}