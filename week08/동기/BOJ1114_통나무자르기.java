import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
/*
 * 	324ms
 */
public class Main {
    static int l, k, c;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        l = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Arrays.sort(arr);
        bs();

    }

    static void bs() {
        int min = 1;
        int max = l - 1;

        while (min < max) {
            int mid = (min + max) / 2;
            if (check(mid)) {
                max = mid;
            } else {
                min = mid + 1;
            }
        }
        // min = 가장 긴 조각의 길이
        int minStartPos = getMinStartPos(min);
        System.out.println(min + " " + minStartPos);

    }

    static boolean check(int d) {
        int cnt = c;
        long lastPos = 0;
        for (int i = 0; i < k; i++) {
            if (cnt == 0)
                break;

            if (i == k - 1) { // 마지막이라 다음꺼 확인을 못함
                if (arr[i] - lastPos <= d)
                    lastPos = arr[i];
                break;
            }

            if (arr[i] - lastPos <= d) {
                if (arr[i + 1] - lastPos > d) {// 다음으로 넘어가면 안됨
                    lastPos = arr[i];
                    cnt--;
                }
            } else {
                break;
            }

        }

        return l - lastPos <= d; // 마지막 통나무 조각이 d보다 작거나 같아야 true
    }

    static int getMinStartPos(int d) {
        int s = 0;
        while (arr[s] <= d) {
            int cnt = c - 1;
            long lastPos = arr[s];
            for (int i = s + 1; i < k; i++) {
                if (cnt == 0)
                    break;

                if (i == k - 1) { // 마지막이라 다음꺼 확인을 못함
                    if (arr[i] - lastPos <= d)
                        lastPos = arr[i];
                    break;
                }

                if (arr[i] - lastPos <= d) {
                    if (arr[i + 1] - lastPos > d) {// 다음으로 넘어가면 안됨
                        lastPos = arr[i];
                        cnt--;
                    }
                } else {
                    break;
                }

            }
            if (l - lastPos <= d)
                break;
            s++;
        }

        return arr[s];
    }

}
