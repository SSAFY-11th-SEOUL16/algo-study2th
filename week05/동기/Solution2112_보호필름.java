import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
/*
 * 1,328 ms
 */

public class Solution {
    static int d;
    static int w;
    static int k;
    static String[][] map;
    static String[][] copyMap;
    static int min;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine().trim());
            d = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());
            map = new String[d][w];
            copyMap = new String[d][w];
            min = 13;
            for (int i = 0; i < d; i++) {
                map[i] = br.readLine().trim().split(" ");
                copyMap[i] = map[i].clone();
            }
            subset(0, new boolean[d]);

            sb.append("#").append(tc).append(" ").append(min).append("\n");
        }

        System.out.println(sb);
    }

    // 약품을 사용할 가로 라인의 부분집합
    static void subset(int depth, boolean[] visited) {
        if (depth == d) {
            dfs(0, 0, visited);
            for (int i = 0; i < d; i++)
                copyMap[i] = map[i].clone();
            return;
        }
        // 선택
        visited[depth] = true;
        subset(depth + 1, visited);
        // 비선택
        visited[depth] = false;
        subset(depth + 1, visited);
    }

    // 약품을 사용할 가로 라인의 부분집합을 통해 a약,b약중 선택하는 dfs
    static void dfs(int depth, int line, boolean[] visited) {
        if (depth >= min) return;

        if (line == d) {
            if (isAblePass())
                min = Math.min(min, depth);
            return;
        }
        if (visited[line]) {
            Arrays.fill(copyMap[line], "0");
            dfs(depth + 1, line + 1, visited);

            Arrays.fill(copyMap[line], "1");
            dfs(depth + 1, line + 1, visited);
        } else {
            dfs(depth, line + 1, visited);
        }
    }

    // 문제에 주어진 조건에 부합하는지 체크
    static boolean isAblePass() {
        for (int i = 0; i < w; i++) {
            String curWord = copyMap[0][i];
            int cnt = 1;
            for (int j = 1; j < d; j++) {
                if (cnt == k) break;

                if (copyMap[j][i].equals(curWord)) {
                    cnt++;
                } else {
                    curWord = copyMap[j][i];
                    cnt = 1;
                }
            }
            if (cnt < k) return false;

        }
        return true;
    }
}
