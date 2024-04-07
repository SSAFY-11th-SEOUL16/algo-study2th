// 1668ms
import java.io.*;
import java.util.*;

public class BOJ15573_채굴 {
	
	static class Pair {
		int x, y;
		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static int[][] graph;
	static int N, M;
	static int[][] offsets = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	
	static int dig(int D) {
		boolean[][] visited = new boolean[N][M];
		int total = 0;
		Queue<Pair> queue = new LinkedList<Pair>();
		for(int i = 0; i < N; i++) {
			if(!visited[i][0] && graph[i][0] <= D) {
				queue.offer(new Pair(i, 0));
				visited[i][0] = true;
				total++;
			}
			if(!visited[i][M - 1] && graph[i][M - 1] <= D) {
				queue.offer(new Pair(i, M - 1));
				visited[i][M - 1] = true;
				total++;
			}
		}
		for(int j = 1; j < M - 1; j++) {
			if(graph[0][j] <= D) {
				queue.offer(new Pair(0, j));
				visited[0][j] = true;
				total++;
			}
		}
		
		while(!queue.isEmpty()) {
			Pair cur = queue.poll();
			for(int[] offset: offsets) {
				int x = cur.x + offset[0];
				int y = cur.y + offset[1];
				if(x < 0 || y < 0 || x >= N || y >= M || visited[x][y] || graph[x][y] > D) {
					continue;
				}
				queue.offer(new Pair(x, y));
				visited[x][y] = true;
				total++;
			}
		}
		
		return total;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(input.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		int K = Integer.parseInt(tokens.nextToken());
		graph = new int[N][M];
		int lo = Integer.MAX_VALUE;
		int hi = 0;
		for(int i = 0; i < N; i++) {
			tokens = new StringTokenizer(input.readLine());
			for(int j = 0; j < M; j++) {
				graph[i][j] = Integer.parseInt(tokens.nextToken());
				if(graph[i][j] > hi) hi = graph[i][j];
				if(graph[i][j] < lo) lo = graph[i][j];
			}
		}
		while(lo <= hi) {
			int mid = (lo + hi) / 2;
			if(dig(mid) >= K) {
				hi = mid - 1;
			} else {
				lo = mid + 1;
			}
		}
		System.out.println(lo);
	}

}