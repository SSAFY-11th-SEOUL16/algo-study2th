import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ17220_마약수사대 {

    static int n, m;
    static Node[] tree;
    static boolean[] visited;
    static int result = 0;

    static class Node {
        Node parent;
        int val;
        List<Integer> childs = new ArrayList<>();

        public Node(int val) {
            this.val = val;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "childs=" + childs +
                    '}';
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        tree = new Node[26];
        visited = new boolean[26];
        for(int i=0; i<26; i++) {
            tree[i] = new Node(i);
        }

        for(int i=0; i<m; i++) {
            char[] charArray = br.readLine().toCharArray();
            int parent = charArray[0] - 'A';
            int child = charArray[2] - 'A';
            tree[parent].childs.add(child);
            tree[child].parent = tree[parent];
        }

        boolean[] rootCheck = new boolean[26];

        for(int i=0;i<26;i++) {
            Node root = findRoot(tree[i]);
            if (!tree[root.val].childs.isEmpty()) rootCheck[root.val] = true;
        }

        st = new StringTokenizer(br.readLine());
        int num = Integer.parseInt(st.nextToken());
        for(int i=0; i<num; i++) {
            int remove = st.nextToken().charAt(0) - 'A';
            visited[remove] = true;
        }

        List<Node> roots = new ArrayList<>();

        for(int i=0; i<26; i++) {
            if (rootCheck[i] && !visited[i]) roots.add(tree[i]);
        }

        bfs(roots);

        System.out.println(result);
    }

    static void bfs(List<Node> roots) {
        Queue<Integer> q = new ArrayDeque<>();
        for (Node root : roots) {
            q.add(root.val);
            visited[root.val] = true;
        }

        while (!q.isEmpty()) {
            Integer current = q.poll();

            for (Integer child : tree[current].childs) {
                if (!visited[child]) {
                    visited[child] = true;
                    q.add(child);
                    result++;
                }
            }
        }
    }

    static Node findRoot(Node now) {
        while (now.parent != null) now = now.parent;
        return now;
    }
}
