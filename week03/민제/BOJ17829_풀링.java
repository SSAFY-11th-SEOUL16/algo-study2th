import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ17829_풀링 {

    static int n;
    static int[][] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        graph = new int[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(solve(0, 0, n));

    }

    static int solve(int x, int y, int n) {
        int len = n / 2;
        if (n == 2) {
            List<Integer> arr = new ArrayList<>();
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 2; j++) {
                    arr.add(graph[x + i * len][y + j * len]);
                }
            }
            Collections.sort(arr);
            return arr.get(2);
        }

        List<Integer> arr = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                arr.add(solve(x + i * len, y + j * len, len));
            }
        }
        Collections.sort(arr);
        return arr.get(2);
    }
}
