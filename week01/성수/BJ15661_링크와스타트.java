import java.io.*;
import java.util.*;

public class BJ15661_링크와스타트 {

	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(input.readLine());
		int[][] powers = new int[N][N];
		StringTokenizer tokenizer;
		for(int i = 0; i < N; i++) {
			tokenizer = new StringTokenizer(input.readLine());
			for(int j = 0; j < N; j++) {
				powers[i][j] = Integer.parseInt(tokenizer.nextToken());
			}
		}
		int[] sum = {0, 0};
		int answer = 100 * 20;
		int team;
		for(int visited = 1; visited < (1<<N) - 1; visited++) {
			sum[0] = 0;
			sum[1] = 0;
			for(int i = 0; i < N - 1; i++) {
				team = (visited & (1<<i)) == 0 ? 0:1;
				for(int j = i + 1; j < N; j++) {
					int tmp = (visited & (1<<j)) == 0 ? 0:1;
					if(team == tmp) {
						sum[team] += powers[i][j];
						sum[team] += powers[j][i];
					}
				}
			}
			answer = Math.min(answer, Math.abs(sum[0] - sum[1]));
		}
		System.out.println(answer);
	}

}
