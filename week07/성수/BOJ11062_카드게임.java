// 664ms

import java.io.*;
import java.util.*;

public class BOJ11062_카드게임 {

	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(input.readLine());
		StringTokenizer tokens;
		for(int t = 0; t < T; t++) {
			tokens = new StringTokenizer(input.readLine());
			int N = Integer.parseInt(tokens.nextToken());
			int[] arr = new int[N];
			int[][] dp = new int[N][N];
			tokens = new StringTokenizer(input.readLine());
			boolean turn = N % 2 == 1 ? true: false;
			for(int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(tokens.nextToken());
				if(turn) dp[i][i] = arr[i];
				
			}
//			dp[i][j] = i ~ j 범위의 카드가 주어졌을 때 얻을 수 있는 최대 점수
			for(int k = 1; k < N; k++) {
				for(int i = 0; i < N - k; i++) {
					int j = i + k;
					if(turn) {
						int left = dp[i + 1][j];
						int right = dp[i][j - 1];
						dp[i][j] = Math.min(left, right);
					} else {
						int left = arr[i] + dp[i + 1][j];
						int right = dp[i][j - 1] + arr[j];
						dp[i][j] = Math.max(left, right);
					}
				}
				turn = !turn;
			}
			output.write(dp[0][N - 1] + "\n");
		}
		output.close();
	}

}