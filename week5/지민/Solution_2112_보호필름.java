import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 시간: 681ms
 */
public class Solution_2112_보호필름 {
	static int D;
	static int W;
	static int K;
	static int[][] map;
	static int[][] tmp;
	static int res;
	
	private static void setMap(int y, int flag) {
		for(int x = 0; x < W; x++) tmp[y][x] = flag;
	}
	
	private static void initMap(int y) {
		for(int x = 0; x < W; x++) tmp[y][x] = map[y][x];
	}
	
	private static boolean pass(int x) {
		for(int y = 0; y < D - 1; y++) {
			int cnt = 1;
			int ny = y + 1;
			while(ny < D && tmp[y][x] == tmp[ny][x]) {
				cnt += 1;
				ny += 1;
			}
			if(cnt >= K) return true;
			y = ny - 1;
		}
		return false;
	}
	
	private static boolean possible() {
		for(int x = 0; x < W; x++) {
			if(!pass(x)) return false;
		}
		return true;
	}
	
	private static void solve(int index, int cnt) {
		if(res != -1 && res < cnt) return;
		if(index == D) {
			if(possible() && (res == -1 || res > cnt)) res = cnt;
			return;
		}
		
		solve(index + 1, cnt);
		
		// A 투여
		setMap(index, 0);
		solve(index + 1, cnt + 1);
		initMap(index);
		
		// B 투여
		setMap(index, 1);
		solve(index + 1, cnt + 1);
		initMap(index);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		
		StringBuilder sb = new StringBuilder();
		for(int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			D = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			map = new int[D][W];
			tmp = new int[D][W];
			for(int i = 0; i < D; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < W; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					tmp[i][j] = map[i][j];
				}
			}
			res = -1;
			solve(0, 0);
			sb.append("#").append(t).append(" ").append(res).append("\n");
		}
		System.out.println(sb);

	}
}
