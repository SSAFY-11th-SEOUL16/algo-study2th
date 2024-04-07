import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
 * 1056ms
 */ 
public class Main {
    static int n, m;
    static ArrayList<Node>[] adjList;
    static ArrayList<Node>[] parentList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        adjList = new ArrayList[n + 1];
        parentList = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            adjList[i] = new ArrayList<Node>();
            parentList[i] = new ArrayList<Node>();
        }

        ArrayList<Node> oneInList = new ArrayList<>();
        StringTokenizer st;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            if (to == 1) {
                oneInList.add(new Node(from, cost));
            } else {
                adjList[from].add(new Node(to, cost));
            }
            parentList[to].add(new Node(from, cost));
        }

        // 다익스트라
        int[] dist = new int[n + 1];
        Queue<Node> pq = new LinkedList<>();
        Node startNode = new Node(1, 0);
        pq.add(startNode);

        while (!pq.isEmpty()) {
            Node curNode = pq.poll();

            if (curNode.cost < dist[curNode.vertex])
                continue;

            for (Node next : adjList[curNode.vertex]) {
                if (dist[next.vertex] < dist[curNode.vertex] + next.cost) {
                    dist[next.vertex] = dist[curNode.vertex] + next.cost;
                    pq.add(new Node(next.vertex, dist[next.vertex]));
                }
            }
        }

        int answer = 0;
        int lastIndex = 0;
        for (Node node : oneInList) {
            int value = dist[node.vertex] + node.cost;
            if (value > answer) {
                answer = value;
                lastIndex = node.vertex;
            }
        }

        ArrayList<Integer> pathList = findPath(lastIndex, dist[lastIndex], dist);

        StringBuilder sb = new StringBuilder();
        sb.append(answer).append("\n");
        for (int i = pathList.size() - 1; i >= 0; i--) {
            sb.append(pathList.get(i)).append(" ");
        }
        sb.append(lastIndex).append(" ").append(1);

        System.out.println(sb);

    }

    static ArrayList<Integer> findPath(int lastIndex, int distance, int[] dist) {
        ArrayList<Integer> pathList = new ArrayList<>();
        int curIndex = lastIndex;
        int curDistance = distance;
        while (curIndex != 1) {
            for (Node parent : parentList[curIndex]) {
                if (curDistance == dist[parent.vertex] + parent.cost) {
                    curIndex = parent.vertex;
                    pathList.add(parent.vertex);
                    curDistance -= parent.cost;
                }
            }
        }
        return pathList;
    }

    static class Node implements Comparable<Node> {
        int vertex;
        int cost;

        Node(int vertex, int cost) {
            this.vertex = vertex;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return o.cost - this.cost;
        }
    }
}
