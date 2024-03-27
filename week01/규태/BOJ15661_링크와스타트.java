package daily;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
//import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ15661_링크와스타트 {
	static int n,min=Integer.MAX_VALUE; 
	static boolean link[];
	static int chemi[][]; 
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		link = new boolean[n];
		chemi = new int[n][n];
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<n; j++)
				chemi[i][j] = Integer.parseInt(st.nextToken());
		}
		
		divide(1); //첫 사람은 자동 s팀 배정
		System.out.println(min);
	}
	static void divide(int num) {
		if(num==n) {
//			System.out.println(Arrays.toString(link));
			int diff = sumChemi();
			if(min>diff)
				min=diff;
			return;
		}
		
		divide(num+1);
		link[num]=true;
		divide(num+1);
		link[num]=false;
		
	}
	static int sumChemi() { //s팀 0 l팀 1
		int teamS=0;
		int teamL=0;
		for(int i=0; i<n; i++) {
			for(int j=i+1; j<n; j++) {
				if(link[i]==link[j]) { 
					if(link[i])
						teamL+=(chemi[i][j]+chemi[j][i]);
					else
						teamS+=(chemi[i][j]+chemi[j][i]);
				}
			}
		}
//		System.out.println(teamS+"\t"+teamL);
		return Math.abs(teamS-teamL);
	}
}
