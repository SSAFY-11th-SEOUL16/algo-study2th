import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.BufferedReader;

public class BJ10819_차이를최대로 {

	static StringBuilder output = new StringBuilder();
	static int[] arr, selected;
	static int N;
	static int answer = 0;
	
	public static int getDifference() {
		int diff = 0;
		for(int i = 0; i < N - 1; i++) {
			diff += Math.abs(selected[i] - selected[i + 1]);
		}
		return diff;
	}
	
	public static void updateAnswer() {
		int diff = getDifference();
		if(diff > answer) {
			answer = diff;
		}
	}
	
	public static void permutation(int depth, int visited) {
		if(depth == N) {
			updateAnswer();
			return;
		}
		for(int i = 0; i < N; i++) {
			if((visited & (1<<i)) == 0) {
				selected[depth] = arr[i];
				permutation(depth + 1, visited | (1<<i));
			}
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(input.readLine());
		arr = new int[N];
		selected = new int[N];
		StringTokenizer tokenizer = new StringTokenizer(input.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(tokenizer.nextToken());
		}
		permutation(0, 0);
		System.out.println(answer);
	}

}