import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ15922_아우으우아으이야 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int result = 0;
		StringTokenizer st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		/*
		안겹치는 부분이 생길때만 result에 추가
		겹칠때는 마지막 부분이 큰쪽으로 업데이트
		 */
		for(int i=0; i<n-1; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			//겹치는 부분 x
			if (x > end) {
				result += (end - start);
				start = x;
				end = y;
			}
			//겹치는 부분 o
			else {
				if (end < y) end = y;
			}
		}
		result += (end - start);
		System.out.println(result);
	}

}
