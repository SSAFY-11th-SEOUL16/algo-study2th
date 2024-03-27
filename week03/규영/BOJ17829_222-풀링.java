import java.io.*;
import java.util.*;

// 536ms

public class BOJ17829_222-풀링 {
    static int[][] arr, arr2;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        find(N);
        System.out.println(arr[0][0]);
    }
    static void find(int len) {
        if (len == 1) return;
        arr2 = new int[len/2][len/2];
        for (int i = 0; i < len; i += 2) {
            for (int j = 0; j < len; j += 2) {
                int[] smallBox = {arr[i][j], arr[i][j+1], arr[i+1][j], arr[i+1][j+1]};
                Arrays.sort(smallBox);
                arr2[i/2][j/2] = smallBox[2];
            }
        }
        arr = arr2;
        find(len/2);
    }
}