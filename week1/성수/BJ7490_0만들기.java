import java.io.*;
import java.util.*;

public class BJ7490_0만들기 {
	
	static StringBuilder output = new StringBuilder();
	static int N;
	
	public static int operate(int a, int b, String op) {
		if(op.equals("+")) return a+b;
		return a-b;
	}
	
	public static int getNumber(String str) {
		int number = 0;
		for(char ch: str.toCharArray()) {
			if(ch == ' ') continue;
			number = number * 10 + ch - '0';
		}
		return number;
	}
	
	public static void calculate(String equation) {
		StringTokenizer numbers = new StringTokenizer(equation, "+-");
		StringTokenizer operators = new StringTokenizer(equation, "123456789 ");
		int result = getNumber(numbers.nextToken());
		while(operators.hasMoreTokens()) {
			result = operate(result, getNumber(numbers.nextToken()), operators.nextToken());
		}
		if(result == 0) output.append(equation).append("\n");
	}
	
	public static void permutation(int depth, String equation) {
		if(depth > N) {
			calculate(equation);
			return;
		}
		permutation(depth + 1, equation + " " + depth);
		permutation(depth + 1, equation + "+" + depth);
		permutation(depth + 1, equation + "-" + depth);
	}
	
	public static void main(String[] args) throws IOException {
		InputStreamReader input = new InputStreamReader(System.in);
		int T = input.read() - '0';
		for(int t = 0; t < T; t++) {
			input.read();
			N = input.read() - '0';
			permutation(2, "1");
			output.append("\n");
		}
		System.out.print(output);
	}

}