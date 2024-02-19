import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        StringTokenizer st;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        ArrayList<Info> classList = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            classList.add(new Info(start, end));
        }

        Collections.sort(classList, (a, b) -> {
            return a.start - b.start;
        });
        pq.add(classList.get(0).end);

        for (int i = 1; i < n; i++) {
            Info classs = classList.get(i);
            if (pq.peek() <= classs.start)
                pq.poll();

            pq.add(classs.end);
        }
        System.out.println(pq.size());
    }

    static class Info {
        int start;
        int end;

        Info(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}
// 언어 : Java11 / 시간 : 692 ms
