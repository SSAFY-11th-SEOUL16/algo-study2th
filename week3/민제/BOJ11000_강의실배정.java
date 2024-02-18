import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ11000_강의실배정 {

    static int n;

    static class Node {
        int start, end;

        public Node(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public String toString() {
            return "Node [start=" + start + ", end=" + end + "]";
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        Node[] lectures = new Node[n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            lectures[i] = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        Arrays.sort(lectures, (o1, o2) -> {
            if (o1.start == o2.start) {
                return o1.end - o2.end;
            }
            return o1.start - o2.start;
        });

        int result = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>( (o1, o2) -> o1.end - o2.end);

        for (int i = 0; i < n; i++) {
            while (!pq.isEmpty() && pq.peek().end <= lectures[i].start)
                pq.poll();

            pq.add(lectures[i]);

            result = Math.max(result, pq.size());
        }

        System.out.println(result);

    }

}
