import java.io.*;
import java.util.*;
/*
 * 252ms
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int n;
    static int[] nums;
    static int[] psumFront;
    static int[] psumBack;
    static int ans;

    public static void main(String[] args) throws IOException {
        n = nextInt();
        nums = new int[n];
        psumFront = new int[n];
        psumBack = new int[n];
        int tmp = 0;
        int maxIdx = 0;
        for (int i = 0; i < n; ++i) {
            nums[i] = nextInt();
            tmp += nums[i];
            if (nums[i] > nums[maxIdx]) {
                maxIdx = i;
            }
        }
        psumFront[0] = nums[0];
        psumBack[n - 1] = nums[n - 1];
        for (int i = 1; i < n; ++i) {
            psumFront[i] = psumFront[i - 1] + nums[i];
            psumBack[n - i - 1] = psumBack[n - i] + nums[n - i - 1];
        }
        // 꿀통이 중간에 있을 때
        tmp = tmp - nums[0] - nums[n - 1] + nums[maxIdx];
        ans = tmp;
        // 꿀통이 왼쪽 끝에 있을 때
        for (int leftBee = 1; leftBee < n - 1; ++leftBee) {
            tmp = psumFront[leftBee] + psumFront[n - 1] - nums[leftBee] * 2 - nums[n - 1];
            ans = Math.max(ans, tmp);
        }
        // 꿀통이 오른쪽 끝에 있을 때
        for (int rightBee = 1; rightBee < n - 1; ++rightBee) {
            tmp = psumBack[rightBee] + psumBack[0] - nums[rightBee] * 2 - nums[0];
            ans = Math.max(ans, tmp);
        }
        System.out.println(ans);

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
