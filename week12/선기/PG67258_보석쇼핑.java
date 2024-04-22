import java.util.*;
/*
 * 투포인터
*/
class Solution {
    public int[] solution(String[] gems) {
        Set<String> gemSet = new HashSet<>(Arrays.asList(gems));
        int gemTypeCnt = gemSet.size();
        int curGemTypeCnt = 0;
        Map<String, Integer> curGemCnts = new HashMap<>();
        int left = 0;
        int right = -1;
        int ansLeft = 0;
        int ansRight = gems.length - 1;
        while (left < gems.length - gemTypeCnt) {
            while (right < gems.length - 1) {
                ++right;
                curGemCnts.compute(gems[right], (k, v) -> v == null ? 1 : v + 1);
                if (curGemCnts.get(gems[right]) == 1) {
                    ++curGemTypeCnt;
                    if (curGemTypeCnt == gemTypeCnt) {
                        break;
                    }
                }
            }
            if (curGemTypeCnt != gemTypeCnt) {
                break;
            }
            // System.out.println("outer: " + left + " " + right);
            while (left <= right) {
                // System.out.println("inner: " + left + " " + right);
                if (right - left < ansRight - ansLeft) {
                    ansRight = right;
                    ansLeft = left;
                }
                curGemCnts.compute(gems[left], (k, v) -> v - 1);
                if (curGemCnts.get(gems[left]) == 0) {
                    ++left;
                    --curGemTypeCnt;
                    break;
                }
                ++left;
            }
        }
        
        return new int[] {ansLeft + 1, ansRight + 1};
    }
}
/*
테스트 1 〉	통과 (1.32ms, 74.4MB)
테스트 2 〉	통과 (0.95ms, 78.5MB)
테스트 3 〉	통과 (1.63ms, 70.5MB)
테스트 4 〉	통과 (0.29ms, 77.9MB)
테스트 5 〉	통과 (2.03ms, 72.4MB)
테스트 6 〉	통과 (1.06ms, 72.2MB)
테스트 7 〉	통과 (0.84ms, 78.3MB)
테스트 8 〉	통과 (1.74ms, 74.1MB)
테스트 9 〉	통과 (2.79ms, 76.3MB)
테스트 10 〉	통과 (3.68ms, 80.4MB)
테스트 11 〉	통과 (3.30ms, 73.8MB)
테스트 12 〉	통과 (2.47ms, 81.8MB)
테스트 13 〉	통과 (2.64ms, 78.9MB)
테스트 14 〉	통과 (3.14ms, 77.3MB)
테스트 15 〉	통과 (7.30ms, 89.2MB)
효율성  테스트
테스트 1 〉	통과 (12.80ms, 54.5MB)
테스트 2 〉	통과 (15.42ms, 55.9MB)
테스트 3 〉	통과 (12.52ms, 58.5MB)
테스트 4 〉	통과 (14.73ms, 62.1MB)
테스트 5 〉	통과 (27.50ms, 62.4MB)
테스트 6 〉	통과 (26.75ms, 64.2MB)
테스트 7 〉	통과 (28.23ms, 69MB)
테스트 8 〉	통과 (30.71ms, 68.3MB)
테스트 9 〉	통과 (36.74ms, 71.6MB)
테스트 10 〉	통과 (47.84ms, 74.9MB)
테스트 11 〉	통과 (61.35ms, 78.8MB)
테스트 12 〉	통과 (48.55ms, 80MB)
테스트 13 〉	통과 (61.81ms, 79.8MB)
테스트 14 〉	통과 (76.98ms, 80.9MB)
테스트 15 〉	통과 (64.08ms, 81.6MB)
*/