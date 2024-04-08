// 80ms
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(input.readLine());
		StringTokenizer tokens = new StringTokenizer(input.readLine());
		int[] arr = new int[N];
		for(int i = 0; i < N; i++) arr[i] = Integer.parseInt(tokens.nextToken());
		
		if(N == 1) System.out.println("A");
		else if(N == 2) System.out.println(arr[0]==arr[1] ? arr[0] : "A");
		else {
			// x1 * a + b = x2
			// x2 * a + b = x3
			int a = arr[0]-arr[1]==0 ? 0 : (arr[2] - arr[1]) / (arr[1] - arr[0]);
			int b = a == 0 ? arr[1] : arr[1] - arr[0] * a;
			boolean flag = true;
			for(int i = 1; i < N; i++) {
				if(arr[i] != arr[i - 1] * a + b) {
					flag = false;
					break;
				}
			}
			if(flag) System.out.println(arr[N - 1] * a + b);
			else System.out.println("B");
		}
	}
}