package algostudy.week9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * 시간: 868ms
 */
public class Main_12893_적의적 {
	static int N; // 주위 사람 수
	static int M; // 적대관계 수
	static List<List<Integer>> graph;
	static int[] energy;
	
	public static boolean dfs(int curr, int x) {
		energy[curr] = x;
		for(int next : graph.get(curr)) {
			if(energy[next] == 0 && !dfs(next, 3 - x)) return false;
			else if(energy[curr] == energy[next]) return false;
		}
		return true;
	}
	
	private static int solve() {
		energy = new int[N + 1];
		for(int i = 1; i <= N; i++) {
			if(energy[i] == 0 && !dfs(i, 1)) return 0;
		}
		return 1;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		graph = new ArrayList<>();
		for(int i = 0; i <= N; i++) graph.add(new ArrayList<>());
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			graph.get(u).add(v);
			graph.get(v).add(u);
		}
		System.out.println(solve());
	}
}
