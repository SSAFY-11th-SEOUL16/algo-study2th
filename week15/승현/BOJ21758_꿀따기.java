import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ21758_꿀따기 {     // 264ms
    static int n;
    static int[] honey;
    static long total;
    static long[] sumRight;
    static long[] sumLeft;
    static long result;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        honey = new int[n];
        sumLeft = new long[n];
        sumRight = new long[n];
;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            honey[i] = Integer.parseInt(st.nextToken());
        }

        sumLeft[0] = honey[0];
        for (int i = 1; i < n; i++) {
            sumLeft[i] = sumLeft[i-1] + honey[i];
        }
        total = sumLeft[n-1];

        sumRight[n-1] = honey[n-1];
        for (int i = n-2; i >= 0; i--) {
            sumRight[i] = sumRight[i+1] + honey[i];
        }

        getResult();
        System.out.println(result);
    }

    public static void getResult() {
        long r1, r2;

        // 벌 0번, 벌통 n-1번, 벌 2 중간 어딘가인 경우
        for (int i = 1; i <= n-2; i++) {
            // 0번벌 꿀값은 전체에서 시작인덱스, 중간벌 시작점 뺸거
            r1 = total - honey[0] - honey[i];
            // 중간벌 꿀값은 전체값 - 시작점 이전 누적합
            r2 = total - sumLeft[i];
            // 최대 꿀값 갱신
            result = Math.max(result, r1+r2);
        }

        // 반대로 벌통 0번, 벌 n-1번, 중간 어딘가인 경우
        for (int i = n-2; i >= 1; i--) {
            // n-1번벌 꿀값
            r1 = total - honey[n-1] - honey[i];
            // 중간벌 꿀값은 전체값 - 시작점 이전 누적합
            r2 = total - sumRight[i];
            result = Math.max(result, r1+r2);
        }

        // 벌이 양쪽에 고정이고 벌통이 중간 어딘가인 경우
        for (int i = 1; i <= n-2; i++) {
            // 0번벌 꿀값
            r1 = sumLeft[i] - honey[0];
            // n-1번벌 꿀값
            r2 = sumRight[i] - honey[n-1];
            result = Math.max(result, r1+r2);
        }

    }

}
