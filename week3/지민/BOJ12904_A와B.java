import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 시간: 128ms
 */

public class BOJ12904_A와B {
    static String S;
    static String T;

    public static int canConvert() {
        for (int i = T.length() - 1; i >= S.length() ; i--) {
            if(T.charAt(i) == 'B') {
                StringBuilder sb = new StringBuilder();
                for (int j = i - 1; j >= 0; j--) {
                    sb.append(T.charAt(j));
                }
                T = sb.toString();
            }
            else T = T.substring(0, i);
        }

        if(S.equals(T)) return 1;
        return 0;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        S = br.readLine();
        T = br.readLine();
        System.out.println(canConvert());
    }
}
