import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 296ms
 */
public class Main {
    static long n, m;
    static long[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Long.parseLong(st.nextToken());
        m = Long.parseLong(st.nextToken());

        arr = Arrays.stream(br.readLine().trim().split(" ")).mapToLong(Long::parseLong).toArray();

        System.out.println(bs());
    }

    static long bs() {
        long start = 0;
        long end = n * 30 + 1;
        while (start < end) { // 최소 성공 시간 -1을 구한다.
            long mid = (start + end) / 2;

            if (check(mid) <= 0) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        
        if (start == 0)
            return (m % n) + 1;

        return cal(start - 1);

    }

    static long cal(long time) {
        long nCnt = check(time);
        for (int i = 0; i < m; i++) {
            if (time % arr[i] == 0)
                nCnt--;

            if (nCnt == 0)
                return i + 1;
        }
        return 0;
    }

    static long check(long mid) {
        long nCnt = n;
        for (int i = 0; i < m; i++) {
            long peopleCnt = mid / arr[i];
            if (mid % arr[i] != 0)
                peopleCnt += 1;
            nCnt -= peopleCnt;
            if (nCnt <= 0)
                return nCnt;
        }
        return nCnt;
    }

}