import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1111_IQTest {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		System.out.println(solve(n, arr));
	}
	
	static String solve(int n, int[] arr) {
		
		if (n >= 3) {
			if (arr[1] == arr[0]) {
				for(int i=0; i<n-1; i++) {
					if (arr[i] != arr[i+1]) return "B";
				}
				return "" + arr[0];
			}
			else {
				int a = (arr[2] - arr[1]) / (arr[1] - arr[0]);
				int b = arr[1] - arr[0] * a;
				
				for(int i=1; i<n; i++) {
					if (arr[i-1] * a + b != arr[i]) return "B";
				}
				return "" + (arr[n-1] *a + b);
			}
		}
		else if (n >= 2) {
			if (arr[0] == arr[1]) return "" + arr[0];
			else return "A";
		}
		else return "A";
	}

}
