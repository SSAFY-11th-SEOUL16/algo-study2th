import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static StringBuilder[] sbArr;
    static int K;
    static String[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(br.readLine());
        sbArr = new StringBuilder[K];
        for (int i = 0; i < K; i++)
            sbArr[i] = new StringBuilder();

        arr = br.readLine().split(" ");
        dfs(0, arr.length / 2);
        for (StringBuilder sb : sbArr) System.out.println(sb);
    }

    static void dfs(int depth, int i) {
        if (depth == K) return;
        int diff = (int) Math.pow(2, K - depth - 2);
        sbArr[depth].append(arr[i] + " ");
        // left
        dfs(depth + 1, i - diff);
        // right
        dfs(depth + 1, i + diff);
    }
}