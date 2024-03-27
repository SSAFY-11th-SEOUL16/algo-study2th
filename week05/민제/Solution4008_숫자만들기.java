import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution4008_숫자만들기 {
    static char[] operator = {'+', '-', 'x', '/'};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for(int testCase = 1; testCase <= T; testCase++) {
            int n = Integer.parseInt(br.readLine());
            int[] nums = new int[n];
            int[] ops = new int[n-1];
            int idx = 0;

            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i=0; i<4; i++) {
                int num = Integer.parseInt(st.nextToken());
                for(int j=0; j<num; j++) {
                    ops[idx++] = i;
                }
            }

            st = new StringTokenizer(br.readLine());
            for(int i=0; i<n; i++) {
               nums[i] = Integer.parseInt(st.nextToken());
            }

            int max = Integer.MIN_VALUE;
            int min = Integer.MAX_VALUE;

            do{
                int result = calcualate(nums, ops);
                if (result < min) min = result;
                if (result > max) max = result;
            } while (np(ops));

            sb.append('#').append(testCase).append(' ').append(max - min).append('\n');
        }

        System.out.println(sb);
    }

    static int calcualate(int[] nums, int[] ops) {

        int result = nums[0];

        for(int i=0; i<ops.length; i++) {
            int b = nums[i+1];
            if (ops[i] == 0) result = result + b;
            else if (ops[i] == 1) result = result - b;
            else if (ops[i] == 2) result = result * b;
            else if (ops[i] == 3) result = result / b;
        }

        return result;
    }

    static boolean np(int[] arr) {
        int n = arr.length;

        //step1 top 찾기
        int top = n-1;
        while (top > 0 && arr[top-1] >= arr[top]) top--;

        if (top == 0) return false;

        //step2 swtich값 찾기
        int switchNum = n-1;
        while (arr[top-1] >= arr[switchNum]) switchNum--;
        swap(arr, top-1, switchNum);

        //step3 뒤쪽 정렬
        int k = n-1;
        while (top < k) swap(arr, top++, k--);

        return true;
    }

    static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}
