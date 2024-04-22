import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ23295_스터디시간정하기1 {		// 468ms
    static int n, t, maxTime;
    static int[] sum;
    static int[] timeTable;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());
        timeTable = new int[100001];
        sum = new int[100001];
        maxTime = 0;

        for (int i = 0; i < n; i++) {
            int k = Integer.parseInt(br.readLine());
            for (int j = 0; j < k; j++) {
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                timeTable[s] += 1;
                timeTable[e] -= 1;
                if (e > maxTime)
                    maxTime = e;
            }
        }

        for (int i = 1; i <= maxTime; i++) {
            timeTable[i] += timeTable[i-1];
        }

        sum[0] = timeTable[0];
        for (int i = 1; i <= maxTime; i++) {
            sum[i] = sum[i-1] + timeTable[i];
        }

        int rw = sum[t-1];
        int rs = 0;
        int re = t;

        for (int i = 1; i <= 100000-t; i++) {
            int nw = sum[i + t - 1] - sum[i - 1];
            if (nw > rw) {
                rs = i;
                re = i + t;
            }
            if (nw > rw) {
                rw = nw;
            }
        }

        System.out.println(rs + " " + re);
    }

}