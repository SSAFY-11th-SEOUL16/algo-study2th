import java.io.*;
import java.util.*;

public class BOJ12904_A와B {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		char[] s1 = br.readLine().toCharArray();
		char[] s2 = br.readLine().toCharArray();
		
		int right = s2.length-1;
		int left = 0;
		
		boolean isReverse = false;			// isReverse면 left가 맨 뒤, 아니면 right가 맨 뒤
		
		for(int i=0; i<s2.length-s1.length; i++) {
			if(isReverse) {
				switch(s2[left]) {
				case 'A':					// 맨 뒤가 A면 제거
					left++;
					break;
				case'B':					// B면 제거하고 뒤집음
					left++;
					isReverse = (isReverse) ? false:true;
					break;
				}
			}
			else {
				switch(s2[right]) {
				case 'A':
					right--;
					break;
				case'B':
					right--;
					isReverse = (isReverse) ? false:true;
					break;
				}
			}
		}
		
		// 남은 길이의 string이 같은지 확인
		
		if(isReverse) {
			for(int i=0; i<s1.length; i++) {
				if(s2[right--]!=s1[i]) {System.out.println(0); return;}
			}
		}
		else {
			for(int i=0; i<s1.length; i++) {
				if(s2[left++]!=s1[i]) {System.out.println(0); return;}
			}
		}
		
		System.out.println(1);
	}

}
