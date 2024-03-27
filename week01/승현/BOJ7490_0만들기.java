import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ7490_0¸¸µé±â {
	static int N;
	static StringBuilder sb;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			N = Integer.parseInt(br.readLine());
			sb = new StringBuilder();
			dfs(1);
			System.out.println();
		}
	}

	public static void dfs(int depth) {
		if (depth == N) {
			sb.append(depth);
			if (sumZero(sb.toString())) {
				System.out.println(sb.toString());
			}
			sb.deleteCharAt(sb.length() - 1);
			return;
		}

		sb.append(depth);
		for (String s : new String[] { " ", "+", "-" }) {
			sb.append(s);
			dfs(depth + 1);
			sb.deleteCharAt(sb.length() - 1);
		}
		sb.deleteCharAt(sb.length() - 1);
	}

	public static boolean sumZero(String s) {
		s = s.replaceAll(" ", "");
		StringBuilder temp = new StringBuilder();
		boolean minus = false;
		int sum = 0;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '+') {
				if (minus) 
					sum -= Integer.parseInt(temp.toString());
				else
					sum += Integer.parseInt(temp.toString());
				minus = false;
				temp.setLength(0);
			} else if (s.charAt(i) == '-') {
				if (minus)
					sum -= Integer.parseInt(temp.toString());
				else
					sum += Integer.parseInt(temp.toString());
				minus = true;
				temp.setLength(0);
			}
			else {
				temp.append(s.charAt(i));
			}
		}
		if (minus)
			sum -= Integer.parseInt(temp.toString());
		else
			sum += Integer.parseInt(temp.toString());
		minus = true;
		
		if (sum == 0)
			return true;
		else
			return false;
	}
}
