// 648ms

import java.io.*;
import java.util.*;

public class BOJ17829_222풀링 {

	static int[][] arr;
	
	public static int pool2(int x, int y, int size) {
		if(size == 1) return arr[x][y];
		int newSize = size / 2;
		int max1 = -10_000;
		int max2 = -10_000;
		for(int i = x; i < x + size; i += newSize) {
			for(int j = y; j < y + size; j += newSize) {
				int tmp = pool2(i, j, newSize);
				if(tmp > max2) {
					max2 = tmp;
					if(tmp > max1) {
						max2 = max1;
						max1 = tmp;
					}
				}
			}
		}
		return max2;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(input.readLine());
		arr = new int[N][N];
		for(int i = 0; i < N; i++) {
			StringTokenizer tokens = new StringTokenizer(input.readLine());
			for(int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(tokens.nextToken());
			}
		}
		System.out.println(pool2(0, 0, N));
	}

}
