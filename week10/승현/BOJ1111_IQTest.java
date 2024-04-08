import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1111_IQTest {
	static int N;
	static int[] arr;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		if (N == 1) {
			System.out.println('A');
			return;
		}
		else if (N == 2) {
			caseTwo();
		}
		else {
			caseOverThree();
		}
	}
	
	public static void caseTwo() {
		if (arr[0] == arr[1]) {
			System.out.println(arr[0]);
		}
		else {
			System.out.println('A');
		}
	}
	
	public static void caseOverThree() {
		if (arr[1] - arr[0] != 0) {
			int a = (arr[2] - arr[1]) / (arr[1] - arr[0]);
			int b = arr[1] - a * arr[0];
			for (int i = 0; i < N-1; i++) {
				if (a * arr[i] + b != arr[i+1]) {
					System.out.println('B');
					return;
				}
			}
			System.out.println(arr[N-1] * a + b);
		}
		else {
			for (int i = 0; i < N; i++) {
				if (arr[i] != arr[0]) {
					System.out.println('B');
					return;
				}
			}
			System.out.println(arr[0]);
		}
	}

}
