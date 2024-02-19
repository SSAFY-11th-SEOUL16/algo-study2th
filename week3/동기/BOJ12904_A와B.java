import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static StringBuilder t;
    static String s;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        s = br.readLine();
        t = new StringBuilder();
        t.append(br.readLine());
        dfs(0, t.length() - s.length(), false);
        System.out.println(0);
    }

    static void dfs(int depth, int targetDepth, boolean isFront) {
        if (depth == targetDepth) {
            if (s.equals(isFront ? t.reverse().toString() : t.toString())) {
                System.out.println(1);
                System.exit(0);
            }
            return;
        }

        if (isFront) {
            if (t.charAt(0) == 'A') {
                t.deleteCharAt(0);
                dfs(depth + 1, targetDepth, isFront);
                t.insert(0, "A");
            } else if (t.charAt(0) == 'B') {
                t.deleteCharAt(0);
                dfs(depth + 1, targetDepth, !isFront);
                t.insert(0, "B");
            }

        } else {
            if (t.charAt(t.length() - 1) == 'A') {
                t.deleteCharAt(t.length() - 1);
                dfs(depth + 1, targetDepth, isFront);
                t.append("A");
            } else if (t.charAt(t.length() - 1) == 'B') {
                t.deleteCharAt(t.length() - 1);
                dfs(depth + 1, targetDepth, !isFront);
                t.append("B");
            }
        }
    }
}
// 언어 : Java8 / 시간 : 88 ms