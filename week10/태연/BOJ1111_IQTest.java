import java.io.*;
import java.util.*;

public class BOJ1111_IQTest {
	
	/*
	 *  - 76ms
	 * 
	 *  - a2-a1 = a(a1-a0) 임을 사용하여 일차방정식 도출
	 */

	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		
		// n=1일때의 예외처리
		if(n==1) {
			System.out.println("A");
			return;
		}
		
		int[] arr = new int[n];
		int a, b;
		
		st = new StringTokenizer(br.readLine());
		arr[0] = Integer.parseInt(st.nextToken());
		arr[1] = Integer.parseInt(st.nextToken());
		
		// n=2일때의 예외처리
		if(n==2 && arr[0]==arr[1]) {
			System.out.println(arr[1]);		
			return;
		}
		
		if(n==2) {
			System.out.println("A");
			return;
		}
		
		arr[2] = Integer.parseInt(st.nextToken());
		
		// a0, a1, a2로부터 coef 도출
		if(arr[0]==arr[1]) {
			a=0;
			b=arr[1];
		}
		else if((arr[2]-arr[1])%(arr[1]-arr[0])!=0) {
			System.out.println("B");
			return;
		}
		else {
			a = (arr[2]-arr[1])/(arr[1]-arr[0]);
			b = arr[1] - a*arr[0];
		}
		for(int i=3; i<n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		// n=3부터 검증
		for(int i=1; i<n; i++) {
			if(arr[i-1]*a+b != arr[i]) {
				System.out.println("B");
				return;
			}
		}
		
		System.out.println(arr[n-1]*a+b);
		
	}

}
