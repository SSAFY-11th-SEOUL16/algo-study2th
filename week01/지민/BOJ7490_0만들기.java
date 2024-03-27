import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ7490_0만들기 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;

    private static int calc(String str) {
        str = str.replaceAll(" ", "");
        StringTokenizer numOp = new StringTokenizer(str, "+|-", true);

        int sum = Integer.parseInt(numOp.nextToken());
        while (numOp.hasMoreTokens()){
            String op = numOp.nextToken();
            int next = Integer.parseInt(numOp.nextToken());
            if(op.equals("+")) sum += next;
            else sum -= next;
        }
        return sum;
    }
    private static void solve(int num, String str, StringBuilder sb) {
        if(num == N + 1) {
            if(calc(str) == 0) sb.append(str).append("\n");
            return;
        }
        solve(num + 1, str + " " + num, sb);
        solve(num + 1, str + "+" + num, sb);
        solve(num + 1, str + "-" + num, sb);
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            solve(2, "1", sb);
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
