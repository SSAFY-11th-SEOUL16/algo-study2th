import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ23309_철도공사 {

    static class Node {
        int prev;
        int next;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        Node[] nodes = new Node[1000001];
        for (int i = 0; i < 1000001; i++) {
            nodes[i] = new Node();
        }

        st = new StringTokenizer(br.readLine());
        int head = 0;
        int tail = 0;
        boolean first = true;
        for (int i = 0; i < n; i++) {

            int input = Integer.parseInt(st.nextToken());

            if (first) {
                head = input;
                tail = input;
                nodes[input].prev = input;
                nodes[input].next = input;
                first = false;
                continue;
            }

            nodes[input].next = head;
            nodes[input].prev = tail;
            nodes[tail].next = input;
            nodes[head].prev = input;
            tail = input;
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            String op = st.nextToken();
            int findStation = Integer.parseInt(st.nextToken());

            switch (op) {
                case "BN":

                    int newStation = Integer.parseInt(st.nextToken());
                    sb.append(nodes[findStation].next).append("\n");

                    nodes[newStation].prev = findStation;
                    nodes[newStation].next = nodes[findStation].next;

                    nodes[nodes[findStation].next].prev = newStation;
                    nodes[findStation].next = newStation;

                    break;

                case "BP":

                    newStation = Integer.parseInt(st.nextToken());
                    sb.append(nodes[findStation].prev).append("\n");

                    nodes[newStation].prev = nodes[findStation].prev;
                    nodes[newStation].next = findStation;

                    nodes[nodes[findStation].prev].next = newStation;
                    nodes[findStation].prev = newStation;

                    break;

                case "CN":

                    sb.append(nodes[findStation].next).append("\n");

                    int nextnext = nodes[nodes[findStation].next].next;

                    nodes[nextnext].prev = findStation;
                    nodes[findStation].next = nextnext;

                    break;

                case "CP":

                    sb.append(nodes[findStation].prev).append("\n");

                    int prevprev = nodes[nodes[findStation].prev].prev;

                    nodes[prevprev].next = findStation;
                    nodes[findStation].prev = prevprev;
                    break;
            }

        }

        System.out.println(sb.toString());

    }
}