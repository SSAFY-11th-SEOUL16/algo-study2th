import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
* 시간: 356ms
*/

public class Main_159922_아우으_우아으이야 {
	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());

		int[][] point = new int[N][2];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			point[i][0] = Integer.parseInt(st.nextToken());
			point[i][1] = Integer.parseInt(st.nextToken());
		}
		
		int sum = 0;
		int x = point[0][0];
		int y = point[0][1];
		for(int i = 1; i < N; i++) {
			if(point[i][0] <= y) {
				if(y < point[i][1]) y = point[i][1];
			}
			else {
				sum += (y - x);
				x = point[i][0];
				y = point[i][1];
			}
		}
		
		sum += (y - x);
		System.out.println(sum);

	}
}
