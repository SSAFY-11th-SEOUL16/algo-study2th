import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1239_차트 { // 116ms
    static int n;
    static int[] data;
    static int[] nums;
    static boolean[] visited;
    static int result;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        data = new int[n];
        nums = new int[n];
        visited = new boolean[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int d = Integer.parseInt(st.nextToken());

            // 하나가 50이상인 경우 줄 없으므로 예외처리
            if (d > 50) {
                System.out.println(0);
                return;
            }
            data[i] = d;
        }

        // 순열 돌림
        permutation(0);
        // 셀때 50인경우 다 세서 2번 중복되므로 / 2
        System.out.println(result / 2);
    }

    public static void permutation(int depth) {
        if (depth == n) {
            calMaxResult();
            return;
        }

        for (int i = 0; i < n; i++) {
            if (visited[i]) {
                continue;
            }
            nums[depth] = data[i];
            visited[i] = true;
            permutation(depth+1);
            visited[i] = false;
        }
    }

    public static void calMaxResult() {
        int cnt = 0;

        // 전체 돌면서
        for (int i = 0; i < n; i++) {
            int sum = 0;
            // i번째 인덱스부터
            int idx = i;

            // 합이 50이 넘지 않도록 더함
            while (sum < 50) {
                sum += nums[idx];
                idx = (idx + 1) % n;
            }
            // 합이 정확히 50인 경우만 셈
            if (sum == 50) {
                cnt++;
            }
        }

        result = Math.max(result, cnt);
    }

}
