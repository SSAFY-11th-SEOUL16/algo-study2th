import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ17220_마약수사대 {			// java8, 76ms
	static int N, M, result;
	static ArrayList<Integer> roots;
	static boolean[] visited;
	static ArrayList<ArrayList<Integer>> tree;
	static ArrayList<ArrayList<Integer>> parents;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		tree = new ArrayList<ArrayList<Integer>>();
		parents = new ArrayList<ArrayList<Integer>>();
		roots = new ArrayList<Integer>();
		
		visited = new boolean[N];
		for (int i = 0; i < N; i++) {
			tree.add(new ArrayList<Integer>());
			parents.add(new ArrayList<Integer>());
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			char a = st.nextToken().charAt(0);
			char b = st.nextToken().charAt(0);
			tree.get(a-'A').add(b-'A');
			parents.get(b-'A').add(a-'A');
		}
		for (int i = 0; i < N; i++) {
			if(parents.get(i).isEmpty() && !tree.get(i).isEmpty())
				roots.add(i);
		}
		st = new StringTokenizer(br.readLine());
		int delCnt = Integer.parseInt(st.nextToken());
		for (int i = 0; i < delCnt; i++) {
			char a = st.nextToken().charAt(0);
			visited[a - 'A'] = true;
		}

		for (int r : roots) {
			if (!visited[r])
				bfs(r);
		}
		
		System.out.println(result);
	}
	public static void bfs(int start) {
		Queue<Integer> q = new LinkedList<>();
		q.offer(start);
		visited[start] = true;
		while(!q.isEmpty()) {
			int node = q.poll();
			for (int next : tree.get(node)) {
				if (visited[next])
					continue;
				q.offer(next);
				visited[next] = true;
				result++;
			}
		}
	}
}
