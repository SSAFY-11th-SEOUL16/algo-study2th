import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class BOJ1414_불우이웃돕기 { // 88ms
    static int N;
    static int result;
    static int cnt;
    static ArrayList<Edge> edgeList;
    static int[] parents;

    static class Edge implements Comparable<Edge>{
        int start;
        int end;
        int weight;

        Edge (int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            // TODO Auto-generated method stub
            return this.weight - o.weight;
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        edgeList = new ArrayList<>();
        result = 0;
        cnt = 0;
        N = Integer.parseInt(br.readLine());
        parents = new int[N+1];

        for (int i = 1; i <= N; i++) {
            String line = br.readLine();
            for (int j = 0; j < N; j++) {
                int w = line.charAt(j);
                if (w >= 'A' && w <= 'Z') {
                    w -= 'A' - 27;
                    result += w;
                    if (i != j+1)
                        edgeList.add(new Edge(i, j+1, w));
                }
                else if (w >= 'a' && w <= 'z') {
                    w -= 'a' - 1;
                    result += w;
                    if (i != j+1)
                        edgeList.add(new Edge(i, j+1, w));
                }
            }
        }
        if (N == 1) {
            System.out.println(result);
            return;
        }

        Collections.sort(edgeList);
        make_set();

        for (Edge e : edgeList) {
            if (union(e.start, e.end)) {
                result -= e.weight;
                cnt++;
                if (cnt == N-1)
                    break;
            }
        }
        if (cnt < N-1) {
            System.out.println(-1);
        }
        else {
            System.out.println(result);
        }
    }
    public static void make_set() {
        for (int i = 1; i <= N; i++) {
            parents[i] = i;
        }
    }

    public static boolean union(int v1, int v2) {
        int p1 = find(v1);
        int p2 = find(v2);
        if (p1 == p2) {
            return false;
        }
        parents[p2] = p1;
        return true;
    }

    public static int find(int v) {
        if (parents[v] == v) {
            return v;
        }
        return parents[v] = find(parents[v]);
    }
}
