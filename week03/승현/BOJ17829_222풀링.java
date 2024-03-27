import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ17829_222풀링 {		// java8, 560ms
	static int N;
	static int[][] arr;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		System.out.println(pulling(0, 0, N));
	}
	public static int pulling(int x, int y, int size) {
		if (size == 2) {
			int[] temp = new int[4];
			int idx = 0;
			for (int i = x; i < x+size; i++) {
				for (int j = y; j < y+size; j++) {
					temp[idx++] = arr[i][j];
				}
			}
			Arrays.sort(temp);
			return temp[2];
		}
		int[] t = new int[4];
		t[0] = pulling(x, y, size/2);
		t[1] = pulling(x, y+size/2, size/2);
		t[2] = pulling(x+size/2, y, size/2);
		t[3] = pulling(x+size/2, y+size/2, size/2);
		Arrays.sort(t);
		return t[2];
	}
}
