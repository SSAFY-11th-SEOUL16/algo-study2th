import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ7490_0만들기 {

	static int n;
	static StringBuilder sb = new StringBuilder();

	static int calculate(String s) {

		int result = 0;

		s = s.replaceAll(" ", "");

		int temp = 0;
		char operator = '+';

		for (int i = 0; i < s.length(); i++) {

			if (s.charAt(i) == '+' || s.charAt(i) == '-') {
				if (operator == '+') {
					result += temp;
				} else {
					result -= temp;
				}

				operator = s.charAt(i);
				temp = 0;
			} else {
				temp = temp * 10 + Integer.parseInt(String.valueOf(s.charAt(i)));
			}

		}

		if (operator == '+') {
			result += temp;
		} else {
			result -= temp;
		}

		return result;
	}

	static void make_0(int num, String s) {
		if (num == n + 1) {
			if (calculate(s) == 0) {
				sb.append(s).append('\n');
			}
			return;
		}

		// ' '일때
		make_0(num + 1, s + " " + num);

		// +일때
		make_0(num + 1, s + "+" + num);

		// -일때
		make_0(num + 1, s + "-" + num);

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());

		// 아스키코드 순서 + -> - -> ' '
		for (int i = 0; i < t; i++) {
			n = Integer.parseInt(br.readLine());

			make_0(2, "1");
			sb.append('\n');
		}

		System.out.println(sb.toString());

	}

}
