import java.util.*;
import java.io.*;

public class BOJ15922_아우으우아으이야 {

	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(input.readLine());
		StringTokenizer tokens = new StringTokenizer(input.readLine());
		int answer = 0;
		int l = Integer.parseInt(tokens.nextToken());
		int r = Integer.parseInt(tokens.nextToken());
		
		for(int i = 1; i < n; i++) {
			tokens = new StringTokenizer(input.readLine());
			int x = Integer.parseInt(tokens.nextToken());
			int y = Integer.parseInt(tokens.nextToken());
			if(x <= r) {
				r = Math.max(r, y);
			} else {
				answer += r - l;
				l = x;
				r = y;
			}
		}
		System.out.println(answer + r - l);
	}
}
