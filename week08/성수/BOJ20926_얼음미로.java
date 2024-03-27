/*
26484KB 304ms
*/

import java.io.*;
import java.util.*;

public class BOJ20926_얼음미로 {
	
	static int W, H;
	static int[][] offsets = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	static char[][] graph;
	
	static class Coord implements Comparable<Coord>{
		int x, y, dist;

		public Coord(int x, int y, int dist) {
			super();
			this.x = x;
			this.y = y;
			this.dist = dist;
		}

		@Override
		public int compareTo(Coord o) {
			return this.dist - o.dist;
		}
		
	}
	
	static Coord slide(int x, int y, int dir) {
		int dist = 0;
		int dx = offsets[dir][0];
		int dy = offsets[dir][1];
		int nx = x + dx, ny = y + dy;
		while(nx >= 0 && ny >= 0 && nx < H && ny < W) {
			if(graph[nx][ny] == 'H') return null;
			if(graph[nx][ny] == 'R') return new Coord(x, y, dist);
			x = nx; y = ny;
			nx += dx; ny += dy;
			if(graph[x][y] == 'E') return new Coord(x, y, dist);
			dist += graph[x][y] - '0';
		}
		return null;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(input.readLine());
		W = Integer.parseInt(tokens.nextToken());
		H = Integer.parseInt(tokens.nextToken());
		graph = new char[H][];
		int x=0, y=0, endx=0, endy=0;
		for(int i = 0; i < H; i++) {
			graph[i] = input.readLine().toCharArray();
			for(int j = 0; j < W; j++) {
				if(graph[i][j] == 'T') {
					x = i; y = j;
					graph[i][j] = '0';
				}
				if(graph[i][j] == 'E') {
					endx = i;
					endy = j;
				}
			}
		}
		
		int[][] distances = new int[H][W];
		for(int i = 0; i < H; i++) {
			for(int j = 0; j < W; j++) {
				distances[i][j] = Integer.MAX_VALUE;
			}
		}
		PriorityQueue<Coord> pq = new PriorityQueue<>();
		distances[x][y] = 0;
		pq.offer(new Coord(x, y, 0));
		while(!pq.isEmpty()) {
			Coord cur = pq.poll();
			if(distances[cur.x][cur.y] < cur.dist) continue;
			for(int i = 0; i < 4; i++) {
				Coord next = slide(cur.x, cur.y, i);
				if(next == null) continue;
				next.dist += cur.dist;
				if(next.dist < distances[next.x][next.y]) {

					distances[next.x][next.y] = next.dist;
					pq.offer(next);
				}
			}
		}
		if(distances[endx][endy] == Integer.MAX_VALUE) {
			System.out.println(-1);
		} else {
			System.out.println(distances[endx][endy]);
		}
	}

}