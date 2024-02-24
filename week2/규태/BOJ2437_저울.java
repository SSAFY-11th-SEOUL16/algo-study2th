package daily;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ2437_저울 {
	static int n,weight[];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		weight = new int[n];
		for(int i=0; i<n; i++) {
			weight[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(weight);
	
		int sum=0;
		for(int x : weight) {
			if(sum+1<x) break;
			else sum+=x;
		}
		System.out.println(sum+1);
	}
}
