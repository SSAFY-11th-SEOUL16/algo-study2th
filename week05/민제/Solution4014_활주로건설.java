import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution4014_활주로건설 {

    static int n, x;
    static int[][] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int testCase = 1; testCase <= T; testCase++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            x = Integer.parseInt(st.nextToken());
            graph = new int[n][n];

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    graph[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            
            int result = n * 2;
            
            for(int i=0; i<n; i++) {
                if (!valid_row(i)) result--;
                if (!valid_col(i)) result--;
            }

            sb.append('#').append(testCase).append(' ').append(result).append('\n');
            
        }

        System.out.println(sb);

    }

    static boolean valid_row(int idx) {

        boolean[] used = new boolean[n];

        for(int j=0; j<n-1; j++) {

            //가로 확인
            if (Math.abs(graph[idx][j] - graph[idx][j+1]) >= 2) return false;

            else {
                if (graph[idx][j] < graph[idx][j + 1]) {
                    //올라간곳 왼쪽부터 확인
                    if ((j + 1) - x < 0) return false;
                    int val = graph[idx][j];
                    for (int len = 1; len <= x; len++) {
                        if (used[j + 1 - len] || val != graph[idx][j + 1 - len]) return false;
                        used[j + 1 - len] = true;
                    }
                } else if (graph[idx][j] > graph[idx][j+1]) {
                    //오른쪽 확인
                    if (j + x >= n) return false;
                    int val = graph[idx][j +1];
                    for (int len = 1; len <= x; len++) {
                        if (used[j + len] || val != graph[idx][j + len]) return false;
                        used[j + len] = true;
                    }
                }
            }
        }
        return true;
    }

    static boolean valid_col(int idx) {
        boolean[] used = new boolean[n];

        for(int j=0; j<n-1; j++) {

            //세로 확인
            if (Math.abs(graph[j][idx] - graph[j+1][idx]) >= 2) {
                return false;
            }
            else {
                if (graph[j][idx] < graph[j + 1][idx]) {
                    //올라간곳 왼쪽부터 확인
                    if ((j + 1) - x < 0) return false;
                    int val = graph[j + 1 - 1][idx];
                    for (int len = 1; len <= x; len++) {
                        if (used[j + 1 - len] || val != graph[j + 1 - len][idx]) return false;
                        used[j + 1 - len] = true;
                    }
                } else if (graph[j][idx] > graph[j+1][idx]) {
                    //오른쪽 확인
                    if (j + x >= n) return false;
                    int val = graph[j +1][idx];
                    for (int len = 1; len <= x; len++) {
                        if (used[j + len] || val != graph[j + len][idx]) return false;
                        used[j + len] = true;
                    }
                }
            }
        }

        return true;
    }
}