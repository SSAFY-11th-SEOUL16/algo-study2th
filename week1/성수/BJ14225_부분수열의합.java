import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;


public class BJ14225_부분수열의합 {
	static int MAX = 1_048_577;
	static int[] visited = new int[MAX];

	static int[] arr;

	public static void subset(int depth, int sum) {
		if(depth==-1) {
			if(sum >= MAX) return;
			visited[sum] = 1;
			return;
		}
		subset(depth - 1, sum + arr[depth]);
		subset(depth - 1, sum);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(input.readLine());
		StringTokenizer tokenizer = new StringTokenizer(input.readLine());
		
		arr = new int[N];
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(tokenizer.nextToken());
		}
		subset(N - 1, 0);
		for(int i = 1; i < MAX; i++) {
			if(visited[i] == 0) {
				System.out.println(i);
				break;
			}
		}
		// 그리디 방식
		Arrays.sort(arr);
		int sum = 0;
		for(int x: arr) {
			
			if(sum + 1 < x) {
				break;
			}
			else {
				sum += x;
			}
		}
		System.out.println(sum + 1);
		
	}

}
