import java.io.*;
import java.util.StringTokenizer;

/*
 * 15568KB, 76ms
 * division by zero를 고려 하지 않아 오답
 */

public class BOJ1111_IQTest {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());
        Object ans = null;
        if (N == 1) ans = 'A';
        else if (N == 2) {
            if (arr[0] == arr[1]) ans = arr[0];
            else ans = 'A';
        } else {
            if (arr[0] == arr[1]) {
                ans = arr[0];
                for (int i = 2; i < N; i++) {
                    if ((int)ans != arr[i]) {
                        ans = 'B';
                        break;
                    }
                }
            } else {
                int a = (arr[2]-arr[1])/(arr[1]-arr[0]), b = arr[1]-(a*arr[0]);
                ans = a*arr[N-1]+b;
                for (int i = 2; i < N; i++) {
                    if (arr[i]-(a*arr[i-1]) != b) {
                        ans = 'B';
                        break;
                    }
                }
            }
        }
        System.out.print(ans);
    }
}