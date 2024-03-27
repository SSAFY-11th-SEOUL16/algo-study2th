import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class SW5648_원자소멸시뮬레이션 { // 291ms
	static int N, K;
	static int result;	
	static int[][] atomPos;		// 각 원자의 위치
	static int[] atomDirs;		// 각 원자의 방향
	static boolean[] boom;		// visited와 같은 역할, 이미 다른 원자와 충돌하여 소멸되었으면  true
	static int[] atomEnergy;	// 각 원자의 에너지 값
	static HashMap<String, HashSet<Integer>> map;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			result = 0;
			N = Integer.parseInt(br.readLine());
			atomPos = new int[N][2];
			atomDirs = new int[N];
			atomEnergy = new int[N];
			boom = new boolean[N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				atomPos[i][0] = Integer.parseInt(st.nextToken());
				atomPos[i][1] = Integer.parseInt(st.nextToken());
				atomDirs[i] = Integer.parseInt(st.nextToken());
				atomEnergy[i] = Integer.parseInt(st.nextToken());
			}
			getResult();
			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}
		System.out.println(sb.toString());
	}

	public static void getResult() {
		map = new HashMap<>();
		for (int i = 0; i < N; i++) {		// 한 원자를 기준으로 잡고
			for (int j = 0; j < N; j++) {	// 다른 원자들을 탐색하면서 충돌하면 map에 넣음
				if (i == j)					// 같은 원자끼리는 안봄
					continue;
				if (atomPos[i][0] == atomPos[j][0]) {	// 같은 열인 경우
					if ((atomPos[i][1] > atomPos[j][1] && atomDirs[i] == 1 && atomDirs[j] == 0)
							|| (atomPos[i][1] < atomPos[j][1] && atomDirs[i] == 0 && atomDirs[j] == 1)) {	// 서로 마주보는 방향으로 가는 경우
						double dist = Math.abs(atomPos[i][1] - atomPos[j][1]) / 2.0;						// 거리
						String key = (atomPos[i][0] / 1.0) + " " + ((atomPos[i][1] + atomPos[j][1]) / 2.0) + " " + dist;	// x좌표, y좌표, 거리를 String으로 key값 
						if (!map.containsKey(key)) {	// map에 없으면
							map.put(key, new HashSet<>());	// map을 만들고 추가
							map.get(key).add(i);
						}
						map.get(key).add(j);	// 반대쪽도 추가
					}
				}
				if (atomPos[i][1] == atomPos[j][1]) {	// 같은 행인 경우
					if ((atomPos[i][0] > atomPos[j][0] && atomDirs[i] == 2 && atomDirs[j] == 3)
							|| (atomPos[i][0] < atomPos[j][0] && atomDirs[i] == 3 && atomDirs[j] == 2)) {
						double dist = Math.abs(atomPos[i][0] - atomPos[j][0]) / 2.0;
						String key = ((atomPos[i][0] + atomPos[j][0]) / 2.0) + " " + (atomPos[i][1] / 1.0) + " " + dist;
						if (!map.containsKey(key)) {
							map.put(key, new HashSet<>());
							map.get(key).add(i);
						}
						map.get(key).add(j);
					}
				}
				if (Math.abs(atomPos[i][0] - atomPos[j][0]) == Math.abs(atomPos[i][1] - atomPos[j][1])) {	// ㄱ자나 ㄴ자로 만나는 경우
					if (atomPos[i][0] < atomPos[j][0] && atomPos[i][1] < atomPos[j][1]
							&& ((atomDirs[i] == 3 && atomDirs[j] == 1) || (atomDirs[i] == 0 && atomDirs[j] == 2))) {
						double dist = Math.abs(atomPos[i][0] - atomPos[j][0]);
						String key = null;
						if (atomDirs[i] == 1 || atomDirs[i] == 0) {
							key = (atomPos[i][0] / 1.0) + " " + (atomPos[j][1] / 1.0) + " " + dist;
						} else {
							key = (atomPos[j][0] / 1.0) + " " + (atomPos[i][1] / 1.0) + " " + dist;
						}
						if (!map.containsKey(key)) {
							map.put(key, new HashSet<>());
							map.get(key).add(i);
						}
						map.get(key).add(j);
					}
					if (atomPos[i][0] > atomPos[j][0] && atomPos[i][1] < atomPos[j][1]
							&& ((atomDirs[i] == 0 && atomDirs[j] == 3) || (atomDirs[i] == 2 && atomDirs[j] == 1))) {
						double dist = Math.abs(atomPos[i][0] - atomPos[j][0]);
						String key = null;
						if (atomDirs[i] == 1 || atomDirs[i] == 0) {
							key = (atomPos[i][0] / 1.0) + " " + (atomPos[j][1] / 1.0) + " " + dist;
						} else {
							key = (atomPos[j][0] / 1.0) + " " + (atomPos[i][1] / 1.0) + " " + dist;
						}
						if (!map.containsKey(key)) {
							map.put(key, new HashSet<>());
							map.get(key).add(i);
						}
						map.get(key).add(j);
					}
					if (atomPos[i][0] < atomPos[j][0] && atomPos[i][1] > atomPos[j][1]
							&& ((atomDirs[i] == 1 && atomDirs[j] == 2) || (atomDirs[i] == 3 && atomDirs[j] == 0))) {
						double dist = Math.abs(atomPos[i][0] - atomPos[j][0]);
						String key = null;
						if (atomDirs[i] == 1 || atomDirs[i] == 0) {
							key = (atomPos[i][0] / 1.0) + " " + (atomPos[j][1] / 1.0) + " " + dist;
						} else {
							key = (atomPos[j][0] / 1.0) + " " + (atomPos[i][1] / 1.0) + " " + dist;
						}
						if (!map.containsKey(key)) {
							map.put(key, new HashSet<>());
							map.get(key).add(i);
						}
						map.get(key).add(j);
					}
					if (atomPos[i][0] > atomPos[j][0] && atomPos[i][1] > atomPos[j][1]
							&& ((atomDirs[i] == 1 && atomDirs[j] == 3) || (atomDirs[i] == 2 && atomDirs[j] == 0))) {
						double dist = Math.abs(atomPos[i][0] - atomPos[j][0]);
						String key = null;
						if (atomDirs[i] == 1 || atomDirs[i] == 0) {
							key = (atomPos[i][0] / 1.0) + " " + (atomPos[j][1] / 1.0) + " " + dist;
						} else {
							key = (atomPos[j][0] / 1.0) + " " + (atomPos[i][1] / 1.0) + " " + dist;
						}
						if (!map.containsKey(key)) {
							map.put(key, new HashSet<>());
							map.get(key).add(i);
						}
						map.get(key).add(j);
					}
				}
			}
		}

		List<Map.Entry<String, HashSet<Integer>>> list = new LinkedList<>(map.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<String, HashSet<Integer>>>() {			// map을 dist값을 기준으로 오름차순 정렬
			@Override
			public int compare(Map.Entry<String, HashSet<Integer>> o1, Map.Entry<String, HashSet<Integer>> o2) {
				String[] str1 = o1.getKey().split(" ");
				String[] str2 = o2.getKey().split(" ");
				Double d1 = Double.parseDouble(str1[2]);
				Double d2 = Double.parseDouble(str2[2]);
				if (d1 > d2)
					return 1;
				else if (d1 == d2)
					return 0;
				else
					return -1;
			}
		});
		Map<String, HashSet<Integer>> sortedMap = new LinkedHashMap<>();
		for (Iterator<Map.Entry<String, HashSet<Integer>>> iter = list.iterator(); iter.hasNext();) {	
			Map.Entry<String, HashSet<Integer>> entry = iter.next();
			sortedMap.put(entry.getKey(), entry.getValue());
		}
//		for (String key : sortedMap.keySet()) {
//			System.out.println("{" + key + "," + sortedMap.get(key) + "}");
//		}

		for (HashSet<Integer> value : sortedMap.values()) {	// dist값이 작은것부터 (빨리 만나는 지점부터) 방문하면서
			int cnt = 0;
			int sum = 0;
			for (int a : value)	// 이전 시간에서 터지지 않은 원자를 세고
				if (!boom[a])
					cnt++;
			if (cnt >= 2) {		// 2개 이상인 경우만 합을 구해서 result에 더해줌
				for (int a : value) {
					if (!boom[a]) {
						boom[a] = true;
						sum += atomEnergy[a];
					}
				}
			}
			result += sum;
		}
	}
}
