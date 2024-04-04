package ps;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.TreeSet;


/**
 * 시간: 660ms
 */

/**
 * recommend G x
 * 	- x == 1, 알고리즘 분류 G 중 난이도 가장 큰 문제 번호 출력(여러 개라면 문제 번호가 큰 것)
 * 	- x == -1, 알고리즘 분류 G 중 난이도 가장 낮은 번호 출력(여러 개라면 문제 번호가 작은 것)
 * recommend2 x
 * 	- x == 1, 난이도 가장 큰 문제 번호 출력(여러 개라면 문제 번호가 큰 것)
 * 	- x == -1, 난이도 가장 낮은 문제 번호 출력(여러 개라면 문제 번호가 작은 것)
 * recommend3 x L
 * 	- x == 1, 난이도 L보다 크거나 같은 문제 중 가장 쉬운 문제 번호 출력(여러 개라면 문제번호가 가장 작은 것, 없다면 -1)
 *	- x == -1, 난이도 L보다 작은 문제 중 가장 어려운 문제 번호 출력(여러 개라면 문제번호가 가장 큰 것, 없다면 -1)
 * add P L G
 * 	- 난이도 L이고,알고리즘 분류가 G인 문제 번호 P를 추가함
 * solved P
 * 	- 추천 문제 리스트에서 문제 번호 P 제거
 */
public class Main_21944_문제추천시스템_version2 {
	static class Problem implements Comparable<Problem>{
		int p, l, g;

		public Problem(int p, int l, int g) {
			this.p = p;
			this.l = l;
			this.g = g;
		}

		@Override
		public int compareTo(Problem o) {
			if(this.l == o.l) return this.p - o.p; 
			return this.l - o.l;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + g;
			result = prime * result + l;
			result = prime * result + p;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj) return true;
			if (obj == null) return false;
			if (getClass() != obj.getClass()) return false;
			Problem other = (Problem) obj;
			if (g != other.g) return false;
			if (l != other.l) return false;
			if (p != other.p) return false;
			return true;
		}

	}
	static int N;
	static TreeSet<Problem> tree;
	static HashMap<Integer, Problem> map;
	
	private static int recommend(Problem pro, int x, int g) {
		if(pro.g == g) return pro.p;
		return (x == 1) ? recommend(tree.lower(pro), x, g) : recommend(tree.higher(pro), x, g);
	}
	
	private static int command(String[] command) {
		if(command[0].equals("recommend")) {
			int g = Integer.parseInt(command[1]);
			int x = Integer.parseInt(command[2]);
			return (x == 1) ? recommend(tree.last(), x, g) : recommend(tree.first(), x, g);
		}
		else if(command[0].equals("recommend2")) {
			int x = Integer.parseInt(command[1]);
			return x == 1 ? tree.last().p : tree.first().p;
		}
		else if(command[0].equals("recommend3")){
			int x = Integer.parseInt(command[1]);
			int l = Integer.parseInt(command[2]);
			Problem pro = (x == 1) ? 
					tree.ceiling(new Problem(0, l, 0)) : tree.lower(new Problem(0, l, 0));
			return pro == null ? -1 : pro.p;
		}
		
		if(command[0].equals("add")) {
			int p = Integer.parseInt(command[1]);
			int l = Integer.parseInt(command[2]);
			int g = Integer.parseInt(command[3]);
			tree.add(new Problem(p, l, g));
			map.put(p, new Problem(p, l, g));
		}
		else if(command[0].equals("solved")) {
			int p = Integer.parseInt(command[1]);
			tree.remove(map.remove(p));
		}
		return -2;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		
		tree = new TreeSet<>();
		map = new HashMap<>();
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine()); 
			int p = Integer.parseInt(st.nextToken()); // 문제 번호
			int l = Integer.parseInt(st.nextToken()); // 난이도
			int g = Integer.parseInt(st.nextToken()); // 알고리즘 분류
			tree.add(new Problem(p, l, g));
			map.put(p, new Problem(p, l, g));
		}
		
		st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < M; i++) {
			int x = command(br.readLine().split(" "));
			if(x != -2) sb.append(x).append("\n");
		}
		System.out.println(sb);

	}
}
