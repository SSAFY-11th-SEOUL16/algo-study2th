import java.io.*;
import java.util.*;

/*
 * 12440KB
 * 100ms
 *
 * 20억명 아이들이 30분짜리 놀이기구 1개를 탈 때 마지막 아이가 탑승할 시간이 (600억 - 30)분이 된다.
 * 마지막 아이가 탑승할 시간 endTime을 매개변수 탐색으로 찾는데 해당 시간까지 탑승한 아이 수 riddenCnt가
 * n보다 크거나 같으면서 가장 작을 때를 고른다.
 * riddenCnt에서 n만큼 빼고 이 수 만큼 놀이기구를 뒤에서 부터 탐색하여
 * 마지막 아이가 탑승하게 될 놀이기구를 찾는다.
 */
public class BOJ1561_놀이공원 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int n, m;
    static int[] attractions;
    static int[] attractionCntByTime;

    public static void main(String[] args) throws IOException {
        n = nextInt();
        m = nextInt();
        attractions = new int[m + 1];
        attractionCntByTime = new int[31];
        for (int i = 1; i <= m; ++i) {
            attractions[i] = nextInt();
            ++attractionCntByTime[attractions[i]];
        }
        long low = 0;
        long high = 60_000_000_000L;
        long riddenCnt = 0;
        long endTime = 0;
        while (low <= high) {
            long mid = (low + high) >>> 1;
            riddenCnt = getRiddenCntByTime(mid);
            if (riddenCnt < n) {
                low = mid + 1L;
                endTime = low;
            } else {
                high = mid - 1L;
            }
        }
        int ans = 0;
        riddenCnt = getRiddenCntByTime(endTime);
        int diff = (int) (riddenCnt - n);
        for (int i = m; i >= 1; --i) {
            if (endTime % attractions[i] == 0) {
                --diff;
                if (diff < 0) {
                    ans = i;
                    break;
                }
            }
        }
        System.out.println(ans);
    }

    static long getRiddenCntByTime(long time) {
        long ans = 0;
        for (int i = 1; i <= 30; ++i) {
            int size = attractionCntByTime[i];
            if (size != 0) {
                ans += (time / i + 1) * size;
            }
        }
        return ans;
    }

    static String next() throws IOException {
        while (st == null || !st.hasMoreTokens()) {
            st = new StringTokenizer(br.readLine());
        }
        return st.nextToken();
    }

    static int nextInt() throws IOException {
        return Integer.parseInt(next());
    }
}