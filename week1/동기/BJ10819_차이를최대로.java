import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int max = 0;
    static int N;
    static int[] arr;
    static boolean[] visited;
    static int[] curArr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        for (int i = 0; i < N; i++) {
            visited = new boolean[N];
            visited[i] = true;
            curArr = new int[N];
            curArr[0] = arr[i];
            dfs(1);
        }

        System.out.println(max);
    }

    static void dfs(int depth) {
        if (depth == N) {
            max = Math.max(max, getResult());
        }
        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                curArr[depth] = arr[i];
                dfs(depth + 1);
                visited[i] = false;
            }
        }
    }

    static int getResult() {
        int cnt = 0;
        for (int i = 1; i < N; i++) cnt += Math.abs(curArr[i - 1] - curArr[i]);
        return cnt;
    }
}
