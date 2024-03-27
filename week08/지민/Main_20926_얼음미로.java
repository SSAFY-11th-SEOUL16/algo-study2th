package ps;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * 시간: 460s
 */

// 테라(T), 바위(R), 구멍(H), 출구(E)
public class Main_20926_얼음미로 {
	static class Point implements Comparable<Point>{
		int y, x;
		int dist;
		public Point(int y, int x, int dist) {
			this.y = y;
			this.x = x;
			this.dist = dist;
		}
		@Override
		public int compareTo(Point o) {
			return this.dist - o.dist;
		}
		@Override
		public String toString() {
			return "Point [y=" + y + ", x=" + x + ", dist=" + dist + "]";
		}
	}
	static int N;
	static int M;
	static char[][] map;
	static final int[][] dir = {{0, -1}, {0, 1}, {1, 0}, {-1, 0}};
	
	private static boolean isRange(int y, int x) {
		return 0 <= y && y < N && 0 <= x && x < M;
	}
	
	private static Point move(int y, int x, int d) {
		int dist = 0;
		while(true) {
			int ny = y + dir[d][0];
			int nx = x + dir[d][1];
			if(!isRange(ny, nx)) break;
			if(map[ny][nx] == 'H') break;
			
			
			if(map[ny][nx] == 'E') return new Point(ny, nx, dist);
			if(map[ny][nx] == 'R') return new Point(y, x, dist);
			
			y = ny;
			x = nx;
			dist += (int) (map[y][x] - '0');
		}
		return new Point(-1, -1, -1);
	}
	
	private static int solve(Point start, Point end) {
		Queue<Point> queue = new LinkedList<>();
		int[][] dist = new int[N][N];
		for(int i = 0; i < N; i++) Arrays.fill(dist[i], -1);
		
		queue.add(start);
		dist[start.y][start.x] = 0;
		
		while(!queue.isEmpty()) {
			Point here = queue.poll();
			if(here.dist > dist[here.y][here.x]) continue;
			
			for(int i = 0; i < 4; i++) {
				Point next = move(here.y, here.x, i);
				if(next.dist == -1) continue; // 구멍 또는 절벽
				
				int nextDist = here.dist + next.dist;
				if(dist[next.y][next.x] == -1 || dist[next.y][next.x] > nextDist) {
					dist[next.y][next.x] = nextDist;
					queue.add(new Point(next.y, next.x, nextDist));
				}
			}
		}
		
		return dist[end.y][end.x];
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		Point start = null;
		Point end = null;
		map = new char[N][M];
		for(int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
			for(int j = 0; j < M; j++) {
				if(map[i][j] == 'T') start = new Point(i, j, 0);
				if(map[i][j] == 'E') end = new Point(i, j, 0);
			}
		}
		System.out.println(solve(start, end));
	}
}
