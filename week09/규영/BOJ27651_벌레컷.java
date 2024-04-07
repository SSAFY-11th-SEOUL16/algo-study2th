import java.io.*;
import java.util.StringTokenizer;

/*
 *  122000KB, 492ms
 *  숏코딩도르 수상!
 */

public class BOJ27651_벌레컷 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        long[] arr = new long[N];
        arr[0] = Long.parseLong(st.nextToken());
        for (int i = 1; i < N; i++) arr[i] = arr[i-1]+Long.parseLong(st.nextToken());
        long ans = 0;
        // 머리 | 가슴 | 배
        for (int j = 2; j < N; j++) { // j: 가슴과 배를 나누는 부분
            // i: 머리와 가슴을 나누는 부분
            // start~end: 머리의 최댓값이 될 수 있는! 구간 (start부터 end가 머리인 것이 아니다)
            int i = 0, start = 1, end = j-1;
            // 머리의 최댓값을 찾는 과정. 최댓값을 찾으면 그보다 작은 값은 다 가능하므로 최댓값을 찾는 것
            while (start <= end) {
                int mid = (start+end)/2;
                // 머리 < 배 && 배 < 가슴
                if (arr[mid-1] < arr[N-1]-arr[j-1] && arr[N-1]-arr[j-1] < arr[j-1]-arr[mid-1]) {
                    start = mid+1;
                    i = mid; // 조건을 만족한다면 일단 mid까지는 가능하므로 i 갱신
                } else end = mid-1;
            }
            ans += i; // 머리 길이가 1일 때부터 i일 때까지 모두 가능한 경우이므로 ans에 더해줌
        }
        System.out.print(ans);
    }
}