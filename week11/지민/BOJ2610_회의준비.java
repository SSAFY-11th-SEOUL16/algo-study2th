import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 시간: 120ms
 */
public class BOJ2610_회의준비 {
	static int N;
	static int M;
	static int[] group;
	static int[][] dist;
	static final int INF = 987654321;

	private static void dfs(int curr, int g) {
		group[curr] = g;
		for(int next = 1; next <= N; next++) {
			if(dist[curr][next] == 1 && group[next] == 0) dfs(next, g);
		}
	}
	private static int makeGroup() {
		int groupCnt = 1;
		for(int i = 1; i <= N; i++) {
			if(group[i] != 0) continue;
			dfs(i, groupCnt);
			groupCnt += 1;
		}
		return groupCnt - 1;
	}

	private static void floyd() {
		for(int k = 1; k <= N; k++) {
			for(int i = 1; i <= N; i++) {
				for(int j = 1; j <= N; j++) {
					if(i == j) dist[i][j] = 0;
					if(dist[i][k] == INF ||  dist[k][j] == INF) continue;
					dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
				}
			}
		}
	}

	private static int[] calcTime() {
		int[] time = new int[N + 1];
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) {
				if(group[i] == group[j]) time[i] = Math.max(time[i], dist[i][j]);
			}
		}
		return time;
	}

	private static int[] getResp(int groupCnt, int[] time) {
		int[] respNum = new int[groupCnt + 1];
		int[] respTime = new int[groupCnt + 1];
		Arrays.fill(respTime, INF);

		for(int i = 1; i <= N; i++) {
			int g = group[i];
			if(respTime[g] > time[i]) {
				respNum[g] = i;
				respTime[g] = time[i];
			}
		}
		return respNum;
	}

	private static String solve() {
		floyd();
		int groupCnt = makeGroup();
		int[] time = calcTime();
		int[] resp = getResp(groupCnt, time);
		Arrays.sort(resp);

		StringBuilder sb = new StringBuilder();
		sb.append(groupCnt).append("\n");
		for(int i = 1; i <= groupCnt; i++) sb.append(resp[i]).append("\n");
		return sb.toString();
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());

		group = new int[N + 1];
		dist = new int[N + 1][N + 1];
		for(int i = 0; i <= N; i++) Arrays.fill(dist[i], INF);

		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			dist[u][v] = dist[v][u] = 1;
		}
		System.out.println(solve());
	}
}
