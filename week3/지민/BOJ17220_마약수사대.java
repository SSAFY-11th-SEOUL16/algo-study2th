import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 시간: 76ms
 * bfs
 */

public class BOJ17220_마약수사대 {
    static int N;
    static int M;
    static boolean[] find;
    static List<List<Integer>> graph;
    static int[] parent;

    public static int countNotFound(int root) {
        if(graph.get(root).size() == 0) return 1;

        int cnt = 1;
        find[root] = true;
        for(int child : graph.get(root)) {
            if(find[child]) continue;
            cnt += countNotFound(child);
        }

        return cnt;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        parent = new int[N];
        graph = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = (int)(st.nextToken().charAt(0) - 'A');
            int v = (int)(st.nextToken().charAt(0) - 'A');
            graph.get(u).add(v);
            parent[v] += 1;
        }

        find = new boolean[N];
        st = new StringTokenizer(br.readLine());
        int K = Integer.parseInt(st.nextToken());
        for (int i = 0; i < K; i++) {
            int x = (int)(st.nextToken().charAt(0) - 'A');
            find[x] = true;
        }

        int res = 0;
        for (int i = 0; i < N; i++) {
            if(parent[i] == 0 && !find[i]) res += countNotFound(i) - 1; // 1을 빼는 이유: 루트는 공급책의 수에서 제외됨
        }
        System.out.println(res);
    }
}
