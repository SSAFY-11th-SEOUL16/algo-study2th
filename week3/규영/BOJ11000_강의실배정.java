import java.io.*;
import java.util.*;

// 648ms

public class BOJ11000_강의실배정 {
    static class Class implements Comparable<Class>{
        int start, end;
        Class (int start, int end) {
            this.start = start;
            this.end = end;
        }
        @Override
        public int compareTo(Class o) {
            // 처음에 주석 처리한 부분과 같이 작성해서 오답이 나왔습니다 ..!
//            if (this.end == o.end) return Integer.compare(this.start, o.start);
//            return Integer.compare(this.end, o.end);
            return Integer.compare(this.start, o.start);
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        Class[] classes = new Class[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            classes[i] = new Class(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        Arrays.sort(classes);
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.offer(classes[0].end); // 끝나는 시간이 중요!
        for (int i = 1; i < N; i++) {
            if (pq.peek() <= classes[i].start) pq.poll();
            pq.offer(classes[i].end);
        }
        System.out.println(pq.size());
    }
}