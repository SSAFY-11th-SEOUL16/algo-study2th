import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1561_놀이공원 {

	static int[] times;
	static int[] canRideTimes;
	static long n;
	static int m;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		canRideTimes = new int[31];
		times = new int[m];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < m; i++) {
			int input = Integer.parseInt(st.nextToken());
			canRideTimes[input]++;
			times[i] = input;
		}

		if (n <= m)
			System.out.println(n);

		else {
			// 이분탐색?
			long FillTime = binary();
			
			// 1초전에 구한다음 사람 구하기
			long before = FillTime - 1;

			long cnt = counter(before);

			// 남은 사람수
			long remain = n - cnt;
			
			for (int i = 0; i < m; i++) {

				if (before % times[i] == 0) {
					remain--;
					if (remain == 0) {
						System.out.println(i + 1);
						break;
					}
				}

			}
		}

	}

	static long binary() {
		long left = 1;
		long right = n * 30;

		while (left < right) {

			long mid = (left + right) / 2;
			long cnt = counter(mid);

			if (cnt < n)
				left = mid + 1;
			else
				right = mid;
		}

		return left;
	}

	static long counter(long time) {
		long cnt = 0;
		if (time >= 1)
			cnt += m;

		// 1초 이후 사람수
		for (int i = 1; i <= 30; i++) {
			cnt += ((time - 1) / i) * canRideTimes[i];
		}

		return cnt;
	}

}
