import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ10819_차이를최대로 {
	
	static int n;
	static int[] numbers;
	static boolean[] isSelected;
	static int[] arr;
	static int result = 0;
	
	static int calculate(int[] arr) {
		
		int result = 0;
		for(int i=0; i<n-1; i++) {
			result += Math.abs(arr[i] - arr[i+1]);
		}
		
		return result;
	}
	
	static void permutation(int depth) {
		if(depth == n) {
			result = Math.max(result, calculate(numbers));
			return;
		}
		
		for(int i=0; i<n; i++) {
			if (isSelected[i]) continue;
			isSelected[i] = true;
			numbers[depth] = arr[i];
			permutation(depth+1);
			isSelected[i] = false;
		}
		
	}
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		arr = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		numbers = new int[n];
		isSelected = new boolean[n];
		
		permutation(0);
		
		System.out.println(result);
	}

}
