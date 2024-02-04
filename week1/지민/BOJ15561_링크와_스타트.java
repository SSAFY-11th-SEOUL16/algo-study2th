import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ15561_링크와_스타트 {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;
    private static int[][] linkStart;

    private static int compute(List<Integer> start, List<Integer> link) {
        int startSum = 0;
        int linkSum = 0;
        for(int i = 0; i < start.size(); i++) {
            for(int j = 0; j < start.size(); j++) {
                if(i == j) continue;
                startSum += linkStart[start.get(i)][start.get(j)];
            }
        }
        for(int i = 0; i < link.size(); i++) {
            for(int j = 0; j < link.size(); j++) {
                if(i == j) continue;;
                linkSum += linkStart[link.get(i)][link.get(j)];
            }
        }
        return Math.abs(startSum - linkSum);
    }

    public static int solve() {
        int res = Integer.MAX_VALUE;

        for (int i = 1; i < (1<<N) - 1; i++) { // 아무것도 안고를 때와 모두 고를 때는 제외(조건: 한 명 이상)
            List<Integer> start = new ArrayList<>();
            List<Integer> link = new ArrayList<>();
            for(int j = 0; j < N; j++) {
                if((i & (1 << j)) != 0) start.add(j);
                else link.add(j);
            }
            res = Math.min(res, compute(start, link));
        }

        return res;
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        linkStart = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                linkStart[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(solve());
    }
}
