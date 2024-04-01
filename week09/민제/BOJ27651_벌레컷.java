import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ27651_벌레컷 {

    static int n;
    static int[] arr;
    static long[] sum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        sum = new long[n + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            sum[i + 1] = sum[i] + arr[i];
        }

        long result = 0;
        int head = 0;
        int left = 1;
        int right = n - 1;


        for (head = 0; head < n - 2; head++) {
            long headLen = getRangeSum(0, head);

            //tail 만족하는 right << 방향으로 움직이기
            while (right > 0 && getRangeSum(right, n - 1) <= headLen) {
                right--;
            }

            //body 만족하는 left >> 방향으로 움직이기
            while (left < n && getRangeSum(head + 1, left) <= getRangeSum(left + 1, n - 1)) {
                left++;
            }

            int len = right - left;
            if (len > 0) result += len;
            else break;
        }

        System.out.println(result);
    }

    static long getRangeSum(int left, int right) {
        if (left > right) return 0;
        return sum[right + 1] - sum[left];
    }

}