import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.StringTokenizer;

/*
 * 136ms
 */
public class Main {
    static int n, m;
    static int[] parent;
    static HashMap<Integer, ArrayList<Integer>> team = new HashMap<>();
    static int[][] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        parent = new int[n + 1];
        graph = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++)
            parent[i] = i;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == j) continue;
                graph[i][j] = 100;
            }
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            graph[x][y] = 1;
            graph[y][x] = 1;

            union(x, y);
        }
        for (int i = 1; i <= n; i++) find(i);

        for (int i = 1; i <= n; i++) {
            if (team.containsKey(parent[i])) {
                team.get(parent[i]).add(i);
            } else {
                ArrayList<Integer> list = new ArrayList<>();
                list.add(i);
                team.put(parent[i], list);
            }
        }

        floyd();

        ArrayList<Integer> answer = new ArrayList<>();
        for (Integer key : team.keySet())
            answer.add(solution(key));
        Collections.sort(answer);

        StringBuilder sb = new StringBuilder();
        sb.append(answer.size()).append("\n");
        for (int a : answer) sb.append(a).append("\n");
        System.out.print(sb);
    }

    static int solution(int key) {
        ArrayList<Integer> list = team.get(key);
        int allMin = 100;
        int minNum = list.get(0);
        for (int i : list) {
            int max = 0;
            for (int j : list) {
                max = Math.max(graph[i][j], max);
            }
            if (allMin > max) {
                allMin = max;
                minNum = i;
            }
        }
        return minNum;
    }

    static void floyd() {
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
                }
            }
        }
    }

    static int find(int x) {
        if (x == parent[x]) return x;
        return parent[x] = find(parent[x]);
    }

    static void union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x == y) return;

        if (x < y) parent[y] = x;
        else parent[x] = y;
    }


}
