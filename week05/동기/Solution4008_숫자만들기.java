import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 239ms
 */

public class Solution {
    static int n;
    static int[] numbers;
    static int min;
    static int max;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            n = Integer.parseInt(br.readLine());
            numbers = new int[n];
            min = Integer.MAX_VALUE;
            max = Integer.MIN_VALUE;
            int[] ableCnt = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            numbers = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            permutation(0, new int[n - 1], ableCnt);

            sb.append("#").append(tc).append(" ").append(max - min).append("\n");
        }

        System.out.println(sb);
    }
    
    static void permutation(int depth, int[] operators, int[] ableCnt) {
        if (depth == n - 1) {
            int value = cal(operators);
            min = Math.min(min, value);
            max = Math.max(max, value);
            return;
        }

        for (int i = 0; i < 4; i++) {
            if (ableCnt[i] > 0) {
                ableCnt[i]--;
                operators[depth] = i;
                permutation(depth + 1, operators, ableCnt);
                ableCnt[i]++;
            }
        }
    }

    static int cal(int[] operators) {
        int sum = numbers[0];
        int index = 1;
        while (index < n) {
            int operator = operators[index - 1];
            switch (operator) {
                case 0: {
                    sum += numbers[index];
                    break;
                }
                case 1: {
                    sum -= numbers[index];
                    break;
                }
                case 2: {
                    sum *= numbers[index];
                    break;
                }
                case 3: {
                    sum /= numbers[index];
                    break;
                }
            }
            index++;
        }
        return sum;
    }
}
