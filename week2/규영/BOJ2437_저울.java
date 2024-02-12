package 규영;

import java.io.*;
import java.util.*;

public class BOJ2437_저울 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()), sum = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(arr);
        for (int i = 0; i < N; i++) {
            if (sum+1 < arr[i]) break;
            sum += arr[i];
        }
        System.out.println(sum+1);
    }
}