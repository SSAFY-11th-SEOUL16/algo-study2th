import java.io.*;
import java.util.*;

public class BOJ15684_사다리조작 {
	
	static int N, H, M;
	static boolean[] ladders;
	static int[] results;
	static int answer = 4;
	
	static boolean check() {
		for(int i = 0; i < N; i++) {
			if(results[i] != i) return false;
		}
		return true;
	}
	
	static void swap(int x, int y) {
		int tmp = results[x];
		results[x] = results[y];
		results[y] = tmp;
	}
	
	static void print() {
		for(int i = 0; i < N; i++ ) {
			System.out.print(i + " ");
		}
		System.out.println();
		for(int i = 0; i < H; i++) {
			for(int j = 0; j < N - 1; j++) {
				if(ladders[i * (N - 1) + j]) {
					System.out.print("|-");
				} else {
					System.out.print("| ");
				}
			}
			System.out.println("|");
		}
		for(int i = 0; i < N; i++ ) {
			System.out.print(results[i] + " ");
		}
		System.out.println("end \n");
	}
	
	static void backtrack(int depth, int start) {
		if(start >= H * (N - 1)) {
			if(check()) {
				answer = depth < answer ? depth : answer;
			}
			return;
		}
		if(ladders[start]) {
			int tmp = start % (N - 1);
			swap(tmp, tmp + 1);
			backtrack(depth, start + 1);
			swap(tmp, tmp + 1);
			return;
		}
		boolean flag = depth < 3;
		if(start % (N - 1) != 0 && ladders[start - 1]) flag = false;
		if(start % (N - 1) != N - 2 && ladders[start + 1]) flag = false;
		if(flag) {
			int tmp = start % (N - 1);
			swap(tmp, tmp + 1);
			ladders[start] = true;
			backtrack(depth + 1, start + 1);
			swap(tmp, tmp + 1);
			ladders[start] = false;
		}
		backtrack(depth, start + 1);
	}
	

	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(input.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		H = Integer.parseInt(tokens.nextToken());
		ladders = new boolean[H * (N - 1)];
		for(int i = 0; i < M; i++) {
			tokens = new StringTokenizer(input.readLine());
			int a = Integer.parseInt(tokens.nextToken());
			int b = Integer.parseInt(tokens.nextToken());
			ladders[(a - 1) * (N - 1) + b - 1] = true;
		}
		results = new int[N];
		for(int i = 0; i < N; i++) {
			results[i] = i;
		}
		backtrack(0, 0);
		if(answer == 4) answer = -1;
		System.out.println(answer);
	}
}
