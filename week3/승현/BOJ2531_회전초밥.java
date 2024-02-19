import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2531_회전초밥 { 		// java8, 424ms
	static int N, d, k, c;
	static int[] belt;
	static boolean[] picked;
	static int result;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		belt = new int[N];
		for (int i = 0; i < N; i++) {
			belt[i] = Integer.parseInt(br.readLine());
		}
		for (int i = 0; i < N; i++) {
			int value = getValue(i);
			if (value > result)
				result = value;
		}
		System.out.println(result);
	}
	
	public static int getValue(int start) {
		picked = new boolean[d+1];
		int cnt = 0;
		for (int i = start; i < start+k; i++) {
			if (i < N)
				picked[belt[i]] = true;
			else
				picked[belt[i-N]] = true;
		}
		for (int i = 1; i <= d; i++) {
			if (picked[i])
				cnt++;
		}
		if (!picked[c])
			cnt++;
		
		return cnt;
	}
}
