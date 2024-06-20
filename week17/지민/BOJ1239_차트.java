package BOJ.Etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1239_차트 {
	static int N;
	static int[] dogs;
	static int maxLineCnt;
	static int[] picked;

	private static int countingCenterLines() {
		int cnt = 0;
		boolean[] lines = new boolean[101];
		int pSum = 0;
		for (int i = 0; i < N; i++) {
			pSum += picked[i];
			lines[pSum] = true;
			if(pSum > 50 && lines[pSum-50]) cnt++;
		}
		return cnt;
	}

	private static void permutation(int index, int flag) {
		if(index == N) {
			maxLineCnt = Math.max(maxLineCnt, countingCenterLines());
			return;
		}
		for (int i = 0; i < N; i++) {
			if((flag & (1 << i)) != 0) continue;
			picked[index] = dogs[i];
			permutation(index + 1, flag | (1 << i));
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());

		int maxDog = 0;
		dogs = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			dogs[i] = Integer.parseInt(st.nextToken());
			maxDog = Math.max(maxDog, dogs[i]);
		}

		if(maxDog > 50) System.out.println(0);
		else if(maxDog == 50) System.out.println(1);
		else {
			picked = new int[N];
			permutation(0, 0);
			System.out.println(maxLineCnt);
		}
	}
}
