import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 시간: 1,656ms
 */
public class BOJ15684_사다리조작 {
	static int N; // 세로선
	static int M; // 놓여진 가로선 개수
	static int H; // 가로선
	static boolean[][] ladder;
	static int res;
	static int target;

	private static boolean possible() {
		for(int i = 1; i <= N; i++) {
			int y = 1, x = i;
			while(y <= H) {
				if(ladder[y][x]) x += 1;
				else if(ladder[y][x - 1]) x -= 1;
				y += 1;
			}
			if(i != x) return false;
		}
		return true;
	}

	private static void combi(int cnt, int startY, int startX) {
		if(cnt >= target) {
			if(possible()) res = cnt;
			return;
		}

		for(int i = startY; i <= H; i++) {
			for(int j = startX; j <= N - 1; j++) {
				if(ladder[i][j] || ladder[i][j - 1]) continue;
				ladder[i][j] = true;
				combi(cnt + 1, startY, startX);
				ladder[i][j] = false;
			}
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());

		ladder = new boolean[H + 1][N + 1];
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			ladder[a][b] = true;
		}

		res = -1;
		for(int i = 0; i <= 3; i++) {
			target = i;
			combi(0, 1, 1);
			if(res != -1) break;
		}
		System.out.println(res);
	}
}
