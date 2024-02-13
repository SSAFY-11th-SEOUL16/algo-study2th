import java.io.*;
import java.util.*;

public class BOJ2437_저울 {

	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(input.readLine());
		int arr[] = new int[N];
		StringTokenizer tokenizer = new StringTokenizer(input.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(tokenizer.nextToken());
		}

		Arrays.sort(arr);
		int maxWeight = 0;
		for(int i = 0; i < N; i++) {
			if(maxWeight + 1 < arr[i]) break;
			maxWeight += arr[i];
		}
		System.out.println(maxWeight + 1);
	}

}
