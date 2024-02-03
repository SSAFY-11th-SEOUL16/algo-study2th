import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class BOJ14225_부분수열의합 {
	static int N;
	static int d;
	static HashSet<Integer> s;
	static boolean[] visited;
	static int[] S;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		s = new HashSet<>();
		int result = 0;

		N = Integer.parseInt(br.readLine());
		S = new int[N];
		visited = new boolean[N];
		st = new StringTokenizer(br.readLine());

		for (int i = 0; i < N; i++) {
			S[i] = Integer.parseInt(st.nextToken());
			result += S[i];
		}
		result += 1;

		dfs(0);
		
		for (int i = 1; i < result; i++) {
			if (!s.contains(i)) {
				System.out.println(i);
				return;
			}
		}
		System.out.println(result);
	}

	public static void dfs(int depth) {
		if (depth == N) {
			int sum = 0;
			for (int i = 0; i < N; i++) {
				if (visited[i])
					sum += S[i];
			}
			s.add(sum);
			return;
		}
		visited[depth] = false;
		dfs(depth + 1);
		visited[depth] = true;
		dfs(depth + 1);
	}
}
