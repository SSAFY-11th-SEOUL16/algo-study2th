import java.util.*;

/*
 * 윈도우 길이를 이분탐색 하고
 * 해당 윈도우 길이를 통해 
 * 슬라이딩 윈도우를 수행하면서
 * 모든 보석을 포함하는지 확인하고
 * 포함한다면 길이를 줄여보고 
 * 포함하지 않는다면 길이를 늘려보는 방식으로 풀이해봤습니다.
 * 시간 (효율성 테스트 기준)
 * 테스트 1 〉	통과 (21.72ms, 53.9MB)
 * 테스트 2 〉	통과 (33.56ms, 59.6MB)
 * 테스트 3 〉	통과 (37.06ms, 57.9MB)
 * 테스트 4 〉	통과 (60.96ms, 60.7MB)
 * 테스트 5 〉	통과 (57.38ms, 62.2MB)
 * 테스트 6 〉	통과 (78.78ms, 66MB)
 * 테스트 7 〉	통과 (118.82ms, 67.9MB)
 * 테스트 8 〉	통과 (118.34ms, 70MB)
 * 테스트 9 〉	통과 (96.70ms, 71.5MB)
 * 테스트 10 〉	통과 (115.07ms, 76.7MB)
 * 테스트 11 〉	통과 (138.14ms, 79.7MB)
 * 테스트 12 〉	통과 (194.92ms, 82.8MB)
 * 테스트 13 〉	통과 (169.39ms, 80.1MB)
 * 테스트 14 〉	통과 (118.96ms, 79.4MB)
 * 테스트 15 〉	통과 (189.46ms, 82.3MB)
 */

public class PG67258_보석쇼핑 {
    
    static HashMap<String, Integer> map;
    static int[] gemCnt;
    static int startIdx;
    
    public int[] solution(String[] gems) {
        
        map = new HashMap<>();
        int gemIdx = 0;
        for (int i = 0; i < gems.length; i++) {
            if (!map.containsKey(gems[i])) {
                map.put(gems[i], gemIdx++);
            }
        }
        
        gemCnt = new int[map.size()];
        
        int left = 1;
        int right = gems.length+1;
        int curStart = -1;
        
        while (left < right) {
            int windowSize = (left + right) / 2;
            boolean isPossible = find(windowSize, gems);
            if (!isPossible) {
                left = windowSize+1;
            }
            else {
                right = windowSize;
                curStart = startIdx;
            }
        }

        int[] answer = new int[] {curStart+1, curStart+right};
        
        return answer;
    }
    
    public static boolean find(int windowSize, String[] gems) {
        int gemKind = 0;
        gemCnt = new int[map.size()];
        for (int i = 0; i < windowSize; i++) {
            if (gemCnt[map.get(gems[i])] == 0)
                gemKind++;
            gemCnt[map.get(gems[i])]++;
        }
        if (gemKind == map.size()) {
            startIdx = 0;
            return true;
        }
        for (int i = 1; i < gems.length-windowSize+1; i++) {
            if (gemCnt[map.get(gems[i-1])] == 1) {
                gemKind--;
            }
            gemCnt[map.get(gems[i-1])]--;
            if (gemCnt[map.get(gems[i+windowSize-1])] == 0) {
                gemKind++;
            }
            gemCnt[map.get(gems[i+windowSize-1])]++;
            
            if (gemKind == map.size()) {
                startIdx = i;
                return true;
            }
        }
        return false;
    }
}