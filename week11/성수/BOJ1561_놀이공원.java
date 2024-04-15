import java.io.*;
import java.util.*;


public class BOJ1561_놀이공원 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(input.readLine());
		int N = Integer.parseInt(tokens.nextToken());
		int M = Integer.parseInt(tokens.nextToken());
		int[] arr = new int[M];
		tokens = new StringTokenizer(input.readLine());
		for(int i = 0; i < M; i++) {
			arr[i] = Integer.parseInt(tokens.nextToken());
		}
		if(N <= M) {
			System.out.println(N);
			return;
		}
		
		long low = 1l;
		long high = 60_000_000_000l;
		
		while(low <= high) {
			long mid = (low + high) / 2;
			long count = 0;
			for(int i = 0; i < M; i++) {
				count += (long)Math.ceil((double)mid / arr[i]);
			}
			if(count >= N) {
				high = mid - 1;
			} else {
				low = mid + 1;
			}
		}
		
		
		int count = 0;
		long before = low - 1;
		for(int i = 0; i < M; i++) {
			count += (long)Math.ceil((double)before / arr[i]);
		}
		for(int i = 0; i < M; i++) {
			count += (long)Math.ceil((double)low / arr[i]) - ((long)Math.ceil((double)before / arr[i]));
			if(count == N) {
				System.out.println(i + 1);
				break;
			}
		}
	}

}
