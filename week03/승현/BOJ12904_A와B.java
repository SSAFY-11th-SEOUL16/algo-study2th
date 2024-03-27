import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ12904_A와B {			// java8, 92ms
	static String S, T;
	static int result = 0;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		S = br.readLine();
		T = br.readLine();
		sb.append(T);
		
		if (S.equals(T)) {
			System.out.println(1);
			return;
		}
		
		while (sb.length() > S.length()) {
			if (sb.charAt(sb.length()-1) == 'A') {
				sb.deleteCharAt(sb.length()-1);
			}
				
			else if (sb.charAt(sb.length()-1) == 'B') {
				sb.deleteCharAt(sb.length()-1);
				sb.reverse();
			}
			else {
				System.out.println(0);
				return;
			}
		}
		if (sb.toString().equals(S)) {
			System.out.println(1);
		}
		else
			System.out.println(0);
	}
}
