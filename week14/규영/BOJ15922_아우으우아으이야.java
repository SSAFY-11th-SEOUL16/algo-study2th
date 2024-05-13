import java.io.*;
import java.util.*;

/*
    41288KB, 360ms
    정렬된 상태로 주어짐
    완벽히 겹침, 일부 겹침, 겹치지 않음의 세 경우만 존재
 */

public class BOJ15922_아우으우아으이야 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken()), end = Integer.parseInt(st.nextToken()), ans = end-start;
        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int ns = Integer.parseInt(st.nextToken()), ne = Integer.parseInt(st.nextToken());
//			if (start <= ns && ne <= end) continue; // 완벽히 겹치는 경우
            if (ns <= end && end < ne) { // 일부가 겹치는 경우
                ans += ne-end;
                end = ne;
            } else if (end < ns) { // 겹치지 않는 경우
                ans += ne-ns;
                end = ne;
            }
        }
        System.out.print(ans);
    }
}