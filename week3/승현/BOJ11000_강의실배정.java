import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ11000_강의실배정 {			// java8, 660ms
	static int N;

	static class classInfo implements Comparable<classInfo> {
		int start;
		int end;

		classInfo(int start, int end) {
			this.start = start;
			this.end = end;
		}

		@Override
		public int compareTo(classInfo o) {
			return this.start - o.start;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		ArrayList<classInfo> classes = new ArrayList<>();

		N = Integer.parseInt(br.readLine());

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			classes.add(new classInfo(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		Collections.sort(classes);

		for (classInfo ci : classes) {
			if (pq.isEmpty())
				pq.offer(ci.end);
			else {
				int e = pq.poll();
				if (e <= ci.start) {
					pq.offer(ci.end);
				} else {
					pq.offer(e);
					pq.offer(ci.end);
					}
			}
		}
		System.out.println(pq.size());
	}

}
