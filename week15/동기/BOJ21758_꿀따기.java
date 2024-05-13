import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


public class Main {
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        long[] arr = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
        long[] reverseArr =reverseArray(arr);
        long[] prefixSum = arr.clone();
        long[] reversePrefixSum = reverseArr.clone();
        for (int i = 1; i < n; i++){
            prefixSum[i] += prefixSum[i - 1];
            reversePrefixSum[i] += reversePrefixSum[i - 1];
        }
        long a = Math.max(case1(arr, prefixSum), case2(arr, prefixSum, maxValue(arr)));
        long b = case1(reverseArr, reversePrefixSum);
        System.out.println(Math.max(a,b));
    }

    static long case1(long[] arr, long[] prefixSum) {
        int rightPos = 1;
        long honeyCnt = (prefixSum[n - 1] - prefixSum[0]) + (prefixSum[n - 1] - prefixSum[rightPos]) - arr[rightPos];
        long max = honeyCnt;
        for (int i = 2; i < n - 1; i++) {
            honeyCnt += (arr[i - 1] - (2 * arr[i]));
            
            max = Math.max(max, honeyCnt);
        }
        
        return max;
    }

    static long case2(long[] arr, long[] prefixSum, long maxValue) {
        int leftPos = 0;
        int rightPos = n - 1;
        long honeyCnt = prefixSum[rightPos] - arr[leftPos] - arr[rightPos] + maxValue;
        return honeyCnt;
    }

    static long[] reverseArray(long[] arr) {
        int length = arr.length;
        long[] reverse = new long[length];
        for (int i = 0; i < length; i++)
            reverse[i] = arr[length - 1 - i];
        return reverse;
    }

    static long maxValue(long[] arr) {
        long max = 0;
        for (int i = 1; i < n - 1; i++) 
            max = Math.max(max, arr[i]);
        
        return max;
    }
}
