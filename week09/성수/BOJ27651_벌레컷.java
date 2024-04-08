// 764ms
import java.io.*;
import java.util.*;

public class Main {
	
	static InputStreamReader input = new InputStreamReader(System.in);
	
	static int nextInt() throws IOException{
		int val = 0;
		int tmp = 0;
		
		while(true) {
			tmp = input.read();
			if('0' <= tmp && tmp <= '9') {
				val = tmp - '0';
				break;
			}
		}
		
		try {
			while(true) {
				tmp = input.read();
				if('0' <= tmp && tmp <= '9') {
					val = val * 10 + tmp - '0';
				} else {
					break;
				}
			}
		} catch(IOException e) {}
		
		return val;
	}
	
	static int N;
	static long[] prefix;
	
	
	public static void main(String[] args) throws IOException {
		N = nextInt();
		prefix = new long[N + 1];
		for(int i = 1; i <= N; i++) {
			prefix[i] = nextInt() + prefix[i - 1];
		}
		long answer = 0;
		int head = 0, chest = 0, belly = 0;
		// 머리 고정
		for(head = 1; head < N - 1; head++) {
            if(prefix[head] * 3 >= prefix[N]) break;
			// 배의 최소값을 찾기 위해 머리보다 약간만 큰 지점을 끝에서부터 찾는다
			int right = N;
			int left = head + 2;
			while(left <= right) {
				int mid = (left + right) / 2;
				
				if(prefix[head] >= prefix[N] - prefix[mid - 1]) {
					right = mid - 1;
				} else {
					left = mid + 1;
				}
			}
			belly = right;
			
			// 가슴의 최소값을 구한다
			left = head + 1;
			right = belly - 1;
			while(left <= right) {
				int mid = (left + right) / 2;
				if(prefix[N] - prefix[mid] >= prefix[mid] - prefix[head]) {
					left = mid + 1;
				} else {
					right = mid - 1;
				}
			}
			chest = left;
			answer += belly - chest;
		}
		System.out.println(answer);
	}
}