import java.util.*;

/*
 *  - 효율성 테스트
 * 
 *  테스트 1 〉	통과 (8.38ms, 54.4MB)
 *  테스트 2 〉	통과 (7.79ms, 56.2MB)
 *  테스트 3 〉	통과 (15.17ms, 61.2MB)
 *  테스트 4 〉	통과 (14.48ms, 61.1MB)
 *  테스트 5 〉	통과 (30.16ms, 63.9MB)
 *  테스트 6 〉	통과 (31.47ms, 66.8MB)
 *  테스트 7 〉	통과 (34.05ms, 67.7MB)
 *  테스트 8 〉	통과 (37.20ms, 84.9MB)
 *  테스트 9 〉	통과 (36.29ms, 70.6MB)
 *  테스트 10 〉통과 (50.31ms, 75.8MB)
 *  테스트 11 〉통과 (43.00ms, 79MB)
 *  테스트 12 〉통과 (37.03ms, 78.8MB)
 *  테스트 13 〉통과 (43.63ms, 85MB)
 *  테스트 14 〉통과 (53.65ms, 88MB)
 *  테스트 15 〉통과 (57.21ms, 79.8MB)
 * 
 *  - HashMap을 사용한 투포인터 연산
 */
class Solution {
    public int[] solution(String[] gems) {
        
        Map<String,Integer> map = new HashMap<>();
        int totalGem = 0;
        for(String gem : gems){
            if(!map.containsKey(gem)){
                map.put(gem, 0);
                totalGem++;
            }
        }
        
        int minIdx = 0;
        int minL = Integer.MAX_VALUE;
        
        int left = 0; int right = 0; int nGems=1; int end = gems.length;
        map.put(gems[left],1);
        
        while(right<end){
            if(nGems==totalGem){
                if(right-left + 1 < minL){
                    minIdx = left;
                    minL = right - left + 1;
                }
                
                if(map.get(gems[left])==1){
                    right++;
                    if(right<end){
                        if(map.get(gems[right])==0){
                            nGems++;
                        }
                        map.put(gems[right], map.get(gems[right])+1);
                    }
                } else {
                    map.put(gems[left],map.get(gems[left])-1);
                    left++;
                }
            }
            else{
                right++;
                if(right<end){
                    if(map.get(gems[right])==0){
                        nGems++;
                    }
                    map.put(gems[right], map.get(gems[right])+1);
                }
            }
        }
        
        right--;
        while(left<end && nGems==totalGem){
            if(right-left + 1 < minL){
                left = minIdx;
                minL = right - left + 1;
            }
            
            map.put(gems[left], map.get(gems[left])-1);
            if(map.get(gems[left])==0) break;
            
            left++;
        }
        
        return new int[] {minIdx+1, minIdx+minL};
    }
}