import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ15922_아우으우아으이야 { // 352ms

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        int result = 0;
        for (int i = 0; i < n-1; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            // 전체 포함하는 경우
            if (end >= y) {
                continue;
            }
            // 이어지는 경우
            else if (end >= x) {
                end = y;
            }
            // 떨어진 경우
            else {
                result += Math.abs(end - start);
                start = x;
                end = y;
            }
        }
        result += Math.abs(end - start);
        System.out.println(result);

    }

}