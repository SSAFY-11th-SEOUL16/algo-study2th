import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class BOJ14725_개미굴 {

    static StringBuilder sb = new StringBuilder();

    static class Node {
        String name;
        TreeSet<Node> childs = new TreeSet<>((o1, o2) -> o1.name.compareTo(o2.name));

        public Node() {
        }

        public Node(String name) {
            this.name = name;
        }

        boolean equal(String other) {
            if (name.equals(other)) return true;
            return false;
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        Node head = new Node();

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());

            Node temp = head;

            for (int j = 0; j < k; j++) {
                String input = st.nextToken();
                TreeSet<Node> childs = temp.childs;

                Node next = null;
                for (Node child : childs) {
                    if (child.equal(input)) {
                        next = child;
                        break;
                    }
                }
                //겹치는게 없을때
                if (next == null) {
                    Node newNode = new Node(input);
                    childs.add(newNode);
                    next = newNode;
                }
                //다음칸으로 이동
                temp = next;
            }
        }

        dfs(head, -1);

        System.out.println(sb);

    }

    static void dfs(Node now, int height) {

        for (int i = 0; i < height; i++) {
            sb.append("--");
        }
        if (now.name != null) sb.append(now.name).append('\n');

        for (Node child : now.childs) {
            dfs(child, height + 1);
        }
    }
}
