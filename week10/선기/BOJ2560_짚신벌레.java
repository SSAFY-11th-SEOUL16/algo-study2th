import java.io.*;
import java.util.*;

/**
 * 15580KB
 * 112ms
 */
public class BOJ2560_짚신벌레 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int a, b, d, n;
    static int[] bornCnt; // 날짜 별 태어난 벌레 수
    static int matureCnt; // 새로운 벌레를 만드는 벌레 수
    static int deathCnt; // 죽는 벌레 수
    static int totalCnt; // 전체 벌레 수 (미성숙+성숙+늙은이)

    public static void main(String[] args) throws IOException {
        a = nextInt();
        b = nextInt();
        d = nextInt();
        n = nextInt();
        bornCnt = new int[n + 1];
        totalCnt = 1;
        bornCnt[0] = 1;
        for (int i = 1; i <= n; ++i) {
            if (i >= a) {
                matureCnt += (bornCnt[i - a] % 1000); // a일 전에 태어난 벌레가 성체가 됨
            }
            if (i >= b) {
                matureCnt -= (bornCnt[i - b] % 1000); // b일 전에 태어난 벌레가 늙음
            }
            if (i >= d) {
                deathCnt = (bornCnt[i - d] % 1000); // d일 전에 태어난 벌레가 죽음
            }
            bornCnt[i] = matureCnt % 1000; // 성숙한 벌레 수 만큼 태어남
            totalCnt += (bornCnt[i] - deathCnt) % 1000;
            totalCnt = (totalCnt + 1000) % 1000;
        }
        System.out.println(totalCnt);
    }

    static String next() throws IOException {
        while (st == null || !st.hasMoreTokens()) {
            st = new StringTokenizer(br.readLine());
        }
        return st.nextToken();
    }

    static int nextInt() throws IOException {
        return Integer.parseInt(next());
    }

}
