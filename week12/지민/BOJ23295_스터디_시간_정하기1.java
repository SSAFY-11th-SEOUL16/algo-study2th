package algostudy.week12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 시간: 472ms
 */
public class BOJ23295_스터디_시간_정하기1 {
	static int N;
	static int T;
	static int[] time;
	static int maxEndTime = 0;
	static final int MAX_TIME = 100_000;
	
	private static int findStartTime() {
		for (int i = 1; i <= maxEndTime; i++) {
			time[i] += time[i - 1];
		}

		long[] pTime = new long[MAX_TIME + 2];
		for (int i = 1; i <= maxEndTime; i++) {
			pTime[i] += time[i] + pTime[i - 1];
		}

		long resMaxTime = 0;
		int resS = 0;
		for (int s = 0; s <= maxEndTime - T; s++) {
			if(resMaxTime < (pTime[s + T] - pTime[s])) {
				resMaxTime = pTime[s + T] - pTime[s];
				resS = s;
			}
		}
		return resS;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());

		time = new int[MAX_TIME + 2];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int K = Integer.parseInt(st.nextToken());

			for (int j = 0; j < K; j++) {
				st = new StringTokenizer(br.readLine());
				int S = Integer.parseInt(st.nextToken());
				int E = Integer.parseInt(st.nextToken());
				time[S + 1] += 1;
				time[E + 1] -= 1;
				if(maxEndTime < E) maxEndTime = E;
			}
		}
		int s = findStartTime();
		System.out.println(s + " " + (s + T));
	}
}
