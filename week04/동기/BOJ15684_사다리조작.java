import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 언어 : Java8 / 시간 : 1792ms
 */

import static java.lang.System.exit;
import static java.lang.System.in;

public class Main {
    static int n;
    static int m;
    static int h;
    static boolean[][] ladder;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        ladder = new boolean[h][n + 1];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            ladder[a - 1][b] = true;
        }

        dfs(0, 0);
        dfs(0, 1);
        dfs(0, 2);
        dfs(0, 3);
        System.out.println(-1);


    }

    // 사다리에 가로줄 설치가 가능한 모든 경우의 수 구하기(targetDepth만큼만)
    static void dfs(int depth, int targetDepth) {
        if (depth == targetDepth) {
            if (simulation()) {
                System.out.println(targetDepth);
                exit(0);
            }
            return;
        }

        for (int i = 0; i < h; i++) {
            for (int j = 1; j < n; j++) {
                // 체크한 가로줄 양 옆을 확인하여 설치 가능 여부 확인
                if (!ladder[i][j] && !ladder[i][j - 1] && !ladder[i][j + 1]) {
                    ladder[i][j] = true;
                    dfs(depth + 1, targetDepth);
                    ladder[i][j] = false;
                }
            }
        }
    }

    // 사다리의 모든 index를 검사하여 모든 index 통과 시 true 반환
    static boolean simulation() {
        for (int x = 1; x < n; x++) {
            if (!checkIndex(x))
                return false;
        }
        return true;
    }

    // 사다리의 index 세로줄 하나만 검사하여, 끝에 도달한 x좌표가 index와 같으면 true 반환
    static boolean checkIndex(int index) {
        int x = index;
        for (int y = 0; y < h; y++) {
            if (ladder[y][x - 1])
                x = x - 1;
            else if (ladder[y][x])
                x = x + 1;
        }
        return index == x;
    }
}