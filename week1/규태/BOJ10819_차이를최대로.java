package daily;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ10819_차이를최대로 {
	static int n, num[];
	static boolean used[];
	static int max = Integer.MIN_VALUE;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		num = new int[n];
		used = new boolean[n];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++)
			num[i] = Integer.parseInt(st.nextToken());
		
		for(int i=0; i<n; i++) {
			used[i]=true;
			maxDiff(1,0,num[i]);
			used[i]=false;
		}
		System.out.println(max);
	}
	static void maxDiff(int cnt, int sum, int former) {
		if(cnt==n) {
			if(max<sum)
				max=sum;
			return;
		}
		for(int i=0; i<n; i++) {
			if(used[i]) continue;
			used[i]=true;
			maxDiff(cnt+1, sum+Math.abs(former-num[i]), num[i]);
			used[i]=false;
		}
	}
}
