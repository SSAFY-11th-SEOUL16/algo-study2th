import java.util.*;
import java.io.*;
/*
 * 500ms
 */
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());

        int n = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());
        long[] arr = new long[100001];

        for(int i=0; i<n; i++) {
            int k = Integer.parseInt(br.readLine().trim());
            for(int j=0; j<k; j++) {
                st = new StringTokenizer(br.readLine().trim());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                arr[start]+=1;
                arr[end]-=1;
            }
        }

        for(int i=1; i<arr.length; i++)
            arr[i] += arr[i-1];

        long sum =0;
        for(int i=0; i<t; i++)
            sum+= arr[i];

        int start =0;
        int end = t;
        long max = sum;
        for(int i=1; i<=arr.length -t; i++) {
            sum-=arr[i-1];
            sum+=arr[i+t-1];
            if(sum>max) {
                max = sum;
                start =i;
                end = i+t;
            }
        }
        System.out.println(start +" " + end);
    }
}