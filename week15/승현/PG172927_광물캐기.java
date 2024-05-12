import java.util.*;

/* 시간 
 * 테스트 1 〉	통과 (0.04ms, 74.3MB)
테스트 2 〉	통과 (0.04ms, 74.1MB)
테스트 3 〉	통과 (0.03ms, 73.7MB)
테스트 4 〉	통과 (0.04ms, 75.5MB)
테스트 5 〉	통과 (0.05ms, 75.8MB)
테스트 6 〉	통과 (0.04ms, 74.8MB)
테스트 7 〉	통과 (0.04ms, 77.4MB)
테스트 8 〉	통과 (0.04ms, 72.8MB)
테스트 9 〉	통과 (0.04ms, 77.5MB)
테스트 10 〉	통과 (0.05ms, 76.5MB)
테스트 11 〉	통과 (0.04ms, 72.8MB)
테스트 12 〉	통과 (0.04ms, 73.8MB)
테스트 13 〉	통과 (0.04ms, 72MB)
테스트 14 〉	통과 (0.04ms, 76.1MB)
테스트 15 〉	통과 (0.06ms, 77.4MB)
테스트 16 〉	통과 (0.02ms, 77.4MB)
테스트 17 〉	통과 (0.04ms, 72.8MB)
테스트 18 〉	통과 (0.04ms, 83.3MB)
테스트 19 〉	통과 (0.03ms, 74.2MB)
테스트 20 〉	통과 (0.04ms, 75.2MB)
테스트 21 〉	통과 (0.04ms, 73.2MB)
테스트 22 〉	통과 (0.03ms, 77.8MB)
테스트 23 〉	통과 (0.05ms, 78MB)
테스트 24 〉	통과 (0.04ms, 74.3MB)
테스트 25 〉	통과 (0.03ms, 82.7MB)
테스트 26 〉	통과 (0.04ms, 78.6MB)
테스트 27 〉	통과 (0.06ms, 80.1MB)
테스트 28 〉	통과 (0.06ms, 81.4MB)
테스트 29 〉	통과 (0.03ms, 75.1MB)
테스트 30 〉	통과 (0.03ms, 77MB)
테스트 31 〉	통과 (0.04ms, 76.2MB)
테스트 32 〉	통과 (0.03ms, 73.8MB)
테스트 33 〉	통과 (0.03ms, 72.9MB)
테스트 34 〉	통과 (0.03ms, 75.8MB)
테스트 35 〉	통과 (0.04ms, 76.8MB)
 */

public class PG172927_광물캐기 {
    
    static int[][] info;
    static boolean[] picked;
    
    public int solution(int[] picks, String[] minerals) {
        int answer = 0;
        
        int maxPick = 0;
        for (int i = 0; i < 3; i++) {
            maxPick += picks[i] * 5;
        }
        if (maxPick > minerals.length) {
            maxPick = minerals.length;
        }
        
        info = new int[maxPick % 5 == 0 ? maxPick / 5 : maxPick / 5 + 1][3];
        picked = new boolean[maxPick % 5 == 0 ? maxPick / 5 : maxPick / 5 + 1];
        
        // 5개씩 끊어서 각 그룹들에 대한 피로도 구함
        for (int i = 0; i < maxPick; i++) {
            if (minerals[i].equals("diamond")) {
                info[i / 5][0] += 1;
                info[i / 5][1] += 5;
                info[i / 5][2] += 25;
            }
            else if (minerals[i].equals("iron")) {
                info[i / 5][0] += 1;
                info[i / 5][1] += 1;
                info[i / 5][2] += 5;
            }
            else if (minerals[i].equals("stone")) {
                info[i / 5][0] += 1;
                info[i / 5][1] += 1;
                info[i / 5][2] += 1;
            }
        }
        
        // 다이아 곡괭이부터 소모
        for (int i = 0; i < picks[0]; i++) {
            // 전체 돌면서 차이가 가장 큰 값 구함
            int maxDiff = -1;
            int maxDiffIdx = -1;
            for (int j = 0; j < info.length; j++) {
                // 캐진경우 패스
                if (picked[j])
                    continue;
                // 철곡괭이 없는 경우 돌곡괭이 피로도와의 차이
                if (picks[1] == 0) {
                    if (info[j][2] - info[j][0] > maxDiff) {
                        maxDiff = info[j][2] - info[j][0];
                        maxDiffIdx = j;
                    }
                }
                // 철곡괭이 있는 경우 철곡괭이 피로도와의 차이
                else {
                    if (info[j][1] - info[j][0] > maxDiff) {
                        maxDiff = info[j][1] - info[j][0];
                        maxDiffIdx = j;
                    }
                }
            }
            // 차이가 가장 큰 인덱스 캐짐 처리
            picked[maxDiffIdx] = true;
            answer += info[maxDiffIdx][0];
            // 다 캐졌으면 리턴
            if (isAllPicked()) {
                return answer;
            }
        }

        // 철곡괭이 소모
        for (int i = 0; i < picks[1]; i++) {
            int maxDiff = -1;
            int maxDiffIdx = -1;
            for (int j = 0; j < info.length; j++) {
                // 캐진경우 패스
                if (picked[j])
                    continue;
                if (info[j][2] - info[j][1] > maxDiff) {
                    maxDiff = info[j][2] - info[j][1];
                    maxDiffIdx = j;
                }
            }
            picked[maxDiffIdx] = true;
            answer += info[maxDiffIdx][1];
            if (isAllPicked()) {
                return answer;
            }
        }
        
        // 안캐진것 중에 피로도 낮은거 돌곡괭이 처리
        for (int i = 0; i < picks[2]; i++) {
            int minVal = Integer.MAX_VALUE;
            int minValIdx = -1;
            for (int j = 0; j < info.length; j++) {
                // 캐진경우 패스
                if (picked[j])
                    continue;
                if (info[j][2] < minVal) {
                    minVal = info[j][2];
                    minValIdx = j;
                }
            }
            picked[minValIdx] = true;
            answer += info[minValIdx][2];
            if (isAllPicked()) {
                return answer;
            }
        }
        
        return answer;
    }
    
    public static boolean isAllPicked() {
        for (int i = 0; i < picked.length; i++) {
            if (!picked[i]) {
                return false;
            }
        }
        return true;
    }
}