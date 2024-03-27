import java.io.*;
import java.util.*;

public class SW2112_보호필름 {
	/*
	 *  - 700ms
	 * 
	 *  - i개 투약에 대해서 dCi 조합생성 
	 *  
	 *  - 선택된 레이어 i개에 0을 넣을지 1을 넣을지 2*i 경우의수에 대해 필름 테스트
	 * 
	 * 	- 0과 1로만 이루어진 배열을 미리 만들어놓고 레퍼런스하여 배열 동적할당시간 줄임
	 */
	
	static int d,w,k;
	static int[][] film, copy;
	static int[] zeros;
	static int[] ones;
	
	static boolean cal(int[][] f) {
		nextCol : for(int c=0; c<w; c++) {
			int flag = f[0][c];
			int strike=0;
			int max=0;
			
			for(int r=0; r<d; r++) {
				if (f[r][c]==flag) {
					strike++;
					if(strike==k) continue nextCol;
				}
				else {
					max = Math.max(strike, max);
					flag ^= 1;
					strike= 1;
				}
			}
			
			return false;
		}
		
		return true;
	}
	
	static boolean test(int[] arr, int size) {
		for(int i=0; i<(1<<size); i++) {	// bit가 1이면 1 0이면 0 투약
			int ptr = 0;
			
			for(int j=0; j<d; j++) {
				if(ptr<size && j == arr[ptr]) {
					if((i&(1<<ptr)) > 0) {
						copy[j] = ones;
					}
					else copy[j] = zeros;

					ptr++;
				}
				else {
					copy[j] = film[j];
				}
			}
			
			if(cal(copy)) return true;
		}
		
		return false;
	}
	
	static boolean comb(int size, int maxSize, int idx, int[] arr) {
		if(size == maxSize) {
			if(test(arr,size)) return true;
		}
		else {
			for(int i=idx; i<d; i++) {
				arr[size] = i;
				if(comb(size+1, maxSize, i+1, arr)) return true;
			}
		}
		
		return false;
	}
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input2112.txt"));
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int tc = Integer.parseInt(st.nextToken());
		
		nextTC: for(int t=1; t<=tc; t++) {
			sb.append("#").append(t).append(" ");
			st = new StringTokenizer(br.readLine());
			d = Integer.parseInt(st.nextToken());	// depth
			w = Integer.parseInt(st.nextToken());	// width
			k = Integer.parseInt(st.nextToken());	// k
			
			
			film = new int[d][w];
			copy = new int[d][w];
			
			zeros = new int[w];
			Arrays.fill(zeros, 0);
			ones = new int[w];
			Arrays.fill(ones, 1);
			
			for(int i=0; i<d; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<w; j++) {
					film[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			if(cal(film)) {
				sb.append(0).append("\n");
				continue nextTC;
			}
			
			for(int i=1; i<k ;i++) {
				if(comb(0,i,0,new int[i])) {
					sb.append(i).append("\n");
					continue nextTC;
				}
			}
			
			sb.append(k).append("\n");
		}
		
		System.out.println(sb);
	}

}
