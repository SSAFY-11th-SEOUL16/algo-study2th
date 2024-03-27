import java.io.*;
import java.util.*;
 
 
public class Solution {
    static int min, max;
    static void backtrack(int depth, int result, int N, int[] arr, int plus, int minus, int mul, int div) {
        if(depth == N) {
            if(result > max) max = result;
            if(result < min) min = result;
            return;
        }
        if(plus > 0) backtrack(depth + 1, result + arr[depth], N, arr, plus - 1, minus, mul, div);
        if(minus > 0) backtrack(depth + 1, result - arr[depth], N, arr, plus, minus - 1, mul, div);
        if(mul > 0) backtrack(depth + 1, result * arr[depth], N, arr, plus, minus, mul - 1, div);
        if(div > 0) backtrack(depth + 1, result / arr[depth], N, arr, plus, minus, mul, div - 1);
        return;
    }
 
    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder output = new StringBuilder();
        int T = Integer.parseInt(input.readLine());
        for(int t = 1; t <= T; t++) {
            min = Integer.MAX_VALUE;
            max = Integer.MIN_VALUE;
            int N = Integer.parseInt(input.readLine());
            StringTokenizer tokens = new StringTokenizer(input.readLine());
            int plus = Integer.parseInt(tokens.nextToken());
            int minus = Integer.parseInt(tokens.nextToken());
            int mul = Integer.parseInt(tokens.nextToken());
            int div = Integer.parseInt(tokens.nextToken());
            int[] numbers = new int[N];
            tokens = new StringTokenizer(input.readLine());
            for(int i = 0; i < N; i++) numbers[i] = Integer.parseInt(tokens.nextToken());
            backtrack(1, numbers[0], N, numbers, plus, minus, mul, div);
            output.append('#').append(t).append(' ').append(max - min).append('\n');
        }
        System.out.println(output);
    }
 
}