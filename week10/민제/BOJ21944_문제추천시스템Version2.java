import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class BOJ21944_문제추천시스템Version2 {

	static HashMap<Integer, Problem> problems = new HashMap<>();

	static TreeSet<Problem> treeSet = new TreeSet<>((o1, o2) -> {
		if (o1.difficulty == o2.difficulty) {
			return o1.number - o2.number;
		}
		return o1.difficulty - o2.difficulty;
	});

	static TreeSet<Problem>[] groupTreeSets = new TreeSet[101];
	static TreeSet<Problem> levelTreeSet = new TreeSet<>((o1, o2) -> {
		if (o1.difficulty == o2.difficulty) {
			return o1.number - o2.number;
		}
		return o1.difficulty - o2.difficulty;
	});

	static class Problem {
		int number, difficulty, group;

		public Problem(int number, int difficulty, int group) {
			this.number = number;
			this.difficulty = difficulty;
			this.group = group;
		}

		public Problem() {
		}

		@Override
		public String toString() {
			return "Problem [number=" + number + ", difficulty=" + difficulty + ", group=" + group + "]";
		}
		
		

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		for (int i = 0; i < 101; i++) {
			groupTreeSets[i] = new TreeSet<>((o1, o2) -> {
				if (o1.difficulty == o2.difficulty) {
					return o1.number - o2.number;
				}
				return o1.difficulty - o2.difficulty;
			});
		}
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			add(st);
		}
		int m = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		Problem temp_problem = new Problem();
		temp_problem.number = 0;
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			String command = st.nextToken();

			if (command.equals("add")) {
				add(st);
			} else if (command.equals("solved")) {
				int number = Integer.parseInt(st.nextToken());
				Problem problem = problems.get(number);
				remove(number, problem);
			} else if (command.equals("recommend")) {
				int group = Integer.parseInt(st.nextToken());
				int x = Integer.parseInt(st.nextToken());
				TreeSet<Problem> groupTreeSet = groupTreeSets[group];
				if (x == 1) {
					sb.append(groupTreeSet.last().number).append('\n');
				} else {
					sb.append(groupTreeSet.first().number).append('\n');
				}
			} else if (command.equals("recommend2")) {
				int x = Integer.parseInt(st.nextToken());
				if (x == 1) {
					sb.append(treeSet.last().number).append('\n');
				} else {
					sb.append(treeSet.first().number).append('\n');
				}
			} else if (command.equals("recommend3")) {
				int x = Integer.parseInt(st.nextToken());
				int level = Integer.parseInt(st.nextToken());
				Problem select = null;
				temp_problem.difficulty = level;
				if (x == 1) {
					select = levelTreeSet.higher(temp_problem);
					if (select == null) sb.append(-1).append('\n');
					else sb.append(select.number).append('\n');
				} else {
					select = levelTreeSet.lower(temp_problem);
					if (select == null) sb.append(-1).append('\n');
					else sb.append(select.number).append('\n');
				}
			}
		}
		
		System.out.println(sb);
	}

	private static void remove(int number, Problem problem) {
		treeSet.remove(problem);
		levelTreeSet.remove(problem);
		groupTreeSets[problem.group].remove(problem);
		problems.remove(number);
	}

	private static void add(StringTokenizer st) {
		int number = Integer.parseInt(st.nextToken());
		int difficulty = Integer.parseInt(st.nextToken());
		int group = Integer.parseInt(st.nextToken());
		Problem problem = new Problem(number, difficulty, group);
		treeSet.add(problem);
		levelTreeSet.add(problem);
		groupTreeSets[group].add(problem);
		problems.put(number, problem);
	}

}
