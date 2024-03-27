import java.io.*;
import java.util.*;

public class BOJ17829_222풀링 {
	
	static int arr[][][] = new int[11][][];
	
	static int cal(int iter, int i, int j) {
		int[] a = new int[4];
		a[0] = arr[iter][i*2][j*2];
		a[1] = arr[iter][i*2+1][j*2+1];
		a[2] = arr[iter][i*2][j*2+1];
		a[3] = arr[iter][i*2+1][j*2];
		
		Arrays.sort(a);
		
		return a[2];
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int n = Integer.parseInt(br.readLine());
		
		int temp = n;
		int size = 0;
		while(temp!=0) {
			arr[size++] = new int[temp][temp];			// 2**size -> 1까지 배열 생성
			temp/=2;
		}
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<n; j++) {
				arr[0][i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int iter=1;
		while(iter!=size) {								// 2**size -> 1까지 배열 축소
			n/=2;
			arr[iter] = new int[n][n];
			for(int i=0; i<n; i++) {
				for(int j=0; j<n; j++) {
					arr[iter][i][j] = cal(iter-1, i, j);
				}
			}
			iter++;
		}
		System.out.println(arr[iter-1][0][0]);

	}

}
