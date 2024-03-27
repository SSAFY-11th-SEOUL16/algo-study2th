// 204ms

import java.io.*;
import java.util.*;

public class BOJ2531_회전초밥 {

	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		int N, d, k, c;
		StringTokenizer tokens = new StringTokenizer(input.readLine());
		N = Integer.parseInt(tokens.nextToken());
		d = Integer.parseInt(tokens.nextToken());
		k = Integer.parseInt(tokens.nextToken());
		c = Integer.parseInt(tokens.nextToken());
		int[] arr = new int[N];
		int[] counter = new int[d + 1];
		int answer = 0;
		int count = 0;
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(input.readLine());
		}
		for(int i = 0; i < k - 1; i++) {
			if(arr[i] != c) {
				counter[arr[i]] += 1;
				if(counter[arr[i]] == 1) count += 1;
			}
		}
		for(int i = k - 1; i < N + k - 1; i++) {
			int right = i % N;
			int left = i - (k - 1);
			if(arr[right] != c) {
				counter[arr[right]] += 1;
				if(counter[arr[right]] == 1) {
					count += 1;
				}
			}
			if(count > answer) answer = count;
			if(arr[left] != c) {
				counter[arr[left]] -= 1;
				if(counter[arr[left]] == 0) {
					count -= 1;
				}
			}
		}
		System.out.println(answer + 1);
	}
}
