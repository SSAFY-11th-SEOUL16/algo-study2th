import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ9934_완전이진트리 {
	static int k;
	static int root;
	static int[] seq;
	static boolean[] visited;
	static ArrayList<Integer>[] result;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		k = Integer.parseInt(br.readLine());
		root = (int)((Math.pow(2, k)-1)/2);
		seq = new int[(int) (Math.pow(2, k)-1)];
		result = new ArrayList[k+1];
		for (int i = 0; i < k+1; i++)
			result[i] = new ArrayList<Integer>();
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < (int) (Math.pow(2, k)-1); i++) {
			seq[i] = Integer.parseInt(st.nextToken());
		}
		dfs(0, root);
		for (int i = 0; i < k; i++) {
			for (int j = result[i].size()-1; j >= 0; j--)
				System.out.print(result[i].get(j) + " ");
			System.out.println();
		}
	}
	
	public static void dfs(int depth, int idx) {
		if (depth == k) {
			return;
		}
		result[depth].add(seq[idx]);
		int diff = (int)(Math.pow(2, k-depth-2));
		dfs(depth+1, idx+diff);
		dfs(depth+1, idx-diff);
	}
}
