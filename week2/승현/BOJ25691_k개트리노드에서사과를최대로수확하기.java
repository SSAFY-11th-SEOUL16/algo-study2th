import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ25691_k개트리노드에서사과를최대로수확하기 {
	static int N; // 노드의 수
	static int K; // 방문 노드 최대개수
	static ArrayList<Integer>[] tree;
	static int[] apple;
	static int[] pNode;
	static boolean[] visited;
	static int result;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		tree = new ArrayList[N];
		apple = new int[N];
		visited = new boolean[N];
		pNode = new int[N];
		for (int i = 0; i < N; i++) {
			tree[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int p = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			tree[p].add(c);
			pNode[c] = p;
		}
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			apple[i] = Integer.parseInt(st.nextToken());
		}

		visited[0] = true;
		subsets(1);
		System.out.println(result);
	}
	
	public static void subsets(int depth) {
		if (depth == N) {
			int cnt = 0;
			for (int i = 0; i < N; i++)
				if (visited[i])
					cnt++;
			if (cnt == K) {
				isLinked();
			}
			return;
		}
		visited[depth] = false;
		subsets(depth+1);
		visited[depth] = true;
		subsets(depth+1);
	}
	
	public static void isLinked() {
		int cnt = 1;
		int sum = apple[0];
		boolean[] checked = new boolean[N];
		Queue<Integer> q = new LinkedList<>();
		q.offer(0);
		checked[0] = true;
		System.out.println(Arrays.toString(visited));
		while(!q.isEmpty()) {
			int node = q.poll();
			for (int c : tree[node]) {
				if (!visited[c] || checked[c])
					continue;
				q.offer(c);
				System.out.print(c + " ");
				cnt++;
				checked[c] = true;
				sum += apple[c];
			}
		}
		System.out.println();
		if (cnt == K) {
			result = Math.max(result, sum);
		}
	}
}
