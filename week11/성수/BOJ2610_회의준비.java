import java.io.*;
import java.util.*;

public class BOJ2610_회의준비 {

	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(input.readLine());
		int M = Integer.parseInt(input.readLine());
		
		int[][] floyd = new int[N + 1][N + 1];
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) {
				floyd[i][j] = 101;
			}
			floyd[i][i] = 0;
		}
		for(int i = 0; i < M; i++) {
			StringTokenizer tokens = new StringTokenizer(input.readLine());
			int u = Integer.parseInt(tokens.nextToken());
			int v = Integer.parseInt(tokens.nextToken());
			floyd[u][v] = 1;
			floyd[v][u] = 1;
		}
		for(int k = 1; k <= N; k++) {
			for(int i = 1; i <= N; i++) {
				for(int j = 1; j <= N; j++) {
					floyd[i][j] = Math.min(floyd[i][k] + floyd[k][j], floyd[i][j]);
				}
			}
		}
		int[] totals = new int[N + 1];
		boolean[] visited = new boolean[N + 1];
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) {
				if(floyd[i][j] != 101) totals[i] = Math.max(floyd[i][j], totals[i]);
			}
		}
		ArrayList<Integer> indicies = new ArrayList<>();
		for(int i = 1; i <= N; i++) {
			if(!visited[i]) {
				int idx = i;
				Queue<Integer> queue = new ArrayDeque<>();
				queue.offer(i);
				visited[i] = true;
				while(!queue.isEmpty()) {
					int cur = queue.poll();
					for(int j = 1; j <= N; j++) {
						if(!visited[j] && floyd[cur][j] != 101) {
							queue.offer(j);
							visited[j] = true;
							if(totals[idx] > totals[j]) idx = j;
						}
					}
				}
				indicies.add(idx);
			}
		}
		Collections.sort(indicies);
		System.out.println(indicies.size());
		for(int idx : indicies) System.out.println(idx);
	}

}
