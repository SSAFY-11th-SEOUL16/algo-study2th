import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ10819_차이를최대로 {
	static int N;
	static int result;
	static int[] arr;
	static int[] nums;
	static boolean[] visited; 
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		nums = new int[N];
		visited = new boolean[N];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		result = 0;
		dfs(0);
		System.out.println(result);
	}
	
	public static void dfs(int depth) {
		int sum = 0;
		if (depth == N) {
			for (int i = 0; i < N-1; i++) {
				sum += Math.abs(nums[i] - nums[i+1]);
			}
			result = Math.max(result, sum);
			return;
		}
		for (int i = 0; i < N; i++) {
			if (visited[i])
				continue;
			visited[i] = true;
			nums[depth] = arr[i];
			dfs(depth+1);
			visited[i] = false;
		}
	}
}