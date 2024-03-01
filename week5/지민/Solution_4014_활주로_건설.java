import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 시간: 136ms
 */
public class Solution_4014_활주로_건설 {
	static int N;
	static int X;
	static int[][] map;
	static int[] height;

	private static boolean possible(int k, int flag) {
		boolean[] visited = new boolean[N]; // 경사로 체크
		
		for (int i = 0; i < N; i++) {
		    if(flag == 0) height[i] = map[k][i]; // row
		    else height[i] = map[i][k]; // col
		}
		
		for(int i = 0; i < N - 1; i++) {
			if(Math.abs(height[i] - height[i + 1]) >= 2) return false;

			if(height[i] > height[i + 1]) { // 내리막
				for(int j = i + 1; j <= i + X; j++) {
					if(j >= N) return false;
					if(height[i + 1] != height[j] || visited[j]) return false;
					visited[j] = true;
				}
			}
			else if(height[i] < height[i + 1]) { // 오르막
                for(int j = i; j > i - X; j--) {
                	if(j < 0) return false;
                    if(height[i] != height[j] || visited[j]) return false;
                    visited[j] = true;
                }
			}
		}
		return true;
	}
	
	private static int solve() {
		height = new int[N];
		int cnt = 0;
		for(int i = 0; i < N; i++) {
			if(possible(i, 0)) cnt += 1;
			if(possible(i, 1)) cnt += 1;
		}
		return cnt;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		
		StringBuilder sb = new StringBuilder();
		for(int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken());
			
			map = new int[N][N];
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			sb.append("#").append(t).append(" ").append(solve()).append("\n");
		}
		System.out.println(sb);
	}
}
