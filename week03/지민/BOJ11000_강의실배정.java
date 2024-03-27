import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 시간: 628ms
 * 그리디 + 우선순위 큐
 */

public class BOJ11000_강의실배정 {
    static int N;
    static int[][] lesson;

    public static int solve() {
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        pq.offer(lesson[0][1]); // 끝나는 시간 T만 삽입(넣는 순간 시작 시간은 불필요한 값)

        for (int i = 1; i < N; i++) {
            int t = pq.poll(); // 강의실 중에서 끝나는 시간이 가장 작은 시간
            if(t > lesson[i][0]) { // 현재 강의 시작 시간이 더 작다면 강의실 필요(추가)
                pq.offer(t);
                pq.offer(lesson[i][1]);
            }
            else pq.offer(lesson[i][1]);
        }

        return pq.size();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        lesson = new int[N][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            lesson[i][0] = Integer.parseInt(st.nextToken()); // s
            lesson[i][1] = Integer.parseInt(st.nextToken()); // t
        }

        Arrays.sort(lesson, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        System.out.println(solve());
    }
}
