// 136ms

import java.io.*;
import java.util.*;

public class BOJ12904_A와B {

	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder output = new StringBuilder();
		String dest = input.readLine();
		output.append(input.readLine());
		while(output.length() > dest.length()) {
			if(output.charAt(output.length() - 1) == 'A') {
				output.setLength(output.length() - 1);
			} else {
				output.setLength(output.length() - 1);
				output.reverse();
			}
		}
		if(output.toString().equals(dest)) {
			System.out.println(1);
		} else {
			System.out.println(0);
		}

	}

}
