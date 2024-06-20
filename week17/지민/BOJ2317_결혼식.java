package BOJ.Etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * (태연님의 설명과 풀이로 풀었습니다.)
 * 1. 사자 키의 최소와 최대 값 사이의 수는 모두 상쇄된다.
 * 	 - 따라서 해당 범위를 벗어나는 수에 대해서면 고려한다.
 * 2. 사람의 키에서도 최소와 최대 값이 있다면 그 사이의 수는 모두 상쇄될 수 있다.
 * 	 - 따라서 사람 키의 최소값과 최대값만 사자 사이에 적절히 배치하면 된다.
 * 3. 그리고 사자 키의 MIN과 MAX 사이에 사람 키의 MIN과 MAX가 존재하면 상쇄될 수 있다.
 *   - 결국 아래 두 가지의 경우에 대해서만 사람 키의 최소값과 최대값을 적절히 배치하면 된다.
 *     1) 사자 키 MIN > 사람 키 MIN
 *     2) 사자 키 MAX < 사람 키 MAX
 *  4. 사람 키는 크게 2곳에 배치할 수 있다.
 *    1) 사자 키의 최소값/최대값 자리에 대치 -> 두 값의 차이의 2배만큼 늘어난다.
 *      - abs(사자 키 MIN - 사람 키 MIN) * 2
 *      - abs(사자 키 MAX - 사람 키 MAX) * 2
 *    2) 사자 키 리스트의 양쪽 끝 중에서 차이가 더 작은 한 곳에 위치 -> 두 값의 차이만큼 늘어난다.
 *    	- min(abs(사자 키 LEFT - 사람 키 MIN), abs(사자 키 RIGHT - 사람 키 MIN))
 *    	- min(abs(사자 키 LEFT - 사람 키 MAX), abs(사자 키 RIGHT - 사람 키 MAX))
 */
public class BOJ2317_결혼식 {
	static int N;
	static int K;
	static int[] H;
	static int[] lionH;	  // 사자가족 키의 최소와 최대
	static int[] personH; // 사람 키의 최소와 최대
	static int lionDiffSum;

	private static int calcDiffSum() {
		if(lionH[0] > personH[0]) {
			int diffMinPos = Math.abs(lionH[0] - personH[0]) * 2;
			int diffSidePos = Math.min(Math.abs(H[0] - personH[0]), Math.abs(H[K - 1] - personH[0]));
			lionDiffSum += Math.min(diffMinPos, diffSidePos);
		}
		if(lionH[1] < personH[1]) {
			int diffMaxPos = Math.abs(lionH[1] - personH[1]) * 2;
			int diffSidePos = Math.min(Math.abs(H[0] - personH[1]), Math.abs(H[K - 1] - personH[1]));
			lionDiffSum += Math.min(diffMaxPos, diffSidePos);
		}
		return lionDiffSum;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		H = new int[N];
		lionH = new int[] {Integer.MAX_VALUE, 0};
		personH = new int[] {Integer.MAX_VALUE, 0};
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			H[i] = Integer.parseInt(st.nextToken());
			lionH[0] = Math.min(lionH[0], H[i]);
			lionH[1] = Math.max(lionH[1], H[i]);
			if(i > 0) lionDiffSum += Math.abs(H[i] - H[i - 1]);
		}
		for (int i = K; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			H[i] = Integer.parseInt(st.nextToken());
			personH[0] = Math.min(personH[0], H[i]);
			personH[1] = Math.max(personH[1], H[i]);
		}
		System.out.println(calcDiffSum());
	}
}
