import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class BOJ21944_문제추천시스템Version2 {		// 624ms
	static int N, M;
	static TreeSet<Problem> ts;
	static TreeSet<Problem>[] ts2;
	static HashMap<Integer, Problem> hm;
	
	static class Problem implements Comparable<Problem> {
		int pno;
		int level;
		int classification;
		
		Problem (int pno, int level, int classification) {
			this.pno = pno;
			this.level = level;
			this.classification = classification;
		}

		@Override
		public int compareTo(Problem o) {
			if (this.level == o.level) {
				return this.pno - o.pno;
			}
			else
				return this.level - o.level;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
				
		N = Integer.parseInt(br.readLine());
		ts = new TreeSet<>();
		ts2 = new TreeSet[101];
		for (int i = 1; i <= 100; i++) {
			ts2[i] = new TreeSet<>();
		}
		hm = new HashMap<>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int P = Integer.parseInt(st.nextToken());
			int L = Integer.parseInt(st.nextToken());
			int G = Integer.parseInt(st.nextToken());
			add(P, L, G);
		}
		
		M = Integer.parseInt(br.readLine());
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			String s = st.nextToken();
			if (s.equals("recommend")) {
				int G = Integer.parseInt(st.nextToken());
				int x = Integer.parseInt(st.nextToken());
				if (x == 1) {
					sb.append(ts2[G].last().pno).append("\n");
				}
				else {
					sb.append(ts2[G].first().pno).append("\n");
				}
			}
			else if (s.equals("recommend2")) {
				int x = Integer.parseInt(st.nextToken());
				if (x == 1) {
					sb.append(ts.last().pno).append("\n");
				}
				else {
					sb.append(ts.first().pno).append("\n");
				}
			}
			else if (s.equals("recommend3")) {
				int x = Integer.parseInt(st.nextToken());
				int L = Integer.parseInt(st.nextToken());
				Problem temp = new Problem(0, L, 0);
				if (x == 1) {
					if (ts.higher(temp) != null) {
						sb.append(ts.higher(temp).pno).append("\n");				
					}
					else {
						sb.append(-1).append("\n");
					}
				}
				else {
					if (ts.lower(temp) != null) {
						sb.append(ts.lower(temp).pno).append("\n");				
					}
					else {
						sb.append(-1).append("\n");
					}
				}
			}
			else if (s.equals("add")) {
				int P = Integer.parseInt(st.nextToken());
				int L = Integer.parseInt(st.nextToken());
				int G = Integer.parseInt(st.nextToken());
				add(P, L, G);
			}
			else if (s.equals("solved")) {
				int P = Integer.parseInt(st.nextToken());
				solved(P);
			}
		}
		System.out.println(sb);
	}
	public static void add(int P, int L, int G) {
		Problem problem = new Problem(P, L, G);
		ts.add(problem);
		ts2[G].add(problem);
		hm.put(P, problem);
	}

	public static void solved(int P) {
		Problem problem = hm.get(P);
		ts.remove(problem);
		ts2[problem.classification].remove(problem);
	}
}
