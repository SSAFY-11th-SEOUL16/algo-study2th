package daily;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ12904_A와B {
	static int result=0;
	static String start,target;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		target = br.readLine(); //시작 문자열을 거꾸로 타겟
		start = br.readLine();

		convert(start,0);
		System.out.println(result);
	}
	static void convert(String cur,int flipped) {
		int len = cur.length();
		if(len==target.length()) {
			if(flipped==0) {
				if(cur.equals(target))
					result=1;
			}
			else {
				String flip="";
				for(int i=cur.length()-1; i>=0; i--)
					flip+=cur.charAt(i);
				if(flip.equals(target)) 
					result=1;
			}
			return;
		}
		
		if(flipped==0) {
			if(cur.charAt(len-1)=='A') {
				convert(cur.substring(0, len-1),0);
			}
			else {
				convert(cur.substring(0, len-1),1);
			}
		}
		else {
			if(cur.charAt(0)=='A') {
				convert(cur.substring(1, len),1);
			}
			else {
				convert(cur.substring(1, len),0);
			}
		}
	}
}
