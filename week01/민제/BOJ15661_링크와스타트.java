import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ15661_링크와스타트 {

	static int n;
	static int[][] graph;
	static int result = Integer.MAX_VALUE;

	static void divideTeam(int index, List<Integer> teamA, List<Integer> teamB) {

		if (index == n + 1) {
			if (teamA.isEmpty() || teamB.isEmpty()) {
				return;
			}

			int powerA = getPower(teamA);
			int powerB = getPower(teamB);

			result = Math.min(result, Math.abs(powerA - powerB));
			return;
		}

		// A팀에추가
		teamA.add(index);
		divideTeam(index + 1, teamA, teamB);
		teamA.remove(teamA.size() - 1);
		
		// B팀에 추가
		teamB.add(index);
		divideTeam(index + 1, teamA, teamB);
		teamB.remove(teamB.size() - 1);

	}

	private static int getPower(List<Integer> team) {

		int score = 0;

		for (int i = 0; i < team.size(); i++) {
			for (int j = 0; j < team.size(); j++) {
				score += graph[team.get(i) - 1][team.get(j) - 1];
			}
		}

		return score;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		graph = new int[n][n];
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		divideTeam(1, new ArrayList<Integer>(), new ArrayList<Integer>());

		System.out.println(result);
	}

}
