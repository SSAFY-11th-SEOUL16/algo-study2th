package daily;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14225_부분수열의합 {
	static int n, input[], subset[];
	static boolean sumTemp[] = new boolean[20*100000+1];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		input = new int[n];
		subset = new int[n];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++)
			input[i]=Integer.parseInt(st.nextToken());
		
		sumSet(0,0);
		
		for(int i=1; i<sumTemp.length; i++)
			if(!sumTemp[i]) {
				System.out.println(i);
				break;
			}
		
	}
	static void sumSet(int cnt, int sum) {
		if(cnt==n) {
			sumTemp[sum]=true;
			return;
		}
		sumSet(cnt+1,sum+input[cnt]);
		sumSet(cnt+1,sum);
	}
}
