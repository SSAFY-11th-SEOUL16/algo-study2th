// 1000ms
import java.io.*;
import java.util.*;

public class BOJ21944_문제추천시스템Version2 {
	
	static class Problem implements Comparable<Problem> {
		int P, L, G;
		

		public Problem() {}
		
		public Problem(int p, int l, int g) {
			super();
			P = p;
			L = l;
			G = g;
		}

		@Override
		public int compareTo(Problem o) {
			int diff = L - o.L;
			if(diff != 0) return diff;
			return P - o.P;
		}
		
	}
	
	static HashMap<Integer, Problem> history;
	static TreeSet<Problem>[] list;
	static Problem parameter;
	
	static void init() {
		history = new HashMap<>();
		list = new TreeSet[101];
		Arrays.setAll(list, idx -> new TreeSet<>());
		parameter = new Problem();
	}
	
	static void add(int P, int L, int G) {
		Problem prob = new Problem(P, L, G);
		list[G].add(prob);
		history.put(P, prob);
	}
	
	static void solved(int P) {
		Problem wellknown = history.get(P);
		history.remove(P);
		list[wellknown.G].remove(wellknown);
	}
	
	static int recommend(int G, int x) {
		if(x == 1) return list[G].last().P;
		else return list[G].first().P;
	}

	static int recommend2(int x) {
		Problem result = null;
		for(int i = 1; i <= 100; i++) {
			if(list[i].size() == 0) continue;
			if(x == 1) {
				Problem tmp = list[i].last();
				if(result == null) result = tmp;
				else if(tmp.L > result.L) result = tmp;
				else if(tmp.L == result.L && tmp.P > result.P) result = tmp;
			} else {
				Problem tmp = list[i].first();
				if(result == null) result = tmp;
				else if(tmp.L < result.L) result = tmp;
				else if(tmp.L == result.L && tmp.P < result.P) result = tmp;
			}
		}
		return result.P;
	}
	
	static int recommend3(int x, int L) {
		Problem result = null;
		if(x == 1) {
			parameter.L = L - 1;
			parameter.P = 100_001;
			for(int i = 1; i <= 100; i++) {
				Problem tmp = list[i].higher(parameter);
				if(tmp == null) continue;
				if(result == null) result = tmp;
				else if(tmp.L < result.L) result = tmp;
				else if(tmp.L == result.L && tmp.P < result.P) result = tmp;
			}
		} else {
			parameter.L = L;
			parameter.P = 0;
			for(int i = 1; i <= 100; i++) {
				Problem tmp = list[i].lower(parameter);
				if(tmp == null) continue;
				if(result == null) result = tmp;
				else if(tmp.L > result.L) result = tmp;
				else if(tmp.L == result.L && tmp.P > result.P) result = tmp;
			}
		}
		if(result == null) return -1;
		return result.P;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(input.readLine());
		StringTokenizer tokens;
		int P, L, G, x;
		init();
		for(int i = 0; i < N; i++) {
			tokens = new StringTokenizer(input.readLine());
			P = Integer.parseInt(tokens.nextToken());
			L = Integer.parseInt(tokens.nextToken());
			G = Integer.parseInt(tokens.nextToken());
			add(P, L, G);
		}
		int M = Integer.parseInt(input.readLine());
		for(int i = 0; i < M; i++) {
			tokens = new StringTokenizer(input.readLine());
			String action = tokens.nextToken();
			if(action.equals("add")) {
				P = Integer.parseInt(tokens.nextToken());
				L = Integer.parseInt(tokens.nextToken());
				G = Integer.parseInt(tokens.nextToken());
				add(P, L, G);
			} else if(action.equals("recommend")) {
				G = Integer.parseInt(tokens.nextToken());
				x = Integer.parseInt(tokens.nextToken());
				output.write(recommend(G, x)+"\n");
			} else if(action.equals("recommend2")) {
				x = Integer.parseInt(tokens.nextToken());
				output.write(recommend2(x)+"\n");
			} else if(action.equals("recommend3")) {
				x = Integer.parseInt(tokens.nextToken());
				L = Integer.parseInt(tokens.nextToken());
				output.write(recommend3(x, L)+"\n");
			} else {
				P = Integer.parseInt(tokens.nextToken());
				solved(P);
			}
		}
		output.close();
	}
}