import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ6086_최대유량 {

    static int V = 60;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[][] capacity = new int[V][V];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = (int) st.nextToken().charAt(0) - 'A';
            int end = (int) st.nextToken().charAt(0) - 'A';
            int cost = Integer.parseInt(st.nextToken());

            capacity[start][end] += cost;
            capacity[end][start] += cost;
        }

        int start = 'A' - 'A';
        int dest = 'Z' - 'A';

        System.out.println(fordFulkerson(capacity, start, dest));
    }

    // 에드몬즈-카프 알고리즘
    static int fordFulkerson(int graph[][], int source, int sink) {
        int u, v;

        // 부모 정점 배열
        int parent[] = new int[V];

        int maxFlow = 0; // 최대 유량 초기화

        // 증가 경로를 찾을 때까지 반복
        while (bfs(graph, source, sink, parent)) {
            int pathFlow = Integer.MAX_VALUE;
            // 증가 경로에서 최소 유량 찾기
            for (v = sink; v != source; v = parent[v]) {
                u = parent[v];
                pathFlow = Math.min(pathFlow, graph[u][v]);
            }
            // 증가 경로에서 찾은 최소 유량만큼 유량 더하기
            for (v = sink; v != source; v = parent[v]) {
                u = parent[v];
                graph[u][v] -= pathFlow;
                graph[v][u] += pathFlow;
            }
            maxFlow += pathFlow;
        }
        return maxFlow;
    }

    // 너비 우선 탐색(BFS)로 증가 경로 찾기
    static boolean bfs(int graph[][], int source, int sink, int parent[]) {
        boolean visited[] = new boolean[V];
        Arrays.fill(visited, false);

        Queue<Integer> queue = new LinkedList<>();
        queue.add(source);
        visited[source] = true;
        parent[source] = -1;

        // BFS 수행
        while (!queue.isEmpty()) {
            int u = queue.poll();
            for (int v = 0; v < V; v++) {
                if (!visited[v] && graph[u][v] > 0) {
                    queue.add(v);
                    parent[v] = u;
                    visited[v] = true;
                }
            }
        }
        // sink에 도달 가능하면 true 반환
        return visited[sink];
    }

}
