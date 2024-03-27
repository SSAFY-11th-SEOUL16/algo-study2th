import java.io.*;
import java.util.*;

public class BOJ14225_부분수열의합 {
    static int N;
    static int[] arr;
    static HashSet<Integer> hs;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        arr = new int[N];
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());
        hs = new HashSet<>();
        choice(0, 0);
        List<Integer> list = new ArrayList<>(hs);
        Collections.sort(list);
        int num = 1;
        for (int i : list) {
            if (i != num) break;
            num++;
        }
        System.out.println(num);
    }
    static void choice(int idx, int sum) {
        if (idx == N) {
            if (sum != 0) hs.add(sum);
            return;
        }
        choice(idx+1, sum+arr[idx]);
        choice(idx+1, sum);
    }
}