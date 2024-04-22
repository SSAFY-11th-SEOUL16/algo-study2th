package algostudy.week12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 시간: 80ms
 */
public class BOJ1106_호텔 {
	static class City {
		int cost;
		int people;

		public City(int cost, int people) {
			this.cost = cost;
			this.people = people;
		}
	}
	static int N;
	static int C;
	static City[] cities;
	static final int INF = 987654321;

	private static int solve() {
		int[] dp = new int[C + 1];
		Arrays.fill(dp, INF);

		for (int i = 0; i < N; i++) {
			for (int c = 1; c <= C; c++) {
				if(c <= cities[i].people) dp[c] = Math.min(dp[c], cities[i].cost);
				else dp[c] = Math.min(dp[c], cities[i].cost + dp[c - cities[i].people]);
			}
		}
		return dp[C];
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		C = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		cities = new City[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int c = Integer.parseInt(st.nextToken());
			int n = Integer.parseInt(st.nextToken());
			cities[i] = new City(c, n);
		}
		System.out.println(solve());
	}
}
