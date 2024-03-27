import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1068_트리 {
	static int N; // 노드 개수
	static ArrayList<Integer>[] treeInfo;
	static boolean[] visited;
	static int leafCnt;
	static int rootIdx;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		treeInfo = new ArrayList[N];
		visited = new boolean[N];
		for (int i = 0; i < N; i++)
			treeInfo[i] = new ArrayList<>();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int pNode = Integer.parseInt(st.nextToken());
			if (pNode == -1) {
				rootIdx = i;
				continue;
			}
			treeInfo[pNode].add(i);
		}
		int del = Integer.parseInt(br.readLine());
		if (del == rootIdx) {		// 루트노드 지운 경우 처리
			System.out.println(0);
			return;
		}
		visited[del] = true;
		bfs(rootIdx);
		System.out.println(leafCnt);
	}
	
	public static void bfs(int start) {
		Queue<Integer> q = new LinkedList<>();
		q.offer(start);
		while (!q.isEmpty()) {
			int node = q.poll();
			int childCount = 0;
			for (int cn : treeInfo[node]) {
				if (visited[cn])
					continue;
				visited[cn] = true;
				childCount++;
				q.offer(cn);
			}
			if (childCount == 0)
				leafCnt++;
		}
	}
}
