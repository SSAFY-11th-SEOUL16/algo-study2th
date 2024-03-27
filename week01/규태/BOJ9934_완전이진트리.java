package daily;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ9934_완전이진트리 {
	static int n, tree[];
	static boolean used[];
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		tree = new int[(int)Math.pow(2, n)];
		used = new boolean[(int)Math.pow(2, n)];
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<tree.length; i++)
			tree[i]=Integer.parseInt(st.nextToken());
		
		showTree(1);
		sb.setLength(sb.length()-1);
		System.out.println(sb);
	}
	static void showTree(int idx) {
		if(idx>=tree.length)
			return;
		showTree(idx*2);
		if(sb.length()!=0)
			sb.append('\n');
		for(int i=idx; i<tree.length; i=i+idx) {
			if(!used[i]) {
				used[i]=true;
				sb.append(tree[i]).append(' ');
			}
		}
		
	}
}
