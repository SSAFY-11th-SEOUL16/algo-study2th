import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ25691_k개트리노드에서사과를최대로수확하기 {

    static int n, k;
    static int result = 0;
    static List<Integer>[] graph;
    static int[] apples;

    static void comb(int start, List<Integer> select) {

        if (start > n) {
            return;
        }

        if (select.size() == k) {
            if (valid(select)) result = Math.max(result, countApple(select));
            return;
        }

        select.add(start);
        comb(start + 1, select); // 선택

        select.remove(select.size() - 1);
        comb(start + 1, select); // 비선택
    }

    static boolean valid(List<Integer> select) {
        boolean[] can_visit = new boolean[n];

        for (int i = 0; i < k; i++) {
            can_visit[select.get(i)] = true;
        }

        Queue<Integer> q = new LinkedList<Integer>();
        q.add(select.get(0));
        can_visit[select.get(0)] = false;

        while (!q.isEmpty()) {
            Integer poll = q.poll();

            for (Integer next : graph[poll]) {
                if (can_visit[next]) {
                    can_visit[next] = false;
                    q.add(next);
                }
            }
        }

        for (int i = 0; i < n; i++) {
            if (can_visit[i])
                return false;
        }

        return true;
    }

    static int countApple(List<Integer> select) {
        int count = 0;

        for (int i = 0; i < select.size(); i++) {
            count += apples[select.get(i)];
        }

        return count;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        graph = new List[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<Integer>();
        }

        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            graph[start].add(end);
            graph[end].add(start);
        }

        boolean[] visited = new boolean[n - 1];
        apples = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            apples[i] = Integer.parseInt(st.nextToken());
        }

        List<Integer> select = new ArrayList<>();
        select.add(0);

        comb(1, select);

        System.out.println(result);

    }

}
