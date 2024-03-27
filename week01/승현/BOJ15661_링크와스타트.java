import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ15661_링크와스타트 {
	static int N;
	static int result = Integer.MAX_VALUE;
	static int graph[][];
	static boolean[] isSelected;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		graph = new int[N+1][N+1];
		isSelected = new boolean[N+1];
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		subsets(1);
		System.out.println(result);
	}
	public static void subsets(int depth) {
		if (depth == N) {
			int diff = calDiff();
			result = Math.min(result, diff);
			return;
		}
		isSelected[depth] = false;
		subsets(depth+1);
		isSelected[depth] = true;
		subsets(depth+1);
	}
	
	public static int calDiff() {
		Queue<Integer> teamTrue = new LinkedList<>();
		Queue<Integer> teamFalse = new LinkedList<>();
		int ttScore = 0;
		int tfScore = 0;
		
		for (int i = 1; i <= N; i++) {
			if (isSelected[i]) {
				teamTrue.offer(i);
			}
			else {
				teamFalse.offer(i);
			}
		}
		
		if (teamTrue.isEmpty() || teamFalse.isEmpty()) {
			return Integer.MAX_VALUE;
		}
		while (!teamTrue.isEmpty()) {
			int idx = teamTrue.poll();
			for(int i = 1; i <= N; i++) {
				if (idx != i && isSelected[i])
					ttScore += graph[idx][i];
			}
		}
		while (!teamFalse.isEmpty()) {
			int idx = teamFalse.poll();
			for(int i = 1; i <= N; i++) {
				if (idx != i && !isSelected[i])
					tfScore += graph[idx][i];
			}
		}

		return Math.abs(ttScore - tfScore);
	}
}