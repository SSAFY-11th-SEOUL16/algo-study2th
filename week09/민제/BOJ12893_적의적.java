import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ12893_적의적 {

    static HashSet<Integer>[] enemy;
    static int n;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        enemy = new HashSet[n + 1];
        for (int i = 0; i < n + 1; i++) {
            enemy[i] = new HashSet<>();
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            enemy[a].add(b);
            enemy[b].add(a);
        }

        System.out.println(solve());
    }

    static int solve() {

        Queue<Integer> q = new ArrayDeque<>();
        boolean[] visited = new boolean[n + 1];
        HashSet<Integer> teamA = new HashSet<>();
        HashSet<Integer> teamB = new HashSet<>();

        for (int i = 1; i <= n; i++) {
            if (visited[i]) continue;

            teamA.clear();
            teamB.clear();
            q.add(i);
            teamA.add(i);
            visited[i] = true;
            boolean teamAFriend = false;

            while (!q.isEmpty()) {

                int size = q.size();
                HashSet<Integer> temp = new HashSet<>();
                while (size-- > 0) {
                    Integer poll = q.poll();
                    temp.addAll(enemy[poll]);
                }

                if (teamAFriend) {
                    for (Integer num : temp) {
                        if (teamB.contains(num)) return 0;
                        if (visited[num]) continue;
                        visited[num] = true;
                        teamA.add(num);
                        q.add(num);
                    }
                } else {
                    for (Integer num : temp) {
                        if (teamA.contains(num)) return 0;
                        if (visited[num]) continue;
                        visited[num] = true;
                        teamB.add(num);
                        q.add(num);
                    }
                }

                teamAFriend = !teamAFriend;
            }
        }

        return 1;
    }

}
