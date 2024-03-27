import java.io.*;
import java.util.*;

public class BOJ11559_PuyoPuyo {
	static final int N = 12, M = 6;
	static char[][] graph = new char[N][];
	static boolean[][] visited = new boolean[N][M];
	static int[][] offsets = {{-1, 0},{0, 1},{1, 0},{0, -1}};
	
	static boolean outBound(int x, int y) { return x < 0 || y < 0 || x >= N || y >= M || visited[x][y];}
	
	static boolean blast(int x, int y) {
		if(graph[x][y] == '.' || visited[x][y]) return false;
		Queue<int[]> memo = new LinkedList<int[]>();
		Queue<int[]> queue = new LinkedList<int[]>();
		char color = graph[x][y];
		queue.offer(new int[] {x, y});
		visited[x][y] = true;
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			memo.add(cur);
			for(int[] off: offsets) {
				int nx = cur[0] + off[0];
				int ny = cur[1] + off[1];
				if(outBound(nx, ny) || graph[nx][ny] != color) continue;
				queue.offer(new int[] {nx, ny});
				visited[nx][ny] = true;
			}
		}
		if(memo.size() < 4) return false;
		for(int[] xy: memo) {
			graph[xy[0]][xy[1]] = '.';
		}
		return true;
	}
	
	static void pullColumn(int y) {
		int l = N - 1;
		for(int r = N - 1; r >= 0; r--) {
			if(graph[r][y] != '.') {
				char tmp = graph[r][y];
				graph[r][y] = graph[l][y];
				graph[l][y] = tmp;
				l--;
			}
		}
	}
	
	static void pull() {
		for(int i = 0; i < M; i++) {
			pullColumn(i);
		}
	}
	
	static void init() {
		for(int i = 0; i < N; i++)
			for(int j = 0; j < M; j++)
				visited[i][j] = false;
	}
	
	static void print() {
		for(int i = 0; i < N; i++) {
			System.out.println(graph[i]);
		}
		System.out.println();
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		for(int i = 0; i < N; i++) {
			graph[i] = input.readLine().toCharArray();
		}
		boolean flag = true;
		int answer = 0;
		while(true) {
			flag = false;
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < M; j++) {
					flag |= blast(i, j);
				}
			}
			if(flag) {
				answer += 1;
				pull();
				init();
			} else {
				break;
			}
			
		}
		System.out.println(answer);
	}

}
