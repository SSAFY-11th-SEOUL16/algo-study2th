package daily;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ11000_강의실배정 {
	static int n;
	static Classroom[] c;
	static PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		c = new Classroom[n];
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			c[i] = new Classroom(start,end);
		}
		
		Arrays.sort(c);
				
		pq.offer(c[0].end);
		
		for(int i=1; i<n; i++) {
			if(c[i].start>=pq.peek()) {
				pq.poll();
			}		
			pq.offer(c[i].end);
		}
		System.out.println(pq.size());
	}
	static class Classroom implements Comparable<Classroom>{
		int start,end;

		public Classroom(int start, int end) {
			super();
			this.start = start;
			this.end = end;
		}

		@Override
		public int compareTo(Classroom o) {
			if(start==o.start)
				return end-o.end;
			return start-o.start;
		}
		
	}
}
