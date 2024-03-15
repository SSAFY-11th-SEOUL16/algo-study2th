import java.io.*;
import java.util.*;

public class SW4014_활주로건설 {
	
	/*
	 * - 155ms
	 * 
	 * - 각 row column에 대해 가능 여부 완탐
	 */
	static int n,x;
	static int[][] map;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input4014.txt"));
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int tc = Integer.parseInt(st.nextToken());
		
		for(int t=1; t<=tc; t++) {
			sb.append("#").append(t).append(" ");
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			x = Integer.parseInt(st.nextToken());
			map = new int[n][n];
			int cnt = 0 ;
			
			for(int i=0; i<n; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<n ;j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			nextLine : for(int i=0; i<n; i++) {
				// map[i] 가로로 
				int cur=map[i][0];
				int strike = 0;
				
				for(int j=0; j<n; j++) {
					if(map[i][j]==cur) {
						strike++;
					}
					else if(map[i][j] == cur+1) {
						if(strike>=x) {	// 올라가는거 놓을 수 있는 상태
							cur++;
							strike = 1;
						}
						else continue nextLine;
					}
					else if(map[i][j] == cur-1) {	// 내려가자
						strike = 1;
						while(++j!=n && map[i][j] == cur-1 && strike<x) {
							strike++;
						}
						if(strike!=x) continue nextLine; 
						else {
							j--;
							strike=0;
							cur--;
						}
					}
					else continue nextLine;
				}
				cnt++;
				
			}
			
			nextLine : for(int j=0; j<n; j++) {
				// map[i] 가로로 
				int cur=map[0][j];
				int strike = 0;
				
				for(int i=0; i<n; i++) {
					if(map[i][j]==cur) {
						strike++; continue;
					}
					else if(map[i][j] == cur+1) {
						if(strike>=x) {	// 올라가는거 놓을 수 있는 상태
							cur++;
							strike = 1;
						}
						else continue nextLine;
					}
					else if(map[i][j] == cur-1) {	// 내려가자
						strike = 1;
						while(++i!=n && map[i][j] == cur-1 && strike<x) {
							strike++;
						}
						if(strike!=x) continue nextLine; 
						else {
							i--;
							strike=0;
							cur--;
						}
					}
					else continue nextLine;
				}
				cnt++;
				
			}
			
			
			sb.append(cnt).append("\n");
		}
		
		System.out.println(sb);
	}

}
