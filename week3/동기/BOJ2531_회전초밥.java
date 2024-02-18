import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int[] window = new int[d + 1];
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) 
            arr[i] = Integer.parseInt(br.readLine());
        
        int cnt = 1;
        window[c]++;

        for (int i = 0; i < k; i++) {
            if (window[arr[i]] == 0)
                cnt++;
            window[arr[i]]++;
        }
        int max = cnt;

        for (int i = 1; i < n; i++) {
            if (max == k + 1) break;

            int removeIndex = i - 1;
            int addIndex = i + k - 1 >= n ? (i + k - 1) - n : i + k - 1;
            window[arr[removeIndex]]--;
            if (window[arr[removeIndex]] == 0)
                cnt--;
            window[arr[addIndex]]++;

            if (window[arr[addIndex]] == 1)
                cnt++;

            max = Math.max(max, cnt);
        }
        System.out.println(max);
    }
}
// 언어 : java8 /시간 : 144 ms