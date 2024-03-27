import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2531_회전초밥 {

    static int n, d, k, c;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        int[] sushi = new int[n];

        for (int i = 0; i < n; i++) {
            sushi[i] = Integer.parseInt(br.readLine());
        }

        // sliding window
        int result = 0;

        int[] types = new int[d + 1];
        int nowtypes = 0;

        Queue<Integer> q = new ArrayDeque<>();

        for (int i = 0; i < n + k; i++) {
            int now_sushi = sushi[i % n];

            if (types[now_sushi] == 0) {
                nowtypes++;
            }
            types[now_sushi]++;

            q.add(now_sushi);
            if (q.size() > k) {
                int out = q.poll();
                types[out]--;
                if (types[out] == 0) {
                    nowtypes--;
                }

                if (types[c] == 0) {
                    result = Math.max(nowtypes + 1, result);
                } else {
                    result = Math.max(nowtypes, result);
                }
            }
        }

        System.out.println(result);
    }

}