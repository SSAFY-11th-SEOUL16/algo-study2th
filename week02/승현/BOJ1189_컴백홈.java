import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1189_컴백홈 {
	static int R, C, K;
	static String[][] graph;
	static boolean[][] visited;
	static int result;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		graph = new String[R][C];
		visited = new boolean[R][C];
		
		for (int i = 0; i < R; i ++) {
			graph[i] = br.readLine().split("");
		}
		
		visited[R-1][0] = true;
		dfs(0, R-1, 0);
		System.out.println(result);
	}
	
	public static void dfs(int depth, int x, int y) {
		if (depth == K-1) {
			if(x == 0 && y == C-1)
				result++;
			return;
		}
		for (int[] dir : new int[][]{{1, 0},{-1, 0},{0, 1},{0, -1}}) {
			int nx = x + dir[0];
			int ny = y + dir[1];
			if (nx < 0 || nx >= R || ny < 0 || ny >= C) {
				continue;
			}
			if (visited[nx][ny] || graph[nx][ny].equals("T"))
				continue;
			visited[nx][ny] = true;
			dfs(depth+1, nx, ny);
			visited[nx][ny] = false;
		}
	}
}