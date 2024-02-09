import java.io.*;
import java.util.*;

public class BOJ1189_컴백홈 {
	
	static char[][] graph;
	static int R, C, K;
	static int[][] offsets = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}}; 
	static int answer = 0;
	static void dfs(int x, int y, int dist) {
		if(x < 0 || y < 0 || x >= R || y >= C || dist > K || graph[x][y] == 'T') return;
		if(x == 0 && y == C - 1 && dist == K) {
			answer++;
			return;
		}
		graph[x][y] = 'T';
		for(int[] offset: offsets) {
			dfs(x + offset[0], y + offset[1], dist + 1);
		}
		graph[x][y] = '.';
	}

	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokenizer = new StringTokenizer(input.readLine());
		R = Integer.parseInt(tokenizer.nextToken());
		C = Integer.parseInt(tokenizer.nextToken());
		K = Integer.parseInt(tokenizer.nextToken());
		graph = new char[R][C];
		for(int i = 0; i < R; i++) {
			String row = input.readLine();
			for(int j = 0; j < C; j++) {
				graph[i][j] = row.charAt(j);
			}
		}
		dfs(R - 1, 0, 1);
		System.out.println(answer);
	}

}
