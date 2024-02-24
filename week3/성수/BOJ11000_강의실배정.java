// 668ms

import java.io.*;
import java.util.*;

public class BOJ11000_강의실배정 {

	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(input.readLine());
		int[][] arr = new int[N][2];
		StringTokenizer tokens;
		for(int i = 0; i < N; i++) {
			tokens = new StringTokenizer(input.readLine());
			int s = Integer.parseInt(tokens.nextToken());
			int e = Integer.parseInt(tokens.nextToken());
			arr[i][0] = s;
			arr[i][1] = e;
		}
		Arrays.sort(arr, new Comparator() {
			@Override
			public int compare(Object o1, Object o2) {
				int[] a = (int[]) o1;
				int[] b = (int[]) o2;
				return a[0] - b[0];
			}
			
		});
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		int count = 1;
		pq.add(arr[0][1]);
		for(int i = 1; i < N; i++) {
			if(pq.peek() <= arr[i][0]) {
				pq.poll();
				pq.offer(arr[i][1]);
			} else {
				pq.offer(arr[i][1]);
				count++;
			}
		}
		System.out.println(count);
	}

}
