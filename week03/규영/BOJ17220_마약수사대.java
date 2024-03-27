import java.io.*;
import java.util.*;

// 76ms

public class BOJ17220_마약수사대 {
    static int ans;
    static boolean[] visited;
    static ArrayList<ArrayList<Integer>> graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken());
        graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) graph.add(new ArrayList<>());
        int[] parents = new int[N];
        Arrays.fill(parents, -1);
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = st.nextToken().charAt(0)-'A', B = st.nextToken().charAt(0)-'A';
            graph.get(A).add(B);
            parents[B] = A;
        }
        st = new StringTokenizer(br.readLine());
        visited = new boolean[N];
        int K = Integer.parseInt(st.nextToken());
        for (int i = 0; i < K; i++) visited[st.nextToken().charAt(0)-'A'] = true;
        ans = 0;
        for (int i = 0; i < N; i++) {
            if (parents[i] != -1 || visited[i]) continue;
            visited[i] = true;
            dfs(i);
        }
        System.out.println(ans);
    }
    static void dfs(int idx) {
        for (int i : graph.get(idx)) {
            if (visited[i]) continue;
            visited[i] = true;
            dfs(i);
            ans++;
        }
    }
}