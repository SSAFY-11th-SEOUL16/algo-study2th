import java.io.*;
import java.util.*;

public class BOJ1414_불우이웃돕기 {
    static class Node implements Comparable<Node> {
        int start, end, cost;
        Node (int start, int end, int cost) {
            this.start = start;
            this.end = end;
            this.cost = cost;
        }
        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.cost, o.cost);
        }
    }
    static int N;
    static int[] parents;
    static ArrayList<Node> nodes;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        nodes = new ArrayList<>();
        parents = new int[N];
        int total = 0, minSum = 0, cnt = 0;
        for (int i = 0; i < N; i++) {
            char[] line = br.readLine().toCharArray();
            for (int j = 0; j < N; j++) {
                char c = line[j];
                if (c == '0') continue;
                int len = 'a' <= c && c <= 'z' ? c-'a'+1 : c-'A'+27;
                nodes.add(new Node(i, j, len));
                total += len;
            }
        }
        Collections.sort(nodes);
        make();
        for (Node n : nodes) {
            if (!union(n.start, n.end)) continue;
            minSum += n.cost;
            if (++cnt == N-1) break;
        }
        System.out.print(cnt == N-1 ? total-minSum : -1);
    }

    static void make() {
        for (int i = 0; i < N; i++) {
            parents[i] = i;
        }
    }

    static int find(int a) {
        if (a == parents[a]) return a;
        return parents[a] = find(parents[a]);
    }

    static boolean union(int a, int b) {
        int aRoot = find(a), bRoot = find(b);
        if (aRoot == bRoot) return false;
        parents[bRoot] = aRoot;
        return true;
    }
}