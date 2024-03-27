import java.io.*;
import java.util.*;

class Class implements Comparable<Class> {
	int sTime;
	int eTime;

	public Class() {
	}

	public Class(int sTime, int eTime) {
		this.sTime = sTime;
		this.eTime = eTime;
	}

	public int compareTo(Class c) {		// class는 start time순으로 정렬
		if (this.sTime == c.sTime)
			return (this.eTime - c.eTime);
		else
			return (this.sTime - c.sTime);
	}
}

public class BOJ11000_강의실배정 {

	static PriorityQueue<Class> pq = new PriorityQueue<Class>((c1, c2) -> {	// pq는 end time순으로 정렬
		if (c1.eTime == c2.eTime)
			return (c2.sTime - c1.sTime);
		else
			return (c1.eTime - c2.eTime);
	});

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int nClass = Integer.parseInt(br.readLine());
		Class[] c = new Class[nClass];

		for (int i = 0; i < nClass; i++) {
			st = new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			c[i] = new Class(n1, n2);
		}

		Arrays.sort(c);

		int maxClass = 1;
		for (int i = 0; i < nClass; i++) {
			if (pq.isEmpty())
				pq.add(c[i]);
			else {
				Class t = pq.peek();

				if (t.eTime <= c[i].sTime) { // 방빼
					pq.poll();
					pq.add(c[i]);

				} else {
					pq.add(c[i]);
					maxClass++;

				}
			}
		}
		System.out.println(maxClass);
	}
}