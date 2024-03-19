import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ2533_사회망서비스 {
	/*
	 * 메모리 446332KB 시간 2608ms
	 * Tree DP 사용
	 * 둘 이상과 연결되어 있는 아무 노드를 잡아 root로 지정하여 tree를 구성하고 
	 * 재귀적으로 하위 트리의 minimum 구성을 고려함
	 * 다만 다른 유사한 트리 dp 문제들을 풀어보니 아무 노드를 root로 지정해서 풀어도 상관없는 것으로 보이며
	 * 메모리 사용량을 절반이상 줄이고 시간도 줄일수 있을듯
	 */
	static int n,dp[][];
	static ArrayList<Integer>[] list;
	static ArrayList<Integer>[] tree;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		dp = new int[2][n+1];
		list = new ArrayList[n+1];
		tree = new ArrayList[n+1];
		for(int i=1; i<=n; i++) {
			list[i] = new ArrayList<>();
			tree[i] = new ArrayList<>();
		}
		Arrays.fill(dp[0], -1);
		Arrays.fill(dp[1], -1);

		for(int i=1; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			list[a].add(b);	list[b].add(a);
		}
		if(n==2)
			System.out.println(1);
		else {
			int root=-1;
			for(int i=1; i<=n; i++) { // leaf아닌 노드 아무거나 root 지정
				if(list[i].size()==1) continue;
				root=i; break;
			}
			setTree(root,-1);
			int ans1 = filldp(0,root);
			int ans2 = filldp(1,root);
			System.out.println(Math.min(ans1, ans2));
		}
	}
	static int filldp(int choose, int cur) {
//		System.out.println(choose+" "+cur);
		if(tree[cur].isEmpty()) return choose;
		if(dp[choose][cur]!=-1) return dp[choose][cur];
		if(choose==0) {
			int sum=0;
			for(int i=0; i<tree[cur].size(); i++)
				sum+=filldp(1,tree[cur].get(i));
			dp[choose][cur]=sum;
		}
		else {
			int sum=0;
			for(int i=0; i<tree[cur].size(); i++)
				sum+=Math.min(filldp(0,tree[cur].get(i)),filldp(1,tree[cur].get(i)));
			dp[choose][cur]=sum+1;
		}
		return dp[choose][cur];
	}
	static void setTree(int cur, int parent) {
		for(int idx:list[cur]) {
			if(idx==parent) continue;
			tree[cur].add(idx);
			setTree(idx,cur);
		}
	}
}
