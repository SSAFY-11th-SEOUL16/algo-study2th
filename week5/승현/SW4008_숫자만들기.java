import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW4008_숫자만들기 {		// 136 ms
	static int max, min;
	static int N;
	static int[] operCnt; 	// + - * / 갯수
	static int[] opers;		// 연산자 순서 순열
	static int[] nums;		// 숫자들

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			max = Integer.MIN_VALUE;
			min = Integer.MAX_VALUE;
			N = Integer.parseInt(br.readLine());
			operCnt = new int[4];
			opers = new int[N - 1];
			nums = new int[N];
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 4; i++) {
				operCnt[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				nums[i] = Integer.parseInt(st.nextToken());
			}
			perm(0);
			sb.append("#").append(tc).append(" ").append(max - min).append("\n");
		}
		System.out.println(sb.toString());
	}

	public static void perm(int depth) {	// 연산자 순열 생성
		if (depth == N - 1) {
			int result = getResult();		// N-1개 순열 만들어지면 식 계산해서
			if (result > max) {				// max, min 업데이트
				max = result;
			}
			if (result < min)
				min = result;
			return;
		}
		for (int i = 0; i < 4; i++) {
			if (operCnt[i] == 0)
				continue;
			opers[depth] = i;
			operCnt[i]--;
			perm(depth + 1);
			operCnt[i]++;
		}
	}

	public static int getResult() {			// 식 계산
		int result = nums[0];
		for (int i = 1; i < N; i++) {
			if (opers[i-1] == 0) {
				result += nums[i];
			} else if (opers[i-1] == 1) {
				result -= nums[i];
			} else if (opers[i-1] == 2) {
				result *= nums[i];
			} else if (opers[i-1] == 3) {
				result /= nums[i];
			}
		}
		return result;
	}
}