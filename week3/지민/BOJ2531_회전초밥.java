import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 시간: 164ms
 * 투포인터
 */

public class BOJ2531_회전초밥 {
    static int N; // 접시의 수
    static int d; // 초밥의 가짓수 (1~d)
    static int k; // 연속해서 먹는 접시의 수
    static int c; // 쿠폰 번호
    static int[] dish; // 벨트에 놓인 초밥 종류

    private static int solve() {
        int res = 0;
        int[] eat = new int[d + 1];
        int speciesCnt = 1;

        eat[c] += 1;
        for (int i = 0; i < k; i++) {
            if(eat[dish[i]] == 0) speciesCnt += 1;
            eat[dish[i]] += 1;
        }

        int i = 0;
        int j = k;
        while (i < N) {
            res = Math.max(res, speciesCnt);
            eat[dish[i]] -= 1;
            if(eat[dish[i]] == 0) speciesCnt -= 1;
            if(eat[dish[j]] == 0) speciesCnt += 1;
            eat[dish[j]] += 1;

            i += 1;
            j = (j + 1) % N;
        }
        return res;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        dish = new int[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            dish[i] = Integer.parseInt(st.nextToken());
        }
        System.out.println(solve());
    }
}
