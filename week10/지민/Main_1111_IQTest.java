package ps;


/**
 * 시간: 236ms
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_1111_IQTest {
	static int N;
	static int[] arr;
	
	private static String solve() {
		if(N == 1) return "A";
		else if(N == 2) {
			return (arr[0] == arr[1]) ? String.valueOf(arr[0]) : "A";
		}
		
		int a = (arr[1] - arr[0]) == 0 ? 0 : (arr[2] - arr[1]) / (arr[1] - arr[0]);
		int b = arr[1] - arr[0] * a;
		
		for(int i = 2; i < N; i++) {
			if(arr[i] != a * arr[i - 1] + b) return "B";
		}
		int res = arr[N - 1] * a + b;
		return String.valueOf(res);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());

		arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		System.out.println(solve());
	}
}
