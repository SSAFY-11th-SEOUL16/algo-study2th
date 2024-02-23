import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ15684_사다리조작 { 		// 3088ms
	static int N, M, H;
	static boolean[][] connected;
	static ArrayList<int[]> ccList;
	static int[] picked;
	static int result = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		connected = new boolean[H+1][N];
		ccList = new ArrayList<>();
		picked = new int[3];
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			connected[a][b] = true;
		}
		for (int i = 1; i <= H; i++) {
			for (int j = 1; j < N; j++) {
				if (canConnect(i, j)) {
					ccList.add(new int[] {i, j});
				}
			}
		}
		comb(0);
		if (result == Integer.MAX_VALUE)
			System.out.println(-1);
		else
			System.out.println(result);
	}
 
	public static void comb(int depth) {
		if (play()) {
			if (depth < result)
				result = depth;
			return;
		}
		if (depth == 3)
			return;
		for (int i = 0; i < ccList.size(); i++) {
			if (!canConnect(ccList.get(i)[0], ccList.get(i)[1]))
				continue;
			connected[ccList.get(i)[0]][ccList.get(i)[1]] = true;
			comb(depth+1);
			connected[ccList.get(i)[0]][ccList.get(i)[1]] = false;
		}
	}
	
	public static boolean canConnect(int i, int j) {
		if (!connected[i][j] && !connected[i][j-1] && ((j < N-1 && !connected[i][j+1]) || j+1 >= N)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public static boolean play() {
		int col;
		for (int i = 0; i < N; i++) {
			col = i;
			for (int j = 1; j <= H; j++) {
				if (col == 0) {
					if (connected[j][col+1]) {
						col = col + 1;
					}
				}
				else if (col == N-1) {
					if (connected[j][col])
						col = col - 1;
				}
				else {
					if (connected[j][col+1]) {
						col = col + 1;
					}
					else if (connected[j][col]) {
						col = col - 1;
					}
				}
			}
			if (col != i) {
				return false;
			}
		}
		return true;
	}
}