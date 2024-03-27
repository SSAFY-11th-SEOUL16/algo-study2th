import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 시간: 532ms
 * 분할정복
 */

public class BOJ17829_222풀링 {
    static int N;
    static int[][] matrix;
    static final int MIN = -10001;

    private static int pooling(int[] list) {
        int first = MIN, second = MIN;
        for(int x : list) {
            if(first < x) {
                second = first;
                first = x;
            }
            else if(second < x) second = x;
        }
        return second;
    }

    private static int solve(int y, int x, int size) {
        if(size == 1) return matrix[y][x];
        int nSize = size / 2;
        int a = solve(y, x, nSize);
        int b = solve(y + nSize, x, nSize);
        int c = solve(y, x + nSize, nSize);
        int d = solve(y + nSize, x + nSize, nSize);
        return pooling(new int[] {a, b, c, d});
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        matrix = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        System.out.println(solve(0, 0, N));
    }
}
