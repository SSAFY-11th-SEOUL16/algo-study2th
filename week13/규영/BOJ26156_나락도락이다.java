import java.io.*;

/*
    26732KB, 212ms
    조합 + 살짝 dp스럽게 ..?
    처음에 num에서 0 하나 빼먹어서 틀림!
 */
public class BOJ26156_나락도락이다 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()), num = 1000000007;
        char[] S = br.readLine().toCharArray();
        int[] cnt = new int[4], pow = new int[N+1];
        // pow에 미리 곱하고 나눠줌. 지금보니 N+1까지 할 필요가 없음
        pow[0] = 1;
        for (int i = 1; i <= N; i++) pow[i] = (pow[i-1]*2)%num;
        for (int i = 0; i < N; i++) {
            // ROCK 여러 번 등장 가능 고려
            switch (S[i]) {
                case 'R':
                    cnt[0] = (pow[i]+cnt[0])%num;
                    break;
                case 'O':
                    cnt[1] = (cnt[0]+cnt[1])%num;
                    break;
                case 'C':
                    cnt[2] = (cnt[1]+cnt[2])%num;
                    break;
                case 'K':
                    cnt[3] = (cnt[2]+cnt[3])%num;
            }
        }
        System.out.print(cnt[3]);
    }
}