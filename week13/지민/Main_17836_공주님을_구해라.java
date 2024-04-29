package ps;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 시간: 140ms
 */
public class Main_17836_공주님을_구해라 {
	static class Node {
		int y, x;
		int gram;
		public Node(int y, int x, int gram) {
			super();
			this.y = y;
			this.x = x;
			this.gram = gram;
		}
		
	}
	static int N;
	static int M;
	static int T;
	static int[][] map;
	static final int[][] dir = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
	 
	private static boolean inRange(int y, int x) {
		return 0 <= y && y < N && 0 <= x && x < M;
	}
	
	private static int bfs() {
		Queue<Node> queue = new LinkedList<>();
		int[][][] dist = new int[N][M][2];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				dist[i][j][0] = dist[i][j][1] = -1;
			}
		}
		
		queue.add(new Node(0, 0, 0));
		dist[0][0][0] = 0;
		
		while(!queue.isEmpty()) {
			Node here = queue.poll(); 
			
			if(here.y == N - 1 && here.x == M - 1) return dist[here.y][here.x][here.gram];
			
			for(int i = 0; i < 4; i++) {
				int ny = here.y + dir[i][0];
				int nx = here.x + dir[i][1];
				if(!inRange(ny, nx)) continue;
				
				int gram = (map[ny][nx] == 2) ? 1 : here.gram; 
			
				if(gram == 0 && map[ny][nx] == 1) continue;
				
				if(dist[ny][nx][gram] == -1) {
					queue.add(new Node(ny, nx, gram));
					dist[ny][nx][gram] = dist[here.y][here.x][here.gram] + 1;
				}
			}
		}
		
		return T + 1;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		for(int i = 0; i < N; i++) {
			 st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int time = bfs();
		if(time <= T) System.out.println(time);
		else System.out.println("Fail");
	}
}
