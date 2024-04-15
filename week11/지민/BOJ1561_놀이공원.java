import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 시간: 128ms
 */
public class BOJ1561_놀이공원 {
	static int N;
	static int M;
	static int[] opTime;
	static final long MAX_TIME = 30L;

	private static long calcNum(long time) {
		long num = M;
		for (int i = 0; i < M; i++) {
			num += time / opTime[i];
		}
		return num;
	}

	private static long lowerBound() {
		long lo = 1, hi = N * MAX_TIME;
		while (lo < hi) {
			long mid = (lo + hi) / 2;
			if(N <= calcNum(mid)) hi = mid;
			else lo = mid + 1;
		}
		return lo;
	}

	private static int solve() {
		if(N <= M) return N;
		long time = lowerBound(); // N명을 모두 태울 수 있는 최소 시간
		long pNum = calcNum(time - 1); // 이전 시간까지 태운 인원 수

		int idx = 0;
		for (; idx < M; idx++) {
			if(time % opTime[idx] == 0) pNum += 1;
			if(pNum == N) break;
		}
		return idx + 1;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		opTime = new int[M];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			opTime[i] = Integer.parseInt(st.nextToken());
		}
		System.out.println(solve());
	}
}
