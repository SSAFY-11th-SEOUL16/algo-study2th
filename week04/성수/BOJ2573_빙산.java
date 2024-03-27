import java.io.*;
import java.util.*;

public class BOJ2573_빙산 {

	static int[][] offsets = {{-1, 0},{0, 1},{1, 0},{0, -1}};
	static int[][] graph;
	static int N, M;
	static Queue<int[]> icebergs;
	
	public static boolean isWhole(int x, int y, int count) {
		Queue<int[]> queue = new LinkedList<int[]>();
		boolean[][] visited = new boolean[N][M];
		queue.offer(new int[] {x, y});
		visited[x][y] = true;
		count--;
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			for(int[] dxdy: offsets) {
				int nx = cur[0] + dxdy[0];
				int ny = cur[1] + dxdy[1];
				if(graph[nx][ny] == 0 || visited[nx][ny]) continue;
				queue.offer(new int[] {nx, ny});
				visited[nx][ny] = true;
				count--;
			}
		}
		return count == 0;
	}
	
	public static int getWater(int x, int y) {
		int water = 0;
		for(int[] dxdy: offsets) {
			int nx = x + dxdy[0];
			int ny = y + dxdy[1];
			if(graph[nx][ny] == 0) water++;
		}
		return water;
	}
	public static void print() {
		for(int[] row: graph) {
			System.out.println(Arrays.toString(row));
		}
		System.out.println();
	}
	public static void print2() {
		for(int[] row: icebergs) {
			System.out.println(Arrays.toString(row));
		}
		System.out.println();
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(input.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		graph = new int[N][M];
		icebergs = new ArrayDeque<>();
		for(int i = 0; i < N; i++) {
			tokens = new StringTokenizer(input.readLine());
			for(int j = 0; j < M; j++) {
				graph[i][j] = Integer.parseInt(tokens.nextToken());
				if(graph[i][j] > 0) icebergs.offer(new int[] {i, j, 0});
			}
		}
		int totalTime = 0;
		while(!icebergs.isEmpty()) {
			if(!isWhole(icebergs.peek()[0], icebergs.peek()[1], icebergs.size())) {
				System.out.println(totalTime);
				return;
			}
			int minTime = 10_000;
			for(int[] ice: icebergs) {
				ice[2] = getWater(ice[0], ice[1]);
				if(ice[2] != 0) {
					int time = (int) Math.ceil((float)graph[ice[0]][ice[1]] / ice[2]);
					if(time < minTime) {
						minTime = time; 
					}
				}
			}
			int count = icebergs.size();
			for(int i = 0; i < count; i++) {
				int[] ice = icebergs.poll();
				if(ice[2] == 0) {
					icebergs.offer(ice);
					continue;
				}
				int x = ice[0];
				int y = ice[1];
				graph[x][y] = Math.max(graph[x][y] - ice[2] * minTime, 0);
				if(graph[x][y] > 0) icebergs.offer(ice);
			}
			totalTime += minTime;
		}
		System.out.println(0);
	}

}
