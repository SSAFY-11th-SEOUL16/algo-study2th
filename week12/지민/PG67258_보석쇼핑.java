package algostudy.week12;

import java.io.IOException;
import java.util.*;

/**
 * 효율성 테스트
 * 테스트 1 〉	통과 (7.74ms, 54.2MB)
 * 테스트 2 〉	통과 (14.94ms, 57.9MB)
 * 테스트 3 〉	통과 (14.06ms, 61.2MB)
 * 테스트 4 〉	통과 (11.90ms, 60.9MB)
 * 테스트 5 〉	통과 (29.50ms, 61.4MB)
 * 테스트 6 〉	통과 (56.85ms, 66.8MB)
 * 테스트 7 〉	통과 (64.90ms, 67MB)
 * 테스트 8 〉	통과 (36.54ms, 66.8MB)
 * 테스트 9 〉	통과 (44.02ms, 68.6MB)
 * 테스트 10 〉	통과 (46.63ms, 74.7MB)
 * 테스트 11 〉	통과 (54.30ms, 80MB)
 * 테스트 12 〉	통과 (57.88ms, 79.8MB)
 * 테스트 13 〉	통과 (56.06ms, 79.6MB)
 * 테스트 14 〉	통과 (77.19ms, 82MB)
 * 테스트 15 〉	통과 (78.49ms, 82.9MB)
 */

class PG67258_보석쇼핑 {
	static int gemCount;
	static HashMap<String, Integer> map;

	public static int[] solution(String[] gems) {
		int[] answer = {-1, -1};
		Set<String> set = new HashSet<String>(Arrays.asList(gems));
		gemCount = set.size();
		map = new HashMap<>();

		int l = 0;
		int r = 0;
		while(l < gems.length && r < gems.length) {
			if(map.size() == gemCount) {
				if(answer[0] == -1 || answer[1] - answer[0] > (r - 1) - l) {
					answer[0] = l + 1;
					answer[1] = r;
				}
				if(map.get(gems[l]) == 1) map.remove(gems[l]);
				else map.put(gems[l], map.get(gems[l]) - 1);
				l++;
			}
			else {
				map.put(gems[r], map.getOrDefault(gems[r], 0) + 1);
				r++;
			}
		}

		while (map.size() == gemCount && l < gems.length) {
			if(answer[0] == -1 || answer[1] - answer[0] > (r - 1) - l) {
				answer[0] = l + 1;
				answer[1] = r;
			}
			if(map.get(gems[l]) == 1) map.remove(gems[l]);
			else map.put(gems[l], map.get(gems[l]) - 1);
			l++;
		}

		return answer;
	}

	public static void main(String[] args) throws IOException {
		System.out.println(Arrays.toString(solution(
			new String[] {"DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA"})));
		System.out.println(Arrays.toString(solution(
			new String[] {"AA", "AB", "AC", "AA", "AC"})));
		System.out.println(Arrays.toString(solution(
			new String[] {"XYZ", "XYZ", "XYZ"})));
		System.out.println(Arrays.toString(solution(
			new String[] {"ZZZ", "YYY", "NNNN", "YYY", "BBB"})));
	}
}
