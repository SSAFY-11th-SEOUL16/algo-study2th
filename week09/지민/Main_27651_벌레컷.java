package algostudy.week9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 시간: 464ms
 * 다른 사람 풀이 참고했습니다.
 */
public class Main_27651_벌레컷 {
	static int N;
	static long[] A;
	static long[] pSum;

	private static long getRangePSum(int left, int right){
		return pSum[right] - pSum[left - 1];
	}

	/**
	 * upperBound -> 이분 탐색으로 구하는 이유(단순 반복으로 하면 시간 초과!)
	 */
	private static int upperBound(int start, int end, long key){
		int ret = end;
		int lo = start - 1, hi = end;
		while(lo <= hi){
			int mid = (lo + hi) / 2;
			if(getRangePSum(start, mid) <= key){
				ret = mid;
				lo = mid + 1;
			}
			else hi = mid - 1;
		}
		return ret;
	}

	private static long solve() {
		long ret = 0;
		int left = 1, right = N - 1; // 머리: [1, left], 가슴: [left + 1, right], 배: [right + 1, N]
		while (left < right) {
			long headSum = getRangePSum(1, left);
			long midSum = getRangePSum(left + 1, right);
			long tailSum = getRangePSum(right + 1, N);
			if(midSum <= headSum || midSum <= tailSum) break;
			if(tailSum <= headSum) right--;
			else { // headSum은 고정, midSum > tailSum 개수 구하기
				long key = getRangePSum(left + 1, N) / 2;
				int index = upperBound(left + 1, N, key); // index: [가슴 < 배] 조건을 만족하는 가장 큰 인덱스
				if(index < right) ret += right - index;
				left++;
			}
		}
		return ret;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());

		A = new long[N + 1];
		pSum = new long[N + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			A[i] = Long.parseLong(st.nextToken());
			pSum[i] += pSum[i - 1] + A[i];
		}

		System.out.println(solve());
	}
}
