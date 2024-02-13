import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ25691_k개트리에서사과최대로수확하기 {
	static int n,k,max=0;
	static int[] apple;
	static int[] visit;
	static ArrayList<Integer>[] subtree;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		subtree = new ArrayList[n];
		for(int i=0; i<n; i++)
			subtree[i] = new ArrayList<Integer>();
		apple = new int[n];
		visit = new int[k];

		for(int i=1; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			int parent = Integer.parseInt(st.nextToken());
			int cur = Integer.parseInt(st.nextToken());
			subtree[parent].add(cur);
			subtree[cur].add(parent);
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++) 
			apple[i] = Integer.parseInt(st.nextToken());
		
		visit[0]=0;
		select(1);
		
		
		System.out.println(max);
	}
	static void select(int cnt) { 
		if(cnt==k) {
			if(connect()) {
				int cur = appleCount();
				if(max<cur)
					max=cur;
			}
			return;
		}
		
		for(int i=visit[cnt-1]+1; i<n; i++) {
			visit[cnt]=i;
			select(cnt+1);
		}
	}
	static boolean connect() {
		int connection=0;
		for(int i=0; i<k; i++) {
			for(int j=i+1; j<k; j++) {
				if(subtree[visit[i]].contains(visit[j]))
						connection++;
			}
		}
		if(connection==k-1)
			return true;
		return false;
	}
	static int appleCount() {
		int sum=0;
		for(int idx : visit)
			sum+=apple[idx];
//		System.out.println(Arrays.toString(visit)+" "+sum);
		return sum;
	}
}
