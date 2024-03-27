import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ2437_저울 {
	static int N;
	static int[] w;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		w = new int[N];
		for (int i = 0; i < N; i++) {
			w[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(w);
		if (w[0] != 1) {
			System.out.println(1);
			return;
		}
		int rs = 0;		// 범위 시작
		int re = w[0];	// 범위 끝
		for (int i = 1; i < N; i++) {
			int nrs = rs + w[i];
			int nre = re + w[i];
			if (re+1 < nrs) {
				System.out.println(re + 1);
				return;
			}
			else {
				re = nre;
			}
		}
		System.out.println(re + 1);
	}
}
