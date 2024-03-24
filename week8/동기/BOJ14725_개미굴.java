import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 116ms
 */
public class Main {
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st;
        Node root = new Node("");
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            Node curNode = root;
            for (int depth = 1; depth <= k; depth++) {
                String food = st.nextToken();
                Node newNode = null;

                for (Node child : curNode.list) {
                    if (child.food.equals(food)) {// 이미 있는 경로
                        newNode = child;
                        break;
                    }
                }
                if (newNode == null) { // 새로운 경로
                    newNode = new Node(food);
                    curNode.list.add(newNode);
                }
                curNode = newNode;
            }

        }
        sb = new StringBuilder();
        while (!root.list.isEmpty()) {
            dfs(root.list.poll(), 0);
        }
        System.out.println(sb);
    }

    static void dfs(Node curNode, int depth) {
        for (int i = 0; i < depth; i++)
            sb.append("--");
        sb.append(curNode.food + "\n");

        while (!curNode.list.isEmpty()) {
            dfs(curNode.list.poll(), depth + 1);
        }

    }

    static class Node implements Comparable<Node> {
        String food;
        PriorityQueue<Node> list;

        Node(String food) {
            this.food = food;
            list = new PriorityQueue<>();
        }

        @Override
        public int compareTo(Node o) {
            return food.compareTo(o.food);
        }
    }

}