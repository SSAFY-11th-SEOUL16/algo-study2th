package daily;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ17829_222풀링 {
	static int n,ans;
	static int[][] cnn;
	static int[][] twobytwo = {{0,0},{0,1},{1,0},{1,1}};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		cnn = new int[n][n];

		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<n; j++) {
				cnn[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		pooling(n);
		System.out.println(ans);
	}
	static void pooling(int n) {
		if(n==2) {
			ans=check(0,0);
			return;
		}
		
		int[][] minicnn = new int[n/2][n/2];
		for(int i=0; i<n/2; i++) {
			for(int j=0; j<n/2; j++) {
				minicnn[i][j] = check(2*i,2*j);
			}
		}
		
		cnn = minicnn;
		pooling(n/2);
	}
	static int check(int r,int c) {//2x2에서 찾기
		ArrayList<Integer> list = new ArrayList<Integer>();
		for(int[] i : twobytwo)
			list.add(cnn[r+i[0]][c+i[1]]);
		Collections.sort(list);
		return list.get(2); //작은순서로 sort
	}
}
