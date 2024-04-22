import java.util.HashMap;
import java.util.HashSet;

class Solution {
    static HashSet<String> set = new HashSet();

    public int[] solution(String[] gems) {
        for (String s : gems) set.add(s);
        return bs(gems);
    }

    // length 만큼의 구간에서 조건을 만족하는 start를 찾는 함수
    static public int isAble(int length, String[] gems) {
        HashMap<String, Integer> gemsCntMap = new HashMap(); // 현재 갖고있는 보석 수를 저장
        HashSet<String> emptySet = new HashSet(); // 나한테 없는 보석 Set
        for (String gem : set) {
            gemsCntMap.put(gem, 0); // 0개로 초기화
            emptySet.add(gem); // 아직 다 없다고 생각하고 초기화
        }

        for (int i = 0; i < length; i++) { // length만큼 초기 보석 정보 채우기
            String gem = gems[i];
            gemsCntMap.put(gem, gemsCntMap.get(gem) + 1); // 현재 갖고있는 보석 수
            emptySet.remove(gem); // 생겼으니까 나한테없는 보석Set에서 빼주기
        }

        if (emptySet.isEmpty()) return 0; // 시작하자마자 조건 만족했으니까 start인 0 반환

        for (int i = 1; i <= gems.length - length; i++) { //슬라이딩 윈도우
            String removeGem = gems[i - 1]; // 지울 보석
            String addGem = gems[i + length - 1]; // 얻을 보석

            gemsCntMap.put(addGem, gemsCntMap.get(addGem) + 1);
            emptySet.remove(addGem);

            gemsCntMap.put(removeGem, gemsCntMap.get(removeGem) - 1);
            if (gemsCntMap.get(removeGem) == 0) // 보유중인 보석에서 1개를 뺐을때 하나도 없으면
                emptySet.add(removeGem);

            if (emptySet.isEmpty()) return i; // 조건 만족했으니까 start인 i 반환
        }

        return -1; // 못찾으면 -1 반환
    }

    static int[] bs(String[] gems) {
        int start = set.size();
        int end = gems.length;
        while (start < end) {
            int mid = (start + end) / 2;
            if (isAble(mid, gems) != -1) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        // 현재 start 값이 가장 짧은 구간의 길이
        int[] answer = new int[2];
        int sPoint = isAble(start, gems); // 가장 짧은 구간의 시작점 구하기
        answer[0] = sPoint + 1;
        answer[1] = sPoint + start;

        return answer;
    }
}

/*
정확성  테스트
테스트 1 〉	통과 (0.11ms, 67.1MB)
테스트 2 〉	통과 (1.14ms, 72.1MB)
테스트 3 〉	통과 (1.03ms, 74.6MB)
테스트 4 〉	통과 (1.07ms, 75.9MB)
테스트 5 〉	통과 (0.61ms, 86.7MB)
테스트 6 〉	통과 (0.12ms, 69.8MB)
테스트 7 〉	통과 (0.16ms, 79.5MB)
테스트 8 〉	통과 (2.95ms, 75.2MB)
테스트 9 〉	통과 (3.57ms, 74.9MB)
테스트 10 〉	통과 (6.16ms, 83.3MB)
테스트 11 〉	통과 (13.92ms, 83.7MB)
테스트 12 〉	통과 (6.10ms, 67.4MB)
테스트 13 〉	통과 (2.47ms, 78.1MB)
테스트 14 〉	통과 (10.40ms, 86.5MB)
테스트 15 〉	통과 (15.22ms, 83.8MB)
효율성  테스트
테스트 1 〉	통과 (32.77ms, 54.6MB)
테스트 2 〉	통과 (53.71ms, 57.5MB)
테스트 3 〉	통과 (65.46ms, 60.4MB)
테스트 4 〉	통과 (104.60ms, 75.4MB)
테스트 5 〉	통과 (89.94ms, 65.4MB)
테스트 6 〉	통과 (104.79ms, 69.1MB)
테스트 7 〉	통과 (155.16ms, 71.2MB)
테스트 8 〉	통과 (123.73ms, 71.6MB)
테스트 9 〉	통과 (155.89ms, 80.9MB)
테스트 10 〉	통과 (148.05ms, 77.7MB)
테스트 11 〉	통과 (190.94ms, 86.6MB)
테스트 12 〉	통과 (276.35ms, 86.7MB)
테스트 13 〉	통과 (354.61ms, 92.3MB)
테스트 14 〉	통과 (272.79ms, 82.9MB)
테스트 15 〉	통과 (371.71ms, 92.9MB)

 */